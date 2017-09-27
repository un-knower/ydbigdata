package com.yd.common.runtime;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.yd.common.auth.CIPAuthManager;
import com.yd.common.cache.CIPCacheManager;
import com.yd.common.conf.CIPConfigureManager;
import com.yd.common.function.admin.service.CIP_admin_codesService;
import com.yd.common.function.admin.service.CIP_admin_commonqueryService;
import com.yd.common.function.admin.service.CIP_admin_textService;
import com.yd.common.session.CIPHttpSession;
import com.yd.common.session.CIPSessionManager;
import com.yd.common.session.CIPUser;

/**
 * 应用上下文初始化类
 * @author 
 *
 * 应用体系初始化加载顺序
 * 1、启动 系统根服务
 * 2、启动 日志服务
 * 3、启动 配置服务
 * 4、启动 缓存服务
 * 5、启动 文本服务
 * 6、启动 用户服务
 * 7、启动 授权服务
 * 8、检查 数据库服务
 * 
 */

public class CIPRuntimeContextInitializer implements InitializingBean{
	private Logger log = Logger.getLogger(CIPRuntimeContextInitializer.class);
	

	@Override
	public void afterPropertiesSet() throws Exception {
		//加载config.properties
		loadConfig();
		//加载文本错误内容到缓存中
		this.inCache();
		//加载编码信息到缓存中
		this.inCode();
		//加载通用查询配置信息到缓存中
		this.inQuery();
		//加载table.properties，初始化ORMapping
	}
	
	@Autowired
	private CIPAuthManager authManager = null;
	
	/**
	 * 加载配置文件
	 */
	private void loadConfig() {
		try {
			//1.启动应用根服务
			Properties props = CIPConfigureManager.getRootConf();
			CIPRuntimeConfigure.cip_system_id = props.getProperty("cip.system.id").trim();
			
			//元数据同步URL
			CIPRuntimeConfigure.cip_conf_changesync_url = props.getProperty("cip.conf.changesync.url").trim();
			
			String xRemote = props.getProperty("cip.system.xcluster").trim();
			if("X".equals(xRemote))
				CIPRuntimeConfigure.cip_is_cluster = true;

			//2.启动 配置服务
			xRemote = props.getProperty("cip.conf.xremote").trim();
			if("X".equals(xRemote))
				CIPRuntimeConfigure.cip_is_remote_conf = true;
			
			CIPRuntimeConfigure.cip_conf_extend_file = props.getProperty("cip.conf.extend.file").trim();
			CIPRuntimeConfigure.cip_conf_redis_file =  props.getProperty("cip.conf.redis.file").trim();
			CIPRuntimeConfigure.cip_conf_log4j_file =  props.getProperty("cip.conf.log4j.file").trim();
			CIPRuntimeConfigure.cip_conf_passcode =  props.getProperty("cip.conf.passcode").trim();

			if(CIPRuntimeConfigure.cip_is_remote_conf){
				CIPRuntimeConfigure.cip_remote_conf_url = props.getProperty("cip.conf.remote.url").trim();
				CIPRuntimeConfigure.cip_remote_conf_auth_code = props.getProperty("cip.conf.remote.auth.code").trim();
				CIPConfigureManager.init();
			}
			else {
				CIPConfigureManager.init();
			}
			
//			//3.启动日志服务
//			CIPLogManager.getLogManager();
			
			
			//6.启动授权服务
			authManager.init();
			CIPRuntime.authManager = authManager;
			
			//8.其它配置项加载
			loadAdditionnalConfigures(props);
			
			//5. 启动系统session
			String sessionId = CIPSessionManager.initSystemSession();
			CIPHttpSession cipSession = CIPSessionManager.getSystemSession(sessionId);
			CIPUser systemUser = new CIPUser();
			systemUser.setUserId("system");
			systemUser.setUserName("system user");
			//set system user for remote system call
			//设置无超时系统session,否则session失效后获取系统session报错
			cipSession.setAttribute(CIPRuntimeConstants.LOGIN_USER, systemUser, false);
			CIPRuntime.runtimeInfo.put(CIPRuntimeConstants.SYSTEM_SESSION_ID, sessionId);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("配置文件config.properties加载失败！");
		}finally{

		}
	}

