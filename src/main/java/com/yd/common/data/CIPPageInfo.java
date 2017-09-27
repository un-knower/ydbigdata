package com.yd.common.data;

public class CIPPageInfo {

	private int page;
	private int rows;  //每页显示条数
	private int total;
	
	private String order_field;
	private String order_type; //DESC ASC
		
	public CIPPageInfo(){
		
	}
	
	public CIPPageInfo(int page, int rows) {
		super();
		this.page = page;
		this.rows = rows;
	}


	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getStartRecord(){
		if((page-1)*rows>total){
			return rows;
		}else{
			return (page-1)*rows;
		}
	}
	
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
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
