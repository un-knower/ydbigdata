package com.yd.common.function.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.admin.data.CIP_admin_redisData;
import com.yd.common.function.admin.data.vo.CIP_admin_redisVO;
import com.yd.common.runtime.CIPRuntimeOperator;

/**
 * <p>Service类</p>
 * <p>Class: CIP_admin_redisService</p>
 *
 * @since 2015-08-24 01:32:29
 */

@Service
public interface CIP_admin_redisService {

	public void updateData(CIP_admin_redisVO d, CIPRuntimeOperator operateInf);
	
	public void addData(CIP_admin_redisVO d, CIPRuntimeOperator operateInf);
	
	public void deleteData(String key, CIPRuntimeOperator operateInf);
	
	public CIP_admin_redisVO getData(String key);
	
	public List<CIP_admin_redisData> searchData(CIPPageInfo page, CIPReqCondition[] conditions);


	/** 
	 * 导出Redis数据概要
	 */
	public List<Map<String, Object>> exportEntities(CIPPageInfo page,CIPReqCondition[] conditions,
			CIPRuntimeOperator operateInf, boolean xFirst);
	
	public void reloadData();
}