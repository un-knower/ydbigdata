package com.yd.ydbi.matrix.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.yd.ydbi.utils.excel.ExcelTitle;

public class Matrix_cross_oper_rt implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ExcelTitle(name = "分拨中心", order = 1)
	private String dbctName;
	private String dbctCd;
	@ExcelTitle(name = "操作类型", order = 2)
	private String operTyp;
	@ExcelTitle(name = "设备编号", order = 3)
	private String eqptId;
	@ExcelTitle(name = "操作件数", order = 4)
	private long crossOperCnt;
	@ExcelTitle(name = "操作总时长(小时)", order = 5)
	private BigDecimal operTotDur;
	@ExcelTitle(name = "操作效率A(件/小时)", order = 6)
	private Integer eqptRate;
	@ExcelTitle(name = "有效操作时长(小时)", order = 7)
	private BigDecimal vldOperDur;
	@ExcelTitle(name = "操作效率B(件/小时)", order = 8)
	private Integer itmRate;
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
	public String getOperTyp() {
		return operTyp;
	}
	public void setOperTyp(String operTyp) {
		this.operTyp = operTyp;
	}
	public String getEqptId() {
		return eqptId;
	}
	public void setEqptId(String eqptId) {
		this.eqptId = eqptId;
	}
	public long getCrossOperCnt() {
		return crossOperCnt;
	}
	public void setCrossOperCnt(long crossOperCnt) {
		this.crossOperCnt = crossOperCnt;
	}
	public BigDecimal getOperTotDur() {
		return operTotDur;
	}
	public void setOperTotDur(BigDecimal operTotDur) {
		this.operTotDur = operTotDur;
	}
	public Integer getEqptRate() {
		return eqptRate;
	}
	public void setEqptRate(Integer eqptRate) {
		this.eqptRate = eqptRate;
	}
	public BigDecimal getVldOperDur() {
		return vldOperDur;
	}
	public void setVldOperDur(BigDecimal vldOperDur) {
		this.vldOperDur = vldOperDur;
	}
	public Integer getItmRate() {
		return itmRate;
	}
	public void setItmRate(Integer itmRate) {
		this.itmRate = itmRate;
	}
	public String getStatsTm() {
		return statsTm;
	}
	public void setStatsTm(String statsTm) {
		this.statsTm = statsTm;
	}
	@Override
	public String toString() {
		return "Matrix_cross_oper_rt [dbctName=" + dbctName + ", dbctCd=" + dbctCd + ", operTyp=" + operTyp
				+ ", eqptId=" + eqptId + ", crossOperCnt=" + crossOperCnt + ", operTotDur=" + operTotDur + ", eqptRate="
				+ eqptRate + ", vldOperDur=" + vldOperDur + ", itmRate=" + itmRate + ", statsTm=" + statsTm + "]";
	}


	
}
