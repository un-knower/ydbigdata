package com.yd.ydbi.cainiao.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.yd.ydbi.utils.excel.ExcelTitle;

public class CaiNiao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ExcelTitle(name="统计日期",order=1)
	private String statsDt;
	@ExcelTitle(name="全链路排名",order=2)
	private Integer fullPathNm;
	@ExcelTitle(name="全链路时效",order=3)
	private BigDecimal fullPathTlns;
	@ExcelTitle(name="行业平均时效",order=4)
	private BigDecimal industAvgTlns;
	@ExcelTitle(name="录入人",order=5)
	private String entrPers;
	@ExcelTitle(name="录入时间",order=6)
	private String entrTm;
	@ExcelTitle(name="备注",order=7)
	private String rmk;
	public String getStatsDt() {
		return statsDt;
	}
	public void setStats_dt(String statsDt) {
		this.statsDt = statsDt;
	}
	public Integer getFullPathNm() {
		return fullPathNm;
	}
	public void setFullPathNm(Integer fullPathNm) {
		this.fullPathNm = fullPathNm;
	}
	public BigDecimal getFullPathTlns() {
		return fullPathTlns;
	}
	public void setFullPathTlns(BigDecimal fullPathTlns) {
		this.fullPathTlns = fullPathTlns;
	}
	public BigDecimal getIndustAvgTlns() {
		return industAvgTlns;
	}
	public void setIndustAvgTlns(BigDecimal industAvgTlns) {
		this.industAvgTlns = industAvgTlns;
	}
	public String getEntrPers() {
		return entrPers;
	}
	public void setEntrPers(String entrPers) {
		this.entrPers = entrPers;
	}
	public String getEntrTm() {
		return entrTm;
	}
	public void setEntrTm(String entrTm) {
		this.entrTm = entrTm;
	}
	public String getRmk() {
		return rmk;
	}
	public void setRmk(String rmk) {
		this.rmk = rmk;
	}
	@Override
	public String toString() {
		return "CaiNiao [statsDt=" + statsDt + ", fullPathNm=" + fullPathNm
				+ ", fullPathTlns=" + fullPathTlns + ", industAvgTlns="
				+ industAvgTlns + ", entrPers=" + entrPers + ", entrTm="
				+ entrTm + ", rmk=" + rmk + "]";
	}
	
	
	
	

}
