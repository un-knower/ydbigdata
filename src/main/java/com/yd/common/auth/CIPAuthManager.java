package com.yd.common.auth;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.yd.common.auth.behavior.CIPAuthKey;
import com.yd.common.auth.behavior.CIPAuthLock;
import com.yd.common.auth.behavior.CIPAuthObject;
import com.yd.common.auth.dao.CIPAuthBehaviorDao;
import com.yd.common.cipher.CIPDesUtils;
import com.yd.common.data.CIPOperatorType;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.exception.CIPRuntimeException;
import com.yd.common.function.admin.constants.CIPAdminConstants;
import com.yd.common.function.admin.dao.CIP_admin_access_ctrlDao;
import com.yd.common.function.admin.dao.CIP_admin_access_relDao;
import com.yd.common.function.admin.dao.CIP_admin_access_resDao;
import com.yd.common.function.admin.dao.CIP_admin_auth_act2objDao;
import com.yd.common.function.admin.dao.CIP_admin_auth_attrDao;
import com.yd.common.function.admin.dao.CIP_admin_auth_objDao;
import com.yd.common.function.admin.dao.CIP_admin_auth_roleDao;
import com.yd.common.function.admin.dao.CIP_admin_auth_userDao;
import com.yd.common.function.admin.dao.CIP_admin_resourceDao;
import com.yd.common.function.admin.dao.CIP_admin_resource_2_resourceDao;
import com.yd.common.function.admin.dao.CIP_admin_rolesDao;
import com.yd.common.function.admin.dao.CIP_admin_user_settingDao;
import com.yd.common.function.admin.data.po.CIP_admin_access_ctrlPO;
import com.yd.common.function.admin.data.po.CIP_admin_access_relPO;
import com.yd.common.function.admin.data.po.CIP_admin_access_resPO;
import com.yd.common.function.admin.data.po.CIP_admin_auth_act2objPO;
import com.yd.common.function.admin.data.po.CIP_admin_auth_attrPO;
import com.yd.common.function.admin.data.po.CIP_admin_auth_objPO;
import com.yd.common.function.admin.data.po.CIP_admin_auth_rolePO;
import com.yd.common.function.admin.data.po.CIP_admin_auth_userPO;
import com.yd.common.function.admin.data.po.CIP_admin_rolesPO;
import com.yd.common.function.admin.data.po.CIP_admin_user_settingPO;
import com.yd.common.function.auth.dao.CIPRole2ResourceDao;
import com.yd.common.function.auth.data.CIPRole2ResourceData;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntime;
import com.yd.common.runtime.CIPRuntimeConfigure;
import com.yd.common.runtime.CIPRuntimeConstants;
import com.yd.common.runtime.CIPRuntimeOperator;
import com.yd.common.session.CIPUser;
import com.yd.common.utils.HttpUtils;
import com.yd.common.utils.JSONUtils;

@Service
public class CIPAuthManager {

	@Autowired
	private CIP_admin_rolesDao roleDao = null;
	@Autowired
	private CIPRole2ResourceDao role2ResDao = null;
	@Autowired
	private CIP_admin_resource_2_resourceDao resource2resourceDao = null;
	@Autowired
	private CIP_admin_resourceDao resourceDao = null;
	@Autowired
	private CIP_admin_auth_userDao authUserDao = null;
	@Autowired
	private CIP_admin_auth_roleDao authRoleDao = null;
	@Autowired
	private CIP_admin_auth_objDao authObjDao = null;
	@Autowired
	private CIP_admin_auth_attrDao authAttrDao = null;
	@Autowired
	private CIP_admin_user_settingDao authUserSettingDao = null;
	@Autowired
	private CIP_admin_auth_act2objDao auth2ObjDao = null;
	@Autowired
	private CIPAuthBehaviorDao authDataDao = null;
	@Autowired
	private CIP_admin_access_ctrlDao accessCtrlDao = null;
	@Autowired
	private CIP_admin_access_relDao accessRelDao = null;
	@Autowired
	private CIP_admin_access_resDao accessResDao = null;

	private Map<String, List<String>> userRoles = new HashMap<String, List<String>>();
	private Map<String, CIP_admin_rolesPO> mRoles = new HashMap<String, CIP_admin_rolesPO>();
	private Map<String, String> authUserColumns = null;

	private Map<String, Map<String, String>> userAuths = new HashMap<String, Map<String, String>>();
	private Map<String, Map<String, String>> userSettingAuths = new HashMap<String, Map<String, String>>();
	private Map<String, Map<String, String>> roleAuths = null;
	private Map<String, CIPAuthObject> authObjects = new HashMap<String, CIPAuthObject>();

