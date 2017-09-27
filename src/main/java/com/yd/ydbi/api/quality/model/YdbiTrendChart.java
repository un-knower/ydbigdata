package com.yd.ydbi.api.quality.model;

import java.io.Serializable;

public class YdbiTrendChart implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String rate_flag;   				// 标识
    private String data_date;   				// 日期
    private String org_id;   					// 机构id
    private String t_data_date;   				// 返回日期
    private String org_name;   					// 机构
    private String rate_num;   					// 利率数量
    private String interest_rate;   			// 利率      
	private String day_percent;           		// 日环比    
	private String date_trend;           		// 趋势  
	private String goal_vlaues;           		// 目标值 
	private String month_avg_trend;           	// 月均期趋势 
	private String quarter_time_trend;          // 季度同期趋势
	private String week_same_term;          	// 周同期
	
	
	
	public String getOrg_id() {
		return org_id;
	}
	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}
	public String getT_data_date() {
		return t_data_date;
	}
	public void setT_data_date(String t_data_date) {
		this.t_data_date = t_data_date;
	}
	public String getData_date() {
		return data_date;
	}
	public void setData_date(String data_date) {
		this.data_date = data_date;
	}
	public String getWeek_same_term() {
		return week_same_term;
	}
	public void setWeek_same_term(String week_same_term) {
		this.week_same_term = week_same_term;
	}
	public String getRate_flag() {
		return rate_flag; 
	}
	public void setRate_flag(String rate_flag) {
		this.rate_flag = rate_flag;
	}
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
	public String getDate_trend() {
		return date_trend;
	}
	public void setDate_trend(String date_trend) {
		this.date_trend = date_trend;
	}
	public String getGoal_vlaues() {
		return goal_vlaues;
	}
	public void setGoal_vlaues(String goal_vlaues) {
		this.goal_vlaues = goal_vlaues;
	}
	public String getMonth_avg_trend() {
		return month_avg_trend;
	}
	public void setMonth_avg_trend(String month_avg_trend) {
		this.month_avg_trend = month_avg_trend;
	}
	public String getQuarter_time_trend() {
		return quarter_time_trend;
	}
	public void setQuarter_time_trend(String quarter_time_trend) {
		this.quarter_time_trend = quarter_time_trend;
	}
	
	
}