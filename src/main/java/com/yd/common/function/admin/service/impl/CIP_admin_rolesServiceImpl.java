package com.yd.common.function.admin.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.common.auth.CIPRoleData;
import com.yd.common.cache.CIPCacheManager;
import com.yd.common.cache.CIPCacheService;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.exception.CIPServiceException;
import com.yd.common.function.admin.dao.CIP_admin_op_logDao;
import com.yd.common.function.admin.dao.CIP_admin_resourceDao;
import com.yd.common.function.admin.dao.CIP_admin_resource_2_resourceDao;
import com.yd.common.function.admin.dao.CIP_admin_rolesDao;
import com.yd.common.function.admin.data.CIP_admin_resourceData;
import com.yd.common.function.admin.data.CIP_admin_resource_2_resourceData;
import com.yd.common.function.admin.data.CIP_admin_resource_treeData;
import com.yd.common.function.admin.data.CIP_admin_rolesData;
import com.yd.common.function.admin.data.po.CIP_admin_op_logPO;
import com.yd.common.function.admin.data.po.CIP_admin_resourcePO;
import com.yd.common.function.admin.data.po.CIP_admin_resource_2_resourcePO;
import com.yd.common.function.admin.data.po.CIP_admin_rolesPO;
import com.yd.common.function.admin.data.vo.CIP_admin_resource_2_resourceVO;
import com.yd.common.function.admin.data.vo.CIP_admin_rolesVO;
import com.yd.common.function.admin.service.CIP_admin_rolesService;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntime;
import com.yd.common.runtime.CIPRuntimeConfigure;
import com.yd.common.runtime.CIPRuntimeConstants;
import com.yd.common.runtime.CIPRuntimeOperator;
import com.yd.common.tag.easyui.vo.TreeData;

/**
 * <p>Service实现类</p>
 * <p>Class: CIP_admin_rolesService</p>
 *
 * @since 2015-07-27 02:40:52
 */

@Service
public class CIP_admin_rolesServiceImpl implements CIP_admin_rolesService {
	
	@Autowired
	private CIP_admin_rolesDao dataDao = null;
	@Autowired
	private CIP_admin_resourceDao resourceDao = null;
	@Autowired
	private CIP_admin_resource_2_resourceDao resource_2_resourceDao = null;
	@Autowired
	private CIP_admin_op_logDao opLogDao =null;
	
	/** 
	 * 修改数据
	 */
	@Override
	public void updateData(CIP_admin_rolesVO vo, CIPRuntimeOperator operateInf){
		//TODO
		CIP_admin_rolesPO po = vo.toPO();
		Object[] keys = po.getKeys();
		CIP_admin_rolesPO po0 = dataDao.getSingle(keys);
		if( po0 == null ){
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
		}
		if(!po.getRoot_resource_id().equals(po0.getRoot_resource_id())){
			throw new CIPServiceException(new CIPErrorCode(-1, "角色资源根节点不允许修改"));
		}
		
		po.setUpdate_time(Timestamp.valueOf(operateInf.getOperate_tm()));
		po.setOperator(operateInf.getSubject_id());
		
		dataDao.update(po);
		
		//使用统一授权用户时同步角色至统一授权系统,放在本地方法后执行，避免本地异常回滚远程无法回滚
		if(CIPRuntimeConfigure.cip_user_xremote){
			List<CIPRoleData> roles = new ArrayList<>();
			CIPRoleData role = new CIPRoleData();
			role.setRoleId(po.getRole_id());
			role.setRoleName(po.getRole_name());
			role.setRoleDesc("");
			role.setType("P");
			role.setOwner("");
			roles.add(role);
			CIPRuntime.authManager.syncRole2Auth("update", roles);
		}
	}
	
