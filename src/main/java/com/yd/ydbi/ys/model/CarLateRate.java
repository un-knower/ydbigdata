package com.yd.ydbi.ys.model;

import java.io.Serializable;
import java.util.List;

public class CarLateRate implements Serializable{
	private String lr;//本日迟发率or延误率
	private String dlr;//日环比
	private String wdr;//周同比
	private String ytlr;//上一日装载率or延误率
	private String wsum;//周平均
	private String ysum;//月平均
	//总趋势or卡班车趋势
	private List<SC_AloKbtrend>  altrend;
	//分拨中心详情
	private List<CLR_Detail> clrDetail;
	public CarLateRate() {
		super();
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
	public String getWdr() {
		return wdr;
	}
	public void setWdr(String wdr) {
		this.wdr = wdr;
	}
	public String getYtlr() {
		return ytlr;
	}
	public void setYtlr(String ytlr) {
		this.ytlr = ytlr;
	}
	public String getWsum() {
		return wsum;
	}
	public void setWsum(String wsum) {
		this.wsum = wsum;
	}
	public String getYsum() {
		return ysum;
	}
	public void setYsum(String ysum) {
		this.ysum = ysum;
	}
	public List<SC_AloKbtrend> getAltrend() {
		return altrend;
	}
	public void setAltrend(List<SC_AloKbtrend> altrend) {
		this.altrend = altrend;
	}
	public List<CLR_Detail> getClrDetail() {
		return clrDetail;
	}
	public void setClrDetail(List<CLR_Detail> clrDetail) {
		this.clrDetail = clrDetail;
	}
	
}
