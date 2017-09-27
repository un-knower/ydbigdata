package com.yd.common.function.mdm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.common.function.mdm.dao.CIPMDMDao;
import com.yd.common.function.mdm.data.BigAreaData;
import com.yd.common.function.mdm.data.CityData;
import com.yd.common.function.mdm.data.CountyData;
import com.yd.common.function.mdm.data.FeeItemData;
import com.yd.common.function.mdm.data.OrgData;
import com.yd.common.function.mdm.data.ProvinceData;
import com.yd.common.function.mdm.data.YdServerGsData;
import com.yd.common.function.mdm.service.CIPMDMService;

@Service("cipmdmService")
public class CIPMDMServiceImpl implements CIPMDMService {
	@Autowired
	private CIPMDMDao cipmdmDao;

	@Override
	public List<BigAreaData> getBigareas() {
		return cipmdmDao.queryBigarea();
	}
	
	@Override
	public List<ProvinceData> getProvinces()  {
		return cipmdmDao.queryProvinces();
	}

	@Override
	public List<CityData> getCities(String provinceId){
		return cipmdmDao.queryCities(provinceId);
	}

	@Override
	public List<OrgData> getOrgs(String cityId, List<String> orgTypes) {
		List<OrgData> orgList = new ArrayList<OrgData>();
		if(cityId == null) return orgList;
		List<CountyData> counties = cipmdmDao.queryCounties(cityId);//获取市下辖区县
		List<String> countyIds = new ArrayList<String>();
		for(CountyData county: counties) {
			countyIds.add(county.getCountyId());
		}
		orgList.addAll(cipmdmDao.queryOrgs(countyIds, orgTypes));//根据区县获取目标机构
		return orgList;
	}
	
	@Override
	public List<OrgData> getOrgs4Citys(List<String> cityIds, List<String> orgTypes) {
		List<OrgData> orgList = new ArrayList<OrgData>();
		if(cityIds == null) return orgList;
		List<String> countyIds = new ArrayList<String>();
		for(int i=0; i<cityIds.size(); i++) {
			String cityId = cityIds.get(i);
			List<CountyData> counties = this.cipmdmDao.queryCounties(cityId);//获取市下辖区县
			for(CountyData county: counties) {
				countyIds.add(county.getCountyId());
			}
		}
		orgList.addAll(cipmdmDao.queryOrgs(countyIds, orgTypes));//根据区县获取目标机构
		return orgList;
	}

	@Override
	public List<CountyData> getCounties(String cityId) {
		return cipmdmDao.queryCounties(cityId);
	}

	@Override
	public List<ProvinceData> getProvinces(List<String> provinceIds) {
		if(provinceIds==null || provinceIds.isEmpty()){
			return new ArrayList<>();
		}
		return cipmdmDao.queryProvinces(provinceIds);
	}

	@Override
	public List<OrgData> getOrgs(List<Integer> orgTypes) {
		return cipmdmDao.queryOrgs(orgTypes);
	}

	@Override
	public List<FeeItemData> getFeeItems() {
		return cipmdmDao.queryFeeItems();
	}

	@Override
	public List<ProvinceData> getProvinces(String bigarea) {
		return cipmdmDao.queryProvinces(bigarea);
	}

	@Override
	public List<OrgData> getOrgs4Prov(String provinceId, List<String> orgTypes) {
		return cipmdmDao.queryOrgsByProv(provinceId, orgTypes);
	}
	/**
	 * 从ydserver的公司表根据编码获得名称
	 */
	@Override
	public YdServerGsData getGsDataBy(String bm){
		return cipmdmDao.getGsDataBy(bm);
	}
	@Override
	public List<BigAreaData> getBigareas(List<String> bigareas) {
		return cipmdmDao.queryBigarea(bigareas);
	}
	
	@Override
	public List<ProvinceData> getProvinces(String bigarea, List<String> provinceIds) {
		return cipmdmDao.queryProvinces(bigarea, provinceIds);
	}

	@Override
	public List<OrgData> getOrgs4Prov(String provinceId, List<String> orgTypes, List<String> orgIds) {
		return cipmdmDao.queryOrgsByProv(provinceId, orgTypes, orgIds);
	}
}
