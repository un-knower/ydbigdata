package com.yd.common.function.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.admin.data.CIP_admin_resource_2_resourceData;
import com.yd.common.function.admin.data.vo.CIP_admin_add_2_configResourceVO;
import com.yd.common.function.admin.data.vo.CIP_admin_resource_2_resourceVO;
import com.yd.common.runtime.CIPRuntimeOperator;

/**
 * <p>Service类</p>
 * <p>Class: CIP_admin_resource_2_resourceService</p>
 *
 * @since 2015-07-27 02:40:52
 */

@Service
public interface CIP_admin_resource_2_resourceService {

	public void updateData(CIP_admin_resource_2_resourceVO d, CIPRuntimeOperator operateInf);
	
	public void addData(CIP_admin_resource_2_resourceVO d, CIPRuntimeOperator operateInf);
	
	public void deleteData(Object[] keys, CIPRuntimeOperator operateInf);
	
	public CIP_admin_resource_2_resourceVO getData(Object[] keys);
	
	public List<CIP_admin_resource_2_resourceData> searchData(CIPPageInfo page, CIPReqCondition[] conditions);
	
	public void addAndConfigRes (CIP_admin_add_2_configResourceVO vo, CIPRuntimeOperator operateInf);


	/** 
	 * 导出资源与资源关系
	 */
	public List<Map<String, Object>> exportEntities(CIPPageInfo page,CIPReqCondition[] conditions,
			CIPRuntimeOperator operateInf, boolean xFirst);
	/**
	 * * addResources:添加未分配的资源.
	*
	* @author jh
	* @param vos 
	* @param parameters 添加的资源id
	* @return
	* @since JDK 1.7
	*/

	public void addResources(List<CIP_admin_resource_2_resourceVO> vos,CIPRuntimeOperator operateInf);
	
	public int getMaxLevel(String root);
}