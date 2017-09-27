package com.yd.common.function.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.function.admin.dao.CIP_admin_ass_role_userDao;
import com.yd.common.function.admin.data.CIP_admin_userData;
import com.yd.common.function.admin.data.po.CIP_admin_user_2_rolesPO;
import com.yd.common.function.admin.service.CIP_admin_ass_role_userService;

/**
 * <p>Service实现类</p>
 * <p>Class: CIP_admin_user_2_rolesService</p>
 *
 * @since 2015-07-27 02:40:52
 */

@Service
public class CIP_admin_ass_role_userServiceImpl implements CIP_admin_ass_role_userService {
	
	@Autowired
	private CIP_admin_ass_role_userDao dataDao = null;
	
	/**
	* TODO 查询未分配当前角色的用户.
	* @see com.yd.common.function.admin.service.CIP_admin_ass_role_userService#searchUser()
	*/
	@Override
	public List<CIP_admin_userData> searchUser(CIPPageInfo page,String id) {
		List<CIP_admin_userData> datas = dataDao.searchUser(page,id);
		return datas;
	}

	/**
	* TODO 查询已经分配过角色的用户.
	* @see com.yd.common.function.admin.service.CIP_admin_ass_role_userService#searchData(com.yd.common.data.CIPPageInfo, java.lang.String)
	*/
	@Override
	public List<CIP_admin_userData> searchData(CIPPageInfo page, String id) {
		List<CIP_admin_userData> datas= dataDao.searchData(page, id);
		return datas;
	}

	/**
	* TODO 移除已经分配角色的用户.
	* @see com.yd.common.function.admin.service.CIP_admin_ass_role_userService#moveUser(java.lang.String, java.lang.String)
	*/
	@Override
	public void moveUser(String user_id,String role_id) {
		dataDao.moveUser(user_id, role_id);
	}

	/**
	* TODO 添加待分配角色的用户.
	* @see com.yd.common.function.admin.service.CIP_admin_ass_role_userService#addUser(java.util.List)
	*/
	@Override
	public void addUser(List<CIP_admin_user_2_rolesPO> list) {
		dataDao.batchAdd(list);
	}

}