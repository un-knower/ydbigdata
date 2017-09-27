package com.yd.common.function.domain.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.yd.common.cache.CIPCacheManager;
import com.yd.common.cache.CIPCacheService;
import com.yd.common.data.CIPResponseMsg;
import com.yd.common.exception.CIPRuntimeException;
import com.yd.common.function.admin.data.vo.CIP_admin_domainVO;
import com.yd.common.function.domain.data.CIPAdminCodesData;
import com.yd.common.function.domain.service.CIPDomainDataService;
import com.yd.common.function.domain.service.CIPDomainService;
import com.yd.common.function.domain.service.impl.CIPDomainDataServiceImpl;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntimeConfigure;
import com.yd.common.utils.JSONUtils;

@RestController
@RequestMapping(value = "/actions/common/domain")
public class CIPDomainController {

	@Autowired
	private CIPDomainService domainService = null;
	private Map<String, CIPDomainDataService<Object>> dataServices = new HashMap<String, CIPDomainDataService<Object>>();

	private static final String _domainId = "domainId";
	private static final String _domainValue = "domainValue";

	private <T> CIPDomainDataService<T> getService(Class<T> clazz) {
		return new CIPDomainDataServiceImpl<T>();
	}

	@RequestMapping(value = "/getDomainMeta")
	public CIPResponseMsg getDomainMeta(HttpServletRequest request) {
		String domainId = request.getParameter(_domainId);
		CIPResponseMsg msg = new CIPResponseMsg();

		try {
			CIP_admin_domainVO domainData = domainService.getDomain(domainId);
			msg.data = domainData;
		} catch (Exception e) {
			msg.errorCode = CIPErrorCode.ERROR_TECHNICAL_ERROR.code;
			msg.msg = e.getMessage();
		}

		return msg;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getDomains")
	public CIPResponseMsg getDomains(HttpServletRequest request){
		CIPResponseMsg msg = new CIPResponseMsg();
		try {
			String domainId = request.getParameter(_domainId);
			String sys_id = CIPRuntimeConfigure.cip_system_id;
			//拼接缓存cache中的主键  为 系统id+标识串
			String key = sys_id+"@domainCode";
			//通过主键，从缓存中拿到 数据编码的json字符串信息
			String jsonData=getCodeById(key);
			//解析数据编码字符串信息
			Map<String,CIPAdminCodesData> map = JSON.parseObject(jsonData,new TypeReference<Map<String, CIPAdminCodesData>>(){});
			//通过主键拿到 封装过的数据编码信息
			CIPAdminCodesData data = map.get(domainId);
			//拿到缓存中存放的tableName,满足下面条件，即从缓存读取，否则从查询数据库
			if(data != null){
				String tableName = data.getRef_table_id();
				if(tableName==null||tableName.toLowerCase().equals("cip_admin_codes")){
				    //	msg.data = domainService.getDomainCodes(domainId);
							msg.data = data.getDatas();									
					}else {
						CIPDomainDataService<Object> dataService = dataServices.get(domainId);
						if (dataService == null) {
							// 1. get domain meta data
							domainService.initDomain(domainId);
							String[] keys = domainService.getDomainTableKeys(domainId);
							Class<?> clazz = domainService.getDomainDataClass(domainId);
							// 2. 生成 domain dataService
							dataService = (CIPDomainDataService<Object>) this.getService(clazz);
							dataService.init(clazz, tableName, keys);
							dataServices.put(domainId, dataService);
						}
						msg.data = dataService.getDatas();		
			        }								
			}else{
				String tableName = domainService.getTableName(domainId);
				if(tableName==null||tableName.toLowerCase().equals("cip_admin_codes")){
					msg.data = domainService.getDomainCodes(domainId);
				}
				else {
					CIPDomainDataService<Object> dataService = dataServices.get(domainId);
					if (dataService == null) {
						// 1. get domain meta data
						domainService.initDomain(domainId);
						String[] keys = domainService.getDomainTableKeys(domainId);
						Class<?> clazz = domainService.getDomainDataClass(domainId);
						// 2. 生成 domain dataService
						dataService = (CIPDomainDataService<Object>) this.getService(clazz);
						dataService.init(clazz, tableName, keys);
						dataServices.put(domainId, dataService);
					}
					msg.data = dataService.getDatas();
				}
				
			}
		} catch (Exception e) {
		//	e.printStackTrace();
			msg.errorCode = new CIPErrorCode(1,"系统错误：数据编码获取异常").code;
			msg.msg = new CIPErrorCode(1,"系统错误：数据编码获取异常").name;
		}			
		return msg;
	}
    /**
    * getCodeById:通过主键，从缓存中读取编码后的数据json字符串.
    *
    * @author jh
    * @param key 主键  （系统id+标识串格式）
    * @return jsonString
    * @since JDK 1.7
    */
    private String getCodeById(String key){
    	//调取系统管理配置的缓存
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
    	return code;
    }
	@RequestMapping(value = "/check")
	public CIPResponseMsg check(HttpServletRequest request)
			throws Exception {
		CIPResponseMsg msg = new CIPResponseMsg();
		try {
			
			String domainId = request.getParameter(_domainId);
			String tableName = domainService.getDomainTableName(domainId);
			if(tableName==null||tableName.toLowerCase().equals("cip_admin_codes")){
				String domainValue = request.getParameter(_domainValue);
				
				msg.data = domainService.checkDomainCode(_domainId, domainValue);
			}
			else {
				CIPDomainDataService<Object> dataService = dataServices
						.get(domainId);
				Class<?> clazz = null;
				if (dataService == null) {
					// 1. get domain meta data
					domainService.initDomain(domainId);
					tableName = domainService.getDomainTableName(domainId);
					String[] keys = domainService.getDomainTableKeys(domainId);
					clazz = domainService.getDomainDataClass(domainId);

					// 2. 生成 domain dataService
					dataService = (CIPDomainDataService<Object>) this
							.getService(clazz);
					dataService.init(clazz, tableName, keys);

					dataServices.put(domainId, dataService);
				}
				
				clazz = domainService.getDomainDataClass(domainId);
						
				String dataStr = IOUtils.toString(request.getInputStream());
				Object data = JSONUtils.convertJson2Object(dataStr, clazz);

				boolean xValid = dataService.checkData(data);
				msg.data = xValid;
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.errorCode = CIPErrorCode.ERROR_TECHNICAL_ERROR.code;
			msg.msg = e.getMessage();
		}
		return msg;

	}
}
