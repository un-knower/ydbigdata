package com.yd.common.function.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.admin.data.CIP_admin_access_resData;
import com.yd.common.function.admin.data.vo.CIP_admin_access_resVO;
import com.yd.common.runtime.CIPRuntimeOperator;

/**
 * <p>Service类</p>
 * <p>Class: CIP_admin_access_resService</p>
 *
 * @since 2015-08-20 09:11:16
 */

@Service
public interface CIP_admin_access_resService {

	public void updateData(CIP_admin_access_resVO d, CIPRuntimeOperator operateInf);
	
	public void addData(CIP_admin_access_resVO d, CIPRuntimeOperator operateInf);
	
	public void deleteData(Object[] keys, CIPRuntimeOperator operateInf);
	
	public CIP_admin_access_resVO getData(Object[] keys);
	
	public List<CIP_admin_access_resData> searchData(CIPPageInfo page, CIPReqCondition[] conditions);


	/** 
	 * 导出系统访问资源配置
	 */
	public List<Map<String, Object>> exportEntities(CIPPageInfo page,CIPReqCondition[] conditions,
			CIPRuntimeOperator operateInf, boolean xFirst);
	
}