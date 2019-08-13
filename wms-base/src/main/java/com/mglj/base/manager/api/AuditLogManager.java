package com.mglj.base.manager.api;



import com.mglj.base.domain.AuditLog;
import com.mglj.base.domain.AuditLogQuery;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 
 * @author zsp
 *
 */
public interface AuditLogManager {
	
	/**
	 * 保存
	 */
	void saveAuditLog(AuditLog auditLog);
	
	/**
	 * 查找多个
	 *
	 * @param query 查找条件
	 * @return
	 */
	List<AuditLog> listAuditLog(AuditLogQuery query);
	
	/**
	 * 统计个数
	 *
	 * @param
	 * @return
	 */
	long countAuditLog(AuditLogQuery query);

}
