package com.yd.common.cache;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.yd.common.exception.CIPRuntimeException;
import com.yd.common.function.admin.data.CIP_admin_codesData;
import com.yd.common.function.domain.data.CIPAdminCodesData;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntime;
import com.yd.common.runtime.CIPRuntimeConfigure;
import com.yd.common.runtime.CIPRuntimeOperator;
import com.yd.common.utils.JSONUtils;


public class CIPCacheManager {
	
	/** ----远程缓存(Redis服务器)-----*/
	public static final int CACHE_SCHEMA_REMOTE = 1;
	
	/** ----本地缓存(当前应用服务器)-----*/
	public static final int CACHE_SCHEMA_LOCAL = 0;
	
	
	private static CIPCacheService cacheService = null;
	
	public static void createCacheManager(int cache_schema) {
		if(cacheService!=null)
			return;
		
		if(cache_schema == CACHE_SCHEMA_REMOTE) {
			cacheService = new RemoteCacheManager();
		}else {
			cacheService = new LocalCacheManager();
		}
	}
	
	public static CIPCacheService getCacheService(){
		return cacheService;
	}
	/**
	* getCodeName:从缓存中拿到编码对应的名称.
	*
	* @author jh
	* @param domain_id 数据域id
	* @param code_type 编码类型
	* @return 编码名称
	* @since JDK 1.7
	*/
	public static String getCodeName(String domain_id,String code_type){
		String name="";
		try {
			String sys_id = CIPRuntimeConfigure.cip_system_id;
			//拼接缓存cache中的主键  为 系统id+标识串
			String key = sys_id+"@domainCode";
			CIPCacheService	cacheService = CIPCacheManager.getCacheService();
			String jsonData = "";
			byte[] bytes=cacheService.get(key,byte[].class);	    
		   jsonData = (bytes==null)?null:new String(bytes,"utf-8");
		    Map<String,CIPAdminCodesData> map = JSON.parseObject(jsonData,new TypeReference<Map<String, CIPAdminCodesData>>(){});
			//通过主键拿到 封装过的数据编码信息
			CIPAdminCodesData data = map.get(domain_id);
			List<CIP_admin_codesData> codesDatas = data.getDatas();
			for(int i=0;i<codesDatas.size();i++){
				String type = codesDatas.get(i).getCode_type();
				if(code_type.equals(type)){
					name = codesDatas.get(i).getCode_name();
					break;
				}			
			}
		}catch(Exception e){
			throw new CIPRuntimeException(new CIPErrorCode(1,"系统错误：获取编码名称失败"));
		} 	
		return name;
	}
	
	/**
	* getCIPErrorCode:(通过输入的code值  返回CIPErrorCode).
	*
	* @author jh
	* @param code
	* @return
	* @since JDK 1.7
	*/
	public static CIPErrorCode getErrorCode(int code,String defined){
		String sys_id = CIPRuntimeConfigure.cip_system_id;
		String key = sys_id+"@errorCode";
		String jsonData = getNameByCode(key);
		Map<String,String> nameMap = JSONUtils.convertJson2Object(jsonData, HashMap.class);
		if(nameMap==null || nameMap.equals("")){
			throw new CIPRuntimeException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
		}
		String codeString = String.valueOf(code);
		CIPRuntimeOperator operateInf = CIPRuntime.getOperateSubject();	
		String lang = "";
		if(operateInf==null || operateInf.equals("")){
			   lang="ZH";
		}else{
			   lang = operateInf.getLang();//获取语言类型
		}
		String codeKey = lang + codeString;//拼接  获得key
		//从redis中获得name值 
		String name = nameMap.get(codeKey);
		//如果name值为空，就输出用户自定义的值
		if(name==null || name.equals("")){
			name = defined;
		}
		CIPErrorCode errorCode = new CIPErrorCode(code,name);
		return errorCode;
	}
	//从redis通过key 获得value值 。
	private static String getNameByCode(String key){
		CIPCacheService	cacheService = CIPCacheManager.getCacheService();
		String code = "";
		byte[] bytes=cacheService.get(key,byte[].class);
	    try {
			code = (bytes==null)?null:new String(bytes,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new CIPRuntimeException(CIPErrorCode.ERROR_DATA_IS_MALFORM);
		}
			/*
			//远程连接redis 
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
	   return code;		
	}
}
