package com.yd.common.function.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.admin.data.CIP_admin_userData;
import com.yd.common.function.admin.data.vo.CIP_admin_userVO;
import com.yd.common.function.auth.data.CIPAuthResult;
import com.yd.common.runtime.CIPRuntimeOperator;

/**
 * <p>Service类</p>
 * <p>Class: CIP_admin_userService</p>
 *
 * @since 2015-07-27 02:40:52
 */

@Service
public interface CIP_admin_userService {

	public void updateData(CIP_admin_userVO d, CIPRuntimeOperator operateInf);
	
	public void addData(CIP_admin_userVO d, CIPRuntimeOperator operateInf);
	
	public void deleteData(Object[] keys, CIPRuntimeOperator operateInf);
	
	public CIP_admin_userVO getData(Object[] keys);
	
	//重置密码
	public CIPAuthResult resetPassword(String userId, String oldP, String newP);
	
	public List<CIP_admin_userData> searchData(CIPPageInfo page, CIPReqCondition[] conditions);


	/** 
	 * 导出用户信息
	 */
	public List<Map<String, Object>> exportEntities(CIPPageInfo page,CIPReqCondition[] conditions,
			CIPRuntimeOperator operateInf, boolean xFirst);
	
}