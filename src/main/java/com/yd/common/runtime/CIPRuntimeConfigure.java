package com.yd.common.runtime;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CIPRuntimeConfigure {

	//系统id
	public static String cip_system_id;
	public static String cip_system_instance_id;
	public static String cip_system_type;
	public static boolean cip_is_cluster = false;
	
	//系统配置
	public static boolean cip_is_remote_conf = false;
	public static String cip_remote_conf_url;
	public static String cip_remote_conf_auth_code;
	public static final String cip_root_conf_file = "config.properties";
	public static String cip_conf_extend_file;
	public static String cip_conf_redis_file;
	public static String cip_conf_log4j_file;
	public static String cip_conf_passcode;//验证码配置
	
	
	//日志配置
	public static boolean cip_is_remote_log = false;
	public static String cip_log_url;
	
	//缓存配置
	public static boolean cip_is_remote_cache = true;
			
	//单点登录服务
	public static boolean cip_cas_sso = false;
	
	//授权服务
	public static boolean cip_user_xremote = false;
	public static boolean cip_auth_xremote = false;
	public static String cip_user_url;
	public static String cip_login_url;
	public static String cip_login_role_url;
	public static String cip_user_role_url;
	public static String cip_sync_role_to_auth_url;
	public static String cip_auth_url;
	public static String cip_menu_url;
	public static long cip_auth_Id;// 权限注册码
	public static String cip_default_role;//系统默认用户角色

	//文本信息服务
	public static boolean cip_text_xremote;
	public static String cip_text_remote_url;
	public static String cip_text_local_file;

	/** JNDI数据源名称*/
	public static String jndiName;  
	
	/** 数据库模式*/
	public static String dbSchema;
	

	
	/** 登录地址*/
	public static String loginURL;
	
	/** 获取菜单地址*/
	public static String menuURL;
	
	//应用个性化配置
	public static String loginPageURI = "login.html";
	public static String ErrorPageURI = "error.html";

	
	public static Properties additionalInfos = new Properties();
	
	public static String cip_default_lang="ZH";//系统默认语言
	
	//文件处理默认路径
	public static String cip_temp_file_path;
	public static String cip_actual_file_path;
	
	//项目默认查询机构类型
	public static List<String> defaultOrgTypes = new ArrayList<>();
	public static String dbSchema_ydserver = "ydserver.";
	
	//变更同步地址
	public static String cip_conf_changesync_url;

	
}
