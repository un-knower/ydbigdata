package com.yd.common.conf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.yd.common.cipher.CIPDesUtils;
import com.yd.common.data.CIPResponseMsg;
import com.yd.common.exception.CIPRuntimeException;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntimeConfigure;
import com.yd.common.runtime.CIPRuntimeConstants;
import com.yd.common.utils.HttpUtils;
import com.yd.common.utils.JSONUtils;

public class CIPRemoteConfigureService implements CIPConfigureService {

	private static Logger log = Logger.getLogger(CIPRemoteConfigureService.class);
			
	private String confURL = null;

	private Map<String, List<CIPConfigureData>> confs = new HashMap<String, List<CIPConfigureData>>();

	@Override
	public CIPConfigureData getConfigure(String confFile, String confId) {

		try {
			List<CIPConfigureData> datas = confs.get(confFile);
			for (CIPConfigureData data : datas) {
				if (data.getConf_id().equals(confId))
					return data;
			}
			return null;
		} catch (Exception e) {
			throw new CIPRuntimeException(
					CIPErrorCode.ERROR_HTTP_REQUEST_IS_NOT_AVAILABLE, e);
		}
	}

	@Override
	public List<CIPConfigureData> getConfigures(String configureFile) {

		List<CIPConfigureData> datas = confs.get(configureFile);
		if (datas != null)
			return datas;
		
		Map<String, String> params = new HashMap<>();
		params.put(CIPRuntimeConstants.AUTH_SOURCE_SYSTEM_ID, CIPRuntimeConfigure.cip_system_id);
		long time = System.currentTimeMillis();
		params.put(CIPRuntimeConstants.CIP_TIME, String.valueOf(time));
		String authCode = CIPRuntimeConfigure.cip_remote_conf_auth_code;
		params.put(CIPRuntimeConstants.AUTH_CODE, CIPDesUtils.encrypt(authCode, time));
		String response;
		try {
			response = HttpUtils.post(confURL, params);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new CIPRuntimeException(
					CIPErrorCode.ERROR_HTTP_REQUEST_IS_NOT_AVAILABLE, e);
		}
		CIPResponseMsg msg = JSONUtils.convertJson2Object(response,
				CIPResponseMsg.class);
		if (msg.errorCode > 0) {
			log.error(msg.errorCode+":"+msg.msg);
			throw new CIPRuntimeException(
					new CIPErrorCode(msg.errorCode, msg.msg));
		}
		CIPConfigureData[] data = JSONUtils.convertJson2Object(msg.data.toString(),
				CIPConfigureData[].class);
		String confFile;
		for (int i = 0; i < data.length;) {
			confFile = data[i].getConf_file();
			datas = confs.get(confFile);
			if (datas == null) {
				datas = new ArrayList<CIPConfigureData>();
				confs.put(confFile, datas);
			}
			datas.add(data[i++]);
		}
		return confs.get(configureFile);
		
	}

	@Override
	public void init(String url) throws Exception {
		confURL = url;
	}

	@Override
	public List<CIPConfigureData> getConfigures() {
		List<CIPConfigureData> datas;
		List<CIPConfigureData> allDatas = new ArrayList<CIPConfigureData>();
		
		Map<String, String> params = new HashMap<>();
		params.put(CIPRuntimeConstants.AUTH_SOURCE_SYSTEM_ID, CIPRuntimeConfigure.cip_system_id);
		long time = System.currentTimeMillis();
		params.put(CIPRuntimeConstants.CIP_TIME, String.valueOf(time));
		String authCode = CIPRuntimeConfigure.cip_remote_conf_auth_code;
		params.put(CIPRuntimeConstants.AUTH_CODE, CIPDesUtils.encrypt(authCode, time));
		String response;
		try {
			response = HttpUtils.postJSON(confURL, params);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new CIPRuntimeException(
					CIPErrorCode.ERROR_HTTP_REQUEST_IS_NOT_AVAILABLE, e);
		}
		CIPResponseMsg msg = JSONUtils.convertJson2Object(response, CIPResponseMsg.class);
		if (msg.errorCode > 0) {
			log.error(msg.errorCode+":"+msg.msg);
			throw new CIPRuntimeException(
					new CIPErrorCode(msg.errorCode, msg.msg));
		}
		CIPConfigureData[] data = JSONUtils.convertJson2Object(
				msg.data.toString(), CIPConfigureData[].class);

		String confFile;
		for (int i = 0; i < data.length;) {
			confFile = data[i].getConf_file();
			datas = confs.get(confFile);
			if (datas == null) {
				datas = new ArrayList<CIPConfigureData>();
				confs.put(confFile, datas);
			}
			datas.add(data[i]);
			allDatas.add(data[i++]);
		}
		
		return allDatas;
	}

	@Override
	public Properties getPConfigures(String confFile) {
		List<CIPConfigureData> configs = getConfigures(confFile);
		Properties p = new Properties();
		for(CIPConfigureData config:configs){
			p.setProperty(config.getConf_id(), config.getConf_value());
		}
		return p;
	}

}
