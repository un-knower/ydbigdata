package com.yd.common.function.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.admin.data.CIP_admin_access_ctrlData;
import com.yd.common.function.admin.data.vo.CIP_admin_access_ctrlVO;
import com.yd.common.runtime.CIPRuntimeOperator;

/**
 * <p>Service类</p>
 * <p>Class: CIP_admin_access_ctrlService</p>
 *
 * @since 2015-08-18 02:49:22
 */

@Service
public interface CIP_admin_access_ctrlService {

	public void updateData(CIP_admin_access_ctrlVO d, CIPRuntimeOperator operateInf);
	
	public void addData(CIP_admin_access_ctrlVO d, CIPRuntimeOperator operateInf);
	
	public void deleteData(Object[] keys, CIPRuntimeOperator operateInf);
	
	public CIP_admin_access_ctrlVO getData(Object[] keys);
	
	public List<CIP_admin_access_ctrlData> searchData(CIPPageInfo page, CIPReqCondition[] conditions);


	/** 
	 * 导出系统访问控制
	 */
	public List<Map<String, Object>> exportEntities(CIPPageInfo page,CIPReqCondition[] conditions,
			CIPRuntimeOperator operateInf, boolean xFirst);
	
}