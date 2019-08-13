package com.mglj.base.manager.impl;

import com.mglj.base.dao.api.AuditLogDao;
import com.mglj.base.domain.AuditLog;
import com.mglj.base.domain.AuditLogQuery;
import com.mglj.base.manager.api.AuditLogManager;
//import com.yhdx.baseframework.tool.ds.ShardingKeyHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class AuditLogManagerImpl implements AuditLogManager, InitializingBean, DisposableBean {
	
	private static final Logger logger = LoggerFactory.getLogger(AuditLogManagerImpl.class);
	
	private final BlockingQueue<AuditLog> queue = new LinkedBlockingQueue<AuditLog>(1024);
	private final List<AuditLog> tempAuditLog = new ArrayList<AuditLog>();
	private Thread saveThread;
	//秒
	private final static int POLL_TIME_OUT = 5;
	//毫秒
	private final static int OFFER_TIME_OUT = 100;
	private final static int MAX_LOOP_SIZE = 20;
	//10秒
	private final static int MAX_LOOP_MILLIS = 10000;
	
	@Autowired
	private AuditLogDao auditLogDao;



	@Override
	public void afterPropertiesSet() throws Exception {
		saveThread = new Thread(new Consumer());
		saveThread.setDaemon(true);
		saveThread.setName("审计日志批量保存任务");
		saveThread.start();
	}


	@Override
	public void destroy() throws Exception {
		if(saveThread != null) {
			saveThread.interrupt();
		}
		save();
	}


	@Override
	public void saveAuditLog(AuditLog auditLog) {
		logger.info("blocking queue size {}" , queue);
		try {
			// offer: 将指定元素插入此队列中（如果立即可行且不会违反容量限制），成功时返回 true，
			// 如果当前没有可用的空间，则返回 false，不会抛异常：
			boolean offer = queue.offer(auditLog, OFFER_TIME_OUT, TimeUnit.MILLISECONDS);
			logger.info("blocking queue offer result {}" , offer);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<AuditLog> listAuditLog(AuditLogQuery query) {
		return auditLogDao.listAuditLog(query);
	}

	@Override
	public long countAuditLog(AuditLogQuery query) {
		return auditLogDao.countAuditLog(query);
	}


	
	class Consumer implements Runnable {
		@Override
		public void run() {
			try {
				long time = System.currentTimeMillis();
				for(;;) {
					AuditLog auditLog = queue.poll(POLL_TIME_OUT, TimeUnit.SECONDS);
					if(auditLog != null) {
						tempAuditLog.add(auditLog);
					}
					if(tempAuditLog.size() >= MAX_LOOP_SIZE || (System.currentTimeMillis() - time) > MAX_LOOP_MILLIS) {
						save();
						time = System.currentTimeMillis();
					}
				}
			} catch (InterruptedException e) {
				save();
			}
		}
	}
	
	private void save() {
		if(tempAuditLog.size() > 0) {
			int loop = 0;
			for(;;) {
				try {
					if(loop > 3) {
						break;
					}
					Map<Long, List<AuditLog>> groupByWarehouseId
							= tempAuditLog.stream().collect(Collectors.groupingBy(AuditLog::getWarehouseId));
					Long warehouseId;
					for(Map.Entry<Long, List<AuditLog>> entry : groupByWarehouseId.entrySet()) {
						warehouseId = entry.getKey();
						if(warehouseId == null || 0 == warehouseId) {
							continue;
						}
						try {
							//ShardingKeyHolder.set(warehouseId);
							List<AuditLog> value = entry.getValue();
							logger.info("auditLog list {}" , value);
							auditLogDao.saveAllAuditLog(entry.getValue());
						} finally {
							//ShardingKeyHolder.clear();
						}
					}
					break;
				} catch(Throwable t) {
					try {
						logger.error("记录审计日志时异常", t);
						Thread.sleep(1000);
						loop++;
					} catch(Throwable t2) {
						t2.printStackTrace();
					}
				}
			}
			tempAuditLog.clear();
		}
	}



}