	private Map<String, Map<String, String>> mAuthResources = new HashMap<String, Map<String, String>>();
	private Map<String, CIPUserProfileData> mUsers = new HashMap<String, CIPUserProfileData>();

	// private Map<String,String> mAccessRelations = new
	// HashMap<String,String>();
	// private Map<String,List<CIP_admin_access_resPO>> mAccessResources = new
	// HashMap<String,List<CIP_admin_access_resPO>>();
	//
	// 0.初始化授权管理
	public void init() {
		initRoles();
		initUserColumns();
		initRoleAuth();
		initAuthObject();
		reloadResources();

		// initSystemAccess();
	}

	//
	// private void initSystemAccess() {
	// // TODO Auto-generated method stub
	//
	// }

	// 0.用户验证-remote validation
	public CIPUserProfileData validateUser(String userId, String pwd)
			throws CIPRuntimeException {
		// 封装参数
		Map<String, String> params = new HashMap<String, String>();
		params.put("authId", String.valueOf(CIPRuntimeConfigure.cip_auth_Id));
		params.put("userId", userId);
		params.put("password", pwd);

		CIPUserProfileData user = new CIPUserProfileData();
		// POST请求获取数据
		String response;
		try {
			response = HttpUtils
					.post(CIPRuntimeConfigure.cip_login_url, params);
		} catch (Exception e) {
			throw new CIPRuntimeException(new CIPErrorCode(-1, "调用授权系统服务失败"));
		}
		Map<String, Object> responseMap = JSONUtils.convertJson2Map(response);
		int retcode = (int) responseMap.get("retcode");

		if (retcode != 0) {// 登录失败
			String retmsg = (String) responseMap.get("retmsg");
			throw new CIPRuntimeException(new CIPErrorCode(retcode, retmsg));
		} else {
			boolean result = (boolean) responseMap.get("result");
			if (!result) {
				String retmsg = (String) responseMap.get("retmsg");
				throw new CIPRuntimeException(new CIPErrorCode(-1, retmsg));
			}
		}

		Map<String, Object> userMap = (Map<String, Object>) responseMap
				.get("userData");
		// 解析用户基本信息
		user.user_id = userId;
		user.user_name = (String) userMap.get("userName");
		user.org_id = (String) userMap.get("orgCode");
		user.org_name = (String) userMap.get("orgName");
		user.org_type = (String) userMap.get("orgType");
		// 登录成功后将用户信息放入缓存中
		mUsers.put(userId, user);
		return user;
	}

	public CIPUserProfileData validateUserAndGetRoles(String userId, String pwd)
			throws CIPRuntimeException {
		// 封装参数
		Map<String, String> params = new HashMap<String, String>();
		params.put("authId", String.valueOf(CIPRuntimeConfigure.cip_auth_Id));
		params.put("userId", userId);
		params.put("password", pwd);

		CIPUserProfileData user = new CIPUserProfileData();
		// POST请求获取数据
		String response;
		try {
			response = HttpUtils.post(CIPRuntimeConfigure.cip_login_role_url,
					params);
		} catch (Exception e) {
			throw new CIPRuntimeException(new CIPErrorCode(-1, "调用授权系统服务失败"));
		}
		Map<String, Object> responseMap = JSONUtils.convertJson2Map(response);
		int retcode = (int) responseMap.get("retcode");

		if (retcode != 0) {// 登录失败
			String retmsg = (String) responseMap.get("retmsg");
			throw new CIPRuntimeException(new CIPErrorCode(retcode, retmsg));
		} else {
			boolean result = (boolean) responseMap.get("result");
			if (!result) {
				String retmsg = (String) responseMap.get("retmsg");
				throw new CIPRuntimeException(new CIPErrorCode(-1, retmsg));
			}
		}

		Map<String, Object> userMap = (Map<String, Object>) responseMap
				.get("userData");
		// 解析用户基本信息
		user.user_id = userId;
		user.user_name = (String) userMap.get("userName");
		user.org_id = (String) userMap.get("orgCode");
		user.org_name = (String) userMap.get("orgName");
		user.org_type = (String) userMap.get("orgType");
		String roles = (String) responseMap.get("userRole");
		if (roles != null) {
			List<String> list = Arrays.asList(roles.split(","));
			user.user_roles = list;
		}
		// 登录成功后将用户信息放入缓存中
		mUsers.put(userId, user);
		return user;
	}

