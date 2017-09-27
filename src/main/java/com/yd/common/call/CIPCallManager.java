package com.yd.common.call;

import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.yd.common.data.CIPResponseMsg;
import com.yd.common.exception.CIPRuntimeException;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntime;
import com.yd.common.runtime.CIPRuntimeConfigure;
import com.yd.common.runtime.CIPRuntimeConstants;
import com.yd.common.utils.HttpUtils;
import com.yd.common.utils.JSONUtils;

public class CIPCallManager {
	private static Logger log = Logger.getLogger(CIPCallManager.class);
			
	public static CIPCallManager getCallManager() {
		return new CIPCallManager();
	}
	
	public String call(String targetSysId, String resourceId, Object sendObj){
		
		return null;
	}
	
	public String call(String targetSysId, String resourceId, Map<String,String> sendParams){
		try {
			//1. 获取体系远程调用参数：
			//a、系统访问授权码 
			//b、系统访问附带参数；
			//c、系统访问资源对应的url；
			
			//返回授权码及相关参数
			Map<String,String> params = CIPRuntime.authManager.getCallParameters(targetSysId);
			//设置系统id及行为id
			params.put(CIPRuntimeConstants.AUTH_SOURCE_SYSTEM_ID, CIPRuntimeConfigure.cip_system_id);
			params.put(CIPRuntimeConstants.CIP_ACTION_ID, resourceId);
			
			String confURL = CIPRuntime.authManager.getRemoteResource(resourceId);
			if(confURL==null||"".equals(confURL)){
				throw new CIPRuntimeException(new CIPErrorCode(90004,"尚未配置访问资源"+resourceId));
			}
			
			Set<String> keys = params.keySet();
			StringBuffer sb = new StringBuffer();
			boolean xStart=true;
			sb.append(confURL);
			
			if(confURL.indexOf('?')>0)
				xStart =false;
			else
				sb.append("?");
			
			for(String key:keys){
				if(xStart){
					sb.append(key).append('=').append(params.get(key));
					xStart = false;
				}
				else
					sb.append('&').append(key).append('=').append(params.get(key));
			}
			
			confURL = sb.toString();
			
			//2. 系统调用
			String response = HttpUtils.postJSON(confURL, sendParams);

			CIPResponseMsg msg = JSONUtils.convertJson2Object(response,
					CIPResponseMsg.class);
			if (msg.errorCode > 0) {
				throw new CIPRuntimeException(
						CIPErrorCode.ERROR_REMOTE_SERVER_RETURN_ERROR);
			}
			
			return msg.data.toString();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new CIPRuntimeException(
					CIPErrorCode.ERROR_HTTP_REQUEST_IS_NOT_AVAILABLE, e);

		}
	}

	public <T> T call(String targetSysId, String resourceId, Object sendObj,
			Class<T> returnObj) {
		try {
			//1. 获取体系远程调用参数：
			//a、系统访问授权码 
			//b、系统访问附带参数；
			//c、系统访问资源对应的url；
			
			//返回授权码及相关参数
			Map<String,String> params = CIPRuntime.authManager.getCallParameters(targetSysId);
			//设置系统id及行为id
			params.put(CIPRuntimeConstants.AUTH_SOURCE_SYSTEM_ID, CIPRuntimeConfigure.cip_system_id);
			params.put(CIPRuntimeConstants.CIP_ACTION_ID, resourceId);
			
			String confURL = CIPRuntime.authManager.getRemoteResource(resourceId);
			if(confURL==null||"".equals(confURL)){
				throw new CIPRuntimeException(new CIPErrorCode(90004,"尚未配置访问资源"+resourceId));
			}
			
			Set<String> keys = params.keySet();
			StringBuffer sb = new StringBuffer();
			boolean xStart=true;
			sb.append(confURL);
			
			if(confURL.indexOf('?')>0)
				xStart =false;
			else
				sb.append("?");
			
			for(String key:keys){
				if(xStart){
					sb.append(key).append('=').append(params.get(key));
					xStart = false;
				}
				else
					sb.append('&').append(key).append('=').append(params.get(key));
			}
			
			confURL = sb.toString();
			
			//2. 系统调用
			String response = HttpUtils.postJSON(confURL, sendObj);

			CIPResponseMsg msg = JSONUtils.convertJson2Object(response,
					CIPResponseMsg.class);
			if (msg.errorCode > 0) {
				throw new CIPRuntimeException(
						CIPErrorCode.ERROR_REMOTE_SERVER_RETURN_ERROR);
			}
			T data = null;
			if(msg.data!=null) data = JSONUtils.convertJson2Object(msg.data.toString(),
					returnObj);
			
			return data;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new CIPRuntimeException(
					CIPErrorCode.ERROR_HTTP_REQUEST_IS_NOT_AVAILABLE, e);

		}
	}

}
