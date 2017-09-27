package com.yd.ydbi.api.quality.model;

import java.io.Serializable;

public class YdbiQualityList implements Serializable {
    private static final long serialVersionUID = 1L;

    private String quality_flag;   				// 标识   
    private String org_name;   					// 机构
    private String rate_num;              		// 票件数量            
    private String interest_rate;              	// 利率
    private String day_percent;              	// 日环比
    
    
	public String getQuality_flag() {
		return quality_flag;
	}
	public void setQuality_flag(String quality_flag) {
		this.quality_flag = quality_flag;
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
}