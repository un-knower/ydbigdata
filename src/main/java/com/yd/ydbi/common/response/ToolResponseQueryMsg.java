package com.yd.ydbi.common.response;

public class ToolResponseQueryMsg {
	
	public Object footer;

	public Object rows;

	// totals of returning records
	public int total;

	// pages in total
	public int total_pages;

	// current page position
	public int current_page;

	// number records of current page
	public int page_count;
	
	// error code : 0 :success ; non 0， error
	public int errorCode = 0;

	// returning message
	public String msg;

	public Object data;

}