	public CIPUserProfileData getUser(String userId) throws CIPRuntimeException {
		// 封装参数
		Map<String, String> params = new HashMap<String, String>();
		params.put("authId", String.valueOf(CIPRuntimeConfigure.cip_auth_Id));
		params.put("userId", userId);

		CIPUserProfileData user = new CIPUserProfileData();
		// POST请求获取数据
		String response;
		try {
			response = HttpUtils.post(CIPRuntimeConfigure.cip_user_url, params);
		} catch (Exception e) {
			throw new CIPRuntimeException(new CIPErrorCode(-1, "调用授权系统服务失败"));
		}
		Map<String, Object> responseMap = JSONUtils.convertJson2Map(response);
		int retcode = (int) responseMap.get("retcode");

		if (retcode != 0) {// 登录失败
			String retmsg = (String) responseMap.get("retmsg");
			throw new CIPRuntimeException(new CIPErrorCode(retcode, retmsg));
		}

		Map<String, Object> userMap = (Map<String, Object>) responseMap
				.get("userData");
		// 解析用户基本信息
		user.user_id = userId;
		user.user_name = (String) userMap.get("userName");
		user.org_id = (String) userMap.get("orgCode");
		user.org_name = (String) userMap.get("orgName");
		user.org_type = (String) userMap.get("orgType");
		mUsers.put(userId, user);
		return user;
	}

	public CIPUserProfileData getUserAndRoles(String userId)
			throws CIPRuntimeException {
		// 封装参数
		Map<String, String> params = new HashMap<String, String>();
		params.put("authId", String.valueOf(CIPRuntimeConfigure.cip_auth_Id));
		params.put("userId", userId);

		CIPUserProfileData user = new CIPUserProfileData();
		// POST请求获取数据
		String response;
		try {
			response = HttpUtils.post(CIPRuntimeConfigure.cip_user_role_url,
					params);
		} catch (Exception e) {
			throw new CIPRuntimeException(new CIPErrorCode(-1, "调用授权系统服务失败"));
		}
		Map<String, Object> responseMap = JSONUtils.convertJson2Map(response);
		int retcode = (int) responseMap.get("retcode");

		if (retcode != 0) {// 登录失败
			String retmsg = (String) responseMap.get("retmsg");
			throw new CIPRuntimeException(new CIPErrorCode(-1, retmsg));
		}

		Map<String, Object> userMap = (Map<String, Object>) responseMap
				.get("userData");
		// 解析用户基本信息
		user.user_id = userId;
		user.user_name = (String) userMap.get("userName");
		user.org_id = (String) userMap.get("orgCode");
		user.org_name = (String) userMap.get("orgName");
		user.org_type = (String) userMap.get("orgType");
		String roles = (String) responseMap.get("userRole");
		if (roles != null) {
			List<String> list = Arrays.asList(roles.split(","));
			user.user_roles = list;
		}
		mUsers.put(userId, user);
		return user;
	}

	public void setUserProfile(CIPUserProfileData userData) {
		mUsers.put(userData.user_id, userData);
	}

	public void setRole(CIP_admin_rolesPO role) {
		mRoles.put(role.getRole_id(), role);
	}

	public void removeRole(String roleId) {
		mRoles.remove(roleId);
	}

	// 1.资源授权-remote
	public List<CIPResource> getAuthResource(String userId)
			throws CIPRuntimeException {
		// 封装参数
		Map<String, String> params = new HashMap<String, String>();
		params.put("authId", String.valueOf(CIPRuntimeConfigure.cip_auth_Id));
		params.put("userId", userId);
		List<CIPResource> menus = null;
		String response;
		try {
			response = HttpUtils.post(CIPRuntimeConfigure.cip_menu_url, params);
		} catch (Exception e) {
			throw new CIPRuntimeException(new CIPErrorCode(-1, "调用授权系统服务失败"));
		}
		Map<String, Object> responseMap = JSONUtils.convertJson2Object(
				response, Map.class);
		int retcode = (int) responseMap.get("retcode");

		if (retcode != 0) {// 接口调用失败
			String retmsg = (String) responseMap.get("retmsg");
			throw new CIPRuntimeException(new CIPErrorCode(retcode, retmsg));
		}
		menus = JSON.parseArray(JSON.toJSONString(responseMap.get("menus")),
				CIPResource.class);
		return menus;
	}

	public CIPResource[] getAuthResource(String userId, String resourceId) {

		return null;
	}

