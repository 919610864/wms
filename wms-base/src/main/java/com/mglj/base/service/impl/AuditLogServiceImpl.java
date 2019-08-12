package com.mglj.base.service.impl;


import com.mglj.base.domain.AuditLog;
import com.mglj.base.domain.AuditLogQuery;
import com.mglj.base.manager.api.AuditLogManager;
import com.mglj.base.service.api.AuditLogService;
//import com.yhdx.tool.id.service.api.GidService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AuditLogServiceImpl implements AuditLogService {
	
	@Autowired
	private AuditLogManager auditLogManager;


	private static final Logger logger = LoggerFactory.getLogger(AuditLogServiceImpl.class);

	@Override
	public void saveAuditLog(AuditLog auditLog) {
		auditLog.setId(1000010L);
		if(auditLog.getWarehouseId() == null) {
			auditLog.setWarehouseId(0L);
		}
		if(auditLog.getBillId() == null) {
			auditLog.setBillId(0L);
		}
		if(auditLog.getBillCode() == null) {
			auditLog.setBillCode("");
		}
		auditLog.setCreateTime(new Date());
		logger.info("saveAuditLog {}",auditLog);
		auditLogManager.saveAuditLog(auditLog);
	}

	@Override
	public List<AuditLog> listAuditLog(AuditLogQuery query) {
		return auditLogManager.listAuditLog(query);
	}

	@Override
	public long countAuditLog(AuditLogQuery query) {
		return auditLogManager.countAuditLog(query);
	}

}
