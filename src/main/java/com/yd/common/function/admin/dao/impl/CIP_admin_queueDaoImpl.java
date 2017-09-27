package com.yd.common.function.admin.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.yd.common.dao.IDaoImpl;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.exception.CIPDaoException;
import com.yd.common.function.admin.constants.CIPAdminConstants;
import com.yd.common.function.admin.dao.CIP_admin_queueDao;
import com.yd.common.function.admin.data.CIP_admin_queueData;
import com.yd.common.function.admin.data.mapper.CIP_admin_queueMapper;
import com.yd.common.function.admin.data.po.CIP_admin_queuePO;
import com.yd.common.runtime.CIPErrorCode;
/**
 * <p>Dao实现类</p>
 * <p>Class: CIP_admin_queue - 系统队列信息</p>
 *
 * @since 2015-08-18 05:02:08
 */
 
@Repository
public class CIP_admin_queueDaoImpl extends IDaoImpl<CIP_admin_queuePO> 
						implements CIP_admin_queueDao {
	
	private final BeanPropertyRowMapper<CIP_admin_queueData> dataRM = BeanPropertyRowMapper.newInstance(CIP_admin_queueData.class);
	
	public CIP_admin_queueDaoImpl() {
		super();
		this.tableName = "cip_admin_queue";
		this.tableKeys = new String[]{
						   "queue_id" ,
						   "queue_type" 
						};
	}
	
	@Override
	protected String generateWhere(Object... args) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<CIP_admin_queueData> searchData(CIPPageInfo page, CIPReqCondition[] conditions){
		try{
			List<Object> params = new ArrayList<Object>();
			String querySql = generateQuerySql(page, conditions, params);
			return jdbcTemplate.query(querySql.toString(), params.toArray(), dataRM);
		}catch(DataAccessException e) {
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
	
	private CIP_admin_queueMapper mapperRM = new CIP_admin_queueMapper();
	
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
			throw new CIPDaoException(CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
		}
	}

	private String popQueueSql = "select * from cip_admin_queue where queue_type = ? and queue_flag = ? limit ?";
	
	@Override
	public CIP_admin_queuePO popQueueEntry(String queueType) {
		try{
			List<CIP_admin_queuePO> datas = jdbcTemplate.query(popQueueSql, new Object[]{queueType, CIPAdminConstants.QUEUE_QUEUE_FLAG_UNPOP, 1}, rm);
			if(datas==null||datas.size() == 0 )
				return null;
			return datas.get(0);
		}catch(DataAccessException e) {
			throw new CIPDaoException(CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
		}
	}

	@Override
	public List<CIP_admin_queuePO> popQueueEntries(String queueType, int count) {
		try{
			List<CIP_admin_queuePO> datas = jdbcTemplate.query(popQueueSql, new Object[]{queueType, CIPAdminConstants.QUEUE_QUEUE_FLAG_UNPOP, count}, rm);
			return datas;
		}catch(DataAccessException e) {
			throw new CIPDaoException(CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
		}
	}

	private String deleteEntry = "delete from cip_admin_queue where queue_type = ? and queue_flag = ?";
	@Override
	public void deleteEntries(String queueType, String queuePop) {
		try{
			jdbcTemplate.update(deleteEntry, new Object[]{queueType, queuePop});
		}catch(DataAccessException e) {
			throw new CIPDaoException(CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
		}
	}

	private String deleteQueue = "delete from cip_admin_queue where queue_type = ?";
	
	@Override
	public void deleteEntries(String queueType) {
		try{
			jdbcTemplate.update(deleteQueue, new Object[]{queueType});
		}catch(DataAccessException e) {
			throw new CIPDaoException(CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
		}
	}
	
	
	
	
}


