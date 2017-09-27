package com.yd.ydbi.api.quality.model;

import java.io.Serializable;

public class YdbiInterestRate implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String rate_flag;   				// 标识
    private String org_name;   					// 机构
    private String rate_num;   					// 利率数量
    private String interest_rate;   			// 利率      
	private String day_percent;           		// 日环比            
	private String week_with_percent;   		// 周同比    
	private String day_time;   					// 上一日
	private String week_num;   					// 周日均量
	private String month_num;   				// 月日均量
	private String week_rate;   				// 周率
	private String month_rate;   				// 月率
	
	
	public String getOrg_name() {
		return org_name;
	}
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}
	public String getRate_num() {
		return rate_num;
	}
	public void setRate_num(String rate_num) {
		this.rate_num = rate_num;
	}
	public String getRate_flag() {
		return rate_flag;
	}
	public void setRate_flag(String rate_flag) {
		this.rate_flag = rate_flag;
	}
	public String getInterest_rate() {
		return interest_rate;
	}
	public void setInterest_rate(String interest_rate) {
		this.interest_rate = interest_rate;
	}
	public String getDay_percent() {
		return day_percent;
	}
	public void setDay_percent(String day_percent) {
		this.day_percent = day_percent;
	}
	public String getWeek_with_percent() {
		return week_with_percent;
	}
	public void setWeek_with_percent(String week_with_percent) {
		this.week_with_percent = week_with_percent;
	}
	public String getDay_time() {
		return day_time;
	}
	public void setDay_time(String day_time) {
		this.day_time = day_time;
	}
	public String getWeek_num() {
		return week_num;
	}
	public void setWeek_num(String week_num) {
		this.week_num = week_num;
	}
	public String getMonth_num() {
		return month_num;
	}
	public void setMonth_num(String month_num) {
		this.month_num = month_num;
	}
	public String getWeek_rate() {
		return week_rate;
	}
	public void setWeek_rate(String week_rate) {
		this.week_rate = week_rate;
	}
	public String getMonth_rate() {
		return month_rate;
	}
	public void setMonth_rate(String month_rate) {
		this.month_rate = month_rate;
	}
	
	
}