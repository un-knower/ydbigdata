package com.yd.common.conf;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.yd.common.exception.CIPRuntimeException;
import com.yd.common.runtime.CIPErrorCode;

public class CIPLocalConfigureService implements CIPConfigureService {
	private Map<String,Properties> confs = new HashMap<String,Properties>();

	@Override
	public void init(String fileName) throws Exception {
		String confFile = fileName;
		InputStream stream = null;
		Properties props = null;
		props = new Properties();
		stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(confFile);
		props.load(stream);
		confs.put(fileName, props);
	}


	@Override
	public CIPConfigureData getConfigure(String confFile, String confId) {
		
		if(confs.get(confFile)!=null){
			Properties p = confs.get(confFile);
			CIPConfigureData conf = new CIPConfigureData();
			conf.setConf_id(confId);
			conf.setConf_value(p.getProperty(confId));
			return conf;
		}
		
		return null;
	}

	private List<CIPConfigureData> getProperties(Properties props){
		Enumeration<Object> es = props.keys();
		Object e = null;
		CIPConfigureData conf = null;
		List<CIPConfigureData> configs = new ArrayList<CIPConfigureData>();
		String conf_id;
		String conf_value;
		while(es.hasMoreElements()){
			e = es.nextElement();
			conf = new CIPConfigureData();
			conf_id = (String) e;
			conf_value = props.getProperty(conf_id);
			conf.setConf_id(conf_id);
			conf.setConf_value(conf_value);
			configs.add(conf);
		}
		
		return configs;
	}
	

	@Override
	public List<CIPConfigureData> getConfigures(String confFile) {
		
		if(confs.get(confFile)!=null){
			return getProperties(confs.get(confFile));
		}
		
		Properties props = null;
		props = new Properties();
		InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(confFile);
		try {
			props.load(stream);
		} catch (IOException e) {
			throw new CIPRuntimeException(
					CIPErrorCode.ERROR_RESOURCE_IS_NOT_READY, e);
		}
		confs.put(confFile, props);
		
		return getProperties(props);
		
	}


	@Override
	public List<CIPConfigureData> getConfigures() {

		return null;
	}


	@Override
	public Properties getPConfigures(String confFile) {
		if(confs.get(confFile)!=null){
			return confs.get(confFile);
		}
		getConfigures(confFile);
		return confs.get(confFile);
	}

}
