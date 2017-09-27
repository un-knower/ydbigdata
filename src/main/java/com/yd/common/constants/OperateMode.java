package com.yd.common.constants;

public enum OperateMode {
	CREATE("I", "新增"),
	READ("Q", "查看"),
	UPDATE("M", "修改"),
	DELETE("D", "删除");
	
	private String code;
	private String name;

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private OperateMode(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
}
