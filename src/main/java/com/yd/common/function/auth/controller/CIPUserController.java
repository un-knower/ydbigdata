package com.yd.common.function.auth.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yd.common.auth.CIPResource;
import com.yd.common.auth.CIPUserProfileData;

import com.yd.common.data.CIPResponseMsg;
import com.yd.common.exception.CIPRuntimeException;
import com.yd.common.exception.CIPServiceException;
import com.yd.common.function.auth.data.CIPAuthResult;
import com.yd.common.function.auth.data.CIPLoginData;
import com.yd.common.function.auth.data.CIPUserOperateVO;
import com.yd.common.function.auth.service.CIPResourceService;
import com.yd.common.function.auth.service.CIPUserService;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntime;
import com.yd.common.runtime.CIPRuntimeConfigure;
import com.yd.common.runtime.CIPRuntimeConstants;
import com.yd.common.session.CIPHttpSession;
import com.yd.common.session.CIPSessionManager;
import com.yd.common.session.CIPUser;
import com.yd.common.utils.CaptchaUtils;
import com.yd.common.utils.JSONUtils;
import com.yd.ydbi.utils.QywxUtil;

@RestController
@RequestMapping(value = "/")
public class CIPUserController {
	@Autowired
	private CIPUserService userService;

	@Autowired
	private CIPResourceService resourceService;
	private Logger log = Logger.getLogger(getClass());

	private static final String USER_PASS_CODE = "cip_passcode";

	// 登录系统
	@RequestMapping(value = "public/common/user/wxlogin")
	public CIPResponseMsg login(HttpServletRequest request, HttpServletResponse response) {
		
		CIPResponseMsg msg = new CIPResponseMsg();
		
		try {
			CIPHttpSession session = null;
			session = CIPSessionManager.getSession(request, response);
			
			String userId = QywxUtil.getWXuserId(request.getParameter("code"));
			CIPUserProfileData userData = userService.wxloginUser(userId);
			msg.data = userData;
			CIPUser loginUser = new CIPUser();
			loginUser.setUserId(userData.user_id);
			loginUser.setUserName(userData.user_name);
			loginUser.setOrgCode(userData.org_id);
			loginUser.setOrgName(userData.org_name);
			loginUser.setOrgType(userData.org_type);
			loginUser.setUserRoles(userData.user_roles);
			// 设置用户默认登录语言
			if (userData.lang != null && !"".equals(userData.lang))
				loginUser.setLang(userData.lang);
			else
				loginUser.setLang(CIPRuntimeConfigure.cip_default_lang);
			
			session.setAttribute(CIPRuntimeConstants.LOGIN_USER, loginUser);
			
			// 初始化用户行为权限资源，用于行为检查
			CIPRuntime.authManager.initUserAuth(loginUser);
			// 初始化用户访问资源，用于访问检查
			for (String userRole : userData.user_roles)
				resourceService.initResource(userRole);
			
		} catch (CIPRuntimeException e) {
			log.error(e);
			msg.errorCode = e.getErrorCode().code;
			msg.msg = e.getErrorCode().name;
		} catch (CIPServiceException e) {
			log.error(e);
			msg.errorCode = e.getErrorCode().code;
			msg.msg = e.getErrorCode().name;
		} catch (Exception e) {
			log.error(e);
			msg.errorCode = CIPErrorCode.ERROR_RESOURCE_IS_NOT_READY.code;
			msg.msg = e.getMessage();
		}
		
		return msg;
	}
	// 登录系统
	@RequestMapping(value = "public/common/user/login")
	public CIPResponseMsg login(@RequestBody CIPLoginData user,
			HttpServletRequest request, HttpServletResponse response) {

		CIPResponseMsg msg = new CIPResponseMsg();
		try {
			CIPHttpSession session = null;
			session = CIPSessionManager.getSession(request, response);
			user.local_pass_code = session.getAttribute(USER_PASS_CODE,
					String.class);
			CIPUserProfileData userData = userService.loginUser(user);
			msg.data = userData;
			CIPUser loginUser = new CIPUser();
			loginUser.setUserId(user.user_id);
			loginUser.setUserName(userData.user_name);
			loginUser.setOrgCode(userData.org_id);
			loginUser.setOrgName(userData.org_name);
			loginUser.setOrgType(userData.org_type);
			loginUser.setUserRoles(userData.user_roles);
			// 设置用户默认登录语言
			if (userData.lang != null && !"".equals(userData.lang))
				loginUser.setLang(userData.lang);
			else
				loginUser.setLang(CIPRuntimeConfigure.cip_default_lang);

			session.setAttribute(CIPRuntimeConstants.LOGIN_USER, loginUser);

			// 初始化用户行为权限资源，用于行为检查
			CIPRuntime.authManager.initUserAuth(loginUser);
			// 初始化用户访问资源，用于访问检查
			for (String userRole : userData.user_roles)
				resourceService.initResource(userRole);

		} catch (CIPRuntimeException e) {
			log.error(e);
			msg.errorCode = e.getErrorCode().code;
			msg.msg = e.getErrorCode().name;
		} catch (CIPServiceException e) {
			log.error(e);
			msg.errorCode = e.getErrorCode().code;
			msg.msg = e.getErrorCode().name;
		} catch (Exception e) {
			log.error(e);
			msg.errorCode = CIPErrorCode.ERROR_RESOURCE_IS_NOT_READY.code;
			msg.msg = e.getMessage();
		}

		return msg;
	}

