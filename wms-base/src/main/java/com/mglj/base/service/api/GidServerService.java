package com.mglj.base.service.api;



import com.mglj.base.domain.GidServer;

import java.util.List;

/**
 * 
 * id生成服务Service
 * @author zsp
 * @date 2016-11-15
 */
public interface GidServerService {
	
	/**
	 * 保存
	 *
	 * @param gidServer
	 * @return 如果成功，则返回null；否则返回错误码。
	 */
	public String saveGidServer(GidServer gidServer);
	
	
	/**
	 * 更新
	 *
	 * @param gidServer
	 * @return 如果成功，则返回null；否则返回错误码。
	 */
	public String updateGidServer(GidServer gidServer);
	
	/**
	 * 删除
	 *
	 * @param id
	 * @return 如果成功，则返回null；否则返回错误码。
	 */
	public String deleteGidServer(final int id);
	
	/**
	 * 查找一个
	 *
	 * @param id
	 * @return
	 */
	public GidServer findOneGidServer(final int id);
	
	/**
	 * 查找一个
	 *
	 * @param ip
	 * @return
	 */
	public GidServer findOneGidServerByIp(final String ip);
	
	/**
	 * 查找多个
	 *
	 * @return
	 */
	public List<GidServer> findAllGidServer();


	/**
	 * 初始化服务节点
	 */
	void initGidServer();

	/**
	 * 根据IP获取服务节点的序列号
	 *
	 * @param ip
	 * @return
	 */
	Integer getSequenceOrUpdateEmptyGidServer(String ip);
	
}
