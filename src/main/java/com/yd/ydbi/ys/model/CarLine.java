package com.yd.ydbi.ys.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author Administrator
 *车线model
 */
public class CarLine implements Serializable{
	/**
	 * 车线总数
	 */
	private String carLinenum;
	/**
	 * 增长总数
	 */
	private String increased;
	/**
	 * 撤销总数
	 */
	private String canceled;
	/**
	 * 撤销新增详情
	 */
	private CL_iocCar iocCar;
	/**
	 * 增长撤销趋势
	 */
	private List<CL_iocTrend> iocTrend;
	public CarLine() {
		super();
	}
	public String getCarLinenum() {
		return carLinenum;
	}
	public void setCarLinenum(String carLinenum) {
		this.carLinenum = carLinenum;
	}
	public String getIncreased() {
		return increased;
	}
	public void setIncreased(String increased) {
		this.increased = increased;
	}
	public String getCanceled() {
		return canceled;
	}
	public void setCanceled(String canceled) {
		this.canceled = canceled;
	}
	public CL_iocCar getIocCar() {
		return iocCar;
	}
	public void setIocCar(CL_iocCar iocCar) {
		this.iocCar = iocCar;
	}
	public List<CL_iocTrend> getIocTrend() {
		return iocTrend;
	}
	public void setIocTrend(List<CL_iocTrend> iocTrend) {
		this.iocTrend = iocTrend;
	}
}
