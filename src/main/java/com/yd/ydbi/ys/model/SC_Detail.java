package com.yd.ydbi.ys.model;

import java.io.Serializable;

/**
 * 
 * @author Administrator
 *
 */
public class SC_Detail implements Serializable{
	/**
	 * 分拨中心代码(名称)
	 */
	private String fbcode;
	/**
	 * 正班车
	 */
	private String zbCars;
	/**
	 * 卡班车
	 */
	private String kbCars;
	
	/**
	 * 车数量
	 */
	private String knum;
	
	/**
	 * 日环比
	 */
	private String knd;
	/**
	 * 周同比
	 */
	private String knw;
	public SC_Detail() {
		super();
	}
	
	public String getKnum() {
		return knum;
	}

	public void setKnum(String knum) {
		this.knum = knum;
	}

	public String getKnd() {
		return knd;
	}

	public void setKnd(String knd) {
		this.knd = knd;
	}

	public String getKnw() {
		return knw;
	}

	public void setKnw(String knw) {
		this.knw = knw;
	}

	public String getFbcode() {
		return fbcode;
	}
	public void setFbcode(String fbcode) {
		this.fbcode = fbcode;
	}
	public String getZbCars() {
		return zbCars;
	}
	public void setZbCars(String zbCars) {
		this.zbCars = zbCars;
	}
	public String getKbCars() {
		return kbCars;
	}
	public void setKbCars(String kbCars) {
		this.kbCars = kbCars;
	}
	
}
