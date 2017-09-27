package com.yd.common.function.admin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.exception.CIPServiceException;
import com.yd.common.function.admin.dao.CIP_admin_auth_act2objDao;
import com.yd.common.function.admin.dao.CIP_admin_auth_treeDao;
import com.yd.common.function.admin.dao.CIP_admin_op_logDao;
import com.yd.common.function.admin.dao.CIP_admin_resourceDao;
import com.yd.common.function.admin.dao.CIP_admin_resource_2_resourceDao;
import com.yd.common.function.admin.dao.CIP_admin_rolesDao;
import com.yd.common.function.admin.data.CIP_admin_auth_act2objData;
import com.yd.common.function.admin.data.CIP_admin_resource_treeData;
import com.yd.common.function.admin.data.po.CIP_admin_auth_act2objPO;
import com.yd.common.function.admin.data.po.CIP_admin_rolesPO;
import com.yd.common.function.admin.service.CIP_admin_auth_treeService;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.tag.easyui.vo.TreeData;

/**
 * <p>Service实现类</p>
 * <p>Class: CIP_admin_rolesService</p>
 *
 * @since 2015-07-27 02:40:52
 */

@Service
public class CIP_admin_auth_treeServiceImpl implements CIP_admin_auth_treeService {
	
	@Autowired
	private CIP_admin_rolesDao dataDao = null;
	@Autowired
	private CIP_admin_resourceDao resourceDao = null;
	@Autowired
	private CIP_admin_resource_2_resourceDao resource_2_resourceDao = null;
	@Autowired
	private CIP_admin_op_logDao opLogDao =null;
	@Autowired
	private CIP_admin_auth_act2objDao act2objDao =null;
	@Autowired
	private CIP_admin_auth_treeDao treeDao =null;

	
	/**
	* TODO 获得动作树(显示已选根节点下分配权限的所有'动作'树).
	* @see com.yd.common.function.admin.service.CIP_admin_auth_treeService#getActionTree(java.lang.String)
	*/
	@Override
	public List<TreeData> getActionTree(String roleId,String obj_attr_id,String obj_id) {
		CIP_admin_rolesPO role = dataDao.getSingle(roleId);
		if(role==null){
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
		}
		List<TreeData> treeDatas = new ArrayList<>();
		List<CIP_admin_resource_treeData> list = treeDao.queryByRootNodeId(role.getRoot_resource_id(),obj_attr_id,obj_id);
		for(CIP_admin_resource_treeData data : list){
		if(data.getNodeLevel()==-1 && "R".equals(data.getType())){
				TreeData treeData = new TreeData();
				treeData.setId(data.getId());
				treeData.setText(data.getText());
				treeData.setIconCls(data.getIconCls());
				Map<String, Object> attributes = new HashMap<>();
				attributes.put("type", data.getType());
				attributes.put("level", data.getNodeLevel());
				attributes.put("order", data.getNodeOrder());
				treeData.setAttributes(attributes);
				if(!"B".equals(data.getType())){
					treeData.setChildren(getAction(list, data.getId()));
				}
				treeDatas.add(treeData);
			}
		}
	
		return treeDatas;
	}
	/**
	* getAction:(过滤掉页面和表单,以及没有子节点的父节点).
	*
	* @author lyn
	* @param allTreeDatas
	* @param parentId
	* @return
	* @since JDK 1.7
	*/
	private List<TreeData> getAction(List<CIP_admin_resource_treeData> allTreeDatas, String parentId){
		List<TreeData> treeDatas = new ArrayList<>();
		for(CIP_admin_resource_treeData data : allTreeDatas){
			if (!"P".equals(data.getType())) {
				if(parentId!=null && parentId.equals(data.getParentId())){
					TreeData treeData = new TreeData();
					treeData.setId(data.getId());
					treeData.setText(data.getText());
					treeData.setIconCls(data.getIconCls());
					Map<String, Object> attributes = new HashMap<>();
					attributes.put("type", data.getType());
					attributes.put("level", data.getNodeLevel());
					attributes.put("order", data.getNodeOrder());
					treeData.setAttributes(attributes);
					if(!"B".equals(data.getType())){
						if (getAction(allTreeDatas, data.getId()).isEmpty()) {
							treeData.getAttributes().clear();
							continue;
						}else{
							treeData.setChildren(getAction(allTreeDatas, data.getId()));
						}

					}
					treeDatas.add(treeData);
				}
			}	
		}
		return treeDatas;
	}
	/**
	* TODO 添加选中的数据资源.
	* @see com.yd.common.function.admin.service.CIP_admin_auth_treeService#addPreTree(java.util.List)
	*/
	@Override
	public void addPreTree(List<CIP_admin_auth_act2objPO> pos) {
		act2objDao.batchAdd(pos);		
	}
	/**
	* TODO 删除当前权限对象及根节点下所有已经存在的资源.
	* @see com.yd.common.function.admin.service.CIP_admin_auth_treeService#deleteAllAct2Obj(java.lang.String, java.lang.String, java.lang.String)
	*/
	@Override
	public void deleteAct2Obj(List<Object []> keysArr) {
		treeDao.batchDelete(keysArr);	
	}
	/**
	* TODO 查询树节点是否是已选.
	* @see com.yd.common.function.admin.service.CIP_admin_auth_treeService#searchNodeId(java.lang.String, java.lang.String, java.lang.String)
	*/
	@Override
	public List<CIP_admin_auth_act2objData> searchNodeId(String root_resource_id,
			String obj_attr_id, String obj_id) {
		List<CIP_admin_auth_act2objData> pos= treeDao.searchNodeId(root_resource_id,obj_attr_id,obj_id);
		return pos;
	}

	
	/**
	* TODO 获得未分配的'动作'树.
	* @see com.yd.common.function.admin.service.CIP_admin_auth_treeService#uncheckResTree(java.lang.String)
	*/
	@Override
	public List<TreeData> uncheckResTree(String roleId,String obj_attr_id,String obj_id) {
		CIP_admin_rolesPO role = dataDao.getSingle(roleId);
		if(role==null){
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
		}
		List<TreeData> treeDatas = new ArrayList<>();
		List<CIP_admin_resource_treeData> list = treeDao.uncheckResTree(role.getRoot_resource_id(),obj_attr_id,obj_id);
		for(CIP_admin_resource_treeData data : list){
		if(data.getNodeLevel()==0 && "F".equals(data.getType())){
				TreeData treeData = new TreeData();
				treeData.setId(data.getId());
				treeData.setText(data.getText());
				treeData.setIconCls(data.getIconCls());
				Map<String, Object> attributes = new HashMap<>();
				attributes.put("type", data.getType());
				attributes.put("level", data.getNodeLevel());
				attributes.put("order", data.getNodeOrder());
				treeData.setAttributes(attributes);
				if(!"B".equals(data.getType())){
					treeData.setChildren(getAction(list, data.getId()));
				}
				treeDatas.add(treeData);
			}
		}
	
		return treeDatas;
	}
	/**
	* TODO 通过角色id 和 查询条件查询出过滤后未分配的'动作'树.
	* @see com.yd.common.function.admin.service.CIP_admin_auth_treeService#getSearchTree(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	*/
	@Override
	public List<TreeData> getSearchTree(String roleId, String resource_id,
			String resource_name, String obj_attr_id, String obj_id) {
		CIP_admin_rolesPO role = dataDao.getSingle(roleId);
		if(role==null){
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
		}
		List<TreeData> treeDatas = new ArrayList<>();
		List<CIP_admin_resource_treeData> list = treeDao.getSearchTree(role.getRoot_resource_id(),resource_id,resource_name,obj_attr_id,obj_id);
		for(CIP_admin_resource_treeData data : list){
		if(data.getNodeLevel()==0 && "F".equals(data.getType())){
				TreeData treeData = new TreeData();
				treeData.setId(data.getId());
				treeData.setText(data.getText());
				treeData.setIconCls(data.getIconCls());
				Map<String, Object> attributes = new HashMap<>();
				attributes.put("type", data.getType());
				attributes.put("level", data.getNodeLevel());
				attributes.put("order", data.getNodeOrder());
				treeData.setAttributes(attributes);
				if(!"B".equals(data.getType())){
					treeData.setChildren(getAction(list, data.getId()));
				}
				treeDatas.add(treeData);
			}
		}
	
		return treeDatas;
	}
	/**
	* TODO 通过资源id和权限对象id 根节点 属性id 查询出已分配来源的资源.
	* @see com.yd.common.function.admin.service.CIP_admin_auth_treeService#getRourceByIds(com.yd.common.data.CIPPageInfo, java.lang.String, java.lang.String, java.lang.String)
	*/
	@Override
	public List<CIP_admin_auth_act2objData> getRourceByIds(CIPPageInfo page,String root_node_id,String obj_attr_id, String obj_id) {
		List<CIP_admin_auth_act2objData> datas = treeDao.getRourceByIds(page,root_node_id,obj_attr_id,obj_id);
		return datas;
	}
	/**
	* TODO 查询分配数据来源的权限对象.
	* @see com.yd.common.function.admin.service.CIP_admin_auth_treeService#searchData(com.yd.common.data.CIPPageInfo, com.yd.common.data.CIPReqCondition[])
	*/
	@Override
	public List<CIP_admin_auth_act2objData> searchSource(CIPPageInfo page,String condition,String[] dataArr) {
		String root_node_id = dataArr[0];//获得资源根节点
		String obj_attr_id = dataArr[1];//获得权限对象id
		String obj_id = dataArr[2];//获得权限对象属性id	 
		List<CIP_admin_auth_act2objData> datas = treeDao.searchSource(page,condition,root_node_id,obj_attr_id,obj_id);
		return datas;
	}
	/**
	* TODO 修改属性值配置.
	* @see com.yd.common.function.admin.service.CIP_admin_auth_treeService#updataSource(java.util.List)
	*/
	@Override
	public List<CIP_admin_auth_act2objPO> updataSource(List<CIP_admin_auth_act2objPO> list) {
			act2objDao.batchUpdate(list);	
		return null;
	}
	
}