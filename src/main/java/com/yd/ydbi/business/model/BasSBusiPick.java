package com.yd.ydbi.business.model;

import java.util.Date;

public class BasSBusiPick {
	private Integer id;

	private Date pickDt14;// 昨日揽件时间

	private Long pickSite14;// 昨日揽件站点

	private Long pickCnt;// 昨日揽件量

	private Long pickCntYd;// 上一日揽件量

	private Long pickCntWd;// 周同期揽件量

	private Long pickCntMd;// 月同期揽件量

	private Long pickCntQ;// 去年同季度揽件量

	private Long pickCntW;// 上周揽件量

	private Long pickCntM;// 本月1号到昨日的揽件量

	private Integer pickDtM;// 本月1号到昨日的天数

	private Long pickCntLm;// 上月揽件总量

	private Long pickCntLlm;// 上上月揽件总量

	private Long pickCntLllm;// 上上上月揽件总量

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getPickDt14() {
		return pickDt14;
	}

	public void setPickDt14(Date pickDt14) {
		this.pickDt14 = pickDt14;
	}

	public Long getPickSite14() {
		return pickSite14;
	}

	public void setPickSite14(Long pickSite14) {
		this.pickSite14 = pickSite14;
	}

	public Long getPickCnt() {
		return pickCnt;
	}

	public void setPickCnt(Long pickCnt) {
		this.pickCnt = pickCnt;
	}

	public Long getPickCntYd() {
		return pickCntYd;
	}

	public void setPickCntYd(Long pickCntYd) {
		this.pickCntYd = pickCntYd;
	}

	public Long getPickCntWd() {
		return pickCntWd;
	}

	public void setPickCntWd(Long pickCntWd) {
		this.pickCntWd = pickCntWd;
	}

	public Long getPickCntMd() {
		return pickCntMd;
	}

	public void setPickCntMd(Long pickCntMd) {
		this.pickCntMd = pickCntMd;
	}

	public Long getPickCntQ() {
		return pickCntQ;
	}

	public void setPickCntQ(Long pickCntQ) {
		this.pickCntQ = pickCntQ;
	}

	public Long getPickCntW() {
		return pickCntW;
	}

	public void setPickCntW(Long pickCntW) {
		this.pickCntW = pickCntW;
	}

	public Long getPickCntM() {
		return pickCntM;
	}

	public void setPickCntM(Long pickCntM) {
		this.pickCntM = pickCntM;
	}

	public Integer getPickDtM() {
		return pickDtM;
	}

	public void setPickDtM(Integer pickDtM) {
		this.pickDtM = pickDtM;
	}

	public Long getPickCntLm() {
		return pickCntLm;
	}

	public void setPickCntLm(Long pickCntLm) {
		this.pickCntLm = pickCntLm;
	}

	public Long getPickCntLlm() {
		return pickCntLlm;
	}

	public void setPickCntLlm(Long pickCntLlm) {
		this.pickCntLlm = pickCntLlm;
	}

	public Long getPickCntLllm() {
		return pickCntLllm;
	}

	public void setPickCntLllm(Long pickCntLllm) {
		this.pickCntLllm = pickCntLllm;
	}
}