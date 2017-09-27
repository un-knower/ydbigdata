package com.yd.common.function.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.admin.data.CIP_admin_log_jobData;
import com.yd.common.function.admin.data.vo.CIP_admin_log_jobVO;
import com.yd.common.runtime.CIPRuntimeOperator;

/**
 * <p>Service类</p>
 * <p>Class: CIP_admin_log_jobService</p>
 *
 * @since 2015-08-26 08:45:48
 */

@Service
public interface CIP_admin_log_jobService {

	public void updateData(CIP_admin_log_jobVO d, CIPRuntimeOperator operateInf);
	
	public void addData(CIP_admin_log_jobVO d, CIPRuntimeOperator operateInf);
	
	public void deleteData(Object[] keys, CIPRuntimeOperator operateInf);
	
	public CIP_admin_log_jobVO getData(Object[] keys);
	
	public List<CIP_admin_log_jobData> searchData(CIPPageInfo page, CIPReqCondition[] conditions);


	/** 
	 * 导出本地日志-作业日志
	 */
	public List<Map<String, Object>> exportEntities(CIPPageInfo page,CIPReqCondition[] conditions,
			CIPRuntimeOperator operateInf, boolean xFirst);

	public void logJob(CIP_admin_log_jobVO log);
	
}