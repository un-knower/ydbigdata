package com.yd.ydbi.matrix.model;

import java.io.Serializable;
import java.sql.Date;

import com.yd.ydbi.utils.excel.ExcelTitle;

public class Matrix_cross_portId_rt implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ExcelTitle(name="分拨中心",order=1)
	private String dbctName;
	private String dbctCd;
	
	@ExcelTitle(name="面单类型",order=2)
	private String shipTyp;
	
	@ExcelTitle(name="操作总数",order=3)
	private long operCnt;
	
	@ExcelTitle(name="发出数量",order=4)
	private long sndTotCnt;
	
	@ExcelTitle(name="发出识别数",order=5)
	private long sndIdCnt;

	@ExcelTitle(name="发出比率(%)",order=6)
	private String sndTotRate;
	
	@ExcelTitle(name="发出识别率(%)",order=7)
	private String sndIdRate;
	
	@ExcelTitle(name="到达总数",order=8)
	private long arvTotCnt;
	
	@ExcelTitle(name="到达识别数",order=9)
	private long arvIdCnt;
	
	@ExcelTitle(name="到达识别率(%)",order=10)
	private String arvRecogRate;
	
	@ExcelTitle(name="总识别率(%)",order=11)
	private String totalRecogRate;
	
	@ExcelTitle(name="未识别数",order=12)
	private long unIdCnt;
	
	//@ExcelTitle(name="扫描时间",order=10)
	private String statsTm;

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

	public String getShipTyp() {
		return shipTyp;
	}

	public void setShipTyp(String shipTyp) {
		this.shipTyp = shipTyp;
	}

	public long getOperCnt() {
		return operCnt;
	}

	public void setOperCnt(long operCnt) {
		this.operCnt = operCnt;
	}

	public long getSndTotCnt() {
		return sndTotCnt;
	}

	public void setSndTotCnt(long sndTotCnt) {
		this.sndTotCnt = sndTotCnt;
	}

	public long getSndIdCnt() {
		return sndIdCnt;
	}

	public void setSndIdCnt(long sndIdCnt) {
		this.sndIdCnt = sndIdCnt;
	}

	public String getSndTotRate() {
		return sndTotRate;
	}

	public void setSndTotRate(String sndTotRate) {
		this.sndTotRate = sndTotRate;
	}

	public String getSndIdRate() {
		return sndIdRate;
	}

	public void setSndIdRate(String sndIdRate) {
		this.sndIdRate = sndIdRate;
	}

	public long getArvTotCnt() {
		return arvTotCnt;
	}

	public void setArvTotCnt(long arvTotCnt) {
		this.arvTotCnt = arvTotCnt;
	}

	public long getArvIdCnt() {
		return arvIdCnt;
	}

	public void setArvIdCnt(long arvIdCnt) {
		this.arvIdCnt = arvIdCnt;
	}

	public String getArvRecogRate() {
		return arvRecogRate;
	}

	public void setArvRecogRate(String arvRecogRate) {
		this.arvRecogRate = arvRecogRate;
	}

	public String getTotalRecogRate() {
		return totalRecogRate;
	}

	public void setTotalRecogRate(String totalRecogRate) {
		this.totalRecogRate = totalRecogRate;
	}

	public long getUnIdCnt() {
		return unIdCnt;
	}

	public void setUnIdCnt(long unIdCnt) {
		this.unIdCnt = unIdCnt;
	}

	public String getStatsTm() {
		return statsTm;
	}

	public void setStatsTm(String statsTm) {
		this.statsTm = statsTm;
	}

	@Override
	public String toString() {
		return "Matrix_cross_portId_rt [dbctName=" + dbctName + ", dbctCd=" + dbctCd + ", shipTyp=" + shipTyp
				+ ", operCnt=" + operCnt + ", sndTotCnt=" + sndTotCnt + ", sndIdCnt=" + sndIdCnt + ", sndTotRate="
				+ sndTotRate + ", sndIdRate=" + sndIdRate + ", arvTotCnt=" + arvTotCnt + ", arvIdCnt=" + arvIdCnt
				+ ", arvRecogRate=" + arvRecogRate + ", totalRecogRate=" + totalRecogRate + ", unIdCnt=" + unIdCnt
				+ ", statsTm=" + statsTm + "]";
	}
	
}
