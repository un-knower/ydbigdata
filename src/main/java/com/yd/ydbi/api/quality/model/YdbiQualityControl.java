package com.yd.ydbi.api.quality.model;

import java.io.Serializable;

public class YdbiQualityControl implements Serializable {
    private static final long serialVersionUID = 1L;

    private String quality_flag;   				// 标识   
    private String day_per_amount;              // 日人均件量            
	private String delay_rate;   				// 延误率
	private String wrong_rate;   				// 错发率
	private String wrongpoints_rate;   			// 错分率
	private String wrongpackage_rate;   		// 集错包率
	private String loss_rate;   				// 遗失率
	private String Retention_rate;   			// 滞留率
	private String day_percent1;           		// 延误率日环比            
	private String week_with_percent1;   		// 延误率周同比 
	private String day_percent2;           		// 错发率日环比            
	private String week_with_percent2;   		// 错发率周同比 
	private String day_percent3;           		// 错分率日环比            
	private String week_with_percent3;   		// 错分率周同比 
	private String day_percent4;           		// 集错包率日环比            
	private String week_with_percent4;   		// 集错包率周同比 
	private String day_percent5;           		// 遗失率日环比            
	private String week_with_percent5;   		// 遗失率周同比 
	private String day_percent6;           		// 滞留率日环比            
	private String week_with_percent6;   		// 滞留率周同比 
	private String day_percent7;           		// 人均操作件量日环比            
	private String week_with_percent7;   		// 人均操作件量周同比 
	
	public String getDay_per_amount() {
		return day_per_amount;
	}
	public void setDay_per_amount(String day_per_amount) {
		this.day_per_amount = day_per_amount;
	}
	public String getQuality_flag() {
		return quality_flag;
	}
	public void setQuality_flag(String quality_flag) {
		this.quality_flag = quality_flag;
	}
	public String getDelay_rate() {
		return delay_rate;
	}
	public void setDelay_rate(String delay_rate) {
		this.delay_rate = delay_rate;
	}
	public String getWrong_rate() {
		return wrong_rate;
	}
	public void setWrong_rate(String wrong_rate) {
		this.wrong_rate = wrong_rate;
	}
	public String getWrongpoints_rate() {
		return wrongpoints_rate;
	}
	public void setWrongpoints_rate(String wrongpoints_rate) {
		this.wrongpoints_rate = wrongpoints_rate;
	}
	public String getWrongpackage_rate() {
		return wrongpackage_rate;
	}
	public void setWrongpackage_rate(String wrongpackage_rate) {
		this.wrongpackage_rate = wrongpackage_rate;
	}
	public String getLoss_rate() {
		return loss_rate;
	}
	public void setLoss_rate(String loss_rate) {
		this.loss_rate = loss_rate;
	}
	public String getRetention_rate() {
		return Retention_rate;
	}
	public void setRetention_rate(String retention_rate) {
		Retention_rate = retention_rate;
	}
	public String getDay_percent1() {
		return day_percent1;
	}
	public void setDay_percent1(String day_percent1) {
		this.day_percent1 = day_percent1;
	}
	public String getWeek_with_percent1() {
		return week_with_percent1;
	}
	public void setWeek_with_percent1(String week_with_percent1) {
		this.week_with_percent1 = week_with_percent1;
	}
	public String getDay_percent2() {
		return day_percent2;
	}
	public void setDay_percent2(String day_percent2) {
		this.day_percent2 = day_percent2;
	}
	public String getWeek_with_percent2() {
		return week_with_percent2;
	}
	public void setWeek_with_percent2(String week_with_percent2) {
		this.week_with_percent2 = week_with_percent2;
	}
	public String getDay_percent3() {
		return day_percent3;
	}
	public void setDay_percent3(String day_percent3) {
		this.day_percent3 = day_percent3;
	}
	public String getWeek_with_percent3() {
		return week_with_percent3;
	}
	public void setWeek_with_percent3(String week_with_percent3) {
		this.week_with_percent3 = week_with_percent3;
	}
	public String getDay_percent4() {
		return day_percent4;
	}
	public void setDay_percent4(String day_percent4) {
		this.day_percent4 = day_percent4;
	}
	public String getWeek_with_percent4() {
		return week_with_percent4;
	}
	public void setWeek_with_percent4(String week_with_percent4) {
		this.week_with_percent4 = week_with_percent4;
	}
	public String getDay_percent5() {
		return day_percent5;
	}
	public void setDay_percent5(String day_percent5) {
		this.day_percent5 = day_percent5;
	}
	public String getWeek_with_percent5() {
		return week_with_percent5;
	}
	public void setWeek_with_percent5(String week_with_percent5) {
		this.week_with_percent5 = week_with_percent5;
	}
	public String getDay_percent6() {
		return day_percent6;
	}
	public void setDay_percent6(String day_percent6) {
		this.day_percent6 = day_percent6;
	}
	public String getWeek_with_percent6() {
		return week_with_percent6;
	}
	public void setWeek_with_percent6(String week_with_percent6) {
		this.week_with_percent6 = week_with_percent6;
	}
	public String getDay_percent7() {
		return day_percent7;
	}
	public void setDay_percent7(String day_percent7) {
		this.day_percent7 = day_percent7;
	}
	public String getWeek_with_percent7() {
		return week_with_percent7;
	}
	public void setWeek_with_percent7(String week_with_percent7) {
		this.week_with_percent7 = week_with_percent7;
	}
	
	
}