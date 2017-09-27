package com.yd.ydbi.cainiao.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.yd.ydbi.utils.excel.ExcelTitle;

public class LineName implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String lineName;		//线路名称
	private int id;
	private int lineType;			//1：2860线路  2:50城市线路
	private String remark;
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLineType() {
		return lineType;
	}
	public void setLineType(int lineType) {
		this.lineType = lineType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "LineName [lineName=" + lineName + ", id=" + id + ", lineType=" + lineType + ", remark=" + remark + "]";
	}
	
	

}
