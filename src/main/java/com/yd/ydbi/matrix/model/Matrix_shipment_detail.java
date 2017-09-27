package com.yd.ydbi.matrix.model;

import java.io.Serializable;
import java.sql.Date;

import com.yd.ydbi.utils.excel.ExcelTitle;

public class Matrix_shipment_detail implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ExcelTitle(name="单号",order=1)
	private String shipId;
	
	@ExcelTitle(name="操作类型",order=2)
	private String operTyp;
	
	@ExcelTitle(name="分拨中心",order=3)
	private String dbctName;
	
	@ExcelTitle(name="扫描时间",order=4)
	private String scanTm;
	
	@ExcelTitle(name="设备号",order=5)
	private String eqptId;
	
	@ExcelTitle(name="线体号",order=6)
	private String lnId;
	
	@ExcelTitle(name="物理格口号",order=7)
	private String phPortCd;
	
	@ExcelTitle(name="逻辑隔口号",order=8)
	private String portName;
	
	@ExcelTitle(name="扫描人员",order=9)
	private Integer scanPer;
	
	@ExcelTitle(name="物品重量",order=10)
	private Integer frgtWgt;

	public String getShipId() {
		return shipId;
	}

	public void setShipId(String shipId) {
		this.shipId = shipId;
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

	public String getScanTm() {
		return scanTm;
	}

	public void setScanTm(String scanTm) {
		this.scanTm = scanTm;
	}

	public String getEqptId() {
		return eqptId;
	}

	public void setEqptId(String eqptId) {
		this.eqptId = (eqptId == null)? null : eqptId.trim();
	}

	public String getLnId() {
		return lnId;
	}

	public void setLnId(String lnId) {
		this.lnId = (lnId == null)? null:lnId.trim();
	}

	public String getPhPortCd() {
		return phPortCd;
	}

	public void setPhPortCd(String phPortCd) {
		this.phPortCd = phPortCd;
	}

	public String getPortName() {
		return portName;
	}

	public void setPortName(String portName) {
		this.portName = portName;
	}

	public Integer getScanPer() {
		return scanPer;
	}

	public void setScanPer(Integer scanPer) {
		this.scanPer = scanPer;
	}

	public Integer getFrgtWgt() {
		return frgtWgt;
	}

	public void setFrgtWgt(Integer frgtWgt) {
		this.frgtWgt = frgtWgt;
	}

	@Override
	public String toString() {
		return "Matrix_shipment_detail [shipId=" + shipId + ", dbctName="
				+ dbctName + ", scanTm=" + scanTm 
				+ ", eqptId=" + eqptId + ", lnId=" + lnId + ", phPortCd="
				+ phPortCd + ", portName=" + portName + ", scanPer=" + scanPer
				+ ", frgtWgt=" + frgtWgt + "]";
	}
	
	

}
