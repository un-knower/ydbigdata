package com.yd.common.function.common.data;

import java.util.Map;

/**
 * ClassName:CIPCommonQueryData Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON. Date: 2016年8月4日 上午11:06:58
 * 
 * @author lyn
 * @version
 * @since JDK 1.7
 * @see
 */
public class CIPCommonQueryData {

	private String queryId;

	private Map<String,String> param;

	private String page;

	private String rows;

	public String getQueryId() {
		return queryId;
	}

	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public Map<String,String> getParam() {
		return param;
	}

	public void setParam(Map<String,String> param) {
		this.param = param;
	}

	


}
