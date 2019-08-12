package com.mglj.base.aop;


import com.mglj.base.audit.*;
import com.mglj.base.domain.AuditLog;
import com.mglj.base.service.api.AuditLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by zsp on 2018/12/25.
 */
@Aspect
@Component
public class AuditAspect implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(AuditAspect.class);

    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Pointcut("@annotation(com.mglj.base.audit.Auditable)")
    public void execute() {

    }

    @Around("execute()")
    public Object invoke(ProceedingJoinPoint pjp) throws Throwable {
        Object bean = pjp.getTarget();
        Class<?> clazz = bean.getClass();
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        if(logger.isDebugEnabled()) {
            logger.debug("audit class: " + clazz + ", method: " + method);
        }
        //判断方法是否开启审计功能
        final boolean present = method.isAnnotationPresent(Auditable.class);
        if(!present) {
            return pjp.proceed();
        }

        AuditContext.clear();

        long start = System.currentTimeMillis();
        Object bizResult = pjp.proceed();
        long cost = System.currentTimeMillis() - start;
        logger.info("base cost - start time millis {} ", cost - start);
        try {
            final Auditable methodAuditable = method.getAnnotation(Auditable.class);
            String actionCode = methodAuditable.action();
            Resource resource = applicationContext.getBean(AuditManager.class).findResource(clazz);
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
//                auditLog.setWarehouseId(UserContext.getWarehouseId());
//                auditLog.setWarehouseName(UserContext.getWarehouseName());
//                auditLog.setUserId(UserContext.getUserId());
//                auditLog.setUserName(UserContext.getUserName());
//                if(auditLog.getWarehouseId() == null) {
//                    UserSession userSession = AuditContext.getCurrentUserSession();
//                    if(userSession != null) {
//                        auditLog.setWarehouseId(userSession.getWarehouseId());
//                        auditLog.setWarehouseName(userSession.getWarehouseName());
//                        auditLog.setUserId(userSession.getUserId());
//                        auditLog.setUserName(userSession.getUserName());
//                    }
//                }
                auditLog.setCreateTime(new Date());
                auditLog.setCost((int)cost);
                applicationContext.getBean(AuditLogService.class).saveAuditLog(auditLog);
            }
        } catch(Exception ex) {
            logger.error("记录审计日志时发生异常", ex);
        } finally {
            AuditContext.clear();
        }

        return bizResult;
    }
}
