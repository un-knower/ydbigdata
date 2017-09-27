package com.yd.ydbi.matrix.model;

import com.yd.ydbi.utils.excel.ExcelTitle;
import java.io.Serializable;

public class Matrix_hei_ship_wgt_rt implements Serializable {
	private static final long serialVersionUID = 1L;
	@ExcelTitle(name = "分拨中心名称", order = 1)
	private String dbctName;
	@ExcelTitle(name = "分拨中心编码", order = 1)
	private String dbctCd;
	@ExcelTitle(name = "设备编号", order = 2)
	private String eqptId;
	@ExcelTitle(name = "线体号", order = 3)
	private Integer lnId;
	@ExcelTitle(name = "称重总票数", order = 4)
	private Integer wgtTotCnt;
	@ExcelTitle(name = "称重异常数", order = 5)
	private Integer wgtExcpCnt;
	@ExcelTitle(name = "计泡数", order = 6)
	private Integer volCnt;
	@ExcelTitle(name = "未计泡数", order = 7)
	private Integer unVolCnt;
	@ExcelTitle(name = "计泡率", order = 8)
	private Integer volRate;
	@ExcelTitle(name = "计泡重量异常数", order = 9)
	private Integer volExcpCnt;
	@ExcelTitle(name = "计泡重量异常率", order = 10)
	private Integer volExcpRate;
	private String statsTm;

	public String getDbctName() {
		return this.dbctName;
	}

	public void setDbctName(String dbctName) {
		this.dbctName = dbctName;
	}

	public String getDbctCd() {
		return this.dbctCd;
	}

	public void setDbctCd(String dbctCd) {
		this.dbctCd = dbctCd;
	}

	public String getEqptId() {
		return this.eqptId;
	}

	public void setEqptId(String eqptId) {
		this.eqptId = eqptId;
	}

	public Integer getLnId() {
		return this.lnId;
	}

	public void setLnId(Integer lnId) {
		this.lnId = lnId;
	}

	public Integer getWgtTotCnt() {
		return this.wgtTotCnt;
	}

	public void setWgtTotCnt(Integer wgtTotCnt) {
		this.wgtTotCnt = wgtTotCnt;
	}

	public Integer getWgtExcpCnt() {
		return this.wgtExcpCnt;
	}

	public void setWgtExcpCnt(Integer wgtExcpCnt) {
		this.wgtExcpCnt = wgtExcpCnt;
	}

	public Integer getVolCnt() {
		return this.volCnt;
	}

	public void setVolCnt(Integer volCnt) {
		this.volCnt = volCnt;
	}

	public Integer getUnVolCnt() {
		return this.unVolCnt;
	}

	public void setUnVolCnt(Integer unVolCnt) {
		this.unVolCnt = unVolCnt;
	}

	public Integer getVolRate() {
		return this.volRate;
	}

	public void setVolRate(Integer volRate) {
		this.volRate = volRate;
	}

	public Integer getVolExcpCnt() {
		return this.volExcpCnt;
	}

	public void setVolExcpCnt(Integer volExcpCnt) {
		this.volExcpCnt = volExcpCnt;
	}

	public Integer getVolExcpRate() {
		return this.volExcpRate;
	}

	public void setVolExcpRate(Integer volExcpRate) {
		this.volExcpRate = volExcpRate;
	}

	public String getStatsTm() {
		return this.statsTm;
	}

	public void setStatsTm(String statsTm) {
		this.statsTm = statsTm;
	}

	public String toString() {
		return "Matrix_hei_ship_wgt_rt [dbctName=" + this.dbctName + ", dbctCd=" + this.dbctCd + ", eqptId="
				+ this.eqptId + ", lnId=" + this.lnId + ", wgtTotCnt=" + this.wgtTotCnt + ", wgtExcpCnt="
				+ this.wgtExcpCnt + ", volCnt=" + this.volCnt + ", unVolCnt=" + this.unVolCnt + ", volRate="
				+ this.volRate + ", volExcpCnt=" + this.volExcpCnt + ", volExcpRate=" + this.volExcpRate + ", statsTm="
				+ this.statsTm + "]";
	}
}