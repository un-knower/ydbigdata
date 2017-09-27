package com.yd.ydbi.hp.model;

import java.io.Serializable;

/**
 * @author Administrator
 * 	人均效能趋势
 */
public class PerCaptaEffectTrend implements Serializable{
	private String date_time;//日期
	private String allcountryAverange;//全国平均
	private String allcountryCycleRing;//全国周环比
	private String no;//序号
	public PerCaptaEffectTrend() {
		super();
	}
	
	public PerCaptaEffectTrend(String date_time, String allcountryAverange, String allcountryCycleRing, String no) {
		super();
		this.date_time = date_time;
		this.allcountryAverange = allcountryAverange;
		this.allcountryCycleRing = allcountryCycleRing;
		this.no = no;
	}

	public String getDate_time() {
		return date_time;
	}
	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}
	public String getAllcountryAverange() {
		return allcountryAverange;
	}
	public void setAllcountryAverange(String allcountryAverange) {
		this.allcountryAverange = allcountryAverange;
	}
	public String getAllcountryCycleRing() {
		return allcountryCycleRing;
	}
	public void setAllcountryCycleRing(String allcountryCycleRing) {
		this.allcountryCycleRing = allcountryCycleRing;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}	
	
}
