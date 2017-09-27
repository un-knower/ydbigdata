package com.yd.common.function.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.admin.data.CIP_admin_log_mdmData;
import com.yd.common.function.admin.data.vo.CIP_admin_log_mdmVO;
import com.yd.common.runtime.CIPRuntimeOperator;

/**
 * <p>Service类</p>
 * <p>Class: CIP_admin_log_mdmService</p>
 *
 * @since 2015-08-26 08:45:49
 */

@Service
public interface CIP_admin_log_mdmService {

	public void updateData(CIP_admin_log_mdmVO d, CIPRuntimeOperator operateInf);
	
	public void addData(CIP_admin_log_mdmVO d, CIPRuntimeOperator operateInf);
	
	public void deleteData(Object[] keys, CIPRuntimeOperator operateInf);
	
	public CIP_admin_log_mdmVO getData(Object[] keys);
	
	public List<CIP_admin_log_mdmData> searchData(CIPPageInfo page, CIPReqCondition[] conditions);


	/** 
	 * 导出本地日志-主数据变更
	 */
	public List<Map<String, Object>> exportEntities(CIPPageInfo page,CIPReqCondition[] conditions,
			CIPRuntimeOperator operateInf, boolean xFirst);

	public void logMasterDataChange(CIP_admin_log_mdmVO log);
	
}