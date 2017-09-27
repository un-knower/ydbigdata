package com.yd.ydbi.matrix.model;

import java.io.Serializable;

import com.yd.ydbi.utils.excel.ExcelTitle;

public class Matrix_shipment_sum implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ExcelTitle(name="分拨中心",order=1)
	private String dbctName;
	
	@ExcelTitle(name="设备编号",order=2)
	private String eqptId;
	
	@ExcelTitle(name="线体号",order=3)
	private String lnId;
	
	@ExcelTitle(name="操作总件数",order=4)
	private int operCnt;
	
	@ExcelTitle(name="操作总重量",order=5)
	private float operWgt;

	public String getDbctName() {
		return dbctName;
	}

	public void setDbctName(String dbctName) {
		this.dbctName = (dbctName == null)? null : dbctName.trim();
	}

	public String getEqptId() {
		return eqptId;
	}

	public void setEqptId(String eqptId) {
		this.eqptId = (eqptId == null)? null: eqptId.trim();
	}

	public String getLnId() {
		return lnId;
	}

	public void setLnId(String lnId) {
		this.lnId = (lnId == null)? null : lnId.trim();
	}

	public int getOperCnt() {
		return operCnt;
	}

	public void setOperCnt(int operCnt) {
		this.operCnt = operCnt;
	}

	public float getOperWgt() {
		return operWgt;
	}

	public void setOperWgt(float operWgt) {
		this.operWgt = operWgt;
	}

	@Override
	public String toString() {
		return "Matrix_shipment_sum [dbctName=" + dbctName + ", eqptId="
				+ eqptId + ", lnId=" + lnId + ", operCnt=" + operCnt
				+ ", operWgt=" + operWgt + "]";
	}
	
}
