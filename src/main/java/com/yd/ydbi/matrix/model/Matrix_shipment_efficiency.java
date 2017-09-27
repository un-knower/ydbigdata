package com.yd.ydbi.matrix.model;

import java.io.Serializable;

import com.yd.ydbi.utils.excel.ExcelTitle;

public class Matrix_shipment_efficiency implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ExcelTitle(name="分拨中心",order=1)
	private String dbctName;
	
	@ExcelTitle(name="操作类型",order=2)
	private String operTyp;
	
	@ExcelTitle(name="时长",order=3)
	private Integer spenTime;
	
	@ExcelTitle(name="设备编号",order = 4)
	private String eqptId;
	
	@ExcelTitle(name="操作件数(设备)",order=5)
	private long heiOperCnt;
	
	@ExcelTitle(name="件/小时(设备)",order=6)
	private Integer eqptRate;
	
	@ExcelTitle(name="线体号",order=7)
	private String lnId;
	
	@ExcelTitle(name="操作件数(线体)",order=8)
	private long lnOperCnt;
	
	@ExcelTitle(name="件/小时(线体)",order=9)
	private Integer lnRate;
	
	@ExcelTitle(name="物理隔口号",order=10)
	private String phPortId;
	
	@ExcelTitle(name="操作件数(隔口)",order=11)
	private long portOperCnt;
	
	@ExcelTitle(name="件/小时(隔口)",order=12)
	private Integer portRate;

	public String getDbctName() {
		return dbctName;
	}

	public void setDbctName(String dbctName) {
		this.dbctName = (dbctName == null) ? null : dbctName.trim();
	}

	public String getOperTyp() {
		return operTyp;
	}

	public void setOperTyp(String operTyp) {
		this.operTyp = operTyp;
	}

	public Integer getSpenTime() {
		return spenTime;
	}

	public void setSpenTime(Integer spenTime) {
		this.spenTime = spenTime;
	}

	public String getEqptId() {
		return eqptId;
	}

	public void setEqptId(String eqptId) {
		this.eqptId = (eqptId == null)? null: eqptId.trim();
	}

	public long getHeiOperCnt() {
		return heiOperCnt;
	}

	public void setHeiOperCnt(long heiOperCnt) {
		this.heiOperCnt = heiOperCnt;
	}

	public Integer getEqptRate() {
		return eqptRate;
	}

	public void setEqptRate(Integer eqptRate) {
		this.eqptRate = eqptRate;
	}

	public String getLnId() {
		return lnId;
	}

	public void setLnId(String lnId) {
		this.lnId = (lnId == null)? null: lnId.trim();
	}

	public long getLnOperCnt() {
		return lnOperCnt;
	}

	public void setLnOperCnt(long lnOperCnt) {
		this.lnOperCnt = lnOperCnt;
	}

	public Integer getLnRate() {
		return lnRate;
	}

	public void setLnRate(Integer lnRate) {
		this.lnRate = lnRate;
	}

	public String getPhPortId() {
		return phPortId;
	}

	public void setPhPortId(String phPortId) {
		this.phPortId = (phPortId == null)? null : phPortId.trim();
	}

	public long getPortOperCnt() {
		return portOperCnt;
	}

	public void setPortOperCnt(long portOperCnt) {
		this.portOperCnt = portOperCnt;
	}

	public Integer getPortRate() {
		return portRate;
	}

	public void setPortRate(Integer portRate) {
		this.portRate = portRate;
	}

	@Override
	public String toString() {
		return "Matrix_shipment_efficiency [dbctName=" + dbctName
				+ ", operTyp=" + operTyp + ", spenTime=" + spenTime
				+ ", eqptId=" + eqptId + ", heiOperCnt=" + heiOperCnt
				+ ", eqptRate=" + eqptRate + ", lnId=" + lnId + ", lnOperCnt="
				+ lnOperCnt + ", lnRate=" + lnRate + ", phPortId=" + phPortId
				+ ", portOperCnt=" + portOperCnt + ", portRate=" + portRate
				+ "]";
	}
	
	
}
