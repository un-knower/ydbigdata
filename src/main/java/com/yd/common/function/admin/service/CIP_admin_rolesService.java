package com.yd.common.function.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.admin.data.CIP_admin_resourceData;
import com.yd.common.function.admin.data.CIP_admin_resource_2_resourceData;
import com.yd.common.function.admin.data.CIP_admin_rolesData;
import com.yd.common.function.admin.data.vo.CIP_admin_resource_2_resourceVO;
import com.yd.common.function.admin.data.vo.CIP_admin_rolesVO;
import com.yd.common.runtime.CIPRuntimeOperator;
import com.yd.common.tag.easyui.vo.TreeData;

/**
 * <p>Service类</p>
 * <p>Class: CIP_admin_rolesService</p>
 *
 * @since 2015-07-27 02:40:52
 */

@Service
public interface CIP_admin_rolesService {

	public void updateData(CIP_admin_rolesVO d, CIPRuntimeOperator operateInf);
	
	public void addData(CIP_admin_rolesVO d, CIPRuntimeOperator operateInf);
	
	public void deleteData(Object[] keys, CIPRuntimeOperator operateInf);
	
	public CIP_admin_rolesVO getData(Object[] keys);
	
	public List<CIP_admin_rolesData> searchData(CIPPageInfo page, CIPReqCondition[] conditions);
	
	public List<CIP_admin_rolesData> searchroleData(CIPPageInfo page, CIPReqCondition[] conditions);


	/** 
	 * 导出角色信息
	 */
	public List<Map<String, Object>> exportEntities(CIPPageInfo page,CIPReqCondition[] conditions,
			CIPRuntimeOperator operateInf, boolean xFirst);
	
	public List<TreeData> getRoleTree(String roleId);
	
	public List<TreeData> getModelTree(String roleId,String root);
	
	//public List<TreeData> getActionTree(String roleId);
	/**
	* saveTree:保存资源树,并添加事务回滚.
	* @author jh
	* @param roleId 角色id
	* @return
	* @since JDK 1.7
	*/
	@Transactional(readOnly = false, rollbackFor = DataAccessException.class)
	public void saveTree(String roleId, List<CIP_admin_resource_2_resourceVO> vos);

	public List<CIP_admin_resourceData> searchResources(String roleId, CIPReqCondition[] conditions);
	
	/**
	* getDefaultTree:获取根节点为default下的资源树.
	*
	* @author jh
	* @param root 根节点
	* @return
	* @since JDK 1.7
	*/
	public List<TreeData> getDefaultTree(String root);
	
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
	/**
	* getResources:查询根节点下未分配的资源信息.
	*
	* @author jh
	* @param root 根节点
	* @param conditions
	* @return
	* @since JDK 1.7
	*/
	public List<CIP_admin_resourceData> getResources(String root,String resource_type,CIPReqCondition[] conditions);
	
	public List<CIP_admin_resource_2_resourceData> searchNodeId(String root);
}