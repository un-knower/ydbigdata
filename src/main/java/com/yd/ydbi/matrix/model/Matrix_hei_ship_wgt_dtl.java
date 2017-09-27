package com.yd.ydbi.matrix.model;

import com.yd.ydbi.utils.excel.ExcelTitle;
import java.io.Serializable;

public class Matrix_hei_ship_wgt_dtl implements Serializable {
	private static final long serialVersionUID = 1L;
	@ExcelTitle(name = "单号", order = 1)
	private String shipId;
	@ExcelTitle(name = "分拨中心名称", order = 2)
	private String dbctName;
	@ExcelTitle(name = "分拨中心编码", order = 3)
	private String dbctCd;
	@ExcelTitle(name = "设备编号", order = 4)
	private String eqptId;
	@ExcelTitle(name = "线体号", order = 5)
	private Integer lnId;
	@ExcelTitle(name = "体积", order = 6)
	private Integer frgtVol;
	@ExcelTitle(name = "重量", order = 7)
	private Integer frgtWgt;
	@ExcelTitle(name = "计泡重量", order = 8)
	private Integer volWgt;
	@ExcelTitle(name = "重量差", order = 9)
	private Integer wgtVal;
	@ExcelTitle(name = "扫描类型", order = 10)
	private Integer operTyp;
	@ExcelTitle(name = "扫描时间", order = 11)
	private Integer scanTm;
	@ExcelTitle(name = "扫描人员", order = 12)
	private Integer scanPers;

	public String getShipId() {
		return this.shipId;
	}

	public void setShipId(String shipId) {
		this.shipId = shipId;
	}

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

	public Integer getFrgtVol() {
		return this.frgtVol;
	}

	public void setFrgtVol(Integer frgtVol) {
		this.frgtVol = frgtVol;
	}

	public Integer getFrgtWgt() {
		return this.frgtWgt;
	}

	public void setFrgtWgt(Integer frgtWgt) {
		this.frgtWgt = frgtWgt;
	}

	public Integer getVolWgt() {
		return this.volWgt;
	}

	public void setVolWgt(Integer volWgt) {
		this.volWgt = volWgt;
	}

	public Integer getWgtVal() {
		return this.wgtVal;
	}

	public void setWgtVal(Integer wgtVal) {
		this.wgtVal = wgtVal;
	}

	public Integer getOperTyp() {
		return this.operTyp;
	}

	public void setOperTyp(Integer operTyp) {
		this.operTyp = operTyp;
	}

	public Integer getScanTm() {
		return this.scanTm;
	}

	public void setScanTm(Integer scanTm) {
		this.scanTm = scanTm;
	}

	public Integer getScanPers() {
		return this.scanPers;
	}

	public void setScanPers(Integer scanPers) {
		this.scanPers = scanPers;
	}

	public String toString() {
		return "Matrix_hei_ship_wgt_dtl [shipId=" + this.shipId + ", dbctName=" + this.dbctName + ", dbctCd="
				+ this.dbctCd + ", eqptId=" + this.eqptId + ", lnId=" + this.lnId + ", frgtVol=" + this.frgtVol
				+ ", frgtWgt=" + this.frgtWgt + ", volWgt=" + this.volWgt + ", wgtVal=" + this.wgtVal + ", operTyp="
				+ this.operTyp + ", scanTm=" + this.scanTm + ", scanPers=" + this.scanPers + "]";
	}
}