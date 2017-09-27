package com.yd.ydbi.hp.model;

import java.io.Serializable;

public class PerCapitalEffectTopN implements Serializable{
	private String fb_code;//分拨中心
	private String p_num;//人均值
	private String top_num;//峰值
	private String week_cycle;//周环比
	public PerCapitalEffectTopN() {
		super();
	}
	public String getFb_code() {
		return fb_code;
	}
	public void setFb_code(String fb_code) {
		this.fb_code = fb_code;
	}
	public String getP_num() {
		return p_num;
	}
	public void setP_num(String p_num) {
		this.p_num = p_num;
	}
	public String getTop_num() {
		return top_num;
	}
	public void setTop_num(String top_num) {
		this.top_num = top_num;
	}
	public String getWeek_cycle() {
		return week_cycle;
	}
	public void setWeek_cycle(String week_cycle) {
		this.week_cycle = week_cycle;
	}
}
