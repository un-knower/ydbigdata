package com.yd.common.conf;

import java.util.List;
import java.util.Properties;

public interface CIPConfigureService {

	//初始化，给定url
	public void init(String url) throws Exception;
	
	//获取特定配置文件下，特定配置项信息
	public CIPConfigureData getConfigure(String confFile, String confId);

	//获取特定配置文件所有配置项信息
	public List<CIPConfigureData> getConfigures(String confFile);
	
	public Properties getPConfigures(String confFile);
	
	//获取所有系统配置文件信息
	public List<CIPConfigureData> getConfigures();

}
