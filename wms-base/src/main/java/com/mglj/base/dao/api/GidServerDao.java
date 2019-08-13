package com.mglj.base.dao.api;



import com.mglj.base.domain.GidServer;

import java.util.Collection;
import java.util.List;

/**
 * 
 * id生成服务Dao
 * @author zsp
 * @date 2016-11-15
 */
public interface GidServerDao {
	
	/**
	 * 保存
	 *
	 * @param entity
	 */
	public void save(GidServer entity);
	
	/**
	 * 更新
	 *
	 * @param entity
	 */
	public void update(GidServer entity);
	
	/**
	 * 删除
	 *
	 * @param id
	 */
	public void delete(final int id);
	
	/**
	 * 查找一个
	 *
	 * @param id
	 * @return
	 */
	public GidServer findOne(final int id);
	
	/**
	 * 查找一个
	 *
	 * @param ip
	 * @return
	 */
	public GidServer findOneByIp(final String ip);
	
	/**
	 * 判断是否存在，根据ip或sequence匹配，并排除所指定id的记录。
	 *
	 * @param ip 
	 * @param sequence
	 * @param id
	 * @return 返回true表示存在；否则返回false表示不存在。
	 */
	public boolean existsBy(final String ip, final int sequence,
                            final Integer id);
	
	/**
	 * 查找多个
	 *
	 * @return
	 */
	public List<GidServer> findAll();

	void saveAll(Collection<GidServer> collection);

	int updateEmpty(Integer sequence, String ip);

	Integer getSequenceByIp(String ip);

	List<GidServer> listEmpty(int size);
	
}
