package com.mglj.base.audit;


import org.springframework.util.StringUtils;

/**
 * 
 * @author zsp
 *
 */
public class AuditContext {
	
	private final static ThreadLocal<Long> billId = new InheritableThreadLocal<Long>();
	private final static ThreadLocal<String> billCode = new InheritableThreadLocal<String>();
	private final static ThreadLocal<String> description = new InheritableThreadLocal<String>();

	//private final static ThreadLocal<UserSession> currentUserSession = new InheritableThreadLocal<UserSession>();
	
	public static Long getBillId() {
		return billId.get();
	}
	public static void setBillId(Long _billId) {
		billId.set(_billId);
	}
	public static String getBillCode() {
		return billCode.get();
	}
	public static void setBillCode(String _billCode) {
		billCode.set(_billCode);
	}
	public static String getDescription() {
		return description.get();
	}
	public static void setDescription(String _description) {
		description.set(_description);
	}
//	public static UserSession getCurrentUserSession() {
//		return currentUserSession.get();
//	}
//	public static void setCurrentUserSession(UserSession userSession) {
//		currentUserSession.set(userSession);
//	}
	
	public static void with(Long _billId, String _billCode) {
		setBillId(_billId);
		setBillCode(_billCode);
	}
	
	public static void with(Long _billId, String _billCode, String _description) {
		setBillId(_billId);
		setBillCode(_billCode);
		setDescription(_description);
	}
	
	public static void with(String _description) {
		setDescription(_description);
	}
	
	private final static String DEFAULT_SPILITOR = "$$";
	public static void append(String...values) {
		if(values == null || values.length == 0) {
			return;	
		}
		StringBuilder builder = new StringBuilder();
		String str = description.get();
		boolean padding = false;
		if(StringUtils.hasText(str)) {
			builder.append(str);
			padding = true;
		}
		for(String value : values) {
			if(padding) {
				builder.append(DEFAULT_SPILITOR);
			}
			builder.append(value);
			padding = true;
		}
		setDescription(builder.toString());
	}
	
	public static void clear() {
		billId.remove();
		billCode.remove();
		description.remove();
//		currentUserSession.remove();
	}

}
