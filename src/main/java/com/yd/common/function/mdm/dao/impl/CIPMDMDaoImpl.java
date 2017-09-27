package com.yd.common.function.mdm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yd.common.exception.CIPDaoException;
import com.yd.common.function.mdm.dao.CIPMDMDao;
import com.yd.common.function.mdm.data.BigAreaData;
import com.yd.common.function.mdm.data.CityData;
import com.yd.common.function.mdm.data.CountyData;
import com.yd.common.function.mdm.data.FeeItemData;
import com.yd.common.function.mdm.data.OrgData;
import com.yd.common.function.mdm.data.ProvinceData;
import com.yd.common.function.mdm.data.YdServerGsData;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntimeConfigure;

@Repository
public class CIPMDMDaoImpl implements CIPMDMDao{
	
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	String sql_orgs = "SELECT t.bm as orgCode,t.mc as orgName " +
			"FROM " + CIPRuntimeConfigure.dbSchema_ydserver + "gs t ";
	private final String sql_provinces = "SELECT ProvinceID as provinceId, ProvinceName as provinceName FROM " + CIPRuntimeConfigure.dbSchema_ydserver + "province";
	private final String sql_cities = "SELECT CityID as cityId, CityName FROM " + CIPRuntimeConfigure.dbSchema_ydserver + "city WHERE ProvinceID=?";
	private final String sql_counties = "SELECT CountyID as countyId, CountyName FROM " + CIPRuntimeConfigure.dbSchema_ydserver + "county WHERE CityID=?";
	private final String sql_orgs_prov = "select t.bm as orgCode,t.mc as orgName from ydserver.gs t LEFT JOIN ydserver.county c on c.CountyID=t.szd LEFT JOIN ydserver.city c2 on c2.CityID=c.CityID LEFT JOIN ydserver.province p on p.ProvinceID=c2.ProvinceID where p.ProvinceID=?";
	private final String sql_bigarea = "select bigarea as areaName from " + CIPRuntimeConfigure.dbSchema_ydserver + "province GROUP BY bigarea";
	
	private final BeanPropertyRowMapper<BigAreaData> bigAreaRM = BeanPropertyRowMapper.newInstance(BigAreaData.class);
	private final BeanPropertyRowMapper<ProvinceData> provinceRM = BeanPropertyRowMapper.newInstance(ProvinceData.class);
	private final BeanPropertyRowMapper<CityData> cityRM = BeanPropertyRowMapper.newInstance(CityData.class);
	private final BeanPropertyRowMapper<CountyData> countyRM = BeanPropertyRowMapper.newInstance(CountyData.class);
	private final BeanPropertyRowMapper<OrgData> orgRM = BeanPropertyRowMapper.newInstance(OrgData.class);
	private final BeanPropertyRowMapper<FeeItemData> feeItemRM = BeanPropertyRowMapper.newInstance(FeeItemData.class);
	private final BeanPropertyRowMapper<YdServerGsData> gsDataRM = BeanPropertyRowMapper.newInstance(YdServerGsData.class);
	private Logger log = Logger.getLogger(getClass());
	
