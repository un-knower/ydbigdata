package com.yd.ydbi.ys.model;

import java.io.Serializable;

public class CLR_Detail implements Serializable{
	private String fb_code;//
	private String lr;//迟发率or延误率
	private String dlr;//日环比
	private String wlr;//周同比
	public CLR_Detail() {
		super();
	}
	public String getFb_code() {
		return fb_code;
	}
	public void setFb_code(String fb_code) {
		this.fb_code = fb_code;
	}
	public String getLr() {
		return lr;
	}
	public void setLr(String lr) {
		this.lr = lr;
	}
	public String getDlr() {
		return dlr;
	}
	public void setDlr(String dlr) {
		this.dlr = dlr;
	}
	public String getWlr() {
		return wlr;
	}
	public void setWlr(String wlr) {
		this.wlr = wlr;
	}
	
}
