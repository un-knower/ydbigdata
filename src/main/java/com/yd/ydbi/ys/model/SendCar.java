package com.yd.ydbi.ys.model;

import java.io.Serializable;
import java.util.List;

public class SendCar implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 总发车数
	 */
	private String allCars;
	/**
	 * 总发车数日环比
	 */
	private String allCDoD;
	/**
	 * 总发车数周同比
	 */
	private String allCWoW;
	/**
	 * 上一日发车数
	 */
	private String lday;
	/**
	 * 周平均
	 */
	private String wave;
	/**
	 * 月平均
	 */
	private String mave;
	/**
	 * 卡班车数
	 */
	private String kbCars;
	/**
	 * 卡班车日环比
	 */
	private String kbCDoD;
	/**
	 * 卡班车周同比
	 */
	private String kbCWoW;
	/**
	 * 总趋势or卡班车趋势
	 */
	private List<SC_AloKbtrend>  altrend;
	
	/**
	 * 分拨中心详细
	 */
	private List<SC_Detail> sCDetail;
	public SendCar() {
		super();
	}
	
	public String getLday() {
		return lday;
	}

	public void setLday(String lday) {
		this.lday = lday;
	}

	public String getWave() {
		return wave;
	}

	public void setWave(String wave) {
		this.wave = wave;
	}

	public String getMave() {
		return mave;
	}

	public void setMave(String mave) {
		this.mave = mave;
	}

	public String getAllCars() {
		return allCars;
	}
	public void setAllCars(String allCars) {
		this.allCars = allCars;
	}
	public String getAllCDoD() {
		return allCDoD;
	}
	public void setAllCDoD(String allCDoD) {
		this.allCDoD = allCDoD;
	}
	public String getAllCWoW() {
		return allCWoW;
	}
	public void setAllCWoW(String allCWoW) {
		this.allCWoW = allCWoW;
	}
	public String getKbCars() {
		return kbCars;
	}
	public void setKbCars(String kbCars) {
		this.kbCars = kbCars;
	}
	public String getKbCDoD() {
		return kbCDoD;
	}
	public void setKbCDoD(String kbCDoD) {
		this.kbCDoD = kbCDoD;
	}
	public String getKbCWoW() {
		return kbCWoW;
	}
	public void setKbCWoW(String kbCWoW) {
		this.kbCWoW = kbCWoW;
	}
	public List<SC_AloKbtrend> getAltrend() {
		return altrend;
	}
	public void setAltrend(List<SC_AloKbtrend> altrend) {
		this.altrend = altrend;
	}

	public List<SC_Detail> getsCDetail() {
		return sCDetail;
	}
	public void setsCDetail(List<SC_Detail> sCDetail) {
		this.sCDetail = sCDetail;
	}
}