	/** 
	 * 添加数据
	 */
	@Override
	public void addData(CIP_admin_rolesVO vo, CIPRuntimeOperator operateInf){
		CIP_admin_rolesPO po = vo.toPO();
		Object[] keys = po.getKeys();
		CIP_admin_rolesPO po0 = dataDao.getSingle(keys);
		if( po0!= null ){
			throw new CIPServiceException(CIPErrorCode.ERROR_DUMPLICATE_PRIMARY_KEY);
		}
		if(po.getRoot_resource_id()==null || po.getRoot_resource_id().equals("")){
			throw new CIPServiceException(new CIPErrorCode(-1, "资源根节点不能为空"));
		}
		Map<String, Object>  conditions = new HashMap<>(1);
		conditions.put("root_resource_id", po.getRoot_resource_id());
		List<CIP_admin_rolesPO> list = dataDao.getByCondition(conditions);
		if(list!=null && list.size()>0){
			throw new CIPServiceException(new CIPErrorCode(-1, "资源根节点【"+po.getRoot_resource_id()+"】已存在"));
		}
		
		//TODO　添加记录基本判断
		po.setCreate_time(Timestamp.valueOf(operateInf.getOperate_tm()));
		po.setOperator(operateInf.getSubject_id());
		
		dataDao.add(po);
		
		//添加资源根节点
		CIP_admin_resourcePO admin_resourcePO = new CIP_admin_resourcePO();
		admin_resourcePO.setResource_id(po.getRoot_resource_id());
		admin_resourcePO.setResource_name("角色"+vo.getRole_id()+"根节点");
		admin_resourcePO.setResource_type("R");
		admin_resourcePO.setCreate_time(Timestamp.valueOf(operateInf.getOperate_tm()));
		admin_resourcePO.setOperator(operateInf.getSubject_id());
		resourceDao.add(admin_resourcePO);
		
		//添加根节点默认资源
		CIP_admin_resource_2_resourcePO resource_2_resourcePO = new CIP_admin_resource_2_resourcePO();
		resource_2_resourcePO.setRes_node_id(po.getRoot_resource_id());
		resource_2_resourcePO.setRes_node_sup("_"+po.getRoot_resource_id());
		resource_2_resourcePO.setRoot_node_id(po.getRoot_resource_id());
		resource_2_resourcePO.setRes_level(-1);
		resource_2_resourcePO.setNode_order(0);
		resource_2_resourcePO.setRoot_flag(1);
		resource_2_resourcePO.setLeaf_flag(0);
		resource_2_resourcePO.setCreate_time(Timestamp.valueOf(operateInf.getOperate_tm()));
		resource_2_resourcePO.setOperator(operateInf.getSubject_id());
		resource_2_resourceDao.add(resource_2_resourcePO);
		
		//将新增角色添加至系统缓存中
		CIPRuntime.authManager.setRole(po);
		
		//使用统一授权用户时同步角色至统一授权系统,放在本地方法后执行，避免本地异常回滚远程无法回滚
		if(CIPRuntimeConfigure.cip_user_xremote){
			List<CIPRoleData> roles = new ArrayList<>();
			CIPRoleData role = new CIPRoleData();
			role.setRoleId(po.getRole_id());
			role.setRoleName(po.getRole_name());
			role.setRoleDesc("");
			role.setType("P");
			role.setOwner("");
			roles.add(role);
			CIPRuntime.authManager.syncRole2Auth("add", roles);
		}
		
	}
	
	/** 
	 * 删除数据
	 */
	@Override
	public void deleteData(Object[] keys, CIPRuntimeOperator operateInf){
		CIP_admin_rolesPO po0 = dataDao.getSingle(keys);
		if( po0== null ){
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
		}
		//添加删除资源关系
		resourceDao.delete(po0.getRoot_resource_id());
		resource_2_resourceDao.batchDelete(po0.getRoot_resource_id());
		
		dataDao.delete(keys);
		
		CIPRuntime.authManager.removeRole(po0.getRole_id());
		
		//使用统一授权用户时同步角色至统一授权系统,放在本地方法后执行，避免本地异常回滚远程无法回滚
		if(CIPRuntimeConfigure.cip_user_xremote){
			List<CIPRoleData> roles = new ArrayList<>();
			CIPRoleData role = new CIPRoleData();
			role.setRoleId(po0.getRole_id());
			role.setRoleName(po0.getRole_name());
			role.setRoleDesc("");
			role.setType("P");
			role.setOwner("");
			roles.add(role);
			CIPRuntime.authManager.syncRole2Auth("delete", roles);
		}
	}
	
	/** 
	 * 获取数据
	 */		
	@Override
	public CIP_admin_rolesVO getData(Object[] keys ){
		CIP_admin_rolesPO  po = dataDao.getSingle(keys);
		if(po == null)
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
			
		return po.toVO();
	}
	
