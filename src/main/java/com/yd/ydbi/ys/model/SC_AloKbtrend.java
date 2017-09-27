package com.yd.ydbi.ys.model;

import java.io.Serializable;

public class SC_AloKbtrend implements Serializable{
	/**
	 * 排序号,越小时间越近
	 */
	private String non;
	/**
	 * 时间下边界
	 */
	private String startdate;
	/**
	 * 时间上边界
	 */
	private String enddate;
	/**
	 * 实际数量
	 */
	private String realnum;
	/**
	 * 同期数量
	 */
	private String onsamenum;
	public SC_AloKbtrend() {
		super();
	}
	public String getNon() {
		return non;
	}
	public void setNon(String non) {
		this.non = non;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getRealnum() {
		return realnum;
	}
	public void setRealnum(String realnum) {
		this.realnum = realnum;
	}
	public String getOnsamenum() {
		return onsamenum;
	}
	public void setOnsamenum(String onsamenum) {
		this.onsamenum = onsamenum;
	}

}
