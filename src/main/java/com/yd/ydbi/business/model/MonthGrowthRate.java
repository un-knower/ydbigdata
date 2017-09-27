package com.yd.ydbi.business.model;

public class MonthGrowthRate {
	private String orgName;
	private String orgCode;
	private String theLastMonthPickAmount;
	private String theMonthPickAmount;
	private String monthGrowthRate;

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

	public String getTheLastMonthPickAmount() {
		return theLastMonthPickAmount;
	}

	public void setTheLastMonthPickAmount(String theLastMonthPickAmount) {
		this.theLastMonthPickAmount = theLastMonthPickAmount;
	}

	public String getTheMonthPickAmount() {
		return theMonthPickAmount;
	}

	public void setTheMonthPickAmount(String theMonthPickAmount) {
		this.theMonthPickAmount = theMonthPickAmount;
	}

	public String getMonthGrowthRate() {
		return monthGrowthRate;
	}

	public void setMonthGrowthRate(String monthGrowthRate) {
		this.monthGrowthRate = monthGrowthRate;
	}
}
