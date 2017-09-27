package com.yd.common.function.admin.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.data.CIPReqParameter;
import com.yd.common.data.CIPResponseMsg;
import com.yd.common.data.CIPResponseQueryMsg;
import com.yd.common.exception.CIPDaoException;
import com.yd.common.exception.CIPRuntimeException;
import com.yd.common.exception.CIPServiceException;
import com.yd.common.function.admin.data.CIP_admin_auth_act2objData;
import com.yd.common.function.admin.data.po.CIP_admin_auth_act2objPO;
import com.yd.common.function.admin.service.CIP_admin_auth_objService;
import com.yd.common.function.admin.service.CIP_admin_auth_treeService;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.tag.easyui.vo.TreeData;
import com.yd.common.utils.JSONUtils;
/**
* ClassName: CIP_admin_auth_perTreeController
* date: 2016-6-23 下午1:51:12
*
* @author jh
* @version 
* @since JDK 1.7
*/
@RestController
@RequestMapping(value = "/actions/admin_auth_perTree")
public class CIP_admin_auth_perTreeController {
	
	@Autowired
	private CIP_admin_auth_objService dataService  = null;

	@Autowired
	private CIP_admin_auth_treeService treeService  = null;
	

	private static final String C_ACTION_GET_RES_TREE_DATA = "cip_admin_auth_preTree_getActionTreeData";
	/**
	* getResTree:(获得该角色下已分配的'动作'树).
	* @author lyn
	* @param roleId
	* @return
	* @since JDK 1.7
	*/
	@RequestMapping(value = "/getActionTree")
	public Object getResTree(String roleId,String obj_attr_id,String obj_id) {
		CIPResponseMsg msg = new CIPResponseMsg();
		try {
			List<TreeData> data = treeService.getActionTree(roleId,obj_attr_id,obj_id);
			return data;
		} catch (CIPServiceException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		} catch (CIPDaoException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		} catch (CIPRuntimeException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		}

		return new ArrayList<>();
	}
	private static final String C_ACTION_GET_UNCHECK_RES_TREE_DATA = "cip_admin_auth_preTree_uncheckResTreeData";
	/**
	* getResTree:(获得该角色下未分配的'动作'树).
	* @author lyn
	* @param roleId
	* @return
	* @since JDK 1.7
	*/
	@RequestMapping(value = "/uncheckResTree")
	public Object uncheckResTree(String roleId,String obj_attr_id,String obj_id) {
		CIPResponseMsg msg = new CIPResponseMsg();
		try {
			List<TreeData> data = treeService.uncheckResTree(roleId,obj_attr_id,obj_id);
			return data;
		} catch (CIPServiceException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		} catch (CIPDaoException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		} catch (CIPRuntimeException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		}

		return new ArrayList<>();
	}
	private static final String C_ACTION_SAVE_PRE_TREE = "cip_admin_auth_perTreer_savePreTree";

	/**
	* savePreTree:保存选中的数据权限树.
	*
	* @author jh
	* @param parameters 选中的数据资源id
	* @param arr (root_resource_id;//资源根节点
		         obj_attr_id;//权限对象id
		         obj_id;//权限对象属性id)
	* @return
	* @since JDK 1.7
	*/
	@RequestMapping(value = "/savePreTree")
	public CIPResponseMsg savePreTree(@RequestBody ArrayList<String> parameters,String arr[]) {
		CIPResponseMsg msg = new CIPResponseMsg();			
			try {
				boolean xAuth = true;
				List<CIP_admin_auth_act2objPO> list = new ArrayList<CIP_admin_auth_act2objPO>();
				if(xAuth){
					 String root_resource_id = arr[0];//获得资源根节点
					 String obj_attr_id = arr[1];//获得权限对象id
					 String obj_id = arr[2];//获得权限对象属性id
					for (int i = 0; i < parameters.size(); i++) {
						 String id = parameters.get(i);//获取选中节点数据资源id						
						 CIP_admin_auth_act2objPO data = new CIP_admin_auth_act2objPO();
						 data.setRoot_node_id(root_resource_id);
						 data.setResource_id(id);
						 data.setObj_id(obj_id);
						 data.setObj_attr_id(obj_attr_id);
						 list.add(data);
					}
				//	roleService.deleteAllAct2Obj(root_resource_id,obj_attr_id,obj_id);//先删除已存在的数据资源后添加
					treeService.addPreTree(list);//添加选中的数据资源
					msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
					msg.msg = CIPErrorCode.CALL_SUCCESS.name;
				}
				else{
					msg.errorCode = CIPErrorCode.ERROR_INVALID_AUTHORIZATION.code;
					msg.msg = CIPErrorCode.ERROR_INVALID_AUTHORIZATION.name;
				}		
		} catch (CIPServiceException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		} catch (CIPDaoException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		} catch (CIPRuntimeException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		}

		return msg;
	}
	private static final String C_ACTION_REMOVE_PRE_TREE = "cip_admin_auth_perTreer_removePreTree";

