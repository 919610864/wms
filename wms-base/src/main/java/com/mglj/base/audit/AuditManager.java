package com.mglj.base.audit;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 审计管理器
 *
 * @author zsp
 *
 */
@Component
public class AuditManager implements DisposableBean {
	
	private final Set<String> groupSet = new CopyOnWriteArraySet<String>();
	private final Map<String, Resource> resourceMap = new ConcurrentHashMap<String, Resource>();
	
	public AuditManager() {
		groupSet.add(Resource.DEFAULT_GROUP);
	}

	@Override
	public void destroy() throws Exception {
		groupSet.clear();
		resourceMap.clear();
	}


	/**
	 * 添加一个审计资源
	 * 
	 * @param obj
	 * @param group
	 * @param code
	 * @param name
	 * @return
	 */
	public Resource addResource(Object obj, String group, String code, String name) {
		if(obj == null) {
			throw new IllegalArgumentException("obj");
		}
		if(group == null) {
			group = Resource.DEFAULT_GROUP;
		}
		groupSet.add(group);
		Resource resource = new Resource(code, name);
		resource.setGroup(group);
		resourceMap.put(obj.getClass().getName(), resource);
		return resource;
	}
	
	/**
	 * 获取一个审计资源
	 * 
	 * @param clasz
	 * @return
	 */
	public Resource findResource(Class<?> clasz) {
		if(clasz == null) {
			throw new IllegalArgumentException();
		}
		return resourceMap.get(clasz.getName());
	}
	
	/**
	 * 查询审计资源
	 * 
	 * @return
	 */
	public List<Resource> listResource(String group) {
		if(group == null) {
			group = Resource.DEFAULT_GROUP;
		}
		/*合并具有相同编码的资源*/
		Map<String, Resource> map = new HashMap<String, Resource>();
		Resource resource;
		String resourceCode;
		for(Map.Entry<String, Resource> entry : resourceMap.entrySet()) {
			resource = entry.getValue();
			if(group.equals(resource.getGroup())) {
				resourceCode = resource.getCode();
				Resource r = map.get(resourceCode);
				if(r == null) {
					r = new Resource(resourceCode, resource.getName());
					r.setGroup(resource.getGroup());
					map.put(resourceCode, r);
				}
				for(Action a : resource.getActions()) {
					r.addAction(a);
				}
			}
		}
		List<Resource> list = new ArrayList<Resource>();
		for(Map.Entry<String, Resource> entry : map.entrySet()) {
			list.add(entry.getValue());
		}
		return list;
	}
	
	public List<String> groups() {
		return new ArrayList<String>(groupSet);
	}


}
