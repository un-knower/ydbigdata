package com.yd.ydbi.business.model;

public class MonthVolatility {
	private String orgName;
	private String orgCode;
	private String monthTheGrowthRate;
	private String monthLastGrowthRate;
	private String monthvolatility;

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getMonthTheGrowthRate() {
		return monthTheGrowthRate;
	}

	public void setMonthTheGrowthRate(String monthTheGrowthRate) {
		this.monthTheGrowthRate = monthTheGrowthRate;
	}

	public String getMonthLastGrowthRate() {
		return monthLastGrowthRate;
	}

	public void setMonthLastGrowthRate(String monthLastGrowthRate) {
		this.monthLastGrowthRate = monthLastGrowthRate;
	}

	public String getMonthvolatility() {
		return monthvolatility;
	}

	public void setMonthvolatility(String monthvolatility) {
		this.monthvolatility = monthvolatility;
	}

}
