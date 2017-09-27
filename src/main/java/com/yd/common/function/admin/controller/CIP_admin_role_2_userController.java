package com.yd.common.function.admin.controller;

import java.sql.Timestamp;
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
import com.yd.common.data.CIPResponseQueryMsg;
import com.yd.common.exception.CIPDaoException;
import com.yd.common.exception.CIPRuntimeException;
import com.yd.common.exception.CIPServiceException;
import com.yd.common.function.admin.data.CIP_admin_userData;
import com.yd.common.function.admin.data.po.CIP_admin_user_2_rolesPO;
import com.yd.common.function.admin.service.CIP_admin_ass_role_userService;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntime;
import com.yd.common.utils.JSONUtils;

/**
 * <p>控制层实现类</p>
 * <p>Class: CIP_admin_user_2_rolesController</p>
 *
 * @since 2015-07-27 02:40:52
 */
@RestController
@RequestMapping(value = "/actions/admin_role_2_user")
public class CIP_admin_role_2_userController {
	
	@Autowired
	private CIP_admin_ass_role_userService dataService = null;
	
	private static final String C_ACTION_SEARCH_DATA = "CIP_admin_role_2_user_searchData";
	/**
	* searchData:查询已经分配当前角色的用户.
	*
	* @author jh
	* @param parameter
	* @return
	* @since JDK 1.7
	*/
	@RequestMapping(value = "/searchData")
	public CIPResponseQueryMsg searchData(CIPReqParameter parameter) {
		CIPResponseQueryMsg msg = new CIPResponseQueryMsg();
		try {
			CIPPageInfo page = new CIPPageInfo(parameter.getPage(),parameter.getRows());
			
			String conditonStr = parameter.getSearch_condition();
			
			CIPReqCondition[] conditions = null;
			String id = null;
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
				 id = conditions[i-1].getLowValue();//取出参数中角色id  即role_id
			}
		    List<CIP_admin_userData> vos = dataService.searchData(page, id);
			
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
	
private static final String C_ACTION_SEARCH_User = "CIP_admin_role_2_user_searchUser";
	/**
	* searchUser:查询未分配当前角色的用户.
	*
	* @author jh
	* @param parameter
	* @return
	* @since JDK 1.7
	*/
	@RequestMapping(value = "/searchUser")
	public CIPResponseQueryMsg searchUser(CIPReqParameter parameter) {
		CIPResponseQueryMsg msg = new CIPResponseQueryMsg();
		try {
			CIPPageInfo page = new CIPPageInfo(parameter.getPage(),parameter.getRows());
			
			String conditonStr = parameter.getSearch_condition();
			
			CIPReqCondition[] conditions = null;
			String id = null;
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
				id = conditions[i-1].getLowValue();//取出参数中当前角色id  即role_id
			}
			
			List<CIP_admin_userData> vos = dataService.searchUser(page,id);
			
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
	
	
private static final String C_ACTION_MOVE_User = "CIP_admin_role_2_user_moveUser";
	/**
	* moveUser:移除已经赋予角色的用户.
	*
	* @author jh
	* @param parameters（选中移除用户的id）
	* @param id  用户的角色id
	* @return
	* @since JDK 1.7
	*/
	@RequestMapping(value = "/moveUser")
	public CIPResponseQueryMsg moveUser(@RequestBody ArrayList<String> parameters,String id) {
		CIPResponseQueryMsg msg = new CIPResponseQueryMsg();
			
			try {
				boolean xAuth = true;		
				if(xAuth){
					for (int i = 0; i < parameters.size(); i++) {
						//System.out.println(filenames.get(i));
						String user_id = parameters.get(i);
						String role_id = id;
				     	dataService.moveUser(user_id,role_id);
					}
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
	
private static final String C_ACTION_ADD_User = "CIP_admin_role_2_user_addUser";
		
	/**
	* addUser:给选中的角色添加用户.
	*
	* @author jh
	* @param parameters 要添加的用户的id
	* @param id 选中的角色id
	* @return
	* @since JDK 1.7
	*/
	@RequestMapping(value = "/addUser")
	public CIPResponseQueryMsg addUser(@RequestBody ArrayList<String> parameters,String id) {
		CIPResponseQueryMsg msg = new CIPResponseQueryMsg();
			
			try {
				boolean xAuth = true;	
				List<CIP_admin_user_2_rolesPO> list = new ArrayList<CIP_admin_user_2_rolesPO>();
				if(xAuth){
					for (int i = 0; i < parameters.size(); i++) {
						String user_id = parameters.get(i);//获取用户id
						String role_id = id;//获取角色id
						String operator = CIPRuntime.getOperateSubject().getSubject_id();//获取操作人
						String upDate_time = CIPRuntime.getOperateSubject().getOperate_tm();////获取更新时间
						CIP_admin_user_2_rolesPO data = new CIP_admin_user_2_rolesPO();
						data.setRole_id(role_id);
						data.setUser_id(user_id);
						data.setOperator(operator);						
						data.setUpdate_time(Timestamp.valueOf(upDate_time));
						list.add(data);
					}
				//	System.out.println(list);
					dataService.addUser(list);
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