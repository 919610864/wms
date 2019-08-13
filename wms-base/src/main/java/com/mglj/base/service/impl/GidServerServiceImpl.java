package com.mglj.base.service.impl;


import com.mglj.base.dao.api.GidServerDao;
import com.mglj.base.domain.GidServer;
import com.mglj.base.service.api.GidServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * id生成服务Service实现
 * @author zsp
 * @date 2016-11-15
 */
@Service
public class GidServerServiceImpl implements GidServerService {

	private final int fetchSize = Runtime.getRuntime().availableProcessors() * 2;
	private final SecureRandom random = new SecureRandom();
	
	@Autowired
	private GidServerDao gidServerDao;
	
	@Override
	public String saveGidServer(GidServer entity) {
		final String ip = entity.getIp();
		if(!StringUtils.hasText(ip)) {
			throw new IllegalArgumentException("ip is null");
		}
		if(entity.getSequence() == null) {
			throw new IllegalArgumentException("sequence is null");
		}
		final int sequence = entity.getSequence();
		if(sequence < 0 || sequence > 31) {
			throw new IllegalArgumentException("sequence should be 0-31");
		}
		if(gidServerDao.existsBy(ip, sequence, null)) {
			return GidServer.IP_SEQUENCE_CONFLICT;
		}
		gidServerDao.save(entity);
		return null;
	}
	
	
	@Override
	public String updateGidServer(GidServer entity) {
		final Integer id = entity.getId();
		if(id == null || id == 0) {
			throw new IllegalArgumentException("id is null or 0");
		}
		final String ip = entity.getIp();
		if(!StringUtils.hasText(ip)) {
			throw new IllegalArgumentException("ip is null");
		}
		if(entity.getSequence() == null) {
			throw new IllegalArgumentException("sequence is null");
		}
		final int sequence = entity.getSequence();
		if(sequence < 0 || sequence > 31) {
			throw new IllegalArgumentException("sequence should be 0-31");
		}
		if(gidServerDao.existsBy(ip, sequence, id)) {
			return GidServer.IP_SEQUENCE_CONFLICT;
		} 
		gidServerDao.update(entity);
		return null;
	}
	

	@Override
	public String deleteGidServer(final int id) {
		gidServerDao.delete(id);
		return null;
	}
	

	@Override
	public GidServer findOneGidServer(final int id) {
		return gidServerDao.findOne(id);
	}
	

	@Override
	public GidServer findOneGidServerByIp(final String ip) {
		return gidServerDao.findOneByIp(ip);
	}
	

	@Override
	public List<GidServer> findAllGidServer() {
		return gidServerDao.findAll();
	}

	@Override
	public void initGidServer() {
		List<GidServer> serverList = new ArrayList<>();
		GidServer server;
		int maxSize = 1024;
		for (int i = 0; i < maxSize; i++) {
			server = new GidServer();
			server.setId(i);
			server.setIp("");
			server.setSequence(i);
			serverList.add(server);
		}
		gidServerDao.saveAll(serverList);
	}

	@Override
	public Integer getSequenceOrUpdateEmptyGidServer(String ip) {
		Integer sequence = gidServerDao.getSequenceByIp(ip);
		if (sequence == null) {
			List<GidServer> emptyServerList;
			int index, affectedRows = 0;
			Set<Integer> indexSet = new HashSet<>();
			Outter: for(int i = 0; i < 3; i++) {
				emptyServerList = gidServerDao.listEmpty(fetchSize);
				for (int j = 0; j < 3; j++) {
					do {
						index = random.nextInt(emptyServerList.size() + 1) - 1;
						if (index < 0) {
							index = 0;
						}
					} while (indexSet.contains(index));
					indexSet.add(index);
					sequence = emptyServerList.get(index).getSequence();
					affectedRows = gidServerDao.updateEmpty(sequence, ip);
					if (affectedRows > 0) {
						break Outter;
					}
				}
				indexSet.clear();
			}
			if(affectedRows < 1) {
				throw new IllegalStateException("抢占服务节点序号失败");
			}
		}
		return sequence;
	}

}
