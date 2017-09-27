package com.yd.ydbi.cainiao.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.yd.ydbi.utils.excel.ExcelTitle;

public class CaiNiaoDxl implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@ExcelTitle(name="线路名称",order=1)
	private String lnNm;
	@ExcelTitle(name="去程单量",order=2)
	private Integer levUnitCnt;
	@ExcelTitle(name="返程单量",order=3)
	private Integer rtnUnitCnt;
	@ExcelTitle(name="去返比",order=4)
	private BigDecimal levRtnRate;
	@ExcelTitle(name="去程时效",order=5)
	private BigDecimal levTlns;
	@ExcelTitle(name="去程行业排名",order=6)
	private Integer levIndustNm;
	@ExcelTitle(name="去程行业均值",order=7)
	private BigDecimal levIndustVal;
	@ExcelTitle(name="返程时效",order=8)
	private BigDecimal rtnTlns;
	@ExcelTitle(name="返程行业排名",order=9)
	private Integer rtnIndustNm;
	@ExcelTitle(name="返程行业均值",order=10)
	private BigDecimal rtnIndustVal;
	@ExcelTitle(name="统计日期",order=11)
	private String startDt;
	
	private String startPlace;		//始发地
	private String endPlace;		//目的地
	private int lineType;		//1,省-省  2,市-市
	
	public String getLnNm() {
		return lnNm;
	}
	public void setLnNm(String lnNm) {
		this.lnNm = lnNm;
	}
	public Integer getLevUnitCnt() {
		return levUnitCnt;
	}
	public void setLevUnitCnt(Integer levUnitCnt) {
		this.levUnitCnt = levUnitCnt;
	}
	public Integer getRtnUnitCnt() {
		return rtnUnitCnt;
	}
	public void setRtnUnitCnt(Integer rtnUnitCnt) {
		this.rtnUnitCnt = rtnUnitCnt;
	}
	public BigDecimal getLevRtnRate() {
		return levRtnRate;
	}
	public void setLevRtnRate(BigDecimal levRtnRate) {
		this.levRtnRate = levRtnRate;
	}
	public BigDecimal getLevTlns() {
		return levTlns;
	}
	public void setLevTlns(BigDecimal levTlns) {
		this.levTlns = levTlns;
	}
	public Integer getLevIndustNm() {
		return levIndustNm;
	}
	public void setLevIndustNm(Integer levIndustNm) {
		this.levIndustNm = levIndustNm;
	}
	public BigDecimal getLevIndustVal() {
		return levIndustVal;
	}
	public void setLevIndustVal(BigDecimal levIndustVal) {
		this.levIndustVal = levIndustVal;
	}
	public BigDecimal getRtnTlns() {
		return rtnTlns;
	}
	public void setRtnTlns(BigDecimal rtnTlns) {
		this.rtnTlns = rtnTlns;
	}
	public Integer getRtnIndustNm() {
		return rtnIndustNm;
	}
	public void setRtnIndustNm(Integer rtnIndustNm) {
		this.rtnIndustNm = rtnIndustNm;
	}
	public BigDecimal getRtnIndustVal() {
		return rtnIndustVal;
	}
	public void setRtnIndustVal(BigDecimal rtnIndustVal) {
		this.rtnIndustVal = rtnIndustVal;
	}
	
	public String getStartDt() {
		return startDt;
	}
	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}
	public String getStartPlace() {
		return startPlace;
	}
	public void setStartPlace(String startPlace) {
		this.startPlace = startPlace;
	}
	public String getEndPlace() {
		return endPlace;
	}
	public void setEndPlace(String endPlace) {
		this.endPlace = endPlace;
	}
	public int getLineType() {
		return lineType;
	}
	public void setLineType(int lineType) {
		this.lineType = lineType;
	}
	
	@Override
	public String toString() {
		return "CaiNiaoDxl [lnNm=" + lnNm + ", levUnitCnt=" + levUnitCnt + ", rtnUnitCnt=" + rtnUnitCnt
				+ ", levRtnRate=" + levRtnRate + ", levTlns=" + levTlns + ", levIndustNm=" + levIndustNm
				+ ", levIndustVal=" + levIndustVal + ", rtnTlns=" + rtnTlns + ", rtnIndustNm=" + rtnIndustNm
				+ ", rtnIndustVal=" + rtnIndustVal + ", startDt=" + startDt + ", startPlace=" + startPlace
				+ ", endPlace=" + endPlace + ", lineType=" + lineType + "]";
	}
	
	
}
