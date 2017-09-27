package com.yd.common.function.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.function.admin.data.CIP_admin_auth_act2objData;
import com.yd.common.function.admin.data.po.CIP_admin_auth_act2objPO;
import com.yd.common.tag.easyui.vo.TreeData;

/**
 * <p>Service类</p>
 * <p>Class: CIP_admin_rolesService</p>
 *
 * @since 2015-07-27 02:40:52
 */

@Service
public interface CIP_admin_auth_treeService {

	
	/**
	* getActionTree:(获得该角色下的'动作'树).
	*
	* @author lyn
	* @param roleId
	* @return
	* @since JDK 1.7
	*/
	public List<TreeData> getActionTree(String roleId,String obj_attr_id,String obj_id);
	
	/**
	* uncheckResTree:(获得该角色下未分配的'动作'树).
	*
	* @author lyn
	* @param roleId
	* @return
	* @since JDK 1.7
	*/
	public List<TreeData> uncheckResTree(String roleId,String obj_attr_id,String obj_id);
	/**
	* getSearchTree:通过角色id 和 查询条件查询出过滤条件后的资源树.
	*
	* @author lyn
	* @param search_condition 查询条件
	* @param roleId 角色id
	* @return list对象
	* @since JDK 1.7
	*/
	public List<TreeData> getSearchTree(String roleId,String resource_id,String resource_name,String obj_attr_id,String obj_id);	/**
	* addPreTree:添加选中的数据资源.
	*
	* @author jh
	* @param pos 资源对象
	* @since JDK 1.7
	*/
	public void addPreTree(List<CIP_admin_auth_act2objPO> pos);
	
	/**
	* deleteAllAct2Obj:删除当前权限对象和根节点下所有已选的资源.
	*
	* @author jh
	* @since JDK 1.7
	*/
	public void deleteAct2Obj(List<Object []> keysArr);
	
	
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
	public List<CIP_admin_auth_act2objData> searchNodeId(String root_resource_id,String obj_attr_id,String obj_id);
	/**
	* getRourceByIds:通过资源id和权限对象id 根节点 属性id 查询出已分配来源的资源.
	*
	* @author jh
	* @param keysArr id数组
	* @return
	* @since JDK 1.7
	*/
	public List<CIP_admin_auth_act2objData> getRourceByIds(CIPPageInfo page,String root_node_id,String obj_attr_id,String obj_id);
	
	
	/**
	* searchData:查询分配数据来源的权限对象.
	*
	* @author jh
	* @param page 分页信息
	* @param conditions 查询条件
	* @return
	* @since JDK 1.7
	*/
	public List<CIP_admin_auth_act2objData> searchSource(CIPPageInfo page, String condition,String[] dataArr);
	
	/**
	* updataSource:(修改属性值配置).
	*
	* @author lyn
	* @param list
	* @return
	* @since JDK 1.7
	*/
	public List<CIP_admin_auth_act2objPO> updataSource(List<CIP_admin_auth_act2objPO> list);
}
	