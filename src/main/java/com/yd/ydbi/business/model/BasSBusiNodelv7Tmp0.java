package com.yd.ydbi.business.model;

import java.util.Date;

public class BasSBusiNodelv7Tmp0 {
	private Integer id;

	private Date nodelvDt7;// 统计时间
	
	private Integer pickSite14;// 揽件站点
	
	private Integer pickDbct14;// 揽件分拨
	
	private Long pickCnt;// 揽件数量
	
	private Long nodelvCnt3;// 日内签收数量
	
	private Date nodelv7Week;// 昨日对应周一

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getNodelvDt7() {
		return nodelvDt7;
	}

	public void setNodelvDt7(Date nodelvDt7) {
		this.nodelvDt7 = nodelvDt7;
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

	public Long getNodelvCnt3() {
		return nodelvCnt3;
	}

	public void setNodelvCnt3(Long nodelvCnt3) {
		this.nodelvCnt3 = nodelvCnt3;
	}

	public Date getNodelv7Week() {
		return nodelv7Week;
	}

	public void setNodelv7Week(Date nodelv7Week) {
		this.nodelv7Week = nodelv7Week;
	}
}