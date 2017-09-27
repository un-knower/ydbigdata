package com.yd.ydbi.common.function.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.common.runtime.CIPRuntime;
import com.yd.ydbi.common.function.dao.ToolMapper;
import com.yd.ydbi.common.function.model.ComboxBean;
import com.yd.ydbi.common.function.model.OrgInfoBean;
import com.yd.ydbi.common.function.service.ToolService;

@Service
public class ToolServiceImpl implements ToolService {
	@Autowired
	private ToolMapper toolDao;
	
	@Override
	public List<ComboxBean> getRegion(Map<String, String> paramsMap){
		return toolDao.getRegion(paramsMap);
	}
	
	@Override
	public OrgInfoBean getOrgInf(Map<String, String> paramsMap){
		return toolDao.getOrgInf(paramsMap);
	}
	
	@Override
	public OrgInfoBean getRegionByRole(Map<String, String> paramsMap){
		String[] roleArray = paramsMap.get("user_role").split("_");
		String sel_cd = "";
		String sel_level = "";
		if("branch".equals(roleArray[0])){
			sel_level = "5";
			sel_cd = CIPRuntime.getOperateSubject().getSubject_org();
        }else if("dbct".equals(roleArray[0])){
        	sel_level = "4";
        	sel_cd = roleArray[1] ;
        }else if("prov".equals(roleArray[0])){
        	sel_level = "2";
        	sel_cd = roleArray[1] ;
        }else if("area".equals(roleArray[0])){
        	sel_level = "1";
        	sel_cd = roleArray[1] ;
        }else if("hq".equals(roleArray[0]) || "superAdmin".equals(roleArray[0])){
        	sel_level = "0";
        	sel_cd = "0" ;
        }
		paramsMap.put("sel_level", sel_level);
		paramsMap.put("sel_cd", sel_cd);
		return toolDao.getRegionByRole(paramsMap);
	}
	
}