	@Override
	public List<BigAreaData> queryBigarea() throws CIPDaoException {
		String querySql = "SELECT BIGAREAID as areaId,bigarea as areaName from ydserver.region where BIGAREAID IS NOT NULL GROUP BY BIGAREAID";
		try{
			return this.jdbcTemplate.query(querySql, bigAreaRM);
		}catch(DataAccessException e) {
			log.error(e);
			throw new CIPDaoException(CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
		}
	}
	
	@Override
	public List<ProvinceData> queryProvinces() throws CIPDaoException {
		try{
			return this.jdbcTemplate.query(sql_provinces, provinceRM);
		}catch(DataAccessException e) {
			log.error(e);
			throw new CIPDaoException(CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
		}
	}

	@Override
	public List<CityData> queryCities(String provinceId) throws CIPDaoException {
		try{
			return this.jdbcTemplate.query(sql_cities, new Object[]{provinceId}, cityRM);
		}catch(DataAccessException e) {
			log.error(e);
			throw new CIPDaoException(CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
		}
	}

	@Override
	public List<CountyData> queryCounties(String cityId) throws CIPDaoException {
		try{
			return this.jdbcTemplate.query(sql_counties, new Object[]{cityId}, countyRM);
		}catch(DataAccessException e) {
			log.error(e);
			throw new CIPDaoException(CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
		}
	}

	@Override
	public List<OrgData> queryOrgs(List<String> countyIds,
			List<String> orgTypes) throws CIPDaoException {
		String sql = sql_orgs + "WHERE t.sjdw !=0 ";//过滤掉sjdw=0的非机构类型的乱七八糟数据;
		if(countyIds == null || countyIds.isEmpty()) {
			return new ArrayList<OrgData>();
		}else {
			sql += "AND ";
			sql += "t.szd IN(";
			for(int i=0; i<countyIds.size(); i++) {
				sql += "'" + countyIds.get(i) + "',";
			}
			sql += ") ";
			
			if(orgTypes!=null && orgTypes.size()>0) {
				sql += "AND ";
				sql += "t.lb IN(";
				for(int i=0; i<orgTypes.size(); i++) {
					sql += orgTypes.get(i) + ",";
				}
				sql += ") ";
			}
			sql = sql.replace(",)", ")");
			try{
				return this.jdbcTemplate.query(sql, orgRM);
			}catch(DataAccessException e) {
				log.error(e);
				throw new CIPDaoException(CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
			}
		}
	}
	
	@Override
	public List<OrgData> queryOrgsByProvs(List<String> provinceIds,
			List<String> orgTypes) throws CIPDaoException {
		String sql = sql_orgs + "WHERE t.sjdw !=0 ";//过滤掉sjdw=0的非机构类型的乱七八糟数据;
		if(provinceIds == null || provinceIds.isEmpty()) {
			return new ArrayList<OrgData>();
		}else {
			sql += "AND ";
			sql += "t.sheng IN(";
			for(int i=0; i<provinceIds.size(); i++) {
				sql += "'" + provinceIds.get(i) + "',";
			}
			sql += ") ";
			
			if(orgTypes!=null && orgTypes.size()>0) {
				sql += "AND ";
				sql += "t.lb IN(";
				for(int i=0; i<orgTypes.size(); i++) {
					sql += orgTypes.get(i) + ",";
				}
				sql += ") ";
			}
			sql = sql.replace(",)", ")");
			try{
				return this.jdbcTemplate.query(sql, orgRM);
			}catch(DataAccessException e) {
				log.error(e);
				throw new CIPDaoException(CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
			}
		}
	}

	@Override
	public List<OrgData> queryOrgsByCitys(List<String> cityIds,
			List<String> orgTypes) throws CIPDaoException {
		String sql = sql_orgs + "WHERE t.sjdw !=0 ";//过滤掉sjdw=0的非机构类型的乱七八糟数据;
		if(cityIds == null || cityIds.isEmpty()) {
			return new ArrayList<OrgData>();
		}else {
			sql += "AND ";
			sql += "t.shi IN(";
			for(int i=0; i<cityIds.size(); i++) {
				sql += "'" + cityIds.get(i) + "',";
			}
			sql += ") ";
			
			if(orgTypes!=null && orgTypes.size()>0) {
				sql += "AND ";
				sql += "t.lb IN(";
				for(int i=0; i<orgTypes.size(); i++) {
					sql += orgTypes.get(i) + ",";
				}
				sql += ") ";
			}
			sql = sql.replace(",)", ")");
			try{
				return this.jdbcTemplate.query(sql, orgRM);
			}catch(DataAccessException e) {
				log.error(e);
				throw new CIPDaoException(CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
			}
		}
	}

	@Override
	public List<ProvinceData> queryProvinces(List<String> provinceIds)
			throws CIPDaoException {
		StringBuffer sb = new StringBuffer(sql_provinces);
		if(provinceIds!=null && !provinceIds.isEmpty()){
			sb.append(" WHERE ProvinceID IN (");
			for(String provinceId : provinceIds){
				sb.append(provinceId).append(",");
			}
			sb.append(")");
		}
		String sql = sb.toString().replace(",)", ")");
		try{
			return this.jdbcTemplate.query(sql, provinceRM);
		}catch(DataAccessException e) {
			log.error(e);
			throw new CIPDaoException(CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
		}
	}

	@Override
	public List<OrgData> queryOrgs(List<Integer> orgTypes)
			throws CIPDaoException {
		if(orgTypes == null || orgTypes.size() ==0) return new ArrayList<OrgData>();
		String sql = sql_orgs + "WHERE t.sjdw !=0 ";//过滤掉sjdw=0的非机构类型的乱七八糟数据;
		sql += "AND t.lb IN(";
		for(int i=0; i<orgTypes.size(); i++) {
			sql += orgTypes.get(i) + ",";
		}
		sql += ") ";
		sql = sql.replace(",)", ")");
		try{
			return this.jdbcTemplate.query(sql, orgRM);
		}catch(DataAccessException e) {
			log.error(e);
			throw new CIPDaoException(CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
		}
	}

	@Override
	public List<FeeItemData> queryFeeItems() throws CIPDaoException {
		String querySql = "SELECT t.i_id AS fee_code, t.i_name AS fee_name from ydserver.base_payitem t";
		return this.jdbcTemplate.query(querySql, feeItemRM);
	}

	@Override
	public List<ProvinceData> queryProvinces(String bigarea)
			throws CIPDaoException {
//		String querySql = sql_provinces + " where bigarea=?";
		String querySql = "SELECT provinceid as provinceId,provincename as provinceName from ydserver.region where bigareaId=? GROUP BY provinceid";
		try{
			return this.jdbcTemplate.query(querySql, new Object[]{bigarea}, provinceRM);
		}catch(DataAccessException e) {
			log.error(e);
			throw new CIPDaoException(CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
		}
	}

	@Override
	public List<OrgData> queryOrgsByProv(String provinceId,
			List<String> orgTypes) throws CIPDaoException {
		if(orgTypes == null || orgTypes.size() ==0) return new ArrayList<OrgData>();
		String sql = sql_orgs_prov + " AND t.sjdw !=0 ";//过滤掉sjdw=0的非机构类型的乱七八糟数据;
		sql += "AND t.lb IN(";
		for(int i=0; i<orgTypes.size(); i++) {
			sql += orgTypes.get(i) + ",";
		}
		sql += ") ";
		sql = sql.replace(",)", ")");
		try{
			return this.jdbcTemplate.query(sql, new Object[]{provinceId}, orgRM);
		}catch(DataAccessException e) {
			log.error(e);
			throw new CIPDaoException(CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
		}
	}
	/**
	 * 从ydserver的公司表根据编码获得名称
	 */
	@Override
	public YdServerGsData getGsDataBy(String bm){
		String querySql = "SELECT gs.bm as bm,gs.mc as mc from ydserver.gs gs where gs.bm = ?";
		try{
			return this.jdbcTemplate.queryForObject(querySql, new Object[]{bm}, gsDataRM);
		}catch(DataAccessException e) {
			return null;
//			log.error(e);
//			throw new CIPDaoException(CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
		}
	}
	@Override
	public List<BigAreaData> queryBigarea(List<String> bigareas)
			throws CIPDaoException {
		String querySql = "SELECT BIGAREAID as areaId,bigarea as areaName from ydserver.region where BIGAREAID IS NOT NULL";
		if(bigareas!=null && !bigareas.isEmpty()){
			querySql += " and bigarea in(";
			for(String bigarea : bigareas){
				querySql += "'" + bigarea + "',";
			}
			querySql += ") ";
			querySql = querySql.replace(",)", ")");
		}
		querySql += " GROUP BY BIGAREAID";
		try{
			return this.jdbcTemplate.query(querySql, bigAreaRM);
		}catch(DataAccessException e) {
			log.error(e);
			throw new CIPDaoException(CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
		}
	}

	@Override
	public List<ProvinceData> queryProvinces(String bigarea,
			List<String> provinceIds) throws CIPDaoException {
		String querySql = "SELECT provinceid as provinceId,provincename as provinceName from ydserver.region where BIGAREAID=?";
		if(provinceIds!=null && !provinceIds.isEmpty()){
			querySql += " and provinceId in(";
			for(String provinceId : provinceIds){
				querySql += provinceId + ",";
			}
			querySql += ") ";
			querySql = querySql.replace(",)", ")");
		}
		querySql += " GROUP BY provinceid";
		try{
			return this.jdbcTemplate.query(querySql, new Object[]{bigarea}, provinceRM);
		}catch(DataAccessException e) {
			log.error(e);
			throw new CIPDaoException(CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
		}
	}

	@Override
	public List<OrgData> queryOrgsByProv(String provinceId,
			List<String> orgTypes, List<String> orgIds) throws CIPDaoException {
		if(orgTypes == null || orgTypes.size() ==0) return new ArrayList<OrgData>();
		String sql = sql_orgs_prov + " AND t.sjdw !=0 ";//过滤掉sjdw=0的非机构类型的乱七八糟数据;
		sql += "AND t.lb IN(";
		for(int i=0; i<orgTypes.size(); i++) {
			sql += orgTypes.get(i) + ",";
		}
		sql += ") ";
		sql = sql.replace(",)", ")");
		if(orgIds!=null && !orgIds.isEmpty()){
			sql += " and bm in(";
			for(String orgId : orgIds){
				sql += orgId + ",";
			}
			sql += ") ";
			sql = sql.replace(",)", ")");
		}
		try{
			return this.jdbcTemplate.query(sql, new Object[]{provinceId}, orgRM);
		}catch(DataAccessException e) {
			log.error(e);
			throw new CIPDaoException(CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
		}
	}

}
