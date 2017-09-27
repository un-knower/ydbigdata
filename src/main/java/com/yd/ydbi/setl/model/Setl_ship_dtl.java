package com.yd.ydbi.setl.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.yd.ydbi.utils.excel.ExcelTitle;

/**
 * 结算明细信息<br/>
 *
 * @Date: 2017-06-11 16:42:35
 * @author 康元佳
 * @version
 * @since JDK 1.7
 * @see
 */
public class Setl_ship_dtl implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/** 结算日期*/
	@ExcelTitle(name = "结算日期", order = 1)
	private java.lang.String setlDt;
	/** 结算调整日期*/
	@ExcelTitle(name = "结算调整日期", order = 2)
	private java.lang.String setlAdjDt;
	/** 运单号*/
	@ExcelTitle(name = "运单号", order = 3)
	private java.lang.Long shipId;
	/** 大包号*/
	@ExcelTitle(name = "大包号", order = 4)
	private java.lang.Long grtrPkgId;
	/** 结算公司编码*/
	@ExcelTitle(name = "结算公司编码", order = 5)
	private java.lang.Integer setlOrgCd;
	/** 结算公司名称*/
	@ExcelTitle(name = "结算公司名称", order = 6)
	private java.lang.String setlOrgNm;
	/** 结算公司下级站点编码*/
	@ExcelTitle(name = "结算公司下级站点编码", order = 7)
	private java.lang.Integer setlOrgSubCd;
	/** 结算公司下级站点名称*/
	@ExcelTitle(name = "结算公司下级站点名称", order = 8)
	private java.lang.String setlOrgSubNm;
	/** 录单日期*/
	@ExcelTitle(name = "录单日期", order = 9)
	private java.lang.String unitDt;
	/** 录单网点编码*/
	@ExcelTitle(name = "录单网点编码", order = 10)
	private java.lang.Integer unitBrchCd;
	/** 录单网点名称*/
	@ExcelTitle(name = "录单网点名称", order = 11)
	private java.lang.String unitBrchNm;
	/** 条码分配网点编码*/
	@ExcelTitle(name = "条码分配网点编码", order = 12)
	private java.lang.Integer cdAllocBrchCd;
	/** 条码分配网点名称*/
	@ExcelTitle(name = "条码分配网点名称", order = 13)
	private java.lang.String cdAllocBrchNm;
	/** 所分配客户编码*/
	@ExcelTitle(name = "所分配客户编码", order = 14)
	private java.lang.String allocCustCd;
	/** 所分配客户名称*/
	@ExcelTitle(name = "所分配客户名称", order = 15)
	private java.lang.String allocCustNm;
	/** 揽件日期*/
	@ExcelTitle(name = "揽件日期", order = 16)
	private java.lang.String pickDt;
	/** 揽件或始发网点编码*/
	@ExcelTitle(name = "揽件或始发网点编码", order = 17)
	private java.lang.Integer pickBrchCd;
	/** 揽件或始发网点名称*/
	@ExcelTitle(name = "揽件或始发网点名称", order = 18)
	private java.lang.String pickBrchNm;
	/** 揽件分部编码*/
	@ExcelTitle(name = "揽件分部编码", order = 19)
	private java.lang.Integer pickDeviCd;
	/** 揽件分部名称*/
	@ExcelTitle(name = "揽件分部名称", order = 20)
	private java.lang.String pickDeviNm;
	/** 揽件业务员编码*/
	@ExcelTitle(name = "揽件业务员编码", order = 21)
	private java.lang.Integer pickEmpCd;
	/** 揽件业务员名称*/
	@ExcelTitle(name = "揽件业务员名称", order = 22)
	private java.lang.String pickEmpNm;
	/** 始发省份编码*/
	@ExcelTitle(name = "始发省份编码", order = 23)
	private java.lang.Integer strtProvCd;
	/** 始发省份名称*/
	@ExcelTitle(name = "始发省份名称", order = 24)
	private java.lang.String strtProvNm;
	/** 始发城市编码*/
	@ExcelTitle(name = "始发城市编码", order = 25)
	private java.lang.Integer strtCityCd;
	/** 始发城市名称*/
	@ExcelTitle(name = "始发城市名称", order = 26)
	private java.lang.String strtCityNm;
	/** 始发区县编码*/
	@ExcelTitle(name = "始发区县编码", order = 27)
	private java.lang.Integer strtCntyCd;
	/** 始发区县名称*/
	@ExcelTitle(name = "始发区县名称", order = 28)
	private java.lang.String strtCntyNm;
	/** 到件时间*/
	@ExcelTitle(name = "到件时间", order = 29)
	private java.lang.String rchTm;
	/** 分发时间*/
	@ExcelTitle(name = "分发时间", order = 30)
	private java.lang.String distTm;
	/** 签收日期*/
	@ExcelTitle(name = "签收日期", order = 31)
	private java.lang.String cfmRcvTm;
	/** 签收人*/
	@ExcelTitle(name = "签收人", order = 32)
	private java.lang.String cfmRcvPers;
	/** 签收或目的网点编码*/
	@ExcelTitle(name = "签收或目的网点编码", order = 33)
	private java.lang.Integer destBrchCd;
	/** 签收或目的网点名称*/
	@ExcelTitle(name = "签收或目的网点名称", order = 34)
	private java.lang.String destBrchNm;
	/** 签收分部编码*/
	@ExcelTitle(name = "签收分部编码", order = 35)
	private java.lang.Integer cfmRcvDeviCd;
	/** 签收分部名称*/
	@ExcelTitle(name = "签收分部名称", order = 36)
	private java.lang.String cfmRcvDeviNm;
	/** 派件业务员编码*/
	@ExcelTitle(name = "派件业务员编码", order = 37)
	private java.lang.Integer delvEmpCd;
	/** 派件业务员名称*/
	@ExcelTitle(name = "派件业务员名称", order = 38)
	private java.lang.String delvEmpNm;
	/** 目的省份编码*/
	@ExcelTitle(name = "目的省份编码", order = 39)
	private java.lang.Integer destProvCd;
	/** 目的省份名称*/
	@ExcelTitle(name = "目的省份名称", order = 40)
	private java.lang.String destProvNm;
	/** 目的城市编码*/
	@ExcelTitle(name = "目的城市编码", order = 41)
	private java.lang.Integer destCityCd;
	/** 目的城市名称*/
	@ExcelTitle(name = "目的城市名称", order = 42)
	private java.lang.String destCityNm;
	/** 目的区县编码*/
	@ExcelTitle(name = "目的区县编码", order = 43)
	private java.lang.Integer destCntyCd;
	/** 目的区县名称*/
	@ExcelTitle(name = "目的区县名称", order = 44)
	private java.lang.String destCntyNm;
	/** 签收城市编码*/
	@ExcelTitle(name = "签收城市编码", order = 45)
	private java.lang.Integer cfmRcvCityCd;
	/** 签收城市名称*/
	@ExcelTitle(name = "签收城市名称", order = 46)
	private java.lang.String cfmRcvCityNm;
	/** 实际派送历时*/
	@ExcelTitle(name = "实际派送历时", order = 47)
	private java.lang.String actlDelvDur;
	/** 规定派送历时*/
	@ExcelTitle(name = "规定派送历时", order = 48)
	private java.lang.String stipDelvDur;
	/** 实际重量*/
	@ExcelTitle(name = "实际重量", order = 49)
	private BigDecimal actlWgt;
	/** 计泡重量*/
	@ExcelTitle(name = "计泡重量", order = 50)
	private BigDecimal volWgt;
	/** 揽件结算重量*/
	@ExcelTitle(name = "揽件结算重量", order = 51)
	private BigDecimal pickSetlWgt;
	/** 结算重量*/
	@ExcelTitle(name = "结算重量", order = 52)
	private BigDecimal setlWgt;
	/** 第11天结算重量*/
	@ExcelTitle(name = "第11天结算重量", order = 53)
	private BigDecimal setlWgtAdj;
	/** 结算重量类型*/
	@ExcelTitle(name = "结算重量类型", order = 54)
	private java.lang.String setlWgtTyp;
	/** 运输方式*/
	@ExcelTitle(name = "运输方式", order = 55)
	private java.lang.String transTyp;
	/** 一级费用类型编码*/
	@ExcelTitle(name = "一级费用类型编码", order = 56)
	private java.lang.Integer oneGrdExpCd;
	/** 一级费用类型名称*/
	@ExcelTitle(name = "一级费用类型名称", order = 57)
	private java.lang.String oneGrdExpNm;
	/** 二级费用类型编码*/
	@ExcelTitle(name = "二级费用类型编码", order = 58)
	private java.lang.Integer twoGrdExpCd;
	/** 二级费用类型名称*/
	@ExcelTitle(name = "二级费用类型名称", order = 59)
	private java.lang.String twoGrdExpNm;
	/** 费用金额*/
	@ExcelTitle(name = "费用金额", order = 60)
	private BigDecimal amnt;
	/** 小计(新-旧)*/
	@ExcelTitle(name = "小计(新-旧)", order = 61)
	private BigDecimal adjAmnt;
	/** 结算日期范围*/
	@ExcelTitle(name = "结算日期范围", order = 62)
	private java.lang.String setlDtRng;
	/** 是否直跑车*/
	@ExcelTitle(name = "是否直跑车", order = 63)
	private java.lang.String ifCar;
	/** 是否同城件*/
	@ExcelTitle(name = "是否同城件", order = 64)
	private java.lang.String ifShip;
	/** 广东报表整车称重费第1天*/
	@ExcelTitle(name = "广东报表整车称重费第1天", order = 65)
	private BigDecimal carExp;
	/** 广东报表整车称重费第11天*/
	@ExcelTitle(name = "广东报表整车称重费第11天", order = 66)
	private BigDecimal carExpAdj;
	/** 调整方式*/
	@ExcelTitle(name = "调整方式", order = 67)
	private java.lang.String adjTyp;
	/** 错误类别*/
	@ExcelTitle(name = "错误类别", order = 68)
	private java.lang.String errTyp;
	/** 重量*/
	@ExcelTitle(name = "重量", order = 69)
	private BigDecimal wgt;
	/** 录入时间*/
	@ExcelTitle(name = "录入时间", order = 70)
	private java.lang.String entrDt;
	/** 补贴类型*/
	@ExcelTitle(name = "补贴类型", order = 71)
	private java.lang.String subdTyp;
	/** 费率*/
	@ExcelTitle(name = "费率", order = 72)
	private BigDecimal rt;
	/** 揽录分是否一致*/
	@ExcelTitle(name = "揽录分是否一致", order = 73)
	private java.lang.String pickScorIfSame;
	
	
	public java.lang.String getSetlDt() {
		return setlDt;
	}
	public void setSetlDt(java.lang.String setlDt) {
		this.setlDt = setlDt;
	}
	public java.lang.String getSetlAdjDt() {
		return setlAdjDt;
	}
	public void setSetlAdjDt(java.lang.String setlAdjDt) {
		this.setlAdjDt = setlAdjDt;
	}
	public java.lang.Long getShipId() {
		return shipId;
	}
	public void setShipId(java.lang.Long shipId) {
		this.shipId = shipId;
	}
	public java.lang.Long getGrtrPkgId() {
		return grtrPkgId;
	}
	public void setGrtrPkgId(java.lang.Long grtrPkgId) {
		this.grtrPkgId = grtrPkgId;
	}
	public java.lang.Integer getSetlOrgCd() {
		return setlOrgCd;
	}
	public void setSetlOrgCd(java.lang.Integer setlOrgCd) {
		this.setlOrgCd = setlOrgCd;
	}
	public java.lang.String getSetlOrgNm() {
		return setlOrgNm;
	}
	public void setSetlOrgNm(java.lang.String setlOrgNm) {
		this.setlOrgNm = setlOrgNm;
	}
	public java.lang.Integer getSetlOrgSubCd() {
		return setlOrgSubCd;
	}
	public void setSetlOrgSubCd(java.lang.Integer setlOrgSubCd) {
		this.setlOrgSubCd = setlOrgSubCd;
	}
	public java.lang.String getSetlOrgSubNm() {
		return setlOrgSubNm;
	}
	public void setSetlOrgSubNm(java.lang.String setlOrgSubNm) {
		this.setlOrgSubNm = setlOrgSubNm;
	}
	public java.lang.String getUnitDt() {
		return unitDt;
	}
	public void setUnitDt(java.lang.String unitDt) {
		this.unitDt = unitDt;
	}
	public java.lang.Integer getUnitBrchCd() {
		return unitBrchCd;
	}
	public void setUnitBrchCd(java.lang.Integer unitBrchCd) {
		this.unitBrchCd = unitBrchCd;
	}
	public java.lang.String getUnitBrchNm() {
		return unitBrchNm;
	}
	public void setUnitBrchNm(java.lang.String unitBrchNm) {
		this.unitBrchNm = unitBrchNm;
	}
	public java.lang.Integer getCdAllocBrchCd() {
		return cdAllocBrchCd;
	}
	public void setCdAllocBrchCd(java.lang.Integer cdAllocBrchCd) {
		this.cdAllocBrchCd = cdAllocBrchCd;
	}
	public java.lang.String getCdAllocBrchNm() {
		return cdAllocBrchNm;
	}
	public void setCdAllocBrchNm(java.lang.String cdAllocBrchNm) {
		this.cdAllocBrchNm = cdAllocBrchNm;
	}
	public java.lang.String getAllocCustCd() {
		return allocCustCd;
	}
	public void setAllocCustCd(java.lang.String allocCustCd) {
		this.allocCustCd = allocCustCd;
	}
	public java.lang.String getAllocCustNm() {
		return allocCustNm;
	}
	public void setAllocCustNm(java.lang.String allocCustNm) {
		this.allocCustNm = allocCustNm;
	}
	public java.lang.String getPickDt() {
		return pickDt;
	}
	public void setPickDt(java.lang.String pickDt) {
		this.pickDt = pickDt;
	}
	public java.lang.Integer getPickBrchCd() {
		return pickBrchCd;
	}
	public void setPickBrchCd(java.lang.Integer pickBrchCd) {
		this.pickBrchCd = pickBrchCd;
	}
	public java.lang.String getPickBrchNm() {
		return pickBrchNm;
	}
	public void setPickBrchNm(java.lang.String pickBrchNm) {
		this.pickBrchNm = pickBrchNm;
	}
	public java.lang.Integer getPickDeviCd() {
		return pickDeviCd;
	}
	public void setPickDeviCd(java.lang.Integer pickDeviCd) {
		this.pickDeviCd = pickDeviCd;
	}
	public java.lang.String getPickDeviNm() {
		return pickDeviNm;
	}
	public void setPickDeviNm(java.lang.String pickDeviNm) {
		this.pickDeviNm = pickDeviNm;
	}
	public java.lang.Integer getPickEmpCd() {
		return pickEmpCd;
	}
	public void setPickEmpCd(java.lang.Integer pickEmpCd) {
		this.pickEmpCd = pickEmpCd;
	}
	public java.lang.String getPickEmpNm() {
		return pickEmpNm;
	}
	public void setPickEmpNm(java.lang.String pickEmpNm) {
		this.pickEmpNm = pickEmpNm;
	}
	public java.lang.Integer getStrtProvCd() {
		return strtProvCd;
	}
	public void setStrtProvCd(java.lang.Integer strtProvCd) {
		this.strtProvCd = strtProvCd;
	}
	public java.lang.String getStrtProvNm() {
		return strtProvNm;
	}
	public void setStrtProvNm(java.lang.String strtProvNm) {
		this.strtProvNm = strtProvNm;
	}
	public java.lang.Integer getStrtCityCd() {
		return strtCityCd;
	}
	public void setStrtCityCd(java.lang.Integer strtCityCd) {
		this.strtCityCd = strtCityCd;
	}
	public java.lang.String getStrtCityNm() {
		return strtCityNm;
	}
	public void setStrtCityNm(java.lang.String strtCityNm) {
		this.strtCityNm = strtCityNm;
	}
	public java.lang.Integer getStrtCntyCd() {
		return strtCntyCd;
	}
	public void setStrtCntyCd(java.lang.Integer strtCntyCd) {
		this.strtCntyCd = strtCntyCd;
	}
	public java.lang.String getStrtCntyNm() {
		return strtCntyNm;
	}
	public void setStrtCntyNm(java.lang.String strtCntyNm) {
		this.strtCntyNm = strtCntyNm;
	}
	public java.lang.String getRchTm() {
		return rchTm;
	}
	public void setRchTm(java.lang.String rchTm) {
		this.rchTm = rchTm;
	}
	public java.lang.String getDistTm() {
		return distTm;
	}
	public void setDistTm(java.lang.String distTm) {
		this.distTm = distTm;
	}
	public java.lang.String getCfmRcvTm() {
		return cfmRcvTm;
	}
	public void setCfmRcvTm(java.lang.String cfmRcvTm) {
		this.cfmRcvTm = cfmRcvTm;
	}
	public java.lang.String getCfmRcvPers() {
		return cfmRcvPers;
	}
	public void setCfmRcvPers(java.lang.String cfmRcvPers) {
		this.cfmRcvPers = cfmRcvPers;
	}
	public java.lang.Integer getDestBrchCd() {
		return destBrchCd;
	}
	public void setDestBrchCd(java.lang.Integer destBrchCd) {
		this.destBrchCd = destBrchCd;
	}
	public java.lang.String getDestBrchNm() {
		return destBrchNm;
	}
	public void setDestBrchNm(java.lang.String destBrchNm) {
		this.destBrchNm = destBrchNm;
	}
	public java.lang.Integer getCfmRcvDeviCd() {
		return cfmRcvDeviCd;
	}
	public void setCfmRcvDeviCd(java.lang.Integer cfmRcvDeviCd) {
		this.cfmRcvDeviCd = cfmRcvDeviCd;
	}
	public java.lang.String getCfmRcvDeviNm() {
		return cfmRcvDeviNm;
	}
	public void setCfmRcvDeviNm(java.lang.String cfmRcvDeviNm) {
		this.cfmRcvDeviNm = cfmRcvDeviNm;
	}
	public java.lang.Integer getDelvEmpCd() {
		return delvEmpCd;
	}
	public void setDelvEmpCd(java.lang.Integer delvEmpCd) {
		this.delvEmpCd = delvEmpCd;
	}
	public java.lang.String getDelvEmpNm() {
		return delvEmpNm;
	}
	public void setDelvEmpNm(java.lang.String delvEmpNm) {
		this.delvEmpNm = delvEmpNm;
	}
	public java.lang.Integer getDestProvCd() {
		return destProvCd;
	}
	public void setDestProvCd(java.lang.Integer destProvCd) {
		this.destProvCd = destProvCd;
	}
	public java.lang.String getDestProvNm() {
		return destProvNm;
	}
	public void setDestProvNm(java.lang.String destProvNm) {
		this.destProvNm = destProvNm;
	}
	public java.lang.Integer getDestCityCd() {
		return destCityCd;
	}
	public void setDestCityCd(java.lang.Integer destCityCd) {
		this.destCityCd = destCityCd;
	}
	public java.lang.String getDestCityNm() {
		return destCityNm;
	}
	public void setDestCityNm(java.lang.String destCityNm) {
		this.destCityNm = destCityNm;
	}
	public java.lang.Integer getDestCntyCd() {
		return destCntyCd;
	}
	public void setDestCntyCd(java.lang.Integer destCntyCd) {
		this.destCntyCd = destCntyCd;
	}
	public java.lang.String getDestCntyNm() {
		return destCntyNm;
	}
	public void setDestCntyNm(java.lang.String destCntyNm) {
		this.destCntyNm = destCntyNm;
	}
	public java.lang.Integer getCfmRcvCityCd() {
		return cfmRcvCityCd;
	}
	public void setCfmRcvCityCd(java.lang.Integer cfmRcvCityCd) {
		this.cfmRcvCityCd = cfmRcvCityCd;
	}
	public java.lang.String getCfmRcvCityNm() {
		return cfmRcvCityNm;
	}
	public void setCfmRcvCityNm(java.lang.String cfmRcvCityNm) {
		this.cfmRcvCityNm = cfmRcvCityNm;
	}
	public java.lang.String getActlDelvDur() {
		return actlDelvDur;
	}
	public void setActlDelvDur(java.lang.String actlDelvDur) {
		this.actlDelvDur = actlDelvDur;
	}
	public java.lang.String getStipDelvDur() {
		return stipDelvDur;
	}
	public void setStipDelvDur(java.lang.String stipDelvDur) {
		this.stipDelvDur = stipDelvDur;
	}
	public BigDecimal getActlWgt() {
		return actlWgt;
	}
	public void setActlWgt(BigDecimal actlWgt) {
		this.actlWgt = actlWgt;
	}
	public BigDecimal getVolWgt() {
		return volWgt;
	}
	public void setVolWgt(BigDecimal volWgt) {
		this.volWgt = volWgt;
	}
	public BigDecimal getPickSetlWgt() {
		return pickSetlWgt;
	}
	public void setPickSetlWgt(BigDecimal pickSetlWgt) {
		this.pickSetlWgt = pickSetlWgt;
	}
	public BigDecimal getSetlWgt() {
		return setlWgt;
	}
	public void setSetlWgt(BigDecimal setlWgt) {
		this.setlWgt = setlWgt;
	}
	public BigDecimal getSetlWgtAdj() {
		return setlWgtAdj;
	}
	public void setSetlWgtAdj(BigDecimal setlWgtAdj) {
		this.setlWgtAdj = setlWgtAdj;
	}
	public java.lang.String getSetlWgtTyp() {
		return setlWgtTyp;
	}
	public void setSetlWgtTyp(java.lang.String setlWgtTyp) {
		this.setlWgtTyp = setlWgtTyp;
	}
	public java.lang.String getTransTyp() {
		return transTyp;
	}
	public void setTransTyp(java.lang.String transTyp) {
		this.transTyp = transTyp;
	}
	public java.lang.Integer getOneGrdExpCd() {
		return oneGrdExpCd;
	}
	public void setOneGrdExpCd(java.lang.Integer oneGrdExpCd) {
		this.oneGrdExpCd = oneGrdExpCd;
	}
	public java.lang.String getOneGrdExpNm() {
		return oneGrdExpNm;
	}
	public void setOneGrdExpNm(java.lang.String oneGrdExpNm) {
		this.oneGrdExpNm = oneGrdExpNm;
	}
	public java.lang.Integer getTwoGrdExpCd() {
		return twoGrdExpCd;
	}
	public void setTwoGrdExpCd(java.lang.Integer twoGrdExpCd) {
		this.twoGrdExpCd = twoGrdExpCd;
	}
	public java.lang.String getTwoGrdExpNm() {
		return twoGrdExpNm;
	}
	public void setTwoGrdExpNm(java.lang.String twoGrdExpNm) {
		this.twoGrdExpNm = twoGrdExpNm;
	}
	public BigDecimal getAmnt() {
		return amnt;
	}
	public void setAmnt(BigDecimal amnt) {
		this.amnt = amnt;
	}
	public BigDecimal getAdjAmnt() {
		return adjAmnt;
	}
	public void setAdjAmnt(BigDecimal adjAmnt) {
		this.adjAmnt = adjAmnt;
	}
	public java.lang.String getSetlDtRng() {
		return setlDtRng;
	}
	public void setSetlDtRng(java.lang.String setlDtRng) {
		this.setlDtRng = setlDtRng;
	}
	public java.lang.String getIfCar() {
		return ifCar;
	}
	public void setIfCar(java.lang.String ifCar) {
		this.ifCar = ifCar;
	}
	public java.lang.String getIfShip() {
		return ifShip;
	}
	public void setIfShip(java.lang.String ifShip) {
		this.ifShip = ifShip;
	}
	public BigDecimal getCarExp() {
		return carExp;
	}
	public void setCarExp(BigDecimal carExp) {
		this.carExp = carExp;
	}
	public BigDecimal getCarExpAdj() {
		return carExpAdj;
	}
	public void setCarExpAdj(BigDecimal carExpAdj) {
		this.carExpAdj = carExpAdj;
	}
	public java.lang.String getAdjTyp() {
		return adjTyp;
	}
	public void setAdjTyp(java.lang.String adjTyp) {
		this.adjTyp = adjTyp;
	}
	public java.lang.String getErrTyp() {
		return errTyp;
	}
	public void setErrTyp(java.lang.String errTyp) {
		this.errTyp = errTyp;
	}
	public BigDecimal getWgt() {
		return wgt;
	}
	public void setWgt(BigDecimal wgt) {
		this.wgt = wgt;
	}
	public java.lang.String getEntrDt() {
		return entrDt;
	}
	public void setEntrDt(java.lang.String entrDt) {
		this.entrDt = entrDt;
	}
	public java.lang.String getSubdTyp() {
		return subdTyp;
	}
	public void setSubdTyp(java.lang.String subdTyp) {
		this.subdTyp = subdTyp;
	}
	public BigDecimal getRt() {
		return rt;
	}
	public void setRt(BigDecimal rt) {
		this.rt = rt;
	}
	public java.lang.String getPickScorIfSame() {
		return pickScorIfSame;
	}
	public void setPickScorIfSame(java.lang.String pickScorIfSame) {
		this.pickScorIfSame = pickScorIfSame;
	}
	
}

