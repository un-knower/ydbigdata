package com.yd.common.function.mdm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yd.common.exception.CIPDaoException;
import com.yd.common.function.mdm.data.BigAreaData;
import com.yd.common.function.mdm.data.CityData;
import com.yd.common.function.mdm.data.CountyData;
import com.yd.common.function.mdm.data.FeeItemData;
import com.yd.common.function.mdm.data.OrgData;
import com.yd.common.function.mdm.data.ProvinceData;
import com.yd.common.function.mdm.data.YdServerGsData;

@Repository
public interface CIPMDMDao {
	public List<BigAreaData> queryBigarea() throws CIPDaoException;
	
	public List<ProvinceData> queryProvinces() throws CIPDaoException;
	
	public List<ProvinceData> queryProvinces(List<String> provinceIds) throws CIPDaoException;
	
	public List<CityData> queryCities(String provinceId) throws CIPDaoException;
	
	public List<CountyData> queryCounties(String cityId) throws CIPDaoException;
	
	//根据区县列表查公司
	public List<OrgData> queryOrgs(List<String> countyIds, List<String> orgTypes) throws CIPDaoException;

	//根据机构类型查询公司
	public List<OrgData> queryOrgs(List<Integer> orgTypes) throws CIPDaoException;
	
	public List<OrgData> queryOrgsByProvs(List<String> provinceIds, List<String> orgTypes) throws CIPDaoException;

	public List<OrgData> queryOrgsByCitys(List<String> cityIds, List<String> orgTypes) throws CIPDaoException;

	public List<OrgData> queryOrgsByProv(String provinceId, List<String> orgTypes) throws CIPDaoException;
	
	public List<FeeItemData> queryFeeItems() throws CIPDaoException;

	public List<ProvinceData> queryProvinces(String bigarea) throws CIPDaoException;
	/**
	 * 从ydserver的公司表根据编码获得名称
	 */
	public YdServerGsData getGsDataBy(String bm);

	public List<BigAreaData> queryBigarea(List<String> bigareas) throws CIPDaoException;

	public List<ProvinceData> queryProvinces(String bigarea,
			List<String> provinceIds) throws CIPDaoException;

	public List<OrgData> queryOrgsByProv(String provinceId,
			List<String> orgTypes, List<String> orgIds) throws CIPDaoException;

}
