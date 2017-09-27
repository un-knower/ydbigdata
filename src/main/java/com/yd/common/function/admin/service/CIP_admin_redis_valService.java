package com.yd.common.function.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.admin.data.CIP_admin_redis_valData;
import com.yd.common.function.admin.data.vo.CIP_admin_redis_valVO;
import com.yd.common.runtime.CIPRuntimeOperator;

/**
 * <p>Service类</p>
 * <p>Class: CIP_admin_redis_valService</p>
 *
 * @since 2015-08-24 09:34:48
 */

@Service
public interface CIP_admin_redis_valService {

	public void updateData(CIP_admin_redis_valVO d, CIPRuntimeOperator operateInf);
	
	public void addData(CIP_admin_redis_valVO d, CIPRuntimeOperator operateInf);
	
	public void deleteData(String keys, String param, CIPRuntimeOperator operateInf);
	
	public CIP_admin_redis_valVO getData(String keys, String param);
	
	public List<CIP_admin_redis_valData> searchData(CIPPageInfo page, CIPReqCondition[] conditions);


	/** 
	 * 导出Redis数据明细
	 */
	public List<Map<String, Object>> exportEntities(CIPPageInfo page,CIPReqCondition[] conditions,
			CIPRuntimeOperator operateInf, boolean xFirst);
	
}