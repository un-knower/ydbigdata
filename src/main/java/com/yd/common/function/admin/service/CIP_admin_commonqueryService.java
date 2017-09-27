package com.yd.common.function.admin.service;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.admin.data.CIP_admin_commonqueryData;
import com.yd.common.function.admin.data.vo.CIP_admin_commonqueryVO;
import com.yd.common.runtime.CIPRuntimeOperator;

/**
 * <p>Service类</p>
 * <p>Class: CIP_cadmin_commonqueryService</p>
 *
 * @since 2016-08-04 01:17:07
 */

@Service
public interface CIP_admin_commonqueryService {

	public void updateData(CIP_admin_commonqueryVO d, CIPRuntimeOperator operateInf);
	
	public void addData(CIP_admin_commonqueryVO d, CIPRuntimeOperator operateInf);
	
	public void deleteData(Object[] keys, CIPRuntimeOperator operateInf);
	
	public CIP_admin_commonqueryVO getData(Object[] keys);
	
	public List<CIP_admin_commonqueryData> searchData(CIPPageInfo page, CIPReqCondition[] conditions);


	/** 
	 * 导出通用查询配置表
	 */
	public List<Map<String, Object>> exportEntities(CIPPageInfo page,CIPReqCondition[] conditions,
			CIPRuntimeOperator operateInf, boolean xFirst);
	
	/**
	* save:将数据库中的数据以K-V方式存到redis中.
	*
	* @author jh
	* @param 
	* @since JDK 1.7
	*/
	public void save();
}