package com.yd.ydbi.matrix.model;

import java.io.Serializable;
import java.sql.Date;

import com.yd.ydbi.utils.excel.ExcelTitle;

public class Matrix_cross_ship_dtl implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ExcelTitle(name="单号",order=1)
	private long shipId;
	
	@ExcelTitle(name="面单类型",order=2)
	private String shipTyp;
	
	@ExcelTitle(name="操作类型",order=3)
	private String operTyp;
	
	@ExcelTitle(name="分拨中心",order=4)
	private String dbctName;
	private String dbctCd;
	
	@ExcelTitle(name="设备编号",order=5)
	private String eqptId;
	
	@ExcelTitle(name="供件台号",order=6)
	private String itmId;
	
	@ExcelTitle(name="物理格口号",order=7)
	private String physPortCd;
	
	@ExcelTitle(name="视频补码",order=8)
	private String addCd;
	
	@ExcelTitle(name="逻辑格口",order=9)
	private String portCd;
	
	@ExcelTitle(name="扫描时间",order=10)
	private String scanTm;
	
	@ExcelTitle(name="扫描人员",order=11)
	private String scanPers;
	
	@ExcelTitle(name="重量",order=12)
	private String frgtWgt;

	public long getShipId() {
		return shipId;
	}

	public void setShipId(long shipId) {
		this.shipId = shipId;
	}

	public String getShipTyp() {
		return shipTyp;
	}

	public void setShipTyp(String shipTyp) {
		this.shipTyp = shipTyp;
	}

	public String getOperTyp() {
		return operTyp;
	}

	public void setOperTyp(String operTyp) {
		this.operTyp = operTyp;
	}

	public String getDbctName() {
		return dbctName;
	}

	public void setDbctName(String dbctName) {
		this.dbctName = dbctName;
	}

	public String getDbctCd() {
		return dbctCd;
	}

	public void setDbctCd(String dbctCd) {
		this.dbctCd = dbctCd;
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

	public String getPhysPortCd() {
		return physPortCd;
	}

	public void setPhysPortCd(String physPortCd) {
		this.physPortCd = physPortCd;
	}

	public String getAddCd() {
		return addCd;
	}

	public void setAddCd(String addCd) {
		this.addCd = addCd;
	}

	public String getPortCd() {
		return portCd;
	}

	public void setPortCd(String portCd) {
		this.portCd = portCd;
	}

	public String getScanTm() {
		return scanTm;
	}

	public void setScanTm(String scanTm) {
		this.scanTm = scanTm;
	}

	public String getScanPers() {
		return scanPers;
	}

	public void setScanPers(String scanPers) {
		this.scanPers = scanPers;
	}

	public String getFrgtWgt() {
		return frgtWgt;
	}

	public void setFrgtWgt(String frgtWgt) {
		this.frgtWgt = frgtWgt;
	}

	@Override
	public String toString() {
		return "Matrix_cross_ship_dtl [shipId=" + shipId + ", shipTyp=" + shipTyp + ", operTyp=" + operTyp + ", dbctCd="
				+ dbctCd + ", eqptId=" + eqptId + ", itmId=" + itmId + ", physPortCd=" + physPortCd + ", addCd=" + addCd
				+ ", portCd=" + portCd + ", scanTm=" + scanTm + ", scanPers=" + scanPers + ", frgtWgt=" + frgtWgt + "]";
	}

	
}