	/** 
	 * 检索数据
	 */		
	@Override
	public List<CIP_admin_rolesData> searchData(CIPPageInfo page, CIPReqCondition[] conditions){
		
		List<CIP_admin_rolesData> datas = dataDao.searchData(page, conditions);
		return datas;
	}
	
	/** 
	 * 检索未分配角色
	 */		
	@Override
	public List<CIP_admin_rolesData> searchroleData(CIPPageInfo page, CIPReqCondition[] conditions){
		
		List<CIP_admin_rolesData> datas = dataDao.searchroleData(page, conditions);
		return datas;
	}

	
	public List<Map<String, Object>> exportEntities(CIPPageInfo page,CIPReqCondition[] conditions,
			CIPRuntimeOperator operateInf, boolean xFirst){
		if(xFirst){
			CIP_admin_op_logPO log = new CIP_admin_op_logPO();
			log.setOp_seq_no(System.currentTimeMillis());
			log.setOp_table_id("cip_admin_roles");
			log.setOp_obj_id("");
			log.setOp_type("E");
			log.setRemark("批量导出数据");
			log.setOperator(operateInf.getSubject_id());
			log.setCreate_time(Timestamp.valueOf(operateInf.getOperate_tm()));
			opLogDao.add(log);
		}
		return dataDao.exportEntities(page, conditions, xFirst);
	}
	