	@RequestMapping(value = "actions/common/user/changePwd")
	public CIPResponseMsg changePwd(@RequestBody String pwdinfo,
			CIPUserOperateVO operateUser) {
		CIPResponseMsg msg = new CIPResponseMsg();
		try {
			Map<String, Object> info = new HashMap<String, Object>(4);
			info = JSONUtils.convertJson2Map(pwdinfo);
			String userId = CIPRuntime.getOperateSubject().getSubject_id();
			CIPAuthResult result = userService.resetPassword(userId,
					(String) info.get("old_pwd"), (String) info.get("new_pwd"));
			msg.errorCode = result.code;
			msg.msg = result.name;

		} catch (Exception e) {
			msg.errorCode = CIPErrorCode.ERROR_RESOURCE_IS_NOT_READY.code;
			msg.msg = e.getMessage();
		}

		return msg;
	}

	@RequestMapping(value = "actions/common/user/logout")
	public CIPResponseMsg logout(HttpServletRequest request,
			HttpServletResponse response) {
		CIPResponseMsg msg = new CIPResponseMsg();
		try {
			CIPHttpSession session = CIPSessionManager.getSession(request,
					response);
			session.invalidate();

			String userId = CIPRuntime.getOperateSubject().getSubject_id();
			CIPAuthResult returnCode = userService.logoutUser(userId);
			msg.errorCode = returnCode.code;
			msg.msg = returnCode.name;
		} catch (Exception e) {
			msg.errorCode = CIPErrorCode.ERROR_RESOURCE_IS_NOT_READY.code;
			msg.msg = e.getMessage();
		}

		return msg;
	}

	// 随机获取验证码
	@RequestMapping(value = "public/common/user/getPassCode")
	@ResponseBody
	public void getPassCode(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		CIPHttpSession session = CIPSessionManager
				.getSession(request, response);

		// String passCode =
		// userService.getPassCode(response.getOutputStream());

		// response.flushBuffer();

		// 保存passcode 到 session
		// session.setAttribute(USER_PASS_CODE, passCode);

		String captchaCode = CaptchaUtils.generateCaptchaCode(4);
		session.setAttribute(USER_PASS_CODE, captchaCode);

		CaptchaUtils.outputImage(80, 30, response.getOutputStream(),
				captchaCode);

		response.flushBuffer();

	}
	
