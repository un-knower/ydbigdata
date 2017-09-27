package com.yd.common.function.admin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yd.common.dao.IDao;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.admin.data.CIP_admin_user2resData;
import com.yd.common.function.admin.data.CIP_admin_user_2_rolesData;
import com.yd.common.function.admin.data.po.CIP_admin_user2resPO;

/**
 * <p>Dao类</p>
 * <p>Class: CIP_admin_user2res - 主页快速工具表</p>
 *
 * @since 2016-08-31 04:49:06
 */
 
@Repository
public interface CIP_admin_user2resDao extends IDao<CIP_admin_user2resPO> {
	
	public List<CIP_admin_user2resData> searchData(CIPPageInfo page, CIPReqCondition[] conditions);
	
	public List<Map<String, Object>> exportEntities(CIPPageInfo page, CIPReqCondition[] conditions, boolean xFirst);

	/**
	* getToolData:(获取主页常用工具信息).
	*
	* @author jh
	* @param userId 用户id
	* @return
	* @since JDK 1.7
	*/
	public List<CIP_admin_user2resData> getToolData(String userId);

	/**
	* geRoleByUser:通过用户id 获取当前角色id.
	*
	* @author jh
	* @param userId
	* @return
	* @since JDK 1.7
	*/
	public List<CIP_admin_user_2_rolesData> geRoleByUser(String userId);		
}