	/**
	* removePreTree:删除选中的数据权限树节点.
	*
	* @author 
	* @param parameters 选中的数据资源id
	* @param arr (root_resource_id;//资源根节点
		         obj_attr_id;//权限对象id
		         obj_id;//权限对象属性id)
	* @return
	* @since JDK 1.7
	*/
	@RequestMapping(value = "/removePreTree")
	public CIPResponseQueryMsg removePreTree(@RequestBody ArrayList<String> parameters,String arr[]) {
		CIPResponseQueryMsg msg = new CIPResponseQueryMsg();			
			try {
				boolean xAuth = true;
				List<Object []> keysArr = new ArrayList<Object []>();
				if(xAuth){
					 String root_node_id = arr[0];//获得资源根节点
					 String obj_attr_id = arr[1];//获得权限对象id
					 String obj_id = arr[2];//获得权限对象属性id
					for (int i = 0; i < parameters.size(); i++) {
						 String resource_id = parameters.get(i);//获取选中节点数据资源id						
						 Object [] data = new Object[4];
						 data[0]=obj_attr_id;
						 data[1]=obj_id;
						 data[2]=resource_id;
						 data[3]=root_node_id;
						 keysArr.add(data);
					}
					treeService.deleteAct2Obj(keysArr);;//删除已存在的数据资源
					msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
					msg.msg = CIPErrorCode.CALL_SUCCESS.name;
				}
				else{
					msg.errorCode = CIPErrorCode.ERROR_INVALID_AUTHORIZATION.code;
					msg.msg = CIPErrorCode.ERROR_INVALID_AUTHORIZATION.name;
				}		
		} catch (CIPServiceException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		} catch (CIPDaoException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		} catch (CIPRuntimeException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		}

		return msg;
	}
	private static final String C_ACTION_searchNodeId = "cip_admin_auth_perTreer_searchNodeId";
	/**
	* searchNodeId:查询树节点是否是已选.
	*
	* @author lyn
	* @param parameters
	* @param 
	* @return
	* @since JDK 1.7
	*/
	@RequestMapping(value = "/searchNodeId")
	public CIPResponseQueryMsg searchNodeId(@RequestBody ArrayList<String> parameters) {
		CIPResponseQueryMsg msg = new CIPResponseQueryMsg();			
			try {
				boolean xAuth = true;
				if (xAuth) {
					 String root_resource_id = parameters.get(0);
					 String obj_attr_id = parameters.get(1);
					 String obj_id = parameters.get(2);						
				List<CIP_admin_auth_act2objData> data=treeService.searchNodeId(root_resource_id,obj_attr_id,obj_id);
					msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
					msg.msg = CIPErrorCode.CALL_SUCCESS.name;
					msg.data=data;
				}else{	 
					msg.errorCode = CIPErrorCode.ERROR_INVALID_AUTHORIZATION.code;
					msg.msg = CIPErrorCode.ERROR_INVALID_AUTHORIZATION.name;
				}		
		} catch (CIPServiceException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		} catch (CIPDaoException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		} catch (CIPRuntimeException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		}

		return msg;
	}
	private static final String C_ACTION_GETSEARCHTREE_DATA = "cip_admin_auth_perTreer_getSearchTree";
	/**
	* getResTree:(获得该角色下的'动作'树).
	* @author lyn
	* @param roleId
	* @return
	* @since JDK 1.7
	*/
	@RequestMapping(value = "/getSearchTree")
	public Object getSearchTree(String dataAry[]) {
		CIPResponseMsg msg = new CIPResponseMsg();
		try {
			String roleId = dataAry[0];
			String resource_id = dataAry[1];
			String name = dataAry[2];
			String resource_name =new String(name.getBytes("ISO-8859-1"),"UTF-8");
			String obj_attr_id = dataAry[3];
			String obj_id = dataAry[4];
			List<TreeData> data = treeService.getSearchTree(roleId,resource_id,resource_name,obj_attr_id,obj_id);
			return data;
		} catch (CIPServiceException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		} catch (CIPDaoException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		} catch (CIPRuntimeException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		} catch (UnsupportedEncodingException e) {
			msg.errorCode = CIPErrorCode.ERROR_DATA_IS_MALFORM.code;
			msg.msg = CIPErrorCode.ERROR_DATA_IS_MALFORM.name;
		} 

		return new ArrayList<>();
	}
	
	private static final String C_ACTION_SET_SOURCE = "cip_admin_auth_perTreer_setSource";

