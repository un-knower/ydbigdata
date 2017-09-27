package com.yd.common.runtime;

import com.yd.common.text.CIPTextManager;

public class CIPErrorCode {
	public static final CIPErrorCode LOGIN_SUCCESS = new CIPErrorCode(10000, "登录成功");
	public static final CIPErrorCode USER_IS_NULL = new CIPErrorCode(10001, "用户不能为空");
	public static final CIPErrorCode PASSWORD_IS_NULL = new CIPErrorCode(10002,"密码不能为空");
	public static final CIPErrorCode PASS_CODE_IS_NULL = new CIPErrorCode(10003,"验证码不能为空");
	public static final CIPErrorCode PASS_CODE_ERROR = new CIPErrorCode(10004,"验证码不匹配");
	public static final CIPErrorCode USER_LOCKED = new CIPErrorCode(10005,"用户被锁定");
	public static final CIPErrorCode PASSWORD_ERROR = new CIPErrorCode(10006,"密码错误");
	public static final CIPErrorCode LOGIN_FAIL = new CIPErrorCode(10007,"登录系统失败，非法用户");
	public static final CIPErrorCode USER_INFO_ERROR = new CIPErrorCode(10167, "用户名或密码错误");
	public static final CIPErrorCode USER_ROLE_IS_NULL = new CIPErrorCode(10008,"登录失败，用户角色未授权");
	public static final CIPErrorCode LOGOUT_SUCCESS = new CIPErrorCode(10010, "登出成功");
	public static final CIPErrorCode LOGOUT_FAIL = new CIPErrorCode(10011,"登出系统失败");
	public static final CIPErrorCode PASSWORD_RESET = new CIPErrorCode(10021,"初始密码，需重置");
	public static final CIPErrorCode PASSWORD_EXPIRES = new CIPErrorCode(10022, "密码过期请重置密码");
	public static final CIPErrorCode PASSWORD_IS_USED = new CIPErrorCode(10023,"密码在过去三次已经被使用");
	public static final CIPErrorCode PASSWORD_NOT_MATCHED_4_RESET_PASSWORD = new CIPErrorCode(10024,"密码重置：新旧密码不一致");

	
	public static final CIPErrorCode CALL_SUCCESS = new CIPErrorCode(0, "操作成功");
	public static final CIPErrorCode ERROR_HTTP_REQUEST_IS_NOT_AVAILABLE = new CIPErrorCode(90001,"HTTP请求错误"); 
	public static final CIPErrorCode ERROR_REMOTE_SERVER_RETURN_ERROR = new CIPErrorCode(90002, "远程服务返回错误"); 
	public static final CIPErrorCode ERROR_RESOURCE_IS_NOT_READY = new CIPErrorCode(90003, "系统资源缺失"); 
	public static final CIPErrorCode ERROR_TECHNICAL_ERROR = new CIPErrorCode(99999, "技术处理失败"); 
	public static final CIPErrorCode ERROR_CONNECTION_TIMEOUT = new CIPErrorCode(99998, "连接超时"); 
	public static final CIPErrorCode ERROR_INVALID_AUTHORIZATION = new CIPErrorCode(99997, "无授权操作"); 
	public static final CIPErrorCode ERROR_NOT_SUPPORT_REMOTE_CALL = new CIPErrorCode(99996, "不支持远程系统调用"); 
	public static final CIPErrorCode ERROR_FUNCTION_NOT_SUPPORT = new CIPErrorCode(99995, "该功能点不支持");
	public static final CIPErrorCode ERROR_DATABASE_TECH_EXCEPTION = new CIPErrorCode(80001,"数据库操作异常"); 
	public static final CIPErrorCode ERROR_DUMPLICATE_PRIMARY_KEY = new CIPErrorCode(80002, "数据记录已经存在"); 
	public static final CIPErrorCode ERROR_RECORD_NOT_EXISTS = new CIPErrorCode(80003, "数据记录不存在");
	public static final CIPErrorCode ERROR_DATA_IS_MALFORM = new CIPErrorCode(80004, "数据格式不符合要求");
	public static final CIPErrorCode ERROR_ILLEGAL_ACCESS = new CIPErrorCode(80005, "非法用户访问");
	public static final CIPErrorCode ERROR_ILLEGAL_OPERATION = new CIPErrorCode(80006, "没有操作权限，请找管理员确认权限配置!");
	
	public int code;
	public String name;

	public CIPErrorCode(int code, String name) {
		this.code = code;
		String lang = CIPRuntime.getOperateSubject()==null ? CIPRuntimeConfigure.cip_default_lang : CIPRuntime.getOperateSubject().getLang();
		String text = CIPTextManager.gettextManager().getMessageText(lang, code);
		this.name = text==null ? name : text;
	}

	public CIPErrorCode(int code) {
		this(code, null);
	}

	public String getName(int code) {
		
		return name;
	}
}
