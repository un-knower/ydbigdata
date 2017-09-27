package com.yd.ydbi.matrix.model;

import java.io.Serializable;
import java.sql.Date;

import com.yd.ydbi.utils.excel.ExcelTitle;

public class Matrix_cross_comboxBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	String id;
	String name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Matrix_cross_comboxBean [id=" + id + ", name=" + name + "]";
	}
	
	
}
