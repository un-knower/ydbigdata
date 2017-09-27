package com.yd.ydbi.business.model;

import java.util.Date;

public class BasSBusiDelv3 {
	private Integer id;//
	
	private Date delvDt3;//昨日揽件时间
	
	private Integer pickDbct14;//昨日揽件站点
	
	private Long pickCnt;//昨日揽件量
	
	private Long delvCnt;//昨日3日内签收数量
	
	private Long pickCntYd;//上一日揽件量
	
	private Long delvCntYd;//上一日3日内签收数量
	
	private Long pickCntWd;//周同期揽件量
	
	private Long delvCntWd;//周同期3日内签收数量
	
	private Long pickCntW;//上周揽件量
	
	private Long delvCntW;//上周3日内签收数量
	
	private Long pickCntM;//本月1号到昨日的揽件量
	
	private Long delvCntM;//本月1号到昨日3日内签收数量

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDelvDt3() {
        return delvDt3;
    }

    public void setDelvDt3(Date delvDt3) {
        this.delvDt3 = delvDt3;
    }

    public Integer getPickDbct14() {
        return pickDbct14;
    }

    public void setPickDbct14(Integer pickDbct14) {
        this.pickDbct14 = pickDbct14;
    }

    public Long getPickCnt() {
        return pickCnt;
    }

    public void setPickCnt(Long pickCnt) {
        this.pickCnt = pickCnt;
    }

    public Long getDelvCnt() {
        return delvCnt;
    }

    public void setDelvCnt(Long delvCnt) {
        this.delvCnt = delvCnt;
    }

    public Long getPickCntYd() {
        return pickCntYd;
    }

    public void setPickCntYd(Long pickCntYd) {
        this.pickCntYd = pickCntYd;
    }

    public Long getDelvCntYd() {
        return delvCntYd;
    }

    public void setDelvCntYd(Long delvCntYd) {
        this.delvCntYd = delvCntYd;
    }

    public Long getPickCntWd() {
        return pickCntWd;
    }

    public void setPickCntWd(Long pickCntWd) {
        this.pickCntWd = pickCntWd;
    }

    public Long getDelvCntWd() {
        return delvCntWd;
    }

    public void setDelvCntWd(Long delvCntWd) {
        this.delvCntWd = delvCntWd;
    }

    public Long getPickCntW() {
        return pickCntW;
    }

    public void setPickCntW(Long pickCntW) {
        this.pickCntW = pickCntW;
    }

    public Long getDelvCntW() {
        return delvCntW;
    }

    public void setDelvCntW(Long delvCntW) {
        this.delvCntW = delvCntW;
    }

    public Long getPickCntM() {
        return pickCntM;
    }

    public void setPickCntM(Long pickCntM) {
        this.pickCntM = pickCntM;
    }

    public Long getDelvCntM() {
        return delvCntM;
    }

    public void setDelvCntM(Long delvCntM) {
        this.delvCntM = delvCntM;
    }
}