	@Override
	public List<TreeData> getRoleTree(String roleId) {

		CIP_admin_rolesPO role = dataDao.getSingle(roleId);
		if(role==null){
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
		}
		List<TreeData> treeDatas = new ArrayList<>();
		List<CIP_admin_resource_treeData> list = resource_2_resourceDao.queryByRootNodeId(role.getRoot_resource_id());
		for(CIP_admin_resource_treeData data : list){
			//根节点为虚拟节点不显示
/*			if(data.getNodeLevel()==-1){
				continue;
			}*/
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
						treeData.setChildren(getChildrens(list, data.getId()));
					}
					
					treeDatas.add(treeData);
			}
		}
		return treeDatas;
	}
	
	
	private List<TreeData> getChildrens(List<CIP_admin_resource_treeData> allTreeDatas, String parentId){
		List<TreeData> treeDatas = new ArrayList<>();
		for(CIP_admin_resource_treeData data : allTreeDatas){			
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
						treeData.setChildren(getChildrens(allTreeDatas, data.getId()));
					}
					treeDatas.add(treeData);
				}	
		}
		return treeDatas;
	}
	
	@Override
	public List<TreeData> getModelTree(String roleId,String root) {

		CIP_admin_rolesPO role = dataDao.getSingle(roleId);
		String root_resource_id=role.getRoot_resource_id();
		String name = resourceDao.searchNameById(root_resource_id).getResource_name();
		if(role==null){
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
		}
		List<TreeData> treeDatas = new ArrayList<>();
		List<CIP_admin_resource_treeData> list = resource_2_resourceDao.queryByRootNodeId(root);
		for(CIP_admin_resource_treeData data : list){
			//根节点为虚拟节点不显示
/*			if(data.getNodeLevel()==-1){
				continue;
			}*/
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
						treeData.setChildren(getChildrens(list, data.getId()));
					}
					
					treeDatas.add(treeData);
			}
		}
		
		treeDatas.get(0).setId(root_resource_id);
		treeDatas.get(0).setText(name);
		return treeDatas;
	}
	

	@Override
	public void saveTree(String roleId, List<CIP_admin_resource_2_resourceVO> vos) {
		CIP_admin_rolesPO role = dataDao.getSingle(roleId);
		if(role==null){
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
		}
		resource_2_resourceDao.batchDelete(role.getRoot_resource_id());
		List<CIP_admin_resource_2_resourcePO> pos = new ArrayList<>();
		for(CIP_admin_resource_2_resourceVO vo : vos){
			CIP_admin_resource_2_resourcePO po = vo.toPO();
			po.setRoot_node_id(role.getRoot_resource_id());
			po.setRoot_flag(0);
			po.setLeaf_flag(0);
			pos.add(po);
		}
		resource_2_resourceDao.batchAdd(pos);
		
		//刷新角色资源缓存
		CIPRuntime.authManager.reloadResources(roleId);
		//清空缓存服务中角色资源，解决角色资源树重新保存时用户资源树使用之前的缓存
		CIPCacheService cache = CIPCacheManager.getCacheService();
		cache.remove(CIPRuntimeConfigure.cip_system_id+"_"+CIPRuntimeConstants.CIP_MENU_ID+roleId);
	}

	@Override
	public List<CIP_admin_resourceData> searchResources(String roleId, CIPReqCondition[] conditions) {
		CIP_admin_rolesPO role = dataDao.getSingle(roleId);
		if(role==null){
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
		}
		List<CIP_admin_resourceData> datas = resourceDao.queryRoleNotAssignresource(role.getRoot_resource_id(), conditions);
		return datas;
	}

	/**
	* TODO 获取根节点为default下的资源树.
	* @see com.yd.common.function.admin.service.CIP_admin_rolesService#getDefaultTree(java.lang.String)
	*/
	@Override
	public List<TreeData> getDefaultTree(String root) {
		List<TreeData> treeDatas = new ArrayList<>();
		List<CIP_admin_resource_treeData> list = resource_2_resourceDao.queryByRootNodeId(root);
		for(CIP_admin_resource_treeData data : list){
			//根节点为虚拟节点不显示
/*			if(data.getNodeLevel()==-1){
				continue;
			}*/
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
					if(!"A".equals(data.getType())){
						treeData.setChildren(getDefaultTreeChildrens(list, data.getId()));
					}
					treeDatas.add(treeData);
			}
		}
		return treeDatas;
	}
	private List<TreeData> getDefaultTreeChildrens(List<CIP_admin_resource_treeData> allTreeDatas, String parentId){
		List<TreeData> treeDatas = new ArrayList<>();
		for(CIP_admin_resource_treeData data : allTreeDatas){
			if (!"B".equals(data.getType()) && !"P".equals(data.getType())){
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
						treeData.setChildren(getDefaultTreeChildrens(allTreeDatas, data.getId()));
					}
					treeDatas.add(treeData);
				}
		    	}
			 }	

		return treeDatas;
	}

	/**
	* TODO 获取菜单项下的动作和页面资源.）.
	* @see com.yd.common.function.admin.service.CIP_admin_rolesService#getBtnAndPage(java.lang.String, java.lang.String)
	*/
	@Override
	public List<CIP_admin_resourceData> getBtnAndPage(String parentId,String root) {
		List<CIP_admin_resourceData> datas = dataDao.getBtnAndPage(parentId,root);
		return datas;
	}

	/**
	* TODO 查询根节点下未分配的资源信息.
	* @see com.yd.common.function.admin.service.CIP_admin_rolesService#getResources(java.lang.String, com.yd.common.data.CIPReqCondition[])
	*/
	@Override
	public List<CIP_admin_resourceData> getResources(String root,String resource_type,
			CIPReqCondition[] conditions) {
		if(root==null||resource_type==null){
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
		}
		List<CIP_admin_resourceData> datas = resourceDao.queryRoleNotAssignresource(root, conditions);
		if(resource_type=="P"||resource_type.equals("P")){
			for (int i = 0; i < datas.size(); i++) {
				if(datas.get(i).getResource_type().equals("F")|| datas.get(i).getResource_type().equals("A")||datas.get(i).getResource_type().equals("P")||datas.get(i).getResource_type().equals("R")){
					datas.remove(i);
					i--;
				}
			}
		}else if(resource_type=="A"||resource_type.equals("A")){
			for (int i = 0; i < datas.size(); i++) {
				if(datas.get(i).getResource_type().equals("F")|| datas.get(i).getResource_type().equals("A")||datas.get(i).getResource_type().equals("R")){
					datas.remove(i);
					i--;
				}
			}	
		}else if(resource_type=="F"||resource_type.equals("F")){
			for (int i = 0; i < datas.size(); i++) {
				/*if(datas.get(i).getResource_type().equals("F")||datas.get(i).getResource_type().equals("R")){
					datas.remove(i);
					i--;
				}*/
				if(datas.get(i).getResource_type().equals("R")){
					datas.remove(i);
					i--;
				}
			}	
		}else{
			for (int i = 0; i < datas.size(); i++) {
				if(datas.get(i).getResource_type().equals("R")){
					datas.remove(i);
					i--;
				}
			}
		}
		return datas;
	}

	@Override
	public List<CIP_admin_resource_2_resourceData> searchNodeId(String root) {
		List<CIP_admin_resource_2_resourceData> data=resource_2_resourceDao.searchNodeId(root);
		return data;
	}
}