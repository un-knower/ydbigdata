package com.yd.ydbi.matrix.model;

import com.yd.ydbi.utils.excel.ExcelTitle;
import java.io.Serializable;
import java.math.BigDecimal;

public class Matrix_hei_emp_oper_rt implements Serializable {
	private static final long serialVersionUID = 1L;
	@ExcelTitle(name = "分拨中心", order = 1)
	private String dbctName;
	private String dbctCd;
	@ExcelTitle(name = "员工", order = 2)
	private String scanPersName;
	private String scanPers;
	@ExcelTitle(name = "操作件数", order = 3)
	private Integer operCnt;
	@ExcelTitle(name = "操作总时长(小时)", order = 4)
	private BigDecimal operTotDur;
	@ExcelTitle(name = "有效操作时长(小时)", order = 5)
	private BigDecimal vldOperDur;
	@ExcelTitle(name = "操作效率A(件/小时)", order = 6)
	private Integer eqptRate;
	@ExcelTitle(name = "操作效率B(件/小时)", order = 7)
	private Integer itmRate;
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

	public String getScanPersName() {
		return this.scanPersName;
	}

	public void setScanPersName(String scanPersName) {
		this.scanPersName = scanPersName;
	}

	public String getScanPers() {
		return this.scanPers;
	}

	public void setScanPers(String scanPers) {
		this.scanPers = scanPers;
	}

	public Integer getOperCnt() {
		return this.operCnt;
	}

	public void setOperCnt(Integer operCnt) {
		this.operCnt = operCnt;
	}

	public BigDecimal getOperTotDur() {
		return this.operTotDur;
	}

	public void setOperTotDur(BigDecimal operTotDur) {
		this.operTotDur = operTotDur;
	}

	public BigDecimal getVldOperDur() {
		return this.vldOperDur;
	}

	public void setVldOperDur(BigDecimal vldOperDur) {
		this.vldOperDur = vldOperDur;
	}

	public Integer getEqptRate() {
		return this.eqptRate;
	}

	public void setEqptRate(Integer eqptRate) {
		this.eqptRate = eqptRate;
	}

	public Integer getItmRate() {
		return this.itmRate;
	}

	public void setItmRate(Integer itmRate) {
		this.itmRate = itmRate;
	}

	public String getStatsTm() {
		return this.statsTm;
	}

	public void setStatsTm(String statsTm) {
		this.statsTm = statsTm;
	}

	public String toString() {
		return "Matrix_hei_emp_oper_rt [dbctName=" + this.dbctName + ", dbctCd=" + this.dbctCd + ", scanPersName="
				+ this.scanPersName + ", scanPers=" + this.scanPers + ", operCnt=" + this.operCnt + ", operTotDur="
				+ this.operTotDur + ", vldOperDur=" + this.vldOperDur + ", eqptRate=" + this.eqptRate + ", itmRate="
				+ this.itmRate + ", statsTm=" + this.statsTm + "]";
	}
}
