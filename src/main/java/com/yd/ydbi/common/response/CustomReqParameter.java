package com.yd.ydbi.common.response;

import java.io.Serializable;

public class CustomReqParameter implements Serializable {
	private static final long serialVersionUID = 1L;
	private int rows = 1; // 每页显示条数
	private int page = 100;
	private String search_condition = null;
	
	private String timestamp = null;

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getSearch_condition() {
		return search_condition;
	}

	public void setSearch_condition(String search_condition) {
		this.search_condition = search_condition;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
}
