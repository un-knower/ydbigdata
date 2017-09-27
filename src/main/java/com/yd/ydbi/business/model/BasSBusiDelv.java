package com.yd.ydbi.business.model;

import java.util.Date;

public class BasSBusiDelv {
	private Integer id;

	private Date delvDt10;// 昨日签收时间

	private Integer delvDbct10;// 昨日签收站点

	private Long delvCnt;// 昨日签收量

	private Long delvCntYd;// 上一日签收量

	private Long delvCntWd;// 周同期签收量

	private Long delvCntMd;// 月同期签收量

	private Long delvCntQ;// 去年同季度签收量

	private Long delvCntW;// 上周签收量

	private Long delvCntM;// 本月1号到昨日的签收量

	private Integer delvDtM;// 本月1号到昨日的天数

	private Long delvCntLm;// 上月签收总量

	private Long delvCntLlm;// 上上月签收总量

	private Long delvCntLllm;// 上上上月签收总量

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDelvDt10() {
		return delvDt10;
	}

	public void setDelvDt10(Date delvDt10) {
		this.delvDt10 = delvDt10;
	}

	public Integer getDelvDbct10() {
		return delvDbct10;
	}

	public void setDelvDbct10(Integer delvDbct10) {
		this.delvDbct10 = delvDbct10;
	}

	public Long getDelvCnt() {
		return delvCnt;
	}

	public void setDelvCnt(Long delvCnt) {
		this.delvCnt = delvCnt;
	}

	public Long getDelvCntYd() {
		return delvCntYd;
	}

	public void setDelvCntYd(Long delvCntYd) {
		this.delvCntYd = delvCntYd;
	}

	public Long getDelvCntWd() {
		return delvCntWd;
	}

	public void setDelvCntWd(Long delvCntWd) {
		this.delvCntWd = delvCntWd;
	}

	public Long getDelvCntMd() {
		return delvCntMd;
	}

	public void setDelvCntMd(Long delvCntMd) {
		this.delvCntMd = delvCntMd;
	}

	public Long getDelvCntQ() {
		return delvCntQ;
	}

	public void setDelvCntQ(Long delvCntQ) {
		this.delvCntQ = delvCntQ;
	}

	public Long getDelvCntW() {
		return delvCntW;
	}

	public void setDelvCntW(Long delvCntW) {
		this.delvCntW = delvCntW;
	}

	public Long getDelvCntM() {
		return delvCntM;
	}

	public void setDelvCntM(Long delvCntM) {
		this.delvCntM = delvCntM;
	}

	public Integer getDelvDtM() {
		return delvDtM;
	}

	public void setDelvDtM(Integer delvDtM) {
		this.delvDtM = delvDtM;
	}

	public Long getDelvCntLm() {
		return delvCntLm;
	}

	public void setDelvCntLm(Long delvCntLm) {
		this.delvCntLm = delvCntLm;
	}

	public Long getDelvCntLlm() {
		return delvCntLlm;
	}

	public void setDelvCntLlm(Long delvCntLlm) {
		this.delvCntLlm = delvCntLlm;
	}

	public Long getDelvCntLllm() {
		return delvCntLllm;
	}

	public void setDelvCntLllm(Long delvCntLllm) {
		this.delvCntLllm = delvCntLllm;
	}
}