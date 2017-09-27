package com.yd.common.function.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yd.common.dao.IDao;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.function.admin.data.CIP_admin_userData;
import com.yd.common.function.admin.data.po.CIP_admin_user_2_rolesPO;

/**
* ClassName: CIP_admin_ass_role_userDao
* Function: Dao类   执行角色分配用户的相关操作.
* Reason: TODO ADD REASON(可选).
* date: 2016-6-7 下午2:18:40
*
* @author jh
* @version 
* @since JDK 1.7
*/

@Repository
public interface CIP_admin_ass_role_userDao extends IDao<CIP_admin_user_2_rolesPO> {
		
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
	* searchData:查询已经分配过角色的用户.
	*
	* @author jh
	* @param page 分页信息
	* @param id  角色id
	* @return
	* @since JDK 1.7
	*/
	public List<CIP_admin_userData> searchData(CIPPageInfo page,String id);
	
	/**
	* moveUser:移除已经分配角色的用户.
	*
	* @author jh
	* @param user_id 待移除的用户id
	* @param role_id 待移除的用户的角色id
	* @since JDK 1.7
	*/
	public void moveUser(String user_id,String role_id);
}
