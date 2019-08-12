package com.mglj.base.audit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 审计资源
 * 
 * @author zsp
 *
 */
public class Resource extends Name {
	
	public final static String DEFAULT_GROUP = "缺省";
	public final static String AUTH_GROUP = "验证";
	public final static String BASE_GROUP = "基础";
	
	private String group;
	private Map<String, Action> actionMap = new ConcurrentHashMap<String, Action>();

	public Resource(String code, String name) {
		super(code, name);
		addAction(Action.UNDEFINED);
	}
	
	/**
	 * 添加一个审计操作
	 * 
	 * @param action
	 * @return
	 */
	public Resource addAction(Action action) {
		if(action == null) {
			throw new IllegalArgumentException();
		}
		actionMap.put(action.getCode(), action);
		return this;
	}
	
	/**
	 * 添加增改查删的审计操作
	 * @return
	 */
	public Resource addCurdAction() {
		addAction(Action.CREATE_ACTION);
		addAction(Action.UPDATE_ACTION);
		addAction(Action.RETRIEVE_ACTION);
		addAction(Action.DELETE_ACTION);
		return this;
	}
	
	/**
	 * 获取一个审计操作
	 * 
	 * @param actionCode
	 * @return
	 */
	public Action findAction(String actionCode) {
		if(actionCode == null) {
			return Action.UNDEFINED;
		}
		Action action = actionMap.get(actionCode);
		if(action == null) {
			return Action.UNDEFINED;
		}
		return action;
	}
	
	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}
	
	public List<Action> getActions() {
		return new ArrayList<Action>(actionMap.values());
	}

}
