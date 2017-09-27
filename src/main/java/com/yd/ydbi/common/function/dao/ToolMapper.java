package com.yd.ydbi.common.function.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yd.ydbi.common.function.model.ComboxBean;
import com.yd.ydbi.common.function.model.OrgInfoBean;

@Repository
public interface ToolMapper {
	List<ComboxBean> getRegion(Map<String, String> paramsMap);
	
	OrgInfoBean getOrgInf(Map<String, String> paramsMap);
	
	OrgInfoBean getRegionByRole(Map<String, String> paramsMap);
}
