package com.yd.common.function.mdm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yd.common.function.mdm.data.BigAreaData;
import com.yd.common.function.mdm.data.CityData;
import com.yd.common.function.mdm.data.CountyData;
import com.yd.common.function.mdm.data.FeeItemData;
import com.yd.common.function.mdm.data.OrgData;
import com.yd.common.function.mdm.data.ProvinceData;
import com.yd.common.function.mdm.data.YdServerGsData;

@Service
public interface CIPMDMService {
	
	//查询所有大区
	public List<BigAreaData> getBigareas();
	
	//查询所有省
	public List<ProvinceData> getProvinces();
	
	//查询给定省
	public List<ProvinceData> getProvinces(List<String> provinceIds);
	
	//查询指定省份的市
	public List<CityData> getCities(String provinceId);
	
	public List<OrgData> getOrgs(List<Integer> orgTypes);
	
	public List<OrgData> getOrgs(String cityId, List<String> orgTypes);
	
	//获取一个或多个城市的机构，orgTypes为空表示所有类型机构
	public List<OrgData> getOrgs4Citys(List<String> cityIds, List<String> orgTypes);

	public List<OrgData> getOrgs4Prov(String provinceId, List<String> orgTypes);

	public List<CountyData> getCounties(String cityId);

	public List<FeeItemData> getFeeItems();

	public List<ProvinceData> getProvinces(String bigarea);

	List<BigAreaData> getBigareas(List<String> bigareas);

	List<ProvinceData> getProvinces(String bigarea, List<String> provinceIds);

	List<OrgData> getOrgs4Prov(String provinceId, List<String> orgTypes,
			List<String> orgIds);
	
	/**
	 * 从ydserver的公司表根据编码获得名称
	 */
	public YdServerGsData getGsDataBy(String bm);
	
}
