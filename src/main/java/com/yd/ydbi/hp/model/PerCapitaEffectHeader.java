package com.yd.ydbi.hp.model;

import java.io.Serializable;

/**
 * @author Administrator
 *	人均效能页header
 */
public class PerCapitaEffectHeader implements Serializable{
	private String allcountry;//当前机构人均
	private String allcountryDailyChain;//当前机构人均日环比
	private String allcountryCycleRing;//当前机构人均周环比
	private String allcity;// 全国的人均件
	private String upAllcountrythousand;//全国超过人均1000
	private String upAllcountrythousandi;//全国超过人均1000占比
	private String upAllcountryAverange;//超过全国平均
	private String upAllcountryAverangei;//超过全国平均占比
	private String lowAllcountryAverange;//低于全国平均
	private String lowAllcountryAverangei;//低于全国平均占比

	public String getAllcity() {
		return allcity;
	}

	public void setAllcity(String allcity) {
		this.allcity = allcity;
	}

	public PerCapitaEffectHeader() {
		super();
	}
	
	public String getUpAllcountrythousandi() {
		return upAllcountrythousandi;
	}

	public void setUpAllcountrythousandi(String upAllcountrythousandi) {
		this.upAllcountrythousandi = upAllcountrythousandi;
	}

	public String getUpAllcountryAverangei() {
		return upAllcountryAverangei;
	}

	public void setUpAllcountryAverangei(String upAllcountryAverangei) {
		this.upAllcountryAverangei = upAllcountryAverangei;
	}

	public String getLowAllcountryAverangei() {
		return lowAllcountryAverangei;
	}

	public void setLowAllcountryAverangei(String lowAllcountryAverangei) {
		this.lowAllcountryAverangei = lowAllcountryAverangei;
	}

	public String getAllcountry() {
		return allcountry;
	}
	public void setAllcountry(String allcountry) {
		this.allcountry = allcountry;
	}
	public String getAllcountryDailyChain() {
		return allcountryDailyChain;
	}
	public void setAllcountryDailyChain(String allcountryDailyChain) {
		this.allcountryDailyChain = allcountryDailyChain;
	}
	public String getAllcountryCycleRing() {
		return allcountryCycleRing;
	}
	public void setAllcountryCycleRing(String allcountryCycleRing) {
		this.allcountryCycleRing = allcountryCycleRing;
	}
	public String getUpAllcountrythousand() {
		return upAllcountrythousand;
	}
	public void setUpAllcountrythousand(String upAllcountrythousand) {
		this.upAllcountrythousand = upAllcountrythousand;
	}
	public String getUpAllcountryAverange() {
		return upAllcountryAverange;
	}
	public void setUpAllcountryAverange(String upAllcountryAverange) {
		this.upAllcountryAverange = upAllcountryAverange;
	}
	public String getLowAllcountryAverange() {
		return lowAllcountryAverange;
	}
	public void setLowAllcountryAverange(String lowAllcountryAverange) {
		this.lowAllcountryAverange = lowAllcountryAverange;
	}
	
}