	public boolean syncRole2Auth(String synFlag, List<CIPRoleData> roles)
			throws CIPRuntimeException {
		// 封装参数
		Map<String, String> params = new HashMap<String, String>();
		params.put("authId", String.valueOf(CIPRuntimeConfigure.cip_auth_Id));
		params.put("synFlag", synFlag);
		params.put("roles", JSONUtils.convertObject2Json(roles));
		String response;
		try {
			response = HttpUtils.post(
					CIPRuntimeConfigure.cip_sync_role_to_auth_url, params);
		} catch (Exception e) {
			throw new CIPRuntimeException(new CIPErrorCode(-1, "调用授权系统服务失败"));
		}
		Map<String, Object> responseMap = JSONUtils.convertJson2Object(
				response, Map.class);
		int retcode = (int) responseMap.get("retcode");

		if (retcode != 0) {// 接口调用失败
			String retmsg = (String) responseMap.get("retmsg");
			throw new CIPRuntimeException(new CIPErrorCode(retcode, retmsg));
		}
		return true;
	}

	// 3. 初始化用户权限字段信息
	public void initUserAuth(CIPUser user) {
		userRoles.put(user.getUserId(), user.getUserRoles());
		CIPUserProfileData userProfile = mUsers.get(user.getUserId());
		Map<String, String> userAuth = userAuths.get(user.getUserId());
		String authAttrId;
		if (userAuth == null) {
			// 本地用户权限字段配置表 == > 获取当前用户的相关字段信息
			if (authUserColumns != null) {
				Set<String> columns = authUserColumns.keySet();
				userAuth = new HashMap<String, String>(columns.size());
				Object columnValue;
				for (String column : columns) {
					columnValue = getFieldValue(userProfile, column);
					if (columnValue != null) {
						authAttrId = authUserColumns.get(column);
						if (authAttrId != null && !"".equals(authAttrId))
							userAuth.put(authAttrId, columnValue.toString());
						userAuth.put(column, columnValue.toString());
					}
				}
				userAuths.put(user.getUserId(), userAuth);
			} else {
				userAuth = new HashMap<String, String>();
				userAuths.put(user.getUserId(), userAuth);
			}

			// 本地用户权限值配置表 ==》获取当前用户的个性设置信息
			Map<String, Object> conditions = new HashMap<String, Object>(1);
			conditions.put("user_id", user.getUserId());
			List<CIP_admin_user_settingPO> userSettings = authUserSettingDao
					.getByCondition(conditions);
			if (userSettings != null && userSettings.size() > 0) {
				Map<String, String> userSettingAuth = new HashMap<String, String>(
						userSettings.size());
				userSettingAuths.put(user.getUserId(), userSettingAuth);
				for (CIP_admin_user_settingPO userSetting : userSettings) {
					userSettingAuth.put(userSetting.getUser_key(),
							userSetting.getUser_value());
				}
			}

		}
	}

	private Object getFieldValue(CIPUserProfileData userProfile, String column) {
		try {
			Field f = CIPUserProfileData.class.getField(column);
			return f.get(userProfile);
		} catch (Exception e) {
			return null;
		}
	}

	// 3.0.1 初始化用户权限位
	public void initUserColumns() {

		List<CIP_admin_auth_userPO> userConfs = authUserDao.getAll();
		CIP_admin_auth_userPO userConf;
		int size = userConfs.size();
		authUserColumns = new HashMap<String, String>(size);
		for (int i = 0; i < size; i++) {
			userConf = userConfs.get(i);
			authUserColumns.put(userConf.getUser_attr_id(),
					userConf.getAuth_attr_id());
		}
	}

	// 3.0.2 初始化角色权限值配置
	public void initRoleAuth() {
		String roleId = null;
		List<CIP_admin_auth_rolePO> roleConfs = authRoleDao.getAll();
		roleAuths = new HashMap<String, Map<String, String>>();
		Map<String, String> attrs = null;
		for (CIP_admin_auth_rolePO roleConf : roleConfs) {
			if (!roleConf.getAuth_role_id().equals(roleId)) {
				roleId = roleConf.getAuth_role_id();
				attrs = new HashMap<String, String>();
				roleAuths.put(roleId, attrs);
			}
			attrs.put(roleConf.getAuth_attr_id(), roleConf.getAuth_attr_val());
		}
	}

	public void logoutUserAuth(String userId) {
		mUsers.remove(userId);
		userRoles.remove(userId);
		userAuths.remove(userId);
		userSettingAuths.remove(userId);
	}

	// 3.1访问控制
	public CIPErrorCode checkAccess(CIPUser loginUser,
			String resourceId, String resourceUri) {

		// 此处暂时只有本地校验
		if ("index".equals(resourceId)) {
			// 检查uri
			if (resourceUri.indexOf("index.html") == -1) {
				return CIPErrorCode.ERROR_INVALID_AUTHORIZATION;
			}
		} else if (resourceId != null) {
			List<String> userRoles = loginUser.getUserRoles();
			if (userRoles == null) {
				return CIPErrorCode.ERROR_INVALID_AUTHORIZATION;
			}
			Map<String, String> authResources;
			boolean xNotAuth = true;
			String actionUrl = null;
			for (String userRole : userRoles) {
				authResources = mAuthResources.get(userRole);
				if (authResources == null)
					break;
				actionUrl = authResources.get(resourceId);
				if (actionUrl != null && resourceUri.endsWith(actionUrl)) {
					xNotAuth = false;
					break;
				}
			}

			if (xNotAuth) {
				return CIPErrorCode.ERROR_INVALID_AUTHORIZATION;
			}
		} else {
			return CIPErrorCode.ERROR_INVALID_AUTHORIZATION;
		}

		return null;
	}

