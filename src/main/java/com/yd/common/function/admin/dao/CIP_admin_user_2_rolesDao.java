package com.yd.common.function.admin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yd.common.dao.IDao;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.admin.data.CIP_admin_rolesData;
import com.yd.common.function.admin.data.CIP_admin_user_2_rolesData;
import com.yd.common.function.admin.data.po.CIP_admin_user_2_rolesPO;

/**
 * <p>Dao类</p>
 * <p>Class: CIP_admin_user_2_roles - 用户角色关联</p>
 *
 * @since 2015-07-27 02:40:52
 */
 
@Repository
public interface CIP_admin_user_2_rolesDao extends IDao<CIP_admin_user_2_rolesPO> {
	
	public List<CIP_admin_user_2_rolesData> searchData(CIPPageInfo page, CIPReqCondition[] conditions);
	
	public List<Map<String, Object>> exportEntities(CIPPageInfo page, CIPReqCondition[] conditions, boolean xFirst);
	
	public List<String> getRoles(String userId) ;
	
	//根据角色id获得用户id
	public List<String> getUsers(String roleId) ;
	//单一条件删除用户角色信息
	public void deletebyuser(String userId);
	public void deletebyrole(String roleId);
	
	public List<CIP_admin_rolesData> searchroleData(CIPPageInfo page, CIPReqCondition[] conditions);
}
