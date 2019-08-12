package com.mglj.base.audit;

/**
 * 审计操作
 *
 * @author zsp
 *
 */
public class Action extends Name {
	
	public final static String CREATE = "c";
	public final static String UPDATE = "u";
	public final static String DELETE = "d";
	public final static String RETRIEVE = "r";
	public final static String LOGON = "logon";
	public final static String LOGOFF = "logoff";
	
	public final static Action UNDEFINED = new Action("undefined", "未定义");
	public final static Action CREATE_ACTION = new Action(CREATE, "创建");
	public final static Action UPDATE_ACTION = new Action(UPDATE, "更新");
	public final static Action DELETE_ACTION = new Action(DELETE, "删除");
	public final static Action RETRIEVE_ACTION = new Action(RETRIEVE, "获取");
	public final static Action LOGON_ACTION = new Action(LOGON, "登录");
	public final static Action LOGOFF_ACTION = new Action(LOGOFF, "退出");

	public Action(String code, String name) {
		super(code, name);
	}

}
