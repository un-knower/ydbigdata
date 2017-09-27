package com.yd.common.function.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;


import com.yd.common.data.CIPPageInfo;
import com.yd.common.function.admin.data.CIP_admin_commonqueryData;

/**
 * ClassName:CIPCommonQueryService
 * Function: TODO ADD FUNCTION.
 * Reason: TODO ADD REASON.
 * Date: 2016年8月4日 上午10:49:37
 * @author lyn
 * @version
 * @since JDK 1.7
 * @see
 */
@Service
public interface CIPCommonQueryService {
 
	public String getQueryMsgById(String key);
	
	public List<Map<String,Object>> doQuery(CIP_admin_commonqueryData data,CIPPageInfo page,Map<String, String> paramMap);
	
	public int doQueryCount(CIP_admin_commonqueryData data,Map<String, String> paramMap);
}
