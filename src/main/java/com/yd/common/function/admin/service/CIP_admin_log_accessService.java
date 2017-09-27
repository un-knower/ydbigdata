package com.yd.common.function.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.admin.data.CIP_admin_log_accessData;
import com.yd.common.function.admin.data.vo.CIP_admin_log_accessVO;
import com.yd.common.runtime.CIPRuntimeOperator;

/**
 * <p>Service类</p>
 * <p>Class: CIP_admin_log_accessService</p>
 *
 * @since 2015-08-26 08:45:49
 */

@Service
public interface CIP_admin_log_accessService {

	public void updateData(CIP_admin_log_accessVO d, CIPRuntimeOperator operateInf);
	
	public void addData(CIP_admin_log_accessVO d, CIPRuntimeOperator operateInf);
	
	public void deleteData(Object[] keys, CIPRuntimeOperator operateInf);
	
	public CIP_admin_log_accessVO getData(Object[] keys);
	
	public List<CIP_admin_log_accessData> searchData(CIPPageInfo page, CIPReqCondition[] conditions);


	/** 
	 * 导出本地日志-系统访问
	 */
	public List<Map<String, Object>> exportEntities(CIPPageInfo page,CIPReqCondition[] conditions,
			CIPRuntimeOperator operateInf, boolean xFirst);

	public void logSystemAccess(CIP_admin_log_accessVO log);
	
}