	// 3.2行为控制 ---- 这里要采用并行处理，否则会有性能瓶颈
	public boolean checkBehavior(String actionId, CIPAuthLock lock) {
		// 2. 检查属性
		CIPRuntimeOperator operator = CIPRuntime.getOperateSubject();
		String userId = operator.getSubject_id();
		Map<String, String> userAuth = userAuths.get(userId);
		Map<String, String> userSettingAuth = userSettingAuths.get(userId);

		String objectId = lock.getObjectId();
		CIPAuthObject authObject = authObjects.get(objectId);
		CIPAuthKey authKey = authObject.getKey();

		// a、获取当前用户对应的配置信息
		List<String> roles = userRoles.get(userId);
		if (roles == null) {
			return false;
		}
		List<CIP_admin_auth_act2objPO> action2Objs = null;
		CIP_admin_auth_act2objPO action2Obj;
		int size;
		String valSrcType;
		String incl_sub_flag;
		CIP_admin_auth_attrPO valueTable;
		String attrId;
		String attrValue;
		boolean xSub;
		String supStr;
		CIP_admin_rolesPO rolePO = null;
		Map<String, Object> conditions = new HashMap<String, Object>(3);
		for (String role : roles) {
			rolePO = mRoles.get(role);
			conditions.put(CIPAuthConstants.AUTH_OBJ_ID, objectId);
			conditions.put(CIPAuthConstants.AUTH_RESOURCE_ID, actionId);
			conditions.put(CIPAuthConstants.AUTH_ROOT_NODE_ID,
					rolePO.getRoot_resource_id());
			action2Objs = auth2ObjDao.getByCondition(conditions);
			if (action2Objs != null) {
				size = action2Objs.size();
				for (int i = 0; i < size; i++) {
					action2Obj = action2Objs.get(i);
					attrId = action2Obj.getObj_attr_id();
					attrValue = action2Obj.getAttr_value();
					authKey.setAuthValue(attrId, attrValue);
					valSrcType = action2Obj.getVal_src_type();
					incl_sub_flag = action2Obj.getIncl_sub_flag();
					if (valSrcType
							.equals(CIPAuthConstants.C_AUTH_ATTR_SRC_ROLE)) {
						attrValue = roleAuths.get(role).get(attrId);
						authKey.setAuthValue(attrId, attrValue);
					} else if (valSrcType
							.equals(CIPAuthConstants.C_AUTH_ATTR_SRC_USER)) {
						attrValue = userAuth.get(attrId);
						authKey.setAuthValue(attrId, attrValue);
					} else if (valSrcType
							.equals(CIPAuthConstants.C_AUTH_ATTR_SRC_USER_SETTING)) {
						if (userSettingAuth != null) {
							attrValue = userSettingAuth.get(attrId);
							authKey.setAuthValue(attrId, attrValue);
						}
					}

					if (incl_sub_flag
							.equals(CIPAuthConstants.C_AUTH_INCL_SUB_FLAG_DIRECT)) {
						valueTable = authObject.getValueTable(attrId);
						if (valueTable == null)
							break;

						xSub = authDataDao.isSubExist(
								valueTable.getAttr_val_table(),
								valueTable.getSup_col_id(),
								valueTable.getSub_col_id(), attrValue,
								lock.getAuthValue(attrId));
						if (xSub)
							authKey.setAuthValue(attrId,
									lock.getAuthValue(attrId));
					} else if (incl_sub_flag
							.equals(CIPAuthConstants.C_AUTH_INCL_SUB_FLAG_ALL)) {
						valueTable = authObject.getValueTable(attrId);
						if (valueTable == null)
							break;

						supStr = authDataDao.getSupRelation(
								valueTable.getAttr_val_table(),
								valueTable.getSub_col_id(),
								lock.getAuthValue(attrId),
								valueTable.getRel_col_id());
						if (supStr != null) {
							supStr = "," + supStr + ","; // 防止因为是子字符，而产生权限检查误通过
							if (supStr.indexOf("," + attrValue + ",") >= 0)
								authKey.setAuthValue(attrId,
										lock.getAuthValue(attrId));
						}
					}
				}

				if (matchKey2Lock(authKey, lock)) {
					return true;
				}
				authKey.clear();
			}
		}

		return false;
	}