	private void loadAdditionnalConfigures(Properties props) {
		String confFile = props.getProperty("cip.conf.extend.file").trim();
		Properties configures = CIPConfigureManager.getConfigures(confFile);
		
		//4. 启动缓存服务
		String cacheRemote = configures.getProperty("cip.cache.xremote").trim();
		if(cacheRemote!=null&&!cacheRemote.equals("X")){
			CIPRuntimeConfigure.cip_is_remote_cache = false;
		}
		else 
			CIPRuntimeConfigure.cip_is_remote_cache = true;
		
		if(CIPRuntimeConfigure.cip_is_remote_cache){
			CIPCacheManager.createCacheManager(CIPCacheManager.CACHE_SCHEMA_REMOTE);
		}
		else{
			CIPCacheManager.createCacheManager(CIPCacheManager.CACHE_SCHEMA_LOCAL);
		}
		
		CIPRuntimeConfigure.dbSchema = configures.getProperty("cip.db.schema");
		String orgTypeStr = configures.getProperty("cip.default.org.type");
		if(orgTypeStr!=null && !"".equals(orgTypeStr.trim())){
			String[]temp = orgTypeStr.trim().split("\\|");
			for(int i=0; i<temp.length; i++) {
				CIPRuntimeConfigure.defaultOrgTypes.add(temp[i]);
			}
		}
		CIPRuntimeConfigure.cip_actual_file_path = configures.getProperty("cip.actual.file.path").trim();
		CIPRuntimeConfigure.cip_temp_file_path = configures.getProperty("cip.temp.file.path").trim();
		CIPRuntimeConfigure.additionalInfos = configures;
		CIPRuntimeConfigure.cip_system_type = configures.getProperty("cip.system.type").trim();
		CIPRuntimeConfigure.cip_system_instance_id = configures.getProperty("cip.system.instance.id");
		String language = configures.getProperty("cip.default.lang");
		if(language!=null&&!"".equals(language))
			CIPRuntimeConfigure.cip_default_lang = language;

		//系统默认角色，如果设置默认角色，当用户无角色时赋予默认角色
		String defaultRole = configures.getProperty("cip.default.role");
		CIPRuntimeConfigure.cip_default_role = defaultRole!=null?defaultRole.trim():null;
		
		String xRemote = configures.getProperty("cip.user.xremote").trim();

		if("X".equals(xRemote)){
			CIPRuntimeConfigure.cip_user_xremote = true;
		}
		xRemote = configures.getProperty("cip.auth.xremote");
		if("X".equals(xRemote)){
			CIPRuntimeConfigure.cip_auth_xremote = true;
		}
		// 单点登录服务
		String sso = configures.getProperty("cip.cas.sso").trim();
		if("SSO".equals(sso)){
			CIPRuntimeConfigure.cip_cas_sso = true;
			//单点登录使用远程用户
			CIPRuntimeConfigure.cip_user_xremote = true;
		}
		
		
		if(CIPRuntimeConfigure.cip_user_xremote){

			String auth_sys_id = configures.getProperty("cip.auth.sys.url").trim();
			CIPRuntimeConfigure.cip_auth_Id = Integer.parseInt(configures.getProperty("cip.auth.id").trim());
			CIPRuntimeConfigure.cip_user_url = auth_sys_id + configures.getProperty("cip.user.remote.get.user.url").trim();
			CIPRuntimeConfigure.cip_user_role_url = auth_sys_id + configures.getProperty("cip.user.remote.get.user.role.url").trim();
			CIPRuntimeConfigure.cip_login_url = auth_sys_id + configures.getProperty("cip.user.remote.login.url").trim();

			CIPRuntimeConfigure.cip_login_role_url = auth_sys_id + configures.getProperty("cip.user.remote.login.role.url").trim();
			//当使用授权系统用户时将本系统角色同步到授权系统，获取用户角色时取授权系统角色
			CIPRuntimeConfigure.cip_sync_role_to_auth_url = auth_sys_id + configures.getProperty("cip.role.remote.sync.role.url").trim();
		}
		if(CIPRuntimeConfigure.cip_auth_xremote){
			String auth_sys_id = configures.getProperty("cip.auth.sys.url").trim();
			CIPRuntimeConfigure.cip_auth_Id = Integer.parseInt(configures.getProperty("cip.auth.id").trim());
			CIPRuntimeConfigure.cip_menu_url = auth_sys_id + configures.getProperty("cip.auth.remote.resource.url").trim();
		}

		//2. log4j configuration
		confFile = props.getProperty("cip.conf.log4j.file").trim();
		configures = CIPConfigureManager.getConfigures(confFile);
		
		//3. redis configuration
		confFile = props.getProperty("cip.conf.redis.file").trim();
		configures = CIPConfigureManager.getConfigures(confFile);

	}
	
	@Autowired
	private CIP_admin_textService dataService  = null;
	/**
	* inCache:初始化运行项目的时候 将系统错误文本信息存放到redis中.
	*
	* @author jh
	* @since JDK 1.7
	*/
	private void inCache(){	
		dataService.save();
	}
	
	@Autowired
	private CIP_admin_codesService codeService  = null;
	/**
	* inCode:初始化运行项目的时候，将系统的数据编码放入到缓存中.
	*
	* @author jh
	* @since JDK 1.7
	*/
	private void inCode(){
		codeService.save();
	}
	
	@Autowired
	private CIP_admin_commonqueryService queryService  = null;
	/**
	* inCode:初始化运行项目的时候，将系统的通用查询配置语句放入到缓存中.
	*
	* @author jh
	* @since JDK 1.7
	*/
	private void inQuery(){
		queryService.save();
	}
}
