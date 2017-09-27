package com.yd.ydbi.common.function.service;

import java.util.List;
import java.util.Map;

import com.yd.ydbi.common.function.model.ComboxBean;
import com.yd.ydbi.common.function.model.OrgInfoBean;

public interface ToolService {
	
	public List<ComboxBean> getRegion(Map<String, String> paramsMap);
	
	public OrgInfoBean getOrgInf(Map<String, String> paramsMap);
	
	public OrgInfoBean getRegionByRole(Map<String, String> paramsMap);
}
