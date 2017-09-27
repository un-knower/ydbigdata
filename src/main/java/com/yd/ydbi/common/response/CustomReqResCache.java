package com.yd.ydbi.common.response;

import java.io.Serializable;

public class CustomReqResCache implements Serializable {
	private static final long serialVersionUID = 1L;
	private String search_condition = null;
	private Object data;

	public String getSearch_condition() {
		return search_condition;
	}

	public void setSearch_condition(String search_condition) {
		this.search_condition = search_condition;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
