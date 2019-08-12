package com.mglj.base.service.api;



import com.mglj.base.domain.AuditLog;
import com.mglj.base.domain.AuditLogQuery;

import java.util.List;

/**
 * 的Service接口
 * 
 * @author zsp
 * @date 2017-10-26
 */
public interface AuditLogService {
	
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
