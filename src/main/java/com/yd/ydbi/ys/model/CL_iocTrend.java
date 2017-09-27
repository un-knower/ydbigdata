package com.yd.ydbi.ys.model;

import java.io.Serializable;

public class CL_iocTrend implements Serializable{
	/**
	 * 日期
	 */
	private String datetime;
	/**
	 * 编号
	 */
	private String non;
	/**
	 * 增长数
	 */
	private String increase;
	/**
	 * 撤销数
	 */
	private String cancle;
	public CL_iocTrend() {
		super();
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getNon() {
		return non;
	}
	public void setNon(String non) {
		this.non = non;
	}
	public String getIncrease() {
		return increase;
	}
	public void setIncrease(String increase) {
		this.increase = increase;
	}
	public String getCancle() {
		return cancle;
	}
	public void setCancle(String cancle) {
		this.cancle = cancle;
	}
	

}