	// 5.获取查询授权信息
	public CIPReqCondition[] getAuthConditions(String actionId,
			String authObjectId) {
		// 2. 检查属性
		CIPRuntimeOperator operator = CIPRuntime.getOperateSubject();
		String userId = operator.getSubject_id();
		Map<String, String> userAuth = userAuths.get(userId);
		Map<String, String> userSettingAuth = userSettingAuths.get(userId);
		CIPAuthObject authObject = authObjects.get(authObjectId);

		// a、获取当前用户对应的配置信息
		List<String> roles = userRoles.get(userId);
		List<CIP_admin_auth_act2objPO> action2Objs = null;
		CIP_admin_auth_act2objPO action2Obj;
		int size;
		String valSrcType;
		String incl_sub_flag;
		CIP_admin_auth_attrPO valueTable;
		String fieldId;
		String attrId;
		String attrValue;
		List<String> subs;
		CIP_admin_rolesPO rolePO = null;
		Map<String, Object> conditions = new HashMap<String, Object>(3);
		Map<String, Object> returnConds = new HashMap<String, Object>();
		for (String role : roles) {
			rolePO = mRoles.get(role);
			conditions.put(CIPAuthConstants.AUTH_OBJ_ID, authObjectId);
			conditions.put(CIPAuthConstants.AUTH_RESOURCE_ID, actionId);
			conditions.put(CIPAuthConstants.AUTH_ROOT_NODE_ID,
					rolePO.getRoot_resource_id());
			action2Objs = auth2ObjDao.getByCondition(conditions);
			if (action2Objs != null) {
				size = action2Objs.size();
				for (int i = 0; i < size; i++) {
					action2Obj = action2Objs.get(i);
					attrId = action2Obj.getObj_attr_id();
					fieldId = action2Obj.getField_id();
					attrValue = action2Obj.getAttr_value();
					valSrcType = action2Obj.getVal_src_type();
					incl_sub_flag = action2Obj.getIncl_sub_flag();
					if (valSrcType
							.equals(CIPAuthConstants.C_AUTH_ATTR_SRC_ROLE)) {
						attrValue = roleAuths.get(role).get(attrId);
					} else if (valSrcType
							.equals(CIPAuthConstants.C_AUTH_ATTR_SRC_USER)) {
						attrValue = userAuth.get(attrId);
					}else if (valSrcType
							.equals(CIPAuthConstants.C_AUTH_ATTR_SRC_USER_SETTING)) {
						if (userSettingAuth != null) {
							attrValue = userSettingAuth.get(attrId);
						}
					}
					if (attrValue.indexOf("*") >= 0)
						continue;

					if (attrValue.indexOf(",") >= 0) {
						returnConds.put(fieldId, attrValue.split(","));
					} else if (incl_sub_flag
							.equals(CIPAuthConstants.C_AUTH_INCL_SUB_FLAG_DIRECT)) {
						valueTable = authObject.getValueTable(attrId);
						if (valueTable == null)
							continue;

						subs = authDataDao.getDirectSubs(
								valueTable.getAttr_val_table(),
								valueTable.getSup_col_id(),
								valueTable.getSub_col_id(), attrValue);
						if (subs == null) {
							subs = new ArrayList<String>(1);
						}
						subs.add(attrValue);
						returnConds.put(fieldId,
								subs.toArray(new String[subs.size()]));
					} else if (incl_sub_flag
							.equals(CIPAuthConstants.C_AUTH_INCL_SUB_FLAG_ALL)) {
						valueTable = authObject.getValueTable(attrId);
						if (valueTable == null)
							continue;

						subs = authDataDao.getAllSubs(
								valueTable.getAttr_val_table(),
								valueTable.getSup_col_id(), attrValue,
								valueTable.getSub_col_id(),
								valueTable.getRel_col_id());
						if (subs == null) {
							subs = new ArrayList<String>(1);
						}
						subs.add(attrValue);
						returnConds.put(fieldId,
								subs.toArray(new String[subs.size()]));
					} else
						returnConds.put(fieldId, attrValue);
				}

				Set<String> keys = returnConds.keySet();
				size = keys.size();
				if (size > 0) {
					CIPReqCondition[] retConditions = new CIPReqCondition[size];
					int i = 0;
					Object value;
					for (String key : keys) {
						retConditions[i] = new CIPReqCondition();
						retConditions[i].setFieldName(key);
						value = returnConds.get(key);
						if (value instanceof String) {
							retConditions[i]
									.setOperator(CIPOperatorType.EQUAL.code);
							retConditions[i].setLowValue((String) value);
						} else {
							retConditions[i]
									.setOperator(CIPOperatorType.IN.code);
							retConditions[i].setValues((String[]) returnConds
									.get(key));
						}
						i++;
					}

					return retConditions;
				}
			}
		}

		return null;
	}

