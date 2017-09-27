package com.yd.common.function.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.admin.data.CIP_admin_codesData;
import com.yd.common.function.admin.data.vo.CIP_admin_codesVO;
import com.yd.common.runtime.CIPRuntimeOperator;

/**
 * <p>Service类</p>
 * <p>Class: CIP_admin_codesService</p>
 *
 * @since 2015-07-27 02:40:51
 */

@Service
public interface CIP_admin_codesService {

	public void updateData(CIP_admin_codesVO d, CIPRuntimeOperator operateInf);
	
	public void addData(CIP_admin_codesVO d, CIPRuntimeOperator operateInf);
	
	public void deleteData(Object[] keys, CIPRuntimeOperator operateInf);
	
	public CIP_admin_codesVO getData(Object[] keys);
	
	public List<CIP_admin_codesData> searchData(CIPPageInfo page, CIPReqCondition[] conditions);


	/** 
	 * 导出数据编码
	 */
	public List<Map<String, Object>> exportEntities(CIPPageInfo page,CIPReqCondition[] conditions,
			CIPRuntimeOperator operateInf, boolean xFirst);

	/**
	* save:将封装的编码信息存放到缓存中 .
	*
	* @author jh
	* @param 
	* @since JDK 1.7
	*/
	public void save();
}