package com.yd.ydbi.hp.model;

import java.io.Serializable;

public class HomePage implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 揽件量 
	 */
	private String all_collect_num;
	private String day_on_day;//日环比 
	private String weak_on_weak;//周同比 
	private String per_capita_num;//人均件量
	private String pcn_dod;//人均件日环比
	private String pcn_wow;//人均件周同比
	private String newbee_mark;//菜鸟综合评分
	private String newbee_rank;//菜鸟排名
	private String newbee_ave;//菜鸟平均分
	private String sx_flag;//菜鸟升降
	private String order_num;//订单量
	private String on_dod;//订单日环比
	private String on_wow;//订单周同比 
	private String send_num;//派件量
	private String sd_dod;//派件日环比
	private String sd_wow;//派件周同比
	private String sign_rate;//签收率
	private String sr_dod;// 签收率日环比
	private String sr_wow;//签收率周同比
	private String open_line;//开通线路
	private String oi_dod;//开通线路日环比
	private String oi_wow;//开通线路周同比
	private String send_car;//发车数
	private String sc_dod;//发车数日环比
	private String sc_wow;//发车数周同比
	private String freight_rate;//装载率
	private String fr_dod;//装载率日环比
	private String fr_wow;//装载率周同比

	public HomePage() {
		super();
	}

	public String getAll_collect_num() {
		return all_collect_num;
	}

	public void setAll_collect_num(String all_collect_num) {
		this.all_collect_num = all_collect_num;
	}

	public String getDay_on_day() {
		return day_on_day;
	}

	public void setDay_on_day(String day_on_day) {
		this.day_on_day = day_on_day;
	}

	public String getWeak_on_weak() {
		return weak_on_weak;
	}

	public void setWeak_on_weak(String weak_on_weak) {
		this.weak_on_weak = weak_on_weak;
	}

	public String getPer_capita_num() {
		return per_capita_num;
	}

	public void setPer_capita_num(String per_capita_num) {
		this.per_capita_num = per_capita_num;
	}

	public String getPcn_dod() {
		return pcn_dod;
	}

	public void setPcn_dod(String pcn_dod) {
		this.pcn_dod = pcn_dod;
	}

	public String getPcn_wow() {
		return pcn_wow;
	}

	public void setPcn_wow(String pcn_wow) {
		this.pcn_wow = pcn_wow;
	}

	public String getNewbee_mark() {
		return newbee_mark;
	}

	public void setNewbee_mark(String newbee_mark) {
		this.newbee_mark = newbee_mark;
	}

	public String getNewbee_rank() {
		return newbee_rank;
	}

	public void setNewbee_rank(String newbee_rank) {
		this.newbee_rank = newbee_rank;
	}

	public String getNewbee_ave() {
		return newbee_ave;
	}

	public void setNewbee_ave(String newbee_ave) {
		this.newbee_ave = newbee_ave;
	}

	public String getOrder_num() {
		return order_num;
	}

	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}

	public String getOn_dod() {
		return on_dod;
	}

	public void setOn_dod(String on_dod) {
		this.on_dod = on_dod;
	}

	public String getOn_wow() {
		return on_wow;
	}

	public void setOn_wow(String on_wow) {
		this.on_wow = on_wow;
	}

	public String getSend_num() {
		return send_num;
	}

	public void setSend_num(String send_num) {
		this.send_num = send_num;
	}

	public String getSd_dod() {
		return sd_dod;
	}

	public void setSd_dod(String sd_dod) {
		this.sd_dod = sd_dod;
	}

	public String getSd_wow() {
		return sd_wow;
	}

	public void setSd_wow(String sd_wow) {
		this.sd_wow = sd_wow;
	}

	public String getSign_rate() {
		return sign_rate;
	}

	public void setSign_rate(String sign_rate) {
		this.sign_rate = sign_rate;
	}

	public String getSr_dod() {
		return sr_dod;
	}

	public void setSr_dod(String sr_dod) {
		this.sr_dod = sr_dod;
	}

	public String getSr_wow() {
		return sr_wow;
	}

	public void setSr_wow(String sr_wow) {
		this.sr_wow = sr_wow;
	}

	public String getOpen_line() {
		return open_line;
	}

	public void setOpen_line(String open_line) {
		this.open_line = open_line;
	}

	public String getOi_dod() {
		return oi_dod;
	}

	public void setOi_dod(String oi_dod) {
		this.oi_dod = oi_dod;
	}

	public String getOi_wow() {
		return oi_wow;
	}

	public void setOi_wow(String oi_wow) {
		this.oi_wow = oi_wow;
	}

	public String getSend_car() {
		return send_car;
	}

	public void setSend_car(String send_car) {
		this.send_car = send_car;
	}

	public String getSc_dod() {
		return sc_dod;
	}

	public void setSc_dod(String sc_dod) {
		this.sc_dod = sc_dod;
	}

	public String getSc_wow() {
		return sc_wow;
	}

	public void setSc_wow(String sc_wow) {
		this.sc_wow = sc_wow;
	}

	public String getFreight_rate() {
		return freight_rate;
	}

	public void setFreight_rate(String freight_rate) {
		this.freight_rate = freight_rate;
	}

	public String getFr_dod() {
		return fr_dod;
	}

	public void setFr_dod(String fr_dod) {
		this.fr_dod = fr_dod;
	}

	public String getFr_wow() {
		return fr_wow;
	}

	public void setFr_wow(String fr_wow) {
		this.fr_wow = fr_wow;
	}

	public String getSx_flag() {
		return sx_flag;
	}

	public void setSx_flag(String sx_flag) {
		this.sx_flag = sx_flag;
	}

	

	
}
