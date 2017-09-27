package com.yd.common.function.admin.service.impl;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.common.cache.CIPCacheManager;
import com.yd.common.cache.CIPCacheService;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.exception.CIPRuntimeException;
import com.yd.common.exception.CIPServiceException;
import com.yd.common.function.admin.dao.CIP_admin_codesDao;
import com.yd.common.function.admin.dao.CIP_admin_domainDao;
import com.yd.common.function.admin.dao.CIP_admin_op_logDao;
import com.yd.common.function.admin.data.CIP_admin_codesData;
import com.yd.common.function.admin.data.CIP_admin_domainData;
import com.yd.common.function.admin.data.po.CIP_admin_codesPO;
import com.yd.common.function.admin.data.po.CIP_admin_op_logPO;
import com.yd.common.function.admin.data.vo.CIP_admin_codesVO;
import com.yd.common.function.admin.service.CIP_admin_codesService;
import com.yd.common.function.domain.data.CIPAdminCodesData;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntimeConfigure;
import com.yd.common.runtime.CIPRuntimeOperator;
import com.yd.common.utils.JSONUtils;

/**
 * <p>Service实现类</p>
 * <p>Class: CIP_admin_codesService</p>
 *
 * @since 2015-07-27 02:40:51
 */

@Service
public class CIP_admin_codesServiceImpl implements CIP_admin_codesService {
	
	@Autowired
	private CIP_admin_codesDao dataDao = null;
	@Autowired
	private CIP_admin_op_logDao opLogDao =null;
	@Autowired
	private CIP_admin_domainDao domainDao = null;
	/** 
	 * 修改数据
	 */
	@Override
	public void updateData(CIP_admin_codesVO vo, CIPRuntimeOperator operateInf){
		//TODO
		CIP_admin_codesPO po = vo.toPO();
		Object[] keys = po.getKeys();
		CIP_admin_codesPO po0 = dataDao.getSingle(keys);
		if( po0 == null ){
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
		}
		
		po.setUpdate_time(Timestamp.valueOf(operateInf.getOperate_tm()));
		po.setOperator(operateInf.getSubject_id());
		
		
		dataDao.update(po);
		
	}
	
	/** 
	 * 添加数据
	 */
	@Override
	public void addData(CIP_admin_codesVO vo, CIPRuntimeOperator operateInf){
		CIP_admin_codesPO po = vo.toPO();
		Object[] keys = po.getKeys();
		CIP_admin_codesPO po0 = dataDao.getSingle(keys);
		if( po0!= null ){
			throw new CIPServiceException(CIPErrorCode.ERROR_DUMPLICATE_PRIMARY_KEY);
		}
		
		//TODO　添加记录基本判断
		po.setCreate_time(Timestamp.valueOf(operateInf.getOperate_tm()));
		po.setOperator(operateInf.getSubject_id());
		
		dataDao.add(po);
	}
	
	/** 
	 * 删除数据
	 */
	@Override
	public void deleteData(Object[] keys, CIPRuntimeOperator operateInf){
		dataDao.delete(keys);
		
	}
	
	/** 
	 * 获取数据
	 */		
	@Override
	public CIP_admin_codesVO getData(Object[] keys ){
		CIP_admin_codesPO  po = dataDao.getSingle(keys);
		if(po == null)
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
			
		return po.toVO();
	}
	
	/** 
	 * 检索数据
	 */		
	@Override
	public List<CIP_admin_codesData> searchData(CIPPageInfo page, CIPReqCondition[] conditions){
		
		List<CIP_admin_codesData> datas = dataDao.searchData(page, conditions);
		return datas;
	}

	
	public List<Map<String, Object>> exportEntities(CIPPageInfo page,CIPReqCondition[] conditions,
			CIPRuntimeOperator operateInf, boolean xFirst){
		if(xFirst){
			CIP_admin_op_logPO log = new CIP_admin_op_logPO();
			log.setOp_seq_no(System.currentTimeMillis());
			log.setOp_table_id("cip_admin_codes");
			log.setOp_obj_id("");
			log.setOp_type("E");
			log.setRemark("批量导出数据");
			log.setOperator(operateInf.getSubject_id());
			log.setCreate_time(Timestamp.valueOf(operateInf.getOperate_tm()));
			opLogDao.add(log);
		}
		return dataDao.exportEntities(page, conditions, xFirst);
	}

	/**
	* TODO 将封装的编码信息存放到缓存中 .
	* @see com.yd.common.function.admin.service.CIP_admin_codesService#save(java.lang.String, java.util.Map)
	*/
	@Override
	public void save() {
		// 拿到所有的数据域信息
		List<CIP_admin_domainData> datas = domainDao.getData();
		// 获取系统id
		String sys_id = CIPRuntimeConfigure.cip_system_id;
		// 拼接redis中的主键
		String key = sys_id + "@domainCode";
		Map<String, CIPAdminCodesData> map = new HashMap<String, CIPAdminCodesData>();
		for (int i = 0; i < datas.size(); i++) {
			String id = datas.get(i).getDomain_id();
			String table_id = datas.get(i).getRef_table_id();
			// 通过数据域id 拿到所有的编码信息
			List<CIP_admin_codesData> vos = dataDao.getData(id);
			// 封装参数的实体类
			CIPAdminCodesData data = new CIPAdminCodesData();
			data.setDatas(vos);
			data.setRef_table_id(table_id);
			map.put(id, data);
		}
		saved(key, map);
	}
	//将封装的编码数据以K-V的格式 存储在缓存中
	private void saved(String key,Map<String, CIPAdminCodesData> map) {		
	    CIPCacheService	cacheService = CIPCacheManager.getCacheService();
		boolean setExpireTime = false;
		try {
			cacheService.set(key, JSONUtils.convertObject2Json(map).getBytes("utf-8"), setExpireTime);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new CIPRuntimeException(CIPErrorCode.ERROR_DATA_IS_MALFORM);
		}
		
		/*//连接远程缓存 redis
		 Jedis jedis = null;
		boolean isJedisConnectExceptionOccured = false;
		try {
			jedis = CIPRedisUtils.getJedisResource();
			jedis.set(key.getBytes("utf-8"), JSONUtils.convertObject2Json(map).getBytes("utf-8"));
		} catch (JedisConnectionException e) {
			isJedisConnectExceptionOccured = false;
			throw new CIPRuntimeException(CIPErrorCode.ERROR_HTTP_REQUEST_IS_NOT_AVAILABLE);
		} catch (Exception e) {
			throw new CIPRuntimeException(CIPErrorCode.ERROR_TECHNICAL_ERROR);
		} finally {
			CIPRedisUtils.returnJedisResource(jedis,isJedisConnectExceptionOccured);
		}*/
	}

	
}