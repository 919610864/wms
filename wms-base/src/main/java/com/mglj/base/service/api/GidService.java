package com.mglj.base.service.api;

/**
 * 全局ID生成服务。
 * 
 * @author zsp
 * @date 2016-11-15
 */
public interface GidService {

	/**
	 * 生成一个ID。
	 * 
	 * @return 返回一个全局唯一的ID
	 */
	long generate();
	
}
