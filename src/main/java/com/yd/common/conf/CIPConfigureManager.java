package com.yd.common.conf;

import java.util.List;
import java.util.Properties;

import com.yd.common.runtime.CIPRuntimeConfigure;

public class CIPConfigureManager {

	private static CIPConfigureService rootConfigure = null;
	private static CIPConfigureService configureService = null;

	public static void init() throws Exception {
		
		if (CIPRuntimeConfigure.cip_is_remote_conf) {
			configureService = new CIPRemoteConfigureService();
			configureService.init(CIPRuntimeConfigure.cip_remote_conf_url);
		}
		else {
			configureService = new CIPLocalConfigureService();
		}
	}
	
	public static Properties getRootConf() throws Exception{
		if(rootConfigure!=null)
			return rootConfigure.getPConfigures(CIPRuntimeConfigure.cip_root_conf_file);

		rootConfigure = new CIPLocalConfigureService();
		rootConfigure.init(CIPRuntimeConfigure.cip_root_conf_file);
		
		return rootConfigure.getPConfigures(CIPRuntimeConfigure.cip_root_conf_file);
	}

	public static String getConfigure(String confId) {
		CIPConfigureData conf = configureService.getConfigure(CIPRuntimeConfigure.cip_system_id, confId);
		return conf.getConf_value();
	}

	public static String getConfigureText(String confId) {
		CIPConfigureData conf = configureService.getConfigure(CIPRuntimeConfigure.cip_system_id, confId);
		return conf.getConf_value_desc();
	}
	

	public static Properties getConfigures(String confFile) {
		return configureService.getPConfigures(confFile);
	}

	public static Properties getConfigureTexts(String confFile) {
		List<CIPConfigureData> confs = configureService.getConfigures(confFile);
		Properties m = new Properties();
		for(CIPConfigureData conf:confs){
			m.setProperty(conf.getConf_id(), conf.getConf_value_desc());
		}
		return m;
	}

	public static Properties getConfigures() {
		List<CIPConfigureData> confs = configureService.getConfigures();
		Properties m = new Properties();
		for(CIPConfigureData conf:confs){
			m.setProperty(conf.getConf_id(), conf.getConf_value());
		}
		return m;
	}

	public static Properties getConfigureTexts() {
		List<CIPConfigureData> confs = configureService.getConfigures();
		Properties m = new Properties();
		for(CIPConfigureData conf:confs){
			m.setProperty(conf.getConf_id(), conf.getConf_value_desc());
		}
		return m;
	}
}
