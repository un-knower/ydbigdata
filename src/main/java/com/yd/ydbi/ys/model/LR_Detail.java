package com.yd.ydbi.ys.model;

import java.io.Serializable;

public class LR_Detail implements Serializable{
	/**
	 * 分拨中心代码
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
	 * 总量
	 */
	private String allCars;

	public LR_Detail() {
		super();
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

	public String getAllCars() {
		return allCars;
	}

	public void setAllCars(String allCars) {
		this.allCars = allCars;
	}
	
}
