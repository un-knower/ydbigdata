package com.yd.common.function.mdm.util;

import java.util.ArrayList;
import java.util.List;

import com.yd.common.data.CIPOperatorType;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.mdm.data.CityData;
import com.yd.common.function.mdm.data.OrgData;
import com.yd.common.function.mdm.data.OrgSearchData;
import com.yd.common.function.mdm.service.CIPMDMService;
import com.yd.common.runtime.CIPRuntimeConfigure;

public class GenerateSearchUtil {
	
	public static CIPReqCondition generateCompanyConditions(CIPMDMService cipmdmService, String fieldName, OrgSearchData orgSearchData) {
		if(fieldName==null){
			fieldName = "com_id";
		}
		String provinceId = orgSearchData.getProvinceId();
		String cityId = orgSearchData.getCityId(); 
		String orgCode = orgSearchData.getOrgCode();
		List<String> companyList = new ArrayList<String>();
		
		if(orgCode!=null && !orgCode.equals("")) {//如果公司编号参数有值，则忽视proviceId和cityId参数
			companyList.add(orgCode);
		}else if(cityId!=null && !cityId.equals("")) {//如果城市参数有值，则忽视ProvinceId参数。通过市获取县区，进而获取公司列表
			List<String> cityIds = new ArrayList<String>();
			cityIds.add(cityId);
			List<OrgData> orgList = cipmdmService.getOrgs4Citys(cityIds, CIPRuntimeConfigure.defaultOrgTypes);
			for(OrgData org: orgList) {
				companyList.add(String.valueOf(org.getOrgCode()));
			}
		
		}else if(provinceId!=null && !provinceId.equals("")) {//只有省参数，则通过省获取市，通过市获取县区，进而获取公司列表
			List<CityData> cities = cipmdmService.getCities(provinceId);
			List<String> cityIds = new ArrayList<String>();
			for(CityData city: cities) {
				cityIds.add(city.getCityId());
			}
			List<OrgData> orgList = cipmdmService.getOrgs4Citys(cityIds, CIPRuntimeConfigure.defaultOrgTypes);
			for(OrgData org: orgList) {
				companyList.add(String.valueOf(org.getOrgCode()));
			}
		}else {//查询所有
			companyList = null;
		}
		
		CIPReqCondition condition = new CIPReqCondition();
		if(companyList!=null) {
			condition.setFieldName(fieldName);
			condition.setOperator(CIPOperatorType.IN.code);
			condition.setValues(companyList.toArray(new String[companyList.size()]));
		}else if(orgCode != null && !orgCode.equals("")) {
			condition.setFieldName(fieldName);
			condition.setOperator(CIPOperatorType.EQUAL.code);
			condition.setLowValue(orgCode);
		}else{
			condition = null;
		}
		return condition;
	}
}

