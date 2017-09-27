package com.yd.ydbi.matrix.model;

import java.io.Serializable;
import java.sql.Date;

import com.yd.ydbi.utils.excel.ExcelTitle;

public class Matrix_cross_ship_tot implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	
	@ExcelTitle(name="分拨中心",order=1)
	private String dbctName;
	private String dbctCd;
	
	@ExcelTitle(name="设备编号",order=2)
	private String eqptId;
	
	@ExcelTitle(name="供件台号",order=3)
	private String itmId;
	
	@ExcelTitle(name="操作总件数",order=4)
	private long operCnt;
	
	@ExcelTitle(name="操作总重量",order=5)
	private long operWgt;
	
	@ExcelTitle(name="人工补码总数",order=6)
	private long persAddCdCnt;
	
	//@ExcelTitle(name="扫描时间",order=10)
	private String statsTm;

	public String getDbctCd() {
		return dbctCd;
	}

	public void setDbctCd(String dbctCd) {
		this.dbctCd = dbctCd;
	}

	public String getDbctName() {
		return dbctName;
	}

	public void setDbctName(String dbctName) {
		this.dbctName = dbctName;
	}

	public String getEqptId() {
		return eqptId;
	}

	public void setEqptId(String eqptId) {
		this.eqptId = eqptId;
	}

	public String getItmId() {
		return itmId;
	}

	public void setItmId(String itmId) {
		this.itmId = itmId;
	}

	public long getOperCnt() {
		return operCnt;
	}

	public void setOperCnt(long operCnt) {
		this.operCnt = operCnt;
	}

	public long getOperWgt() {
		return operWgt;
	}

	public void setOperWgt(long operWgt) {
		this.operWgt = operWgt;
	}

	public long getPersAddCdCnt() {
		return persAddCdCnt;
	}

	public void setPersAddCdCnt(long persAddCdCnt) {
		this.persAddCdCnt = persAddCdCnt;
	}

	public String getStatsTm() {
		return statsTm;
	}

	public void setStatsTm(String statsTm) {
		this.statsTm = statsTm;
	}

	@Override
	public String toString() {
		return "Matrix_cross_ship_tot [dbctCd=" + dbctCd + ", eqptId=" + eqptId + ", itmId=" + itmId + ", operCnt="
				+ operCnt + ", operWgt=" + operWgt + ", persAddCdCnt=" + persAddCdCnt + ", statsTm=" + statsTm + "]";
	}

	
}
