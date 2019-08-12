package com.mglj.base.audit;

/**
 * 
 * @author zsp
 *
 */
class Name {
	
	private final String code;
	private final String name;
	
	Name(String code) {
		this(code, code);
	}
	
	Name(String code, String name) {
		if(code == null) {
			throw new IllegalArgumentException("code");
		}
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		 if (this == obj) {
			 return true;
		 }
		 return obj != null 
				 && obj instanceof Name 
				 && code != null 
				 && code.equals(((Name)obj).getCode());
	}
	
	@Override
	public int hashCode() {
		if(code != null) {
			return code.hashCode();
		} 
		return super.hashCode();
	}

}
