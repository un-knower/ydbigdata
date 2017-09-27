package com.yd.common.function.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.admin.data.CIP_admin_user_settingData;
import com.yd.common.function.admin.data.vo.CIP_admin_user_settingVO;
import com.yd.common.runtime.CIPRuntimeOperator;

/**
 * <p>Service类</p>
 * <p>Class: CIP_admin_user_settingService</p>
 *
 * @since 2015-07-27 02:40:52
 */

@Service
public interface CIP_admin_user_settingService {

	public void updateData(CIP_admin_user_settingVO d, CIPRuntimeOperator operateInf);
	
	public void addData(CIP_admin_user_settingVO d, CIPRuntimeOperator operateInf);
	
	public void deleteData(Object[] keys, CIPRuntimeOperator operateInf);
	
	public CIP_admin_user_settingVO getData(Object[] keys);
	
	public List<CIP_admin_user_settingData> searchData(CIPPageInfo page, CIPReqCondition[] conditions);


	/** 
	 * 导出用户信息设置
	 */
	public List<Map<String, Object>> exportEntities(CIPPageInfo page,CIPReqCondition[] conditions,
			CIPRuntimeOperator operateInf, boolean xFirst);
	
}