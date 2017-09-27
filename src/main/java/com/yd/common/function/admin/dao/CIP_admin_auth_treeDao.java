package com.yd.common.function.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yd.common.dao.IDao;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.function.admin.data.CIP_admin_auth_act2objData;
import com.yd.common.function.admin.data.CIP_admin_resource_2_resourceData;
import com.yd.common.function.admin.data.CIP_admin_resource_treeData;
import com.yd.common.function.admin.data.po.CIP_admin_auth_act2objPO;

/**
 * <p>Dao类</p>
 * <p>Class: CIP_admin_auth_act2obj - 权限对象授权配置</p>
 *
 * @since 2015-07-27 02:40:51
 */
 
@Repository
public interface CIP_admin_auth_treeDao extends IDao<CIP_admin_auth_act2objPO> {
	
	/**
	* queryByRootNodeId:查询指定根节点下已经配置过的数据资源.
	*
	* @author jh
	* @param root_node_id 根节点
	* @return
	* @since JDK 1.7
	*/
	public List<CIP_admin_resource_treeData> queryByRootNodeId(String root_node_id,String obj_attr_id,String obj_id);
	
	/**
	* deleteAllAct2Obj:删除当前权限对象及根节点下所有已经存在的资源.
	*
	* @author jh
	* @param root_resource_id//资源根节点
	* @param obj_attr_id//权限对象id
	* @param obj_id//权限对象属性id
	* @since JDK 1.7
	*/
	public void deleteAllAct2Obj(String root_resource_id, String obj_attr_id,String obj_id);	
	
	/**
	* searchNodeId:(查询树节点是否是已选).
	*
	* @author lyn
	* @param root_resource_id
	* @param obj_attr_id
	* @param obj_id
	* @return
	* @since JDK 1.7
	*/
	public List<CIP_admin_auth_act2objData> searchNodeId(String root_resource_id, String obj_attr_id,String obj_id);
	
	/**
	* querySearchByRootNodeId:(为获得动作树(显示已选根节点下分配权限的所有'动作'树)查询数据).
	*
	* @author lyn
	* @param search_condition
	* @param root_node_id
	* @return
	* @since JDK 1.7
	*/
	public List<CIP_admin_resource_2_resourceData> querySearchByRootNodeId(String search_condition,String root_node_id);
	
	/**
	* uncheckResTree:(获得该角色下未分配的'动作'树..查询数据).
	*
	* @author lyn
	* @param root_node_id
	* @param obj_attr_id
	* @param obj_id
	* @return
	* @since JDK 1.7
	*/
	public List<CIP_admin_resource_treeData> uncheckResTree(String root_node_id,String obj_attr_id,String obj_id);
	
	/**
	* getSearchTree:(通过角色id 和 查询条件查询出过滤条件后的资源树).
	*
	* @author lyn
	* @param root_node_id
	* @param resource_id
	* @param resource_name
	* @param obj_attr_id
	* @param obj_id
	* @return
	* @since JDK 1.7
	*/
	public List<CIP_admin_resource_treeData> getSearchTree(String root_node_id,String resource_id,String resource_name,String obj_attr_id,String obj_id);
	
	
	/**
	* getRourceByIds:(查询已分配的数据权限资源).
	*
	* @author lyn
	* @param root_node_id
	* @param obj_attr_id
	* @param obj_id
	* @return
	* @since JDK 1.7
	*/
	public List<CIP_admin_auth_act2objData> getRourceByIds(CIPPageInfo page,String root_node_id,String obj_attr_id, String obj_id);
	
	
	/**
	* searchSource:查询分配数据来源的权限对象.
	*
	* @author jh
	* @param page 分页信息
	* @param conditions 查询条件
	* @return
	* @since JDK 1.7
	*/
	public List<CIP_admin_auth_act2objData> searchSource(CIPPageInfo page, String condition,String root_node_id,String obj_attr_id,String obj_id);
}
