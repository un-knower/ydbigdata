package com.yd.common.function.mdm.data;

public class OrgSearchData {
	String provinceId;
	String cityId;
	String orgCode;
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public OrgSearchData(String provinceId, String cityId, String orgCode) {
		super();
		this.provinceId = provinceId;
		this.cityId = cityId;
		this.orgCode = orgCode;
	}
	public OrgSearchData() {
		super();
	}
	
}
