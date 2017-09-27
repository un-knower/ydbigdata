package com.yd.common.function.admin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yd.common.dao.IDao;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.admin.data.CIP_admin_resourceData;
import com.yd.common.function.admin.data.CIP_admin_rolesData;
import com.yd.common.function.admin.data.po.CIP_admin_rolesPO;

/**
 * <p>Dao类</p>
 * <p>Class: CIP_admin_roles - 角色信息</p>
 *
 * @since 2015-07-27 02:40:52
 */
 
@Repository
public interface CIP_admin_rolesDao extends IDao<CIP_admin_rolesPO> {
	
	public List<CIP_admin_rolesData> searchData(CIPPageInfo page, CIPReqCondition[] conditions);
	
	public List<CIP_admin_rolesData> searchroleData(CIPPageInfo page, CIPReqCondition[] conditions);
	
	public List<Map<String, Object>> exportEntities(CIPPageInfo page, CIPReqCondition[] conditions, boolean xFirst);
	
	/**
	* getBtnAndPage:获取菜单项下的动作和页面资源.
	*
	* @author jh
	* @param parentId 菜单项节点id
	* @param root default节点id
	* @return
	* @since JDK 1.7
	*/
	public List<CIP_admin_resourceData> getBtnAndPage(String parentId,String root);
}
