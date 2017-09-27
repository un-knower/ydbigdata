package com.yd.ydbi.ys.model;

import java.io.Serializable;

/**
 * 
 * @author Administrator
 *
 */
public class DB_SendCar implements Serializable{
	/**
	 * 时间
	 */
	private String timezone;
	/**
	 * 发车数量
	 */
	private String sCnum;
	public DB_SendCar() {
		super();
	}
	public String getTimezone() {
		return timezone;
	}
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	public String getsCnum() {
		return sCnum;
	}
	public void setsCnum(String sCnum) {
		this.sCnum = sCnum;
	}
	
	
}
