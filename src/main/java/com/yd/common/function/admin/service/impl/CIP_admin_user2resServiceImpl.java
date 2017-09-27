package com.yd.common.function.admin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.exception.CIPServiceException;
import com.yd.common.function.admin.dao.CIP_admin_resourceDao;
import com.yd.common.function.admin.dao.CIP_admin_resource_2_resourceDao;
import com.yd.common.function.admin.dao.CIP_admin_rolesDao;
import com.yd.common.function.admin.dao.CIP_admin_user2resDao;
import com.yd.common.function.admin.dao.CIP_admin_user_2_rolesDao;
import com.yd.common.function.admin.data.CIP_admin_resource_treeData;
import com.yd.common.function.admin.data.CIP_admin_user2resData;
import com.yd.common.function.admin.data.po.CIP_admin_rolesPO;
import com.yd.common.function.admin.data.po.CIP_admin_user2resPO;
import com.yd.common.function.admin.data.vo.CIP_admin_user2resVO;
import com.yd.common.function.admin.service.CIP_admin_user2resService;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntimeOperator;
import com.yd.common.tag.easyui.vo.TreeData;

/**
 * <p>Service实现类</p>
 * <p>Class: CIP_admin_user2resService</p>
 *
 * @since 2016-08-31 04:49:06
 */

@Service(value="CIP_admin_user2resService")
public class CIP_admin_user2resServiceImpl implements CIP_admin_user2resService {
	
	@Autowired
	private CIP_admin_user2resDao dataDao = null;
	@Autowired
	private CIP_admin_resourceDao resourceDao = null;
	@Autowired
	private CIP_admin_user_2_rolesDao user2roleDao = null;
	@Autowired
	private CIP_admin_rolesDao roleDao = null;
	@Autowired
	private CIP_admin_resource_2_resourceDao resource_2_resourceDao = null;
	/** 
	 * 修改数据
	 */
	@Override
	public void updateData(CIP_admin_user2resVO vo, CIPRuntimeOperator operateInf){
		//TODO
		CIP_admin_user2resPO po = vo.toPO();
		Object[] keys = po.getKeys();
		CIP_admin_user2resPO po0 = dataDao.getSingle(keys);
		if( po0 == null ){
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
		}
		
		
		dataDao.update(po);
		
	}
	
	/** 
	 * 添加数据
	 */
	@Override
	public void addData(CIP_admin_user2resVO vo, CIPRuntimeOperator operateInf){
		CIP_admin_user2resPO po = vo.toPO();
	//	System.out.println(operateInf.getSubject_id());
		po.setUser_id(operateInf.getSubject_id());
		Object[] keys = po.getKeys();
		CIP_admin_user2resPO po0 = dataDao.getSingle(keys);
		if( po0!= null ){
			throw new CIPServiceException(CIPErrorCode.ERROR_DUMPLICATE_PRIMARY_KEY);
		}
		
		//TODO　添加记录基本判断
		
		dataDao.add(po);
	}
	
	/** 
	 * 删除数据
	 */
	@Override
	public void deleteData(Object[] keys, CIPRuntimeOperator operateInf){
		dataDao.delete(keys);
		
	}
	
	/** 
	 * 获取数据
	 */		
	@Override
	public CIP_admin_user2resVO getData(Object[] keys ){
		CIP_admin_user2resPO  po = dataDao.getSingle(keys);
		if(po == null)
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
			
		return po.toVO();
	}
	
	/** 
	 * 检索数据
	 */		
	@Override
	public List<CIP_admin_user2resData> searchData(CIPPageInfo page, CIPReqCondition[] conditions){
		
		List<CIP_admin_user2resData> datas = dataDao.searchData(page, conditions);
		return datas;
	}

	
	public List<Map<String, Object>> exportEntities(CIPPageInfo page,CIPReqCondition[] conditions,
			CIPRuntimeOperator operateInf, boolean xFirst){
		if(xFirst){
			//do something 
		}
		return dataDao.exportEntities(page, conditions, xFirst);
	}

	/**
	* TODO 获取主页常用工具信息.
	* @see com.yd.CIP_admin_user2resService.admin.service.cip_admin_user2resService#getToolData(java.lang.String)
	*/
	@Override
	public List<CIP_admin_user2resData> getToolData(String userId) {
		List<CIP_admin_user2resData> datas = dataDao.getToolData(userId);
		/*for(int i=0;i<datas.size();i++){
			CIP_admin_user2resData data = datas.get(i);
			if(data.getResource_name()==null || data.getResource_name().equals("")){
				String key = data.getResource_id();
				String resource_name = resourceDao.getSingle(key).getResource_name();
				data.setResource_name(resource_name);
			}		
		}*/
		return datas;
	}

	/**
	* TODO 获取当前用户对应角色下的动态菜单.
	* @see com.yd.common.function.admin.service.CIP_admin_user2resService#getResMenu(java.lang.String)
	*/
	@Override
	public List<TreeData> getResMenu(List<String> roleId) {
		Map<String,CIP_admin_resource_treeData> map = new LinkedHashMap<>();
		if (roleId.isEmpty()) {
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
		}
		for(int i=0;i<roleId.size();i++){
			CIP_admin_rolesPO role = roleDao.getSingle(roleId.get(i));
			if(role==null){
				throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
			}
			List<CIP_admin_resource_treeData> list = resource_2_resourceDao.queryByRootNodeId(role.getRoot_resource_id());
			for (int j = 0; j < list.size(); j++) {
				map.put(list.get(j).getId(), list.get(j));
			}
		}
		List<CIP_admin_resource_treeData> lists = new ArrayList<>(); 
		Set set=map.entrySet();
		Iterator iterator=set.iterator();
		while(iterator.hasNext()){
			Map.Entry<String, CIP_admin_resource_treeData> entry=(Map.Entry<String, CIP_admin_resource_treeData>)iterator.next();    
		     lists.add(entry.getValue());
		}
		List<TreeData> treeDatas = new ArrayList<>();
		for(CIP_admin_resource_treeData data : lists){
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
					if(!"A".equals(data.getType())){
						treeData.setChildren(getChildrens(lists, data.getId()));
					}
					treeDatas.add(treeData);
			}
		}
		return treeDatas;
	}
	private List<TreeData> getChildrens(List<CIP_admin_resource_treeData> allTreeDatas, String parentId){
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
						treeData.setChildren(getChildrens(allTreeDatas, data.getId()));
					}
					treeDatas.add(treeData);
				}
		    	}
			 }	

		return treeDatas;
	}

}