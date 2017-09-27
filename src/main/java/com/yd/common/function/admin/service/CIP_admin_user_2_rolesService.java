package com.yd.common.function.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.admin.data.CIP_admin_rolesData;
import com.yd.common.function.admin.data.CIP_admin_user_2_rolesData;
import com.yd.common.function.admin.data.vo.CIP_admin_user_2_rolesVO;
import com.yd.common.runtime.CIPRuntimeOperator;

/**
 * <p>Service类</p>
 * <p>Class: CIP_admin_user_2_rolesService</p>
 *
 * @since 2015-07-27 02:40:52
 */

@Service
public interface CIP_admin_user_2_rolesService {

	public void updateData(CIP_admin_user_2_rolesVO d, CIPRuntimeOperator operateInf);
	
	public void addData(CIP_admin_user_2_rolesVO d, CIPRuntimeOperator operateInf);
	
	public void deleteData(Object[] keys, CIPRuntimeOperator operateInf);
	
	public CIP_admin_user_2_rolesVO getData(Object[] keys);
	
	public List<CIP_admin_user_2_rolesData> searchData(CIPPageInfo page, CIPReqCondition[] conditions);
	
	public List<CIP_admin_rolesData> searchroleData(CIPPageInfo page, CIPReqCondition[] conditions);
	
	//判断是否存在用户角色关联
	public boolean checkrole(String userid);
	public boolean checkuser(String roleid);
	//单一条件删除用户角色关联
	public void deletebyuser(String userId);
	public void deletebyrole(String roleId);


	/** 
	 * 导出用户角色关联
	 */
	public List<Map<String, Object>> exportEntities(CIPPageInfo page,CIPReqCondition[] conditions,
			CIPRuntimeOperator operateInf, boolean xFirst);
	
}