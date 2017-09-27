package com.yd.ydbi.common.aspect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.yd.common.auth.CIPUserProfileData;
import com.yd.common.runtime.CIPRuntime;
import com.yd.common.utils.JSONUtils;

/**
 * @author kangyuanjia
 * @createTime 2017-04-05 11:44:13
 * @describe 请求参数切面处理
 */
@Aspect
@Component
public class ParameterAndAuthAspect {

	@Pointcut("execution (* com.yd.ydbi..service.impl.*.*(..))")
	public void servicePointcut() {
	}

	@SuppressWarnings("unchecked")
	@Around("servicePointcut()")
	public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
		Object[] args = joinPoint.getArgs();
		Map<String, Object> paramsMap = (Map<String, Object>) args[0];
		List<String> sel_cd_list = new ArrayList<String>();
		String conditonStr = (String) paramsMap.get("conditonStr");
		paramsMap.remove("conditonStr");
		if (conditonStr != null) {
			Map<String, Object> reqCondtions = JSONUtils.convertJson2Object(conditonStr, HashMap.class);
			Set<String> keys = reqCondtions.keySet();
			String value;

			if (reqCondtions.containsKey("com_id") && !((String) reqCondtions.get("com_id")).trim().equals("")) {
				paramsMap.put("sel_cd", ((String) reqCondtions.get("com_id")).trim());
				paramsMap.put("sel_level", "5");
			} else if (reqCondtions.containsKey("dbct_id")
					&& !((String) reqCondtions.get("dbct_id")).trim().equals("")) {
				paramsMap.put("sel_cd", ((String) reqCondtions.get("dbct_id")).trim());
				paramsMap.put("sel_level", "4");
			} else if (reqCondtions.containsKey("city_id")
					&& !((String) reqCondtions.get("city_id")).trim().equals("")) {
				paramsMap.put("sel_cd", ((String) reqCondtions.get("city_id")).trim());
				paramsMap.put("sel_level", "3");
			} else if (reqCondtions.containsKey("prov_id")
					&& !((String) reqCondtions.get("prov_id")).trim().equals("")) {
				paramsMap.put("sel_cd", ((String) reqCondtions.get("prov_id")).trim());
				paramsMap.put("sel_level", "2");
				if ("-1".equals(((String) reqCondtions.get("prov_id")).trim())) {
					paramsMap.put("sel_level", "-1");
				}
			} else if (reqCondtions.containsKey("area_id")
					&& !((String) reqCondtions.get("area_id")).trim().equals("")) {
				paramsMap.put("sel_level", "1");
				paramsMap.put("sel_cd", ((String) reqCondtions.get("area_id")).trim());
			} else {
				paramsMap.put("sel_cd", "0");
				paramsMap.put("sel_level", "0");
			}

			for (String key : keys) {
				value = (String) reqCondtions.get(key);
				if (value == null || value.trim().equals(""))
					// continue;
					paramsMap.put(key, "0");
				if (!key.equals("com_id") && !key.equals("dbct_id") && !key.equals("city_id") && !key.equals("prov_id")
						&& !key.equals("area_id")) {
					paramsMap.put(key, value);
				}
			}
			sel_cd_list.add((String) paramsMap.get("sel_cd"));
//			sel_cd_list.add("518000");
			paramsMap.put("sel_cd_list", sel_cd_list);
			args[0] = paramsMap;
		}
		/*String userId = CIPRuntime.getOperateSubject().getSubject_id();
		if(userId == null || userId.trim()==""){
			userId = (String)paramsMap.get("userId");
		}
		boolean sel_flag = false;
		int level = Integer.parseInt((String)paramsMap.get("sel_level"));
		String code = (String)paramsMap.get("sel_cd");
		CIPUserProfileData userProfile = CIPRuntime.authManager.getUserAndRoles(userId);
		if(userProfile.user_roles.size()>0){
			for(String role: userProfile.user_roles){
				String[] roleArray = role.split("_");
				if(roleArray[0] == "branch" && level==5){
					sel_flag = true;
	            }else if(roleArray[0] == "dbct" && level>=4){
	            	if(level == 4 && roleArray[1] != code){
	            		sel_flag = false;
	            	}else{
	            		sel_flag = true;
	            	}
	            }else if(roleArray[0] == "prov" && level>=2){
	            	if(level == 2 && roleArray[1] != code){
	            		sel_flag = false;
	            	}else{
	            		sel_flag = true;
	            	}
	            }else if(roleArray[0] == "area" && level>=1){
	            	if(level == 1 && roleArray[1] != code){
	            		sel_flag = false;
	            	}else{
	            		sel_flag = true;
	            	}
	            }else if(roleArray[0] == "hq" || roleArray[0] == "superAdmin"){
	            	sel_flag = true;
	            }
			}
		}
		if(sel_flag){
			return joinPoint.proceed(args);
		}else{
			return null;
		}*/
		return joinPoint.proceed(args);
	}

}