	public void initRoles() {
		List<CIP_admin_rolesPO> roles = roleDao.getAll();
		for (CIP_admin_rolesPO role : roles) {
			mRoles.put(role.getRole_id(), role);
		}
	}

	public void initAuthObject() {
		// 设置后台所有配置的权限对象
		List<CIP_admin_auth_objPO> objects = authObjDao.getAll();
		List<CIP_admin_auth_attrPO> attrs = null;
		CIP_admin_auth_attrPO attr;
		CIPAuthObject authObj = null;
		Map<String, String> attributes;
		Map<String, CIP_admin_auth_attrPO> valueTables;
		String objId = null;
		int size;
		Map<String, Object> conditions = new HashMap<String, Object>(1);
		for (CIP_admin_auth_objPO obj : objects) {
			objId = obj.getAuth_obj_id();
			authObj = new CIPAuthObject(objId, obj.getAuth_obj_name());
			conditions.put("obj_id", objId);
			attrs = authAttrDao.getByCondition(conditions);
			size = attrs.size();
			attributes = new HashMap<String, String>(size);
			valueTables = new HashMap<String, CIP_admin_auth_attrPO>(size);
			for (int i = 0; i < size; i++) {
				attr = attrs.get(i);
				attributes.put(attr.getObj_attr_id(), attr.getObj_attr_name());

				if (attr.getAttr_val_table() == null
						|| attr.getAttr_val_table().equals(""))
					continue;
				valueTables.put(attr.getObj_attr_id(), attr);
			}

			authObj.setAttributes(attributes);
			authObj.setValueTables(valueTables);
			authObjects.put(objId, authObj);
		}
	}

	public CIPAuthLock getAuthLock(String objectId) {
		CIPAuthObject authObject = authObjects.get(objectId);
		if (authObject != null) {
			return authObject.getLock();
		} else {
			return null;
		}
	}

	private boolean matchKey2Lock(CIPAuthKey key, CIPAuthLock lock) {
		Map<String, String> lockAttrs = lock.getAuthValues();
		Map<String, String> keyAttrs = key.getAuthValues();

		Set<String> keys = lockAttrs.keySet();
		String lockV;
		String keyV;
		for (String k : keys) {
			lockV = lockAttrs.get(k);
			if (lockV == null)
				continue;
			if (lockV.equals(""))
				return false;// 权限字段，不允许填写空值
			if (lockV.equals("*"))
				continue;

			keyV = keyAttrs.get(k);
			if (keyV != null && keyV.equals("*")) // 授权允许访问该字段所有对象
				continue;
			if (keyV == null || keyV.indexOf(lockV) == -1) {
				return false;
			}
		}

		return true;
	}

	// 6. 获取用户概要信息
	public CIPUserProfileData getUserProfile(String userId) {
		return mUsers.get(userId);
	}

	// 7. 获取用户角色信息
	public List<String> getUserRole(String userId) {
		return userRoles.get(userId);
	}

	// 16511 初始化角色资源
	public void reloadResources() {
		if (mAuthResources == null)
			return;

		String resourceId;
		String temp;
		int pos;
		int size;
		CIP_admin_rolesPO rolePO;
		List<CIPRole2ResourceData> resourceDBs;
		CIPRole2ResourceData resourceDB;
		Map<String, String> authResources;

		Set<String> roles = mRoles.keySet();
		for (String role : roles) {
			rolePO = mRoles.get(role);
			resourceId = rolePO.getRoot_resource_id();
			resourceDBs = role2ResDao.getResources(resourceId);
			if (resourceDBs == null)
				continue;

			size = resourceDBs.size();
			authResources = new HashMap<String, String>(size);

			for (int i = 0; i < size; i++) {
				resourceDB = resourceDBs.get(i);
				temp = resourceDB.getSys_uri();
				if (temp == null || temp.equals(""))
					continue;
				pos = temp.indexOf('?');
				if (pos != -1)
					temp = temp.substring(0, pos);

				authResources.put(resourceDB.getResource_id(), temp);
			}

			// 缓存用于访问权限检查filter
			mAuthResources.put(role, authResources);
		}
	}

	
	/**
	* findFromCatch:( 16511  从缓存中查找角色资源).
	*
	* @author liupengfei
	* @param userRole 角色
	* @return
	* @since JDK 1.7
	*/
	public boolean findFromCatch(String userRole){
		
		if(mAuthResources.containsKey(userRole)){
			return true;
		}else{
			return false;
		}
		
	}
	
	
	public void reloadResources(String userRole) {
		// 本地角色与资源关联信息
		CIP_admin_rolesPO role = roleDao.getSingle(userRole);
		String resourceId = role.getRoot_resource_id();
		List<CIPRole2ResourceData> resourceDBs = role2ResDao
				.getResources(resourceId);

		int size = resourceDBs.size();
		Map<String, String> authResources = new LinkedHashMap<String, String>(
				size);
		CIPRole2ResourceData resourceDB;
		String temp;
		int pos;
		for (int i = 0; i < size; i++) {
			resourceDB = resourceDBs.get(i);
			temp = resourceDB.getSys_uri();
			if (temp == null || temp.equals(""))
				continue;
			pos = temp.indexOf('?');
			if (pos != -1)
				temp = temp.substring(0, pos);

			authResources.put(resourceDB.getResource_id(), temp);
		}

		// 缓存用于访问权限检查filter
		mAuthResources.put(userRole, authResources);
	}
	

