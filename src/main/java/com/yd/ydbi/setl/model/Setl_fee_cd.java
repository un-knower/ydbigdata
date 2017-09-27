package com.yd.ydbi.setl.model;

import java.io.Serializable;

import com.yd.ydbi.utils.excel.ExcelTitle;

/**
 * 费用信息<br/>
 *
 * @Date: 2017-06-30 12:48:07
 * @author 康元佳
 * @version
 * @since JDK 1.7
 * @see
 */
public class Setl_fee_cd implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/** 费用编码*/
	private java.lang.Integer id;
	/** 费用名称*/
	private java.lang.String name;
	
	
	public java.lang.Integer getId() {
		return id;
	}
	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	public java.lang.String getName() {
		return name;
	}
	public void setName(java.lang.String name) {
		this.name = name;
	}
	
}

