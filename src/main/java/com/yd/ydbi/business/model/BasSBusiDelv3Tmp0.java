package com.yd.ydbi.business.model;

import java.util.Date;

public class BasSBusiDelv3Tmp0 {
	private Integer id;

	private Date delvDt3;// 统计时间

	private Integer pickSite14;// 揽件网点

	private Integer pickDbct14;// 揽件分拨

	private Long pickCnt;// 揽件数量

	private Long delvCnt;// 3日内签收数量

	private Date delv3Week;// 昨日对应周一

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

	public Integer getPickSite14() {
		return pickSite14;
	}

	public void setPickSite14(Integer pickSite14) {
		this.pickSite14 = pickSite14;
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

	public Date getDelv3Week() {
		return delv3Week;
	}

	public void setDelv3Week(Date delv3Week) {
		this.delv3Week = delv3Week;
	}
}