	// 4.检查系统访问授权
	public boolean checkAuthCode(String authCode, String srcSystemId,
			String srcIp) {
		CIP_admin_access_ctrlPO accessPO = accessCtrlDao.getSingle(
				CIPAdminConstants.ACCESS_FLAG_LOCAL, srcSystemId);
		if (accessPO == null)
			return false;

		// 如果设置了ip，则检查系统访问ip信息
		String ip = accessPO.getAccess_ip();
		if (ip != null && !ip.equals("")) {
			if (!ip.equals(srcIp))
				return false;
		}

		if (accessPO.getAuth_code() == null)
			return false;

		if (!accessPO.getAuth_code().equals(authCode))
			return false;

		return true;
	}

	// 4.1 获取远程系统访问授权
	public String getAuthCode(String tgtSystemId) {
		CIP_admin_access_ctrlPO accessPO = accessCtrlDao.getSingle(
				CIPAdminConstants.ACCESS_FLAG_REMOTE, tgtSystemId);
		if (accessPO == null)
			return null;

		return accessPO.getAuth_code();
	}

	//4.2 远程系统资源访问检查
	public boolean checkSystemAccess(String srcSystemId,
			String resourceId, String resUri) {
		CIP_admin_access_relPO relPO = accessRelDao.getSingle(resourceId,
				srcSystemId);
		if (relPO == null)
			return false;

		CIP_admin_access_resPO resPO = accessResDao.getSingle(CIPAdminConstants.ACCESS_FLAG_LOCAL,resourceId);
		if (resPO == null)
			return false;

		String uri = resPO.getSys_uri();
		if (uri != null && resUri.endsWith(uri))
			return true;

		return false;
	}
	
	//4.3 远程资源访问信息,返回url
	public String getRemoteResource(String resourceId){
		CIP_admin_access_resPO resPO = accessResDao.getSingle(CIPAdminConstants.ACCESS_FLAG_REMOTE,resourceId);
		if (resPO == null)
			return null;
		
		return resPO.getSys_uri();
	}
	
	//4.4 远程资源访问参数信息
	public Map<String,String> getCallParameters(String tgtSystemId){
		Map<String,String> params = new HashMap<String,String>();
		CIP_admin_access_ctrlPO accessPO = accessCtrlDao.getSingle(
				CIPAdminConstants.ACCESS_FLAG_REMOTE, tgtSystemId);
		if (accessPO == null)
			return null;
		long time = System.currentTimeMillis();
		params.put(CIPRuntimeConstants.CIP_TIME, time+"");
		String authCode = accessPO.getAuth_code();
		if(authCode == null)
			authCode = "";
		
		try {
			params.put(CIPRuntimeConstants.AUTH_CODE, URLEncoder.encode(CIPDesUtils.encrypt(authCode, time),CIPRuntimeConstants.CIP_ENCODE));
		} catch (UnsupportedEncodingException e) {
			throw new CIPRuntimeException(CIPErrorCode.ERROR_RESOURCE_IS_NOT_READY,e);
		}
		
		String other_params = null;
		if(accessPO.getOther_params()!=null)
			other_params = CIPDesUtils.decryptInternal(accessPO.getOther_params());
		if(other_params!=null){
			String[] vs = other_params.split(",");
			String[] v;
			for(int i=0;i<vs.length;i++){
				v = vs[i].split("=");
				if(v!=null&&v.length == 2){
					params.put(v[0],v[1]);
				}
			}
		}

		return params;
	}

}
