package com.yd.common.function.admin.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yd.common.data.CIPOperatorType;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.data.CIPReqParameter;
import com.yd.common.data.CIPResponseMsg;
import com.yd.common.data.CIPResponseQueryMsg;
import com.yd.common.exception.CIPDaoException;
import com.yd.common.exception.CIPRuntimeException;
import com.yd.common.exception.CIPServiceException;
import com.yd.common.function.admin.data.CIP_admin_resMaxLevelData;
import com.yd.common.function.admin.data.CIP_admin_resourceData;
import com.yd.common.function.admin.data.CIP_admin_resource_2_resourceData;
import com.yd.common.function.admin.data.CIP_admin_rolesData;
import com.yd.common.function.admin.data.mapper.CIP_admin_rolesMapper;
import com.yd.common.function.admin.data.po.CIP_admin_resource_2_resourcePO;
import com.yd.common.function.admin.data.vo.CIP_admin_resource_2_resourceVO;
import com.yd.common.function.admin.data.vo.CIP_admin_rolesVO;
import com.yd.common.function.admin.service.CIP_admin_resourceService;
import com.yd.common.function.admin.service.CIP_admin_resource_2_resourceService;
import com.yd.common.function.admin.service.CIP_admin_rolesService;
import com.yd.common.function.admin.service.CIP_admin_user_2_rolesService;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntime;
import com.yd.common.tag.easyui.vo.TreeData;
import com.yd.common.utils.DateUtils;
import com.yd.common.utils.ExcelSheetParser;
import com.yd.common.utils.JSONUtils;

/**
 * <p>控制层实现类</p>
 * <p>Class: CIP_admin_rolesController</p>
 *
 * @since 2015-05-21 12:44:17
 */
@RestController
@RequestMapping(value = "/actions/admin_roles")
public class CIP_admin_rolesController {
	
	@Autowired
	private CIP_admin_rolesService dataService  = null;
	@Autowired
	private CIP_admin_user_2_rolesService userroleService  = null;
	@Autowired
	private CIP_admin_resource_2_resourceService resResService  = null;
	@Autowired
	private CIP_admin_resourceService resService  = null;
	
