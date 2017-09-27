package com.yd.common.function.admin.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.yd.common.dao.IDaoImpl;
import com.yd.common.data.CIPOperatorType;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.exception.CIPDaoException;
import com.yd.common.function.admin.dao.CIP_admin_resourceDao;
import com.yd.common.function.admin.data.CIP_admin_resourceData;
import com.yd.common.function.admin.data.mapper.CIP_admin_resourceMapper;
import com.yd.common.function.admin.data.po.CIP_admin_resourcePO;
import com.yd.common.runtime.CIPErrorCode;
/**
 * <p>Dao实现类</p>
 * <p>Class: CIP_admin_resource - 系统资源信息</p>
 *
 * @since 2015-07-27 02:40:52
 */
 
@Repository
public class CIP_admin_resourceDaoImpl extends IDaoImpl<CIP_admin_resourcePO> 
						implements CIP_admin_resourceDao {
	
	private final BeanPropertyRowMapper<CIP_admin_resourceData> dataRM = BeanPropertyRowMapper.newInstance(CIP_admin_resourceData.class);
	private Logger log = Logger.getLogger(getClass());
	
	public CIP_admin_resourceDaoImpl() {
		super();
		this.tableName = "cip_admin_resource";
		this.tableKeys = new String[]{
						   "resource_id" 
						};
	}
	
	@Override
	protected String generateWhere(Object... args) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<CIP_admin_resourceData> searchData(CIPPageInfo page, CIPReqCondition[] conditions){
		try{
			List<Object> params = new ArrayList<Object>();
			String querySql = generateQuerySql(page, conditions, params);
			return jdbcTemplate.query(querySql.toString(), params.toArray(), dataRM);
		}catch(DataAccessException e) {
			log.error(e);
			throw new CIPDaoException(CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
		}
	}
	
	private String generateQuerySql(CIPPageInfo pageUtil, CIPReqCondition[] conditions, List<Object> params){
		StringBuffer countSql = new StringBuffer().append("SELECT COUNT(1) from ").append(tableName).append("_v");
		StringBuffer querySql = new StringBuffer().append("SELECT * from ").append(tableName).append("_v");
		String whereSql = generateWhere(conditions, params);
		querySql.append(whereSql);
		countSql.append(whereSql);

		int total = jdbcTemplate.queryForObject(countSql.toString(), params.toArray(), Integer.class);
		pageUtil.setTotal(total);
		
		querySql = getSqlPage(querySql, pageUtil);
		
		
		return querySql.toString();
	}
	
	private CIP_admin_resourceMapper mapperRM = new CIP_admin_resourceMapper();
	
	public List<Map<String, Object>> exportEntities(CIPPageInfo pageUtil, CIPReqCondition[] conditions, boolean xFirst){
		List<Object> params = new ArrayList<Object>();
		StringBuffer countSql = new StringBuffer().append("SELECT COUNT(1) from ").append(tableName).append("_v");
		StringBuffer querySql = new StringBuffer().append("SELECT * from ").append(tableName).append("_v");
		String whereSql = generateWhere(conditions, params);
		querySql.append(whereSql);
		countSql.append(whereSql);
		
		if(xFirst){
			int total = jdbcTemplate.queryForObject(countSql.toString(), params.toArray(), Integer.class);
			pageUtil.setTotal(total);
		}
		
		querySql = getSqlPage(querySql, pageUtil);
		
		try{
			return jdbcTemplate.query(querySql.toString(), params.toArray(), mapperRM);
		}catch(DataAccessException e) {
			log.error(e);
			throw new CIPDaoException(CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
		}
	}

	@Override
	public List<CIP_admin_resourceData> queryRoleNotAssignresource(
			String rootResId, CIPReqCondition[] conditions) {
		String querySql = "SELECT r.*,resource_type.code_name AS resource_type_name FROM cip_admin_resource r LEFT JOIN cip_admin_codes resource_type ON r.resource_type = resource_type.code_type AND resource_type.domain_id = 'resource_type' WHERE NOT EXISTS (SELECT null FROM cip_admin_resource_2_resource rr WHERE root_node_id=? and r.resource_id=rr.res_node_id)";
		List<Object> params = new ArrayList<Object>();
		params.add(rootResId);
		StringBuffer whereSql = new StringBuffer();
		if(conditions!=null && conditions.length>0) {
			int operator;
			for(int i=0 ; i<conditions.length; i++){
				operator = conditions[i].getOperator();
				if(operator == CIPOperatorType.EQUAL.code){
					whereSql.append(" and ").append(conditions[i].getFieldName()).append(" = ? ");
					params.add(conditions[i].getLowValue());
				}
				else if(operator == CIPOperatorType.LIKE.code){
					whereSql.append(" and ").append(conditions[i].getFieldName()).append(" LIKE ? ");
					params.add(conditions[i].getLowValue());
				}
				else if(operator == CIPOperatorType.GREAT_EQUAL.code){
					whereSql.append(" and ").append(conditions[i].getFieldName()).append(" >= ? ");
					params.add(conditions[i].getLowValue());
				}
				else if(operator == CIPOperatorType.GREAT_THAN.code){
					whereSql.append(" and ").append(conditions[i].getFieldName()).append(" > ? ");
					params.add(conditions[i].getLowValue());
				}
				else if(operator == CIPOperatorType.LESS_EQUAL.code){
					whereSql.append(" and ").append(conditions[i].getFieldName()).append(" <= ? ");
					params.add(conditions[i].getHighValue());
				}
				else if(operator == CIPOperatorType.LESS_THAN.code){
					whereSql.append(" and ").append(conditions[i].getFieldName()).append(" < ? ");
					params.add(conditions[i].getHighValue());
				}
				else if(operator == CIPOperatorType.BETWEEN.code){
					whereSql.append(" and ").append(conditions[i].getFieldName()).append(" BETWEEN ? and ? ");
					params.add(conditions[i].getLowValue());
					params.add(conditions[i].getHighValue());
				}
				else if(operator == CIPOperatorType.IN.code){
					whereSql.append(" and ").append(conditions[i].getFieldName()).append(" IN ( ");
					String[] values = conditions[i].getValues();
					for(int j=0 ; j < values.length; j++ ){
						if( j < values.length - 1)
							whereSql.append("?, ");
						else 
							whereSql.append("? ) ");
						params.add(values[j]);
					}
				}
			}
			querySql += whereSql;
		}
		return jdbcTemplate.query(querySql, params.toArray(), dataRM);
	}

	@Override
	public CIP_admin_resourceData searchNameById(String ID) {
		String sql = "select * from cip_admin_resource where resource_id = '"+ID+"'";
		CIP_admin_resourceData data = jdbcTemplate.queryForObject(sql, dataRM);
		return data;
	}

	
}


