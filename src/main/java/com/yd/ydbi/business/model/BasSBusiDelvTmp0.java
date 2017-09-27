package com.yd.ydbi.business.model;

import java.util.Date;

public class BasSBusiDelvTmp0 {
    private Integer id;

	private Date delvDt10;// 昨日签收时间
	
	private Integer delvDbct10;// 昨日签收分拨
	
	private Integer delvSite10;// 昨日签收网点
	
	private Long delvCnt;// 昨日签收量
	
	private Integer delvQuart;// 昨日签收对应季度
	
    private Date delvWeek;// 昨日对应周一

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

    public Integer getDelvSite10() {
        return delvSite10;
    }

    public void setDelvSite10(Integer delvSite10) {
        this.delvSite10 = delvSite10;
    }

    public Long getDelvCnt() {
        return delvCnt;
    }

    public void setDelvCnt(Long delvCnt) {
        this.delvCnt = delvCnt;
    }

    public Integer getDelvQuart() {
        return delvQuart;
    }

    public void setDelvQuart(Integer delvQuart) {
        this.delvQuart = delvQuart;
    }

    public Date getDelvWeek() {
        return delvWeek;
    }

    public void setDelvWeek(Date delvWeek) {
        this.delvWeek = delvWeek;
    }
}