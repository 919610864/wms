package com.mglj.base.audit;


import com.mglj.base.domain.AuditLog;


import com.mglj.base.service.api.AuditLogService;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

@Component
public class AuditMethodInterceptor implements MethodInterceptor, ApplicationContextAware {

	private static final Logger logger = LoggerFactory.getLogger(AuditMethodInterceptor.class);
	
	private ApplicationContext context;
	
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		final Object targetObj = invocation.getThis();
		final Class<?> clasz = targetObj.getClass();
		final Method method = invocation.getMethod();
		if(logger.isDebugEnabled()) {
			logger.debug("audit class: " + clasz + ", method: " + method);
		}
		//判断方法是否开启审计功能
		final boolean present = method.isAnnotationPresent(Auditable.class);
		if(!present) {
			return invocation.proceed();
		}
	
		AuditContext.clear();
		
		long start = System.currentTimeMillis();
		Object bizResult = invocation.proceed();
		long cost = System.currentTimeMillis() - start;
		
		try {
			final Auditable methodAuditable = method.getAnnotation(Auditable.class);
			String actionCode = methodAuditable.action();
			Resource resource = context.getBean(AuditManager.class).findResource(clasz);
			if(resource != null) {
				Action action = resource.findAction(actionCode);
				AuditLog auditLog = new AuditLog();
				auditLog.setResourceCode(resource.getCode());
				auditLog.setResourceName(resource.getName());
				auditLog.setGroup(resource.getGroup());
				auditLog.setActionCode(action.getCode());
				auditLog.setActionName(action.getName());
				auditLog.setBillId(AuditContext.getBillId());
				auditLog.setBillCode(AuditContext.getBillCode());
				auditLog.setDescription(AuditContext.getDescription());
			    auditLog.setWarehouseId(100000020L);
                auditLog.setWarehouseName("自营仓");
                auditLog.setUserId(9527L);
                auditLog.setUserName("Thirtyfat");
//				if(auditLog.getWarehouseId() == null) {
//					UserSession userSession = AuditContext.getCurrentUserSession();
//					if(userSession != null) {
//						auditLog.setWarehouseId(userSession.getWarehouseId());
//						auditLog.setWarehouseName(userSession.getWarehouseName());
//						auditLog.setUserId(userSession.getUserId());
//						auditLog.setUserName(userSession.getUserName());
//					}
//				}
				auditLog.setCreateTime(new Date());
				auditLog.setCost((int)cost);
				context.getBean(AuditLogService.class).saveAuditLog(auditLog);
			}
		} catch(Exception ex) {
			logger.error("记录审计日志时发生异常", ex);
		} finally {
			AuditContext.clear();
		}
	
		return bizResult;
	}

}
