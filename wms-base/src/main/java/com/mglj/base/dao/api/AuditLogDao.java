package com.mglj.base.dao.api;



import com.mglj.base.domain.AuditLog;
import com.mglj.base.domain.AuditLogQuery;

import java.util.Collection;
import java.util.List;

/**
 * 的Dao
 * 
 * @author zsp
 * @date 2017-10-26
 */
public interface AuditLogDao {
	
	/**
	 * 保存
	 *
	 * @param auditLog
	 */
	void saveAuditLog(AuditLog auditLog);
	
	/**
	 * 保存
	 *
	 * @param col
	 */
	void saveAllAuditLog(Collection<AuditLog> col);
	
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
