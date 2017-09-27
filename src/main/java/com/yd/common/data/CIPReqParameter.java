package com.yd.common.data;

public class CIPReqParameter {
	private int rows = 1;  //每页显示条数
	private int page = 100;
	private String order_field;
	private String order_type; //DESC ASC
	private String search_condition = null;
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
	public String getOrder_field() {
		return order_field;
	}
	public void setOrder_field(String order_field) {
		this.order_field = order_field;
	}
	public String getOrder_type() {
		return order_type;
	}
	public void setOrder_type(String order_type) {
		this.order_type = order_type;
	}
	
	
	
}
