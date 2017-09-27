package com.yd.ydbi.cainiao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.yd.ydbi.utils.excel.ExcelTitle;

/**
 * 菜鸟信息维护<br/>
 *
 * @Date: 2017-08-07 10:09:29
 * @author 康元佳
 * @version
 * @since JDK 1.7
 * @see
 */
public class Cn_compst_scor implements Serializable {
	private static final long serialVersionUID = 1L;
		/** 统计日期*/	@ExcelTitle(name = "统计日期", order = 1)	private java.lang.String statsDt;	/** 评分分数*/	@ExcelTitle(name = "评分分数", order = 2)	private BigDecimal scor;	/** 行业平均分*/	@ExcelTitle(name = "行业平均分", order = 3)	private BigDecimal industAvgScor;	/** 行业排名*/	@ExcelTitle(name = "行业排名", order = 4)	private java.lang.Integer industNm;	/** 名次升降数*/	@ExcelTitle(name = "名次升降数", order = 5)	private java.lang.Integer nmCnt;	/** 录入人编码*/	@ExcelTitle(name = "录入人编码", order = 6)	private java.lang.Integer entrPersCd;	/** 录入时间*/	@ExcelTitle(name = "录入时间", order = 7)	private java.lang.String entrTm;
		public java.lang.String getStatsDt() {	    return this.statsDt;	}	public void setStatsDt(java.lang.String statsDt) {	    this.statsDt=statsDt;	}	public BigDecimal getScor() {	    return this.scor;	}	public void setScor(BigDecimal scor) {	    this.scor=scor;	}	public BigDecimal getIndustAvgScor() {	    return this.industAvgScor;	}	public void setIndustAvgScor(BigDecimal industAvgScor) {	    this.industAvgScor=industAvgScor;	}	public java.lang.Integer getIndustNm() {	    return this.industNm;	}	public void setIndustNm(java.lang.Integer industNm) {	    this.industNm=industNm;	}	public java.lang.Integer getNmCnt() {	    return this.nmCnt;	}	public void setNmCnt(java.lang.Integer nmCnt) {	    this.nmCnt=nmCnt;	}	public java.lang.Integer getEntrPersCd() {	    return this.entrPersCd;	}	public void setEntrPersCd(java.lang.Integer entrPersCd) {	    this.entrPersCd=entrPersCd;	}	public java.lang.String getEntrTm() {	    return this.entrTm;	}	public void setEntrTm(java.lang.String entrTm) {	    this.entrTm=entrTm;	}
}

