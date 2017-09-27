package com.yd.common.function.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.function.admin.data.CIP_admin_userData;
import com.yd.common.function.admin.data.po.CIP_admin_user_2_rolesPO;

/**
 * <p>Service类</p>
 * <p>Class: CIP_admin_user_2_rolesService</p>
 *
 * @since 2015-07-27 02:40:52
 */

@Service
public interface CIP_admin_ass_role_userService {

	/**
	* searchData:查询已分配指定角色的用户.
	*
	* @author jh
	* @param page 分页信息
	* @param id  指定角色id
	* @return
	* @since JDK 1.7
	*/
	public List<CIP_admin_userData> searchData(CIPPageInfo page, String id);
	
	/**
	* searchUser:查询未分配当前角色的用户.
	*
	* @author jh
	* @param page 分页信息
	* @return
	* @since JDK 1.7
	*/
	public List<CIP_admin_userData> searchUser(CIPPageInfo page,String id);

	/**
	* moveUser:移除分配角色的用户.
	*
	* @author jh
	* @param user_id 待移除用户id
	* @param role_id 待移除用户角色id
	* @since JDK 1.7
	*/
	public void moveUser(String user_id,String role_id);	
	/**
	* addUser:给未分配角色额用户分配角色.
	*
	* @author jh
	* @param list(待分配角色的用户)
	* @since JDK 1.7
	*/
	public void addUser(List<CIP_admin_user_2_rolesPO> list);

   
}