	private static final String C_ACTION_ADD_DATA = "cip_admin_roles_addData";
	/**
	* 新增角色信息
	*/
	@RequestMapping(value="/addData")
	public CIPResponseMsg addData(@RequestBody CIP_admin_rolesVO vo) {
		CIPResponseMsg msg = new CIPResponseMsg();
		try {
			dataService.addData(vo,CIPRuntime.getOperateSubject());
			msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
			msg.msg = CIPErrorCode.CALL_SUCCESS.name;
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

	private static final String C_ACTION_GET_DATA = "cip_admin_roles_getData";
	/**
	* 查看角色信息
	*/
	@RequestMapping(value = "/getData")
	public CIPResponseMsg getData(@RequestBody CIP_admin_rolesVO vo) {
		CIPResponseMsg msg = new CIPResponseMsg();
		try {
			Object[] keys = vo.getKeys();
			CIP_admin_rolesVO vo0 = dataService.getData(keys);
			msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
			msg.msg = CIPErrorCode.CALL_SUCCESS.name;
			msg.data = vo0;
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
	
	private static final String C_ACTION_DELETE_DATA = "cip_admin_roles_deleteData";
	/**
	* 删除角色信息
	*/
	@RequestMapping(value = "/deleteData")
	public CIPResponseMsg deleteData(@RequestBody CIP_admin_rolesVO vo) {
		CIPResponseMsg msg = new CIPResponseMsg();
		try {
			Object[] keys = vo.getKeys();
			dataService.deleteData(keys,CIPRuntime.getOperateSubject());
			if (userroleService.checkuser(vo.getRole_id())) {
				userroleService.deletebyrole(vo.getRole_id());;
			}
			msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
			msg.msg = CIPErrorCode.CALL_SUCCESS.name;
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
	
	private static final String C_ACTION_UPDATE_DATA = "cip_admin_roles_updateData";
	/**
	* 更新角色信息
	*/
	@RequestMapping(value = "/updateData")
	public CIPResponseMsg updateData(@RequestBody CIP_admin_rolesVO vo) {
		CIPResponseMsg msg = new CIPResponseMsg();
		try {
			dataService.updateData(vo, CIPRuntime.getOperateSubject());
			msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
			msg.msg = CIPErrorCode.CALL_SUCCESS.name;
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
	
	
	private static final String C_ACTION_SEARCH_DATA = "cip_admin_roles_searchData";
	
	/**
	* 查询角色信息
	*/
	@RequestMapping(value = "/searchData")
	public CIPResponseQueryMsg searchData(CIPReqParameter parameter) {
		CIPResponseQueryMsg msg = new CIPResponseQueryMsg();
		try {
			CIPPageInfo page = new CIPPageInfo(parameter.getPage(),parameter.getRows());
			
			String conditonStr = parameter.getSearch_condition();
			
			CIPReqCondition[] conditions = null;
			if(conditonStr!=null){
				Map reqCondtions = JSONUtils.convertJson2Object(conditonStr, HashMap.class);
				//CIPConfigureData0 data = JSONUtils.convertJson2Object(msg.data.toString(), CIPConfigureData0.class);
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
			}
			
			List<CIP_admin_rolesData> vos = dataService.searchData(page,conditions);
			
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

	private static final String C_ACTION_GET_RES_TREE_DATA = "cip_admin_role_getResTreeData";
	/**
	* 查看资源与资源关系
	*/
	@RequestMapping(value = "/getResTree")
	public Object getResTree(String roleId) {
		CIPResponseMsg msg = new CIPResponseMsg();
		try {
			List<TreeData> data = dataService.getRoleTree(roleId);
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
	
	private static final String C_ACTION_GET_MDL_TREE_DATA = "cip_admin_roles_getModelTree";
	/**
	* 查看资源与资源关系
	*/
	@RequestMapping(value = "/getModelTree")
	public Object getModelTree(String roleId,String root) {
		CIPResponseMsg msg = new CIPResponseMsg();
		try {
			List<TreeData> data = dataService.getModelTree(roleId,root);
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
	
	private static final String C_ACTION_SAVE_MODEL_TREE_DATA = "cip_admin_roles_saveModelTree";
	@RequestMapping(value = "/saveModelTree")
	public CIPResponseMsg saveModelTree(String roleId, @RequestBody List<CIP_admin_resource_2_resourceVO> treeData){
		CIPResponseMsg msg = new CIPResponseMsg();
		try {
			//dataService.saveTree(roleId, treeData);
			msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
			msg.msg = CIPErrorCode.CALL_SUCCESS.name;
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
	
	private static final String C_ACTION_EXPORT_EXCEL = "cip_admin_roles_exportEntities";
	
	/** 
	 * 导出Excel
	 */
	@RequestMapping(value="/exportEntities")
	public void exportEntities(CIPReqParameter parameter, HttpServletRequest request, HttpServletResponse response) throws Exception {
		OutputStream os = null;
		try {
			//初始化检索条件
			//CIPPageInfo page = new CIPPageInfo(parameter.getPage(),parameter.getRows());
			String conditonStr = parameter.getSearch_condition();
			
			CIPReqCondition[] conditions = null;
			if(conditonStr!=null){
				Map reqCondtions = JSONUtils.convertJson2Object(conditonStr, HashMap.class);
				//CIPConfigureData0 data = JSONUtils.convertJson2Object(msg.data.toString(), CIPConfigureData0.class);
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
			}
			
			//导出excel
			String as = "导出角色信息_"+DateUtils.getDate(new Date());

			String fileName = new String(as.getBytes("GB2312"), "ISO_8859_1");
			response.setCharacterEncoding("utf-8");  
			response.setContentType("application/vnd.ms-excel");  
			response.setHeader("Content-Disposition", "attachment;fileName="+fileName+".xls");  
			String sheetName = "角色信息";
			CIPPageInfo page = new CIPPageInfo(1,10000);

			//获取订单，每次最多导出1万条记录
			List<Map<String, Object>> entities = dataService.exportEntities(page, conditions, CIPRuntime.getOperateSubject(), true);
			SXSSFWorkbook wb = ExcelSheetParser.createWorkBook(sheetName,
					CIP_admin_rolesMapper.getExcelTitle(), entities);
			
			int total = page.getTotal();
			int rows = page.getRows();
			if (total > rows) {
				int count = total / rows + 1;
				for (int i = 2; i <= count; i++) {
					page.setPage(i);
					entities = dataService.exportEntities(page, conditions, CIPRuntime.getOperateSubject(), false);
					ExcelSheetParser.appendWorkBook(wb, sheetName, CIP_admin_rolesMapper.getExcelTitle(), entities);
				}
			}
			os = response.getOutputStream();
			wb.write(os);
		}
		catch (Exception e) {
			throw e;
		}finally {
			try {
				if(os!=null){
					os.flush();
					os.close();
				}
			} catch (IOException e) {
				
			}				
		}
	}
	
	private static final String C_ACTION_SAVE_TREE_DATA = "cip_admin_roles_saveTreeData";
	@RequestMapping(value = "/saveTree")
	public CIPResponseMsg saveTree(String roleId, @RequestBody List<CIP_admin_resource_2_resourceVO> treeData){
		CIPResponseMsg msg = new CIPResponseMsg();
		try {
			dataService.saveTree(roleId, treeData);
			msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
			msg.msg = CIPErrorCode.CALL_SUCCESS.name;
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
	
	private static final String C_ACTION_SEARCH_RESOURCE = "cip_admin_role_resource_searchData";
	
	/**
	* 查询角色信息
	*/
	@RequestMapping(value = "/searchResources")
	public CIPResponseQueryMsg searchResources(String roleId, CIPReqParameter parameter) {
		CIPResponseQueryMsg msg = new CIPResponseQueryMsg();
		try {
			String conditonStr = parameter.getSearch_condition();
			
			CIPReqCondition[] conditions = null;
			if(conditonStr!=null){
				Map reqCondtions = JSONUtils.convertJson2Object(conditonStr, HashMap.class);
				//CIPConfigureData0 data = JSONUtils.convertJson2Object(msg.data.toString(), CIPConfigureData0.class);
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
					req.setLowValue("%"+value+"%");
					req.setOperator(CIPOperatorType.LIKE.code);
					tempConditions[i++] = req;
				}
				if( i > 0 ){
					conditions = new CIPReqCondition[i];
					System.arraycopy(tempConditions, 0, conditions, 0, i);
				}
			}
			List<CIP_admin_resourceData> vos = dataService.searchResources(roleId, conditions);
			
			msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
			msg.msg = CIPErrorCode.CALL_SUCCESS.name;
			msg.rows = vos;
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
	private static final String C_ACTION_GET_DEFAULT_TREE_DATA = "cip_admin_role_getDefaultTreeData";
	
	/**
	* getDefaultTree:获取根节点为default下的资源树.
	*
	* @author jh
	* @param root 根节点
	* @return
	* @since JDK 1.7
	*/
	@RequestMapping(value = "/getDefaultTree")
	public Object getDefaultTree(String root) {
		CIPResponseMsg msg = new CIPResponseMsg();
		try {
			List<TreeData> data = dataService.getDefaultTree(root);
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
	
	private static final String C_ACTION_GET_BTN_AND_PAGE = "cip_admin_roles_getBtnAndPage";

	/**
	* setSource:显示选中的数据资源已分配的数据来源.
	*
	* @author jh
	* @param parameters 数据资源数组
	* @param dataArr
	* @return
	* @since JDK 1.7
	*/
	@RequestMapping(value = "/getBtnAndPage")
	public CIPResponseQueryMsg getBtnAndPage(CIPReqParameter parameter) {
		CIPResponseQueryMsg msg = new CIPResponseQueryMsg();			
			try {
				boolean xAuth = true;
				List<CIP_admin_resourceData> datas = new ArrayList<CIP_admin_resourceData>();
				if(xAuth){
				  //CIPPageInfo page = new CIPPageInfo(parameter.getPage(),parameter.getRows());
					String conditonStr = parameter.getSearch_condition();
					String parentId = "";
					String root = "";
					if(conditonStr!=null){
						Map<String, String> reqCondtions = JSONUtils.convertJson2Object(conditonStr, HashMap.class);
						parentId = reqCondtions.get("res_node_id");//获得父节点id
						root = reqCondtions.get("root");//获得资源根节点					
					} 			
					datas = dataService.getBtnAndPage(parentId,root);
					msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
					msg.msg = CIPErrorCode.CALL_SUCCESS.name;
					msg.rows = datas;
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
	private static final String C_ACTION_GET_BTN = "cip_admin_roles_getBtn";

	/**
	* setSource:获得已选页面下的动作.
	*
	* @author jh
	* @param parameter
	* @param dataArr
	* @return
	* @since JDK 1.7
	*/
	@RequestMapping(value = "/getBtn")
	public CIPResponseQueryMsg getBtn(CIPReqParameter parameter) {
		CIPResponseQueryMsg msg = new CIPResponseQueryMsg();			
			try {
				boolean xAuth = true;
				List<CIP_admin_resourceData> datas = new ArrayList<CIP_admin_resourceData>();
				if(xAuth){
				  //CIPPageInfo page = new CIPPageInfo(parameter.getPage(),parameter.getRows());
					String conditonStr = parameter.getSearch_condition();
					String parentId = "";
					String root = "";
					if(conditonStr!=null){
						Map<String, String> reqCondtions = JSONUtils.convertJson2Object(conditonStr, HashMap.class);
						parentId = reqCondtions.get("res_node_id");//获得父节点id
						root = reqCondtions.get("root");//获得资源根节点					
					} 			
					datas = dataService.getBtnAndPage(parentId,root);
					msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
					msg.msg = CIPErrorCode.CALL_SUCCESS.name;
					msg.rows = datas;
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
	
	private static final String C_ACTION_deleteRes = "cip_admin_roles_deleteRes";
	/**
	* deleteRes:删除所选资源.
	*
	* @author lyn
	* @param parameters
	* @param 
	* @return
	* @since JDK 1.7
	*/
	@RequestMapping(value = "/deleteRes")
	public CIPResponseQueryMsg deleteRes(@RequestBody CIP_admin_resource_2_resourcePO po) {
		CIPResponseQueryMsg msg = new CIPResponseQueryMsg();			
			try {
				boolean xAuth = true;
				if (xAuth) {
					 String root_node_id = po.getRoot_node_id();
					 String res_node_id = po.getRes_node_id();
					 String res_node_sup = po.getRes_node_sup();
					 Object [] keys= new Object[3];
					 keys[2]=root_node_id;
					 keys[0]=res_node_id;
					 keys[1]=res_node_sup;
					/* Object [] key= new Object[1];
					 key[0]=res_node_id;*/
					 resResService.deleteData(keys, CIPRuntime.getOperateSubject());
					 //resService.deleteData(key, CIPRuntime.getOperateSubject());
					msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
					msg.msg = CIPErrorCode.CALL_SUCCESS.name;
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
	
private static final String C_ACTION_GET_RESOURCE = "cip_admin_new_role_resource_getResource";

	/**
	* getResources:查询根节点下未分配的资源信息.
	*
	* @author jh
	* @param root 根节点
	* @param parameter
	* @return
	* @since JDK 1.7
	*/
	@RequestMapping(value = "/getResources")
	public CIPResponseQueryMsg getResources(String root,String resource_type,CIPReqParameter parameter) {
		CIPResponseQueryMsg msg = new CIPResponseQueryMsg();
		try {
			String conditonStr = parameter.getSearch_condition();
			CIPReqCondition[] conditions = null;
			if(conditonStr!=null){
				Map reqCondtions = JSONUtils.convertJson2Object(conditonStr, HashMap.class);
				//CIPConfigureData0 data = JSONUtils.convertJson2Object(msg.data.toString(), CIPConfigureData0.class);
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
					req.setLowValue("%"+value+"%");
					req.setOperator(CIPOperatorType.LIKE.code);
					tempConditions[i++] = req;
				}
				if( i > 0 ){
					conditions = new CIPReqCondition[i];
					System.arraycopy(tempConditions, 0, conditions, 0, i);
				}
			}
			List<CIP_admin_resourceData> vos = dataService.getResources(root,resource_type,conditions);
			
			msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
			msg.msg = CIPErrorCode.CALL_SUCCESS.name;
			msg.rows = vos;
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
	private static final String C_ACTION_ADMIN_ROLES_OPERATEROLES_SEARCHNODEID = "cip_admin_roles_operateRoles_searchNodeId";
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
	public CIPResponseQueryMsg searchNodeId(String root) {
		CIPResponseQueryMsg msg = new CIPResponseQueryMsg();
		try {
			List<CIP_admin_resource_2_resourceData> data = dataService.searchNodeId(root);
			msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
			msg.msg = CIPErrorCode.CALL_SUCCESS.name;
			msg.data = data;
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
	private static final String C_ACTION_ROLES_GETCHECKDATA = "cip_admin_roles_getCheckData";

	/**
	* getCheckData:保存选中的数据权限树.
	*
	* @author lyn
	* @param root;//资源根节点
		     roleId;//角色id
		      dataNode ;//res_2_resvo对象集)
	* @return
	* @since JDK 1.7
	*/
	@RequestMapping(value = "/getCheckData")
	public CIPResponseMsg getCheckData(String root,String roleId,@RequestBody List<CIP_admin_resource_2_resourceVO> dataNode) {
		CIPResponseMsg msg = new CIPResponseMsg();
		List<CIP_admin_resource_2_resourceVO> list=new ArrayList<CIP_admin_resource_2_resourceVO>();
		try {
			for (int j = 0; j < dataNode.size(); j++) {
				if (dataNode.get(j).getRes_node_sup()==root || dataNode.get(j).getRes_node_sup().equals(root)) {
					dataNode.get(j).setRes_node_sup("default");			
				}
				Object[] keys = dataNode.get(j).getKeys();
				CIP_admin_resource_2_resourceVO vo0 =new CIP_admin_resource_2_resourceVO();
				vo0 = resResService.getData(keys);
				vo0.setRoot_node_id(root);
				if (vo0.getRes_node_sup().equals("default")||vo0.getRes_node_sup()=="default") {
					vo0.setRes_node_sup(root);
				}
				list.add(vo0);
			}
			dataService.saveTree(roleId, list);
			
			msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
			msg.msg = CIPErrorCode.CALL_SUCCESS.name;
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
private static final String C_ACTION_ADMIN_ROLE_GETMAXLEVEL = "cip_admin_role_getMaxLevel";
	
	/**
	* getMaxLevel:获取根节点为default的资源树的最大层级数.
	*
	* @author LYN
	* @param root 根节点
	* @return
	* @since JDK 1.7
	*/
	@RequestMapping(value = "/getMaxLevel")
	public CIPResponseMsg getMaxLevel(String root) {
		CIPResponseMsg msg = new CIPResponseMsg();
		try {
			int num= resResService.getMaxLevel(root);
			Map<Integer,String> map =new HashMap<Integer,String>();
			map.put(-1, "一级");
			map.put(0, "二级");
			map.put(1, "三级");
			map.put(2, "四级");
			map.put(3, "五级");
			map.put(4, "六级");
			map.put(5, "七级");
			map.put(6, "八级");
			map.put(7, "九级");
			map.put(8, "十级");
			List<CIP_admin_resMaxLevelData> list=new ArrayList<CIP_admin_resMaxLevelData>();
						for (int i = -1; i <= num; i++) {
							CIP_admin_resMaxLevelData level= new CIP_admin_resMaxLevelData();
							level.setId(i+2);
							String text=map.get(i);
							level.setText(text);
							list.add(level);
						}
			msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
			msg.msg = CIPErrorCode.CALL_SUCCESS.name;
			msg.data = list;
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