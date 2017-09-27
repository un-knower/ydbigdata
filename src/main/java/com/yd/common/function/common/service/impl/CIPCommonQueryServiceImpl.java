package com.yd.common.function.common.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.common.cache.CIPCacheManager;
import com.yd.common.cache.CIPCacheService;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.exception.CIPRuntimeException;
import com.yd.common.function.admin.data.CIP_admin_commonqueryData;
import com.yd.common.function.common.dao.CIPCommonQueryDao;
import com.yd.common.function.common.service.CIPCommonQueryService;
import com.yd.common.runtime.CIPErrorCode;

/**
 * ClassName:CIPCommonQueryServiceImp
 * Function: TODO ADD FUNCTION.
 * Reason: TODO ADD REASON.
 * Date: 2016年8月4日 上午11:35:38
 * @author lyn
 * @version
 * @since JDK 1.7
 * @see
 */
@Service
public class CIPCommonQueryServiceImpl implements CIPCommonQueryService {
	@Autowired
	private CIPCommonQueryDao queryDao=null;
	
	public String getQueryMsgById(String key){
    	//调取系统管理配置的缓存
    	CIPCacheService	cacheService = CIPCacheManager.getCacheService();
		String queryMsg = "";
		byte[] bytes=cacheService.get(key,byte[].class);
	    try {
	    	queryMsg = (bytes==null)?null:new String(bytes,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new CIPRuntimeException(CIPErrorCode.ERROR_DATA_IS_MALFORM);
		}
    	
    	/*
    	 *远程连接redis 
    	 Jedis jedis = null;
		String code;
		boolean isJedisConnectExceptionOccured = false;
		try {
			jedis = CIPRedisUtils.getJedisResource();
			byte[] bytes=jedis.get(key.getBytes("utf-8"));
		    code = (bytes==null)?null:new String(bytes,"utf-8");
		} catch (JedisConnectionException e) {
			isJedisConnectExceptionOccured = false;
			throw new CIPRuntimeException(CIPErrorCode.ERROR_HTTP_REQUEST_IS_NOT_AVAILABLE);
		} catch (Exception e) {
			throw new CIPRuntimeException(CIPErrorCode.ERROR_TECHNICAL_ERROR);
		} finally {
			CIPRedisUtils.returnJedisResource(jedis,isJedisConnectExceptionOccured);
		}*/
    	return queryMsg;
    }

	@Override
	public List<Map<String,Object>> doQuery(CIP_admin_commonqueryData data,CIPPageInfo page,Map<String, String> paramMap) {
		String querySql = data.getStatement();
		String countSql = data.getCount_statement();
		
		return queryDao.doQuery(querySql,countSql,paramMap,page);
	}

	@Override
	public int doQueryCount(CIP_admin_commonqueryData data,Map<String, String> paramMap) {
		String countSql = data.getCount_statement();
		return queryDao.doQueryCount(countSql,paramMap);
	}

	
}
