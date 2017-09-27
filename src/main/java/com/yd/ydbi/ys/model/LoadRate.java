package com.yd.ydbi.ys.model;

import java.io.Serializable;
import java.util.List;

public class LoadRate implements Serializable{
	private String lr;//本日装载率
	private String dlr;//日环比
	private String wdr;//周同比
	private String ytlr;//上一日装载率
	private String wsum;//周平均
	private String ysum;//月平均
	private String zbc;//正班车装载率
	private String lzbc;//上一日
	private String dzbc;//日环比
	private String wzbc;//周同比
	private String kbc;//卡班车装载率
	private String lkbc;//上一日
	private String dkbc;//日环比
	private String wkbc;//周同比
	//总趋势或者卡班车趋势
	private List<SC_AloKbtrend>  altrend;
	//分拨中心详情
	private List<LR_Detail> slrDetail;
	public LoadRate() {
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
	public String getZbc() {
		return zbc;
	}
	public void setZbc(String zbc) {
		this.zbc = zbc;
	}
	public String getLzbc() {
		return lzbc;
	}
	public void setLzbc(String lzbc) {
		this.lzbc = lzbc;
	}
	public String getDzbc() {
		return dzbc;
	}
	public void setDzbc(String dzbc) {
		this.dzbc = dzbc;
	}
	public String getWzbc() {
		return wzbc;
	}
	public void setWzbc(String wzbc) {
		this.wzbc = wzbc;
	}
	public String getKbc() {
		return kbc;
	}
	public void setKbc(String kbc) {
		this.kbc = kbc;
	}
	public String getLkbc() {
		return lkbc;
	}
	public void setLkbc(String lkbc) {
		this.lkbc = lkbc;
	}
	public String getDkbc() {
		return dkbc;
	}
	public void setDkbc(String dkbc) {
		this.dkbc = dkbc;
	}
	public String getWkbc() {
		return wkbc;
	}
	public void setWkbc(String wkbc) {
		this.wkbc = wkbc;
	}
	public List<SC_AloKbtrend> getAltrend() {
		return altrend;
	}
	public void setAltrend(List<SC_AloKbtrend> altrend) {
		this.altrend = altrend;
	}
	public List<LR_Detail> getSlrDetail() {
		return slrDetail;
	}
	public void setSlrDetail(List<LR_Detail> slrDetail) {
		this.slrDetail = slrDetail;
	}
	
}
