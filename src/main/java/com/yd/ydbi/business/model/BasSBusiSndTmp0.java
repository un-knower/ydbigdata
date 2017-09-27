package com.yd.ydbi.business.model;

import java.util.Date;

public class BasSBusiSndTmp0 {
	private Integer id;

	private Date sndDt24;// 昨日派件时间
	
	private Integer sndDbct24;// 昨日派件站点(所属分拨)
	
	private Integer sndSite24;// 昨日派件站点(所属一级网点)
	
	private Long sndCnt;// 昨日派件量
	
	private Integer sndQuart;// 昨日派件对应季度
	
	private Date sndWeek;// 昨日对应周一

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getSndDt24() {
		return sndDt24;
	}

	public void setSndDt24(Date sndDt24) {
		this.sndDt24 = sndDt24;
	}

	public Integer getSndDbct24() {
		return sndDbct24;
	}

	public void setSndDbct24(Integer sndDbct24) {
		this.sndDbct24 = sndDbct24;
	}

	public Integer getSndSite24() {
		return sndSite24;
	}

	public void setSndSite24(Integer sndSite24) {
		this.sndSite24 = sndSite24;
	}

	public Long getSndCnt() {
		return sndCnt;
	}

	public void setSndCnt(Long sndCnt) {
		this.sndCnt = sndCnt;
	}

	public Integer getSndQuart() {
		return sndQuart;
	}

	public void setSndQuart(Integer sndQuart) {
		this.sndQuart = sndQuart;
	}

	public Date getSndWeek() {
		return sndWeek;
	}

	public void setSndWeek(Date sndWeek) {
		this.sndWeek = sndWeek;
	}
}