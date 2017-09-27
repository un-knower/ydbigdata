package com.yd.common.function.admin.service.impl;

import java.io.UnsupportedEncodingException;
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
import com.yd.common.function.admin.dao.CIP_admin_commonqueryDao;
import com.yd.common.function.admin.data.CIP_admin_commonqueryData;
import com.yd.common.function.admin.data.po.CIP_admin_commonqueryPO;
import com.yd.common.function.admin.data.vo.CIP_admin_commonqueryVO;
import com.yd.common.function.admin.service.CIP_admin_commonqueryService;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntimeConfigure;
import com.yd.common.runtime.CIPRuntimeOperator;
import com.yd.common.utils.JSONUtils;

/**
 * <p>Service实现类</p>
 * <p>Class: CIP_cadmin_commonqueryService</p>
 *
 * @since 2016-08-04 01:17:07
 */

@Service(value="CIP_cadmin_commonqueryService")
public class CIP_admin_commonqueryServiceImpl implements CIP_admin_commonqueryService {
	
	@Autowired
	private CIP_admin_commonqueryDao dataDao = null;

	/** 
	 * 修改数据
	 */
	@Override
	public void updateData(CIP_admin_commonqueryVO vo, CIPRuntimeOperator operateInf){
		//TODO
		CIP_admin_commonqueryPO po = vo.toPO();
		Object[] keys = po.getKeys();
		CIP_admin_commonqueryPO po0 = dataDao.getSingle(keys);
		if( po0 == null ){
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
		}
		
		
		dataDao.update(po);
		
	}
	
	/** 
	 * 添加数据
	 */
	@Override
	public void addData(CIP_admin_commonqueryVO vo, CIPRuntimeOperator operateInf){
		CIP_admin_commonqueryPO po = vo.toPO();
		Object[] keys = po.getKeys();
		CIP_admin_commonqueryPO po0 = dataDao.getSingle(keys);
		if( po0!= null ){
			throw new CIPServiceException(CIPErrorCode.ERROR_DUMPLICATE_PRIMARY_KEY);
		}
		
		//TODO　添加记录基本判断
		
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
	public CIP_admin_commonqueryVO getData(Object[] keys ){
		CIP_admin_commonqueryPO  po = dataDao.getSingle(keys);
		if(po == null)
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
			
		return po.toVO();
	}
	
	/** 
	 * 检索数据
	 */		
	@Override
	public List<CIP_admin_commonqueryData> searchData(CIPPageInfo page, CIPReqCondition[] conditions){
		
		List<CIP_admin_commonqueryData> datas = dataDao.searchData(page, conditions);
		return datas;
	}

	
	public List<Map<String, Object>> exportEntities(CIPPageInfo page,CIPReqCondition[] conditions,
			CIPRuntimeOperator operateInf, boolean xFirst){
		if(xFirst){
			//do something 
		}
		return dataDao.exportEntities(page, conditions, xFirst);
	}
	/**
	* TODO 将数据库中的文本文档存到redis缓存中.
	* @see com.yd.common.function.admin.service.CIP_admin_commonqueryService#save()
	*/
	@Override	
	public void save() {
		List<CIP_admin_commonqueryData> datas = dataDao.getAllData();
		Map<String, CIP_admin_commonqueryData> map = new HashMap<String, CIP_admin_commonqueryData>();
		String sys_id = CIPRuntimeConfigure.cip_system_id;
		//拼接主键 系统id+标识串
		String key = sys_id+"@commonQuery";
		for(int i=0;i<datas.size();i++){
			String id = datas.get(i).getQueryId();
			map.put(id,datas.get(i));
		}		
		//-1表示不设置过期时间
		saved(key, map);
	}
	//将封装的数据以K-V的格式存放在缓存cache中
	private void saved(String key,Map<String, CIP_admin_commonqueryData> map) {		
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