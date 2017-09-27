package com.yd.ydbi.business.model;

import java.util.Date;

public class BasSBusiSnd {
	private Integer id;

	private Date sndDt24;// 昨日派件时间
	
	private Integer sndDbct24;// 昨日派件站点
	
	private Long sndCnt;// 昨日派件量
	
	private Long sndCntYd;// 上一日派件量
	
	private Long sndCntWd;// 周同期派件量
	
	private Long sndCntMd;// 月同期派件量
	
	private Long sndCntQ;// 去年同季度派件量
	
	private Long sndCntW;// 上周派件量
	
	private Long sndCntM;// 本月1号到昨日的派件量
	
	private Integer sndDtM;// 本月1号到昨日的天数
	
	private Long sndCntLm;// 上月派件总量
	
	private Long sndCntLlm;// 上上月派件总量
	
	private Long sndCntLllm;// 上上上月派件总量

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

	public Long getSndCnt() {
		return sndCnt;
	}

	public void setSndCnt(Long sndCnt) {
		this.sndCnt = sndCnt;
	}

	public Long getSndCntYd() {
		return sndCntYd;
	}

	public void setSndCntYd(Long sndCntYd) {
		this.sndCntYd = sndCntYd;
	}

	public Long getSndCntWd() {
		return sndCntWd;
	}

	public void setSndCntWd(Long sndCntWd) {
		this.sndCntWd = sndCntWd;
	}

	public Long getSndCntMd() {
		return sndCntMd;
	}

	public void setSndCntMd(Long sndCntMd) {
		this.sndCntMd = sndCntMd;
	}

	public Long getSndCntQ() {
		return sndCntQ;
	}

	public void setSndCntQ(Long sndCntQ) {
		this.sndCntQ = sndCntQ;
	}

	public Long getSndCntW() {
		return sndCntW;
	}

	public void setSndCntW(Long sndCntW) {
		this.sndCntW = sndCntW;
	}

	public Long getSndCntM() {
		return sndCntM;
	}

	public void setSndCntM(Long sndCntM) {
		this.sndCntM = sndCntM;
	}

	public Integer getSndDtM() {
		return sndDtM;
	}

	public void setSndDtM(Integer sndDtM) {
		this.sndDtM = sndDtM;
	}

	public Long getSndCntLm() {
		return sndCntLm;
	}

	public void setSndCntLm(Long sndCntLm) {
		this.sndCntLm = sndCntLm;
	}

	public Long getSndCntLlm() {
		return sndCntLlm;
	}

	public void setSndCntLlm(Long sndCntLlm) {
		this.sndCntLlm = sndCntLlm;
	}

	public Long getSndCntLllm() {
		return sndCntLllm;
	}

	public void setSndCntLllm(Long sndCntLllm) {
		this.sndCntLllm = sndCntLllm;
	}
}