	/**
	* setSource:显示选中的数据资源已分配的数据来源.
	*
	* @author jh
	* @param parameters 数据资源数组
	* @param dataArr
	* @return
	* @since JDK 1.7
	*/
	@RequestMapping(value = "/setSource")
	public CIPResponseQueryMsg setSource(CIPReqParameter parameter,String dataArr[]) {
		CIPResponseQueryMsg msg = new CIPResponseQueryMsg();			
			try {
				boolean xAuth = true;
				CIPPageInfo page = new CIPPageInfo(parameter.getPage(),parameter.getRows());
				List<CIP_admin_auth_act2objData> datas = new ArrayList<CIP_admin_auth_act2objData>();
				if(xAuth){
					String root_node_id = dataArr[0];//获得资源根节点
					String obj_attr_id = dataArr[1];//获得权限对象id
					String obj_id = dataArr[2];//获得权限对象属性id	
					datas = treeService.getRourceByIds(page,root_node_id,obj_attr_id,obj_id);
					msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
					msg.msg = CIPErrorCode.CALL_SUCCESS.name;
					msg.rows = datas;
					msg.total = page.getTotal();
				}
				else{
					msg.errorCode = CIPErrorCode.ERROR_INVALID_AUTHORIZATION.code;
					msg.msg = CIPErrorCode.ERROR_INVALID_AUTHORIZATION.name;
				}		
		} catch (CIPServiceException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		} catch (CIPDaoException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		} catch (CIPRuntimeException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		}
		return msg;
	}
	
	
	private static final String C_ACTION_SEARCH_SOURCE = "CIP_admin_auth_perTree_searchSource";
	
	/**
	* searchSource:查询分配数据来源的权限对象.
	*
	* @author jh
	* @param parameter 查询条件
	* @return
	* @since JDK 1.7
	*/
	@RequestMapping(value = "/searchSource")
	public CIPResponseQueryMsg searchSource(CIPReqParameter parameter,String[] dataArr) {
		CIPResponseQueryMsg msg = new CIPResponseQueryMsg();
		try {
			CIPPageInfo page = new CIPPageInfo(parameter.getPage(),parameter.getRows());
			
			String conditonStr = parameter.getSearch_condition();
			
			CIPReqCondition[] conditions = null;
			String condition = "";
			if(conditonStr!=null){
				Map reqCondtions = JSONUtils.convertJson2Object(conditonStr, HashMap.class);
				Set<String> keys = reqCondtions.keySet();
				CIPReqCondition req = null;
				CIPReqCondition[] tempConditions = new CIPReqCondition[keys.size()];
				int i = 0;
				String value;				
				for( String key : keys ){
					value = (String) reqCondtions.get(key);
					if(value==null || value.trim().equals(""))
						continue;
					req = new CIPReqCondition();
					req.setFieldName(key);
					req.setLowValue(value);
					tempConditions[i++] = req;
				}
				if( i > 0 ){
					conditions = new CIPReqCondition[i];
					System.arraycopy(tempConditions, 0, conditions, 0, i);
				}
				if(conditions!=null && conditions.length>0){
				 condition = conditions[0].getLowValue();
				}
			} 	
			List<CIP_admin_auth_act2objData> vos = treeService.searchSource(page,condition,dataArr);			
			msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
			msg.msg = CIPErrorCode.CALL_SUCCESS.name;
			msg.rows = vos;
			msg.total = page.getTotal();
		} catch (CIPServiceException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		} catch (CIPDaoException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		} catch (CIPRuntimeException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		}

		return msg;
	}
	
private static final String C_ACTION_auth_perTreer_updataAct2objSource = "cip_admin_auth_perTreer_updataAct2objSource";

	
	/**
	* updataSource:(修改数据权限资源).
	*
	* @author lyn
	* @param po
	* @param arr
	* @return
	* @since JDK 1.7
	*/
	@RequestMapping(value = "/updataAct2objSource")
	public CIPResponseQueryMsg updataSource(@RequestBody CIP_admin_auth_act2objPO po,String dataArr[]) {
		CIPResponseQueryMsg msg = new CIPResponseQueryMsg();			
			try {
				List<CIP_admin_auth_act2objPO> list=new ArrayList<CIP_admin_auth_act2objPO>();
				boolean xAuth = true;
				if(xAuth){
					for (int i = 0; i < dataArr.length; i++) {
						String resource_id=dataArr[i];
						CIP_admin_auth_act2objPO datas=new CIP_admin_auth_act2objPO();
						datas.setObj_attr_id(po.getObj_attr_id());
						datas.setObj_id(po.getObj_id());
						datas.setRoot_node_id(po.getRoot_node_id());
						datas.setAttr_value(po.getAttr_value());
						datas.setVal_src_type(po.getVal_src_type());
						datas.setIncl_sub_flag(po.getIncl_sub_flag());
						datas.setField_id(po.getField_id());
						datas.setResource_id(resource_id);
						list.add(datas);
					}
					treeService.updataSource(list);
					msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
					msg.msg = CIPErrorCode.CALL_SUCCESS.name;
				}
				else{
					msg.errorCode = CIPErrorCode.ERROR_INVALID_AUTHORIZATION.code;
					msg.msg = CIPErrorCode.ERROR_INVALID_AUTHORIZATION.name;
				}		
		} catch (CIPServiceException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		} catch (CIPDaoException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		} catch (CIPRuntimeException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		}

		return msg;
	}
	
	
}