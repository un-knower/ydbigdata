package com.yd.common.function.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.admin.data.CIP_admin_op_logData;
import com.yd.common.function.admin.data.CIP_admin_run_logData;
import com.yd.common.function.admin.data.vo.CIP_admin_op_logVO;
import com.yd.common.runtime.CIPRuntimeOperator;

/**
 * <p>Service类</p>
 * <p>Class: CIP_admin_op_logService</p>
 *
 * @since 2015-07-27 02:40:51
 */

@Service
public interface CIP_admin_op_logService {

	public void updateData(CIP_admin_op_logVO d, CIPRuntimeOperator operateInf);
	
	public void addData(CIP_admin_op_logVO d, CIPRuntimeOperator operateInf);
	
	public void deleteData(Object[] keys, CIPRuntimeOperator operateInf);
	
	public CIP_admin_op_logVO getData(Object[] keys);
	
	public List<CIP_admin_op_logData> searchData(CIPPageInfo page, CIPReqCondition[] conditions);


	/** 
	 * 导出系统操作日志
	 */
	public List<Map<String, Object>> exportEntities(CIPPageInfo page,CIPReqCondition[] conditions,
			CIPRuntimeOperator operateInf, boolean xFirst);

	public void logOp(CIP_admin_op_logVO log);
	
	/**
	* getLogData:获取系统运行日志信息.
	*
	* @author jh
	* @return
	* @since JDK 1.7
	*/
	public List<CIP_admin_run_logData> getLogData();
	
	/**
	* getLogMessage:查看运行日志详情.
	*
	* @author jh
	* @param obj 日志对象
	* @return
	* @since JDK 1.7
	*/
	public List<String> getLogMessage(String obj);
	
}