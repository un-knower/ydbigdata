package com.yd.ydbi.cainiao.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.yd.ydbi.utils.excel.ExcelTitle;

public class CaiNiaoWeek implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@ExcelTitle(name="线路名称",order=1)
	private String lnNm;
	@ExcelTitle(name="第一日",order=2)
	private Integer firstDay;
	@ExcelTitle(name="第二日",order=3)
	private Integer secondDay;
	@ExcelTitle(name="第三日",order=4)
	private Integer thirdDay;
	@ExcelTitle(name="第四日",order=5)
	private Integer fourthDay;
	@ExcelTitle(name="第五日",order=6)
	private Integer fifthDay;
	@ExcelTitle(name="第六日",order=7)
	private Integer sixthDay;
	@ExcelTitle(name="第七日",order=8)
	private Integer seventhDay;
	@ExcelTitle(name="线路单量",order=9)
	private Integer lineQuantity;
	@ExcelTitle(name="排名状态",order=10)
	private BigDecimal rankState;
	
	private String startPlace;		//始发地
	private String endPlace;		//目的地
	
	public String getLnNm() {
		return lnNm;
	}
	public void setLnNm(String lnNm) {
		this.lnNm = lnNm;
	}
	public Integer getFirstDay() {
		return firstDay;
	}
	public void setFirstDay(Integer firstDay) {
		this.firstDay = firstDay;
	}
	public Integer getSecondDay() {
		return secondDay;
	}
	public void setSecondDay(Integer secondDay) {
		this.secondDay = secondDay;
	}
	public Integer getThirdDay() {
		return thirdDay;
	}
	public void setThirdDay(Integer thirdDay) {
		this.thirdDay = thirdDay;
	}
	public Integer getFourthDay() {
		return fourthDay;
	}
	public void setFourthDay(Integer fourthDay) {
		this.fourthDay = fourthDay;
	}
	public Integer getFifthDay() {
		return fifthDay;
	}
	public void setFifthDay(Integer fifthDay) {
		this.fifthDay = fifthDay;
	}
	public Integer getSixthDay() {
		return sixthDay;
	}
	public void setSixthDay(Integer sixthDay) {
		this.sixthDay = sixthDay;
	}
	public Integer getSeventhDay() {
		return seventhDay;
	}
	public void setSeventhDay(Integer seventhDay) {
		this.seventhDay = seventhDay;
	}
	public Integer getLineQuantity() {
		return lineQuantity;
	}
	public void setLineQuantity(Integer lineQuantity) {
		this.lineQuantity = lineQuantity;
	}
	public BigDecimal getRankState() {
		return rankState;
	}
	public void setRankState(BigDecimal rankState) {
		this.rankState = rankState;
	}
	public String getStartPlace() {
		return startPlace;
	}
	public void setStartPlace(String startPlace) {
		this.startPlace = startPlace;
	}
	public String getEndPlace() {
		return endPlace;
	}
	public void setEndPlace(String endPlace) {
		this.endPlace = endPlace;
	}
	@Override
	public String toString() {
		return "CaiNiaoWeek [lnNm=" + lnNm + ", firstDay=" + firstDay + ", secondDay=" + secondDay + ", thirdDay="
				+ thirdDay + ", fourthDay=" + fourthDay + ", fifthDay=" + fifthDay + ", sixthDay=" + sixthDay
				+ ", seventhDay=" + seventhDay + ", lineQuantity=" + lineQuantity + ", rankState=" + rankState
				+ ", startPlace=" + startPlace + ", endPlace=" + endPlace + "]";
	}
	

}