	// 重置密码
	@RequestMapping(value = "actions/common/user/resetPwd")
	public CIPResponseMsg resetPwd(HttpServletRequest request,
			HttpServletResponse response) {
		CIPResponseMsg msg = new CIPResponseMsg();
		try {
			CIPHttpSession session = CIPSessionManager.getSession(request,
					response);
			CIPUser user = session.getAttribute(CIPRuntimeConstants.LOGIN_USER,
					CIPUser.class);
			String oldP = request.getParameter("old_pwd");
			String newP = request.getParameter("new_pwd");
			CIPAuthResult returnCode = userService.resetPassword(
					user.getUserId(), oldP, newP);
			msg.errorCode = returnCode.code;
			msg.msg = returnCode.name;
		} catch (Exception e) {
			msg.errorCode = CIPErrorCode.ERROR_RESOURCE_IS_NOT_READY.code;
			msg.msg = e.getMessage();
		}

		return msg;
	}

	// 获取用户概要信息
	@RequestMapping(value = "actions/common/user/getUserProfile")
	public CIPResponseMsg getUserProfile(HttpServletRequest request,
			HttpServletResponse response) {
		CIPResponseMsg msg = new CIPResponseMsg();
		try {
			CIPHttpSession session = CIPSessionManager.getSession(request,
					response);
			CIPUser user = session.getAttribute(CIPRuntimeConstants.LOGIN_USER,
					CIPUser.class);
			CIPUserProfileData userProfile = userService.getUserProfile(user
					.getUserId());
			msg.data = userProfile;
		} catch (Exception e) {
			msg.errorCode = CIPErrorCode.ERROR_RESOURCE_IS_NOT_READY.code;
			msg.msg = e.getMessage();
		}

		return msg;
	}
	
	// 获取用户概要信息
		@RequestMapping(value = "actions/common/user/getDealThingsCount")
		public CIPResponseMsg getDealThingsCount(HttpServletRequest request,
				HttpServletResponse response) {
			CIPResponseMsg msg = new CIPResponseMsg();
			try {
				String user_id=request.getParameter("user_id");
				int dealCount=userService.getDealThingsCount(user_id);
				msg.data = dealCount;
			} catch (Exception e) {
				msg.errorCode = CIPErrorCode.ERROR_RESOURCE_IS_NOT_READY.code;
				msg.msg = e.getMessage();
			}

			return msg;
		}

	// 重新加载资源
	@RequestMapping(value = "actions/common/user/refreshResources")
	public CIPResponseMsg refreshResources(HttpServletRequest request,
			HttpServletResponse response) {
		CIPResponseMsg msg = new CIPResponseMsg();
		try {
			String userRole = request.getParameter("role_id");
			resourceService.reloadResources(userRole);

			msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
			msg.msg = CIPErrorCode.CALL_SUCCESS.name;
		} catch (CIPRuntimeException e) {
			msg.errorCode = e.getErrorCode().code;
			msg.msg = e.getErrorCode().name;
		} catch (Exception e) {
			e.printStackTrace();
			msg.errorCode = CIPErrorCode.ERROR_RESOURCE_IS_NOT_READY.code;
			msg.msg = e.getMessage();
		}

		return msg;
	}

	// 获取用户概要信息
	@RequestMapping(value = "actions/common/user/getResources")
	public CIPResponseMsg getResources(HttpServletRequest request,
			HttpServletResponse response) {
		CIPResponseMsg msg = new CIPResponseMsg();
		try {
			String resource_id = request.getParameter("resource_id");
			CIPHttpSession session = CIPSessionManager.getSession(request,
					response);
			CIPUser user = session.getAttribute(CIPRuntimeConstants.LOGIN_USER,
					CIPUser.class);
			List<CIPResource> resources;
			if (resource_id == null || resource_id.equals(""))
				resources = resourceService.getResources(user);
			else
				resources = resourceService.getResources(user, resource_id);

			msg.data = resources;
		} catch (CIPRuntimeException e) {
			msg.errorCode = e.getErrorCode().code;
			msg.msg = e.getErrorCode().name;
		} catch (Exception e) {
			e.printStackTrace();
			msg.errorCode = CIPErrorCode.ERROR_RESOURCE_IS_NOT_READY.code;
			msg.msg = e.getMessage();
		}

		return msg;
	}

}
