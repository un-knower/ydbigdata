package com.yd.common.session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.yd.common.auth.CIPUserProfileData;
import com.yd.common.function.auth.service.CIPResourceService;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntime;
import com.yd.common.runtime.CIPRuntimeConfigure;
import com.yd.common.runtime.CIPRuntimeConstants;
import com.yd.common.runtime.CIPRuntimeOperator;
import com.yd.common.utils.DateUtils;


/**
 *
 * 登录过滤器
 * 注：解决请求合法性问题，用sessionId识别当前用户的合法性
 */
public class CIPLocalLoginFilter implements Filter {
	
	private CIPResourceService resourceService = null;
    /**
     * Default constructor. 
     */
    public CIPLocalLoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		
		String type = httpRequest.getHeader("X-Requested-With");
		if ("XMLHttpRequest".equalsIgnoreCase(type)) {//ajax请求
			httpResponse.setHeader("Pragma", "no-cache");
			httpResponse.setDateHeader("Expires", 0);
			httpResponse.setHeader("Cache-Control", "no-cache, must-revalidate");
		}
		
		//2. 获取session信息 
		CIPHttpSession session = null;
		CIPUser loginUser = null;
		try{
			session = CIPSessionManager.getSession(httpRequest, httpResponse);
		}catch(Exception e) {
			if ("XMLHttpRequest".equalsIgnoreCase(type)) {
				httpResponse.setHeader("Content-type", "text/html;charset=UTF-8");
				httpResponse.setCharacterEncoding("utf-8");
				httpResponse.getWriter().write(
						"{\"errorCode\":"+CIPErrorCode.ERROR_CONNECTION_TIMEOUT.code+",\"msg\":\""+CIPErrorCode.ERROR_CONNECTION_TIMEOUT.name+"\"}");
				httpResponse.getWriter().flush();
				return;
			}else{
				httpResponse.sendRedirect(CIPRuntimeConfigure.ErrorPageURI);
				return;
			}
		}		
		
		//3. 从session中获取user对象
		loginUser = session.getAttribute(CIPRuntimeConstants.LOGIN_USER, CIPUser.class);
		
		if(loginUser == null){
			if(CIPRuntimeConfigure.cip_cas_sso){
				String userId = httpRequest.getRemoteUser();
				try {
					CIPUserProfileData userProfileData = CIPRuntime.authManager.getUserAndRoles(userId);
					if(userProfileData.user_roles==null || userProfileData.user_roles.size()==0){
						if(CIPRuntimeConfigure.cip_default_role!=null&&!"".equals(CIPRuntimeConfigure.cip_default_role)){
							List<String> roles = new ArrayList<>();
							roles.add(CIPRuntimeConfigure.cip_default_role);
							userProfileData.user_roles = roles;
						}else{
							httpResponse.setHeader("Content-type", "text/html;charset=UTF-8");
							httpResponse.setCharacterEncoding("utf-8");
							httpResponse.getWriter().write(
									"{\"errorCode\":"+CIPErrorCode.USER_ROLE_IS_NULL.code+",\"msg\":\""+CIPErrorCode.USER_ROLE_IS_NULL.name+"\"}");
							httpResponse.getWriter().flush();
							return;
						}
					}
					loginUser = new CIPUser();
					loginUser.setUserId(userProfileData.user_id);
					loginUser.setUserName(userProfileData.user_name);
					loginUser.setOrgCode(userProfileData.org_id);
					loginUser.setOrgName(userProfileData.org_name);
					loginUser.setOrgType(userProfileData.org_type);
					loginUser.setUserRoles(userProfileData.user_roles);
					session.setAttribute(CIPRuntimeConstants.LOGIN_USER, loginUser);
					//初始化用户访问资源，用于访问检查
					if(resourceService==null){
						WebApplicationContext wc = ContextLoader.getCurrentWebApplicationContext();  
						resourceService = (CIPResourceService) wc.getBean("resourceService");
					}
					for( String userRole:userProfileData.user_roles){
						resourceService.initResource(userRole);
					}
					//初始化用户行为权限资源，用于行为检查
					CIPRuntime.authManager.initUserAuth(loginUser);
					
					String dynamicUrl = httpRequest.getPathInfo();
					if(dynamicUrl == null ) 
						dynamicUrl = httpRequest.getRequestURI();
					
					//check access authorization
					String actionId = request.getParameter(CIPRuntimeConstants.CIP_ACTION_ID);
					CIPErrorCode error = CIPRuntime.authManager.checkAccess(loginUser, actionId, dynamicUrl);
					if( error != null ){
						httpResponse.setHeader("Content-type", "text/html;charset=UTF-8");
						httpResponse.setCharacterEncoding("utf-8");
						httpResponse.getWriter().write(
								"{\"errorCode\":"+error.code+",\"msg\":\""+error.name+"\"}");
						httpResponse.getWriter().flush();
						return;
					}
					
					//assign current operator info
					CIPRuntimeOperator operateSubject = new CIPRuntimeOperator();
					operateSubject.setSubject_id(loginUser.getUserId());
					operateSubject.setSubject_name(loginUser.getUserName());
					Date d = new Date();
					operateSubject.setOperate_tm(DateUtils.getDateTime(d));
					//operateSubject.setOperate_dt(DateUtils.getDate(d));
					operateSubject.setSubject_ip(httpRequest.getRemoteAddr());
					operateSubject.setSubject_org(loginUser.getOrgCode());
					operateSubject.setSubject_org_name(loginUser.getOrgName());
					operateSubject.setSubject_org_type(loginUser.getOrgType());
					operateSubject.setSubject_roles(loginUser.getUserRoles());
					operateSubject.setLang(loginUser.getLang());
					
					CIPRuntime.setOperateSubject(operateSubject);
					chain.doFilter(request, response);
				} catch (Exception e) {
					if ("XMLHttpRequest".equalsIgnoreCase(type)) {
						httpResponse.setHeader("Content-type", "text/html;charset=UTF-8");
						httpResponse.setCharacterEncoding("utf-8");
						httpResponse.getWriter().write(
								"{\"errorCode\":"+CIPErrorCode.ERROR_CONNECTION_TIMEOUT.code+",\"msg\":\""+CIPErrorCode.ERROR_CONNECTION_TIMEOUT.name+"\"}");
						httpResponse.getWriter().flush();
						return;
					}else{
						httpResponse.sendRedirect(CIPRuntimeConfigure.ErrorPageURI);
						return;
					}
				}
			}else{
				if ("XMLHttpRequest".equalsIgnoreCase(type)) {
					httpResponse.setHeader("Content-type", "text/html;charset=UTF-8");
					httpResponse.setCharacterEncoding("utf-8");
					httpResponse.getWriter().write(
							"{\"errorCode\":"+CIPErrorCode.ERROR_CONNECTION_TIMEOUT.code+",\"msg\":\""+CIPErrorCode.ERROR_CONNECTION_TIMEOUT.name+"\"}");
					httpResponse.getWriter().flush();
					return;
				}else{
					httpResponse.sendRedirect(httpRequest.getContextPath() + "/" + CIPRuntimeConfigure.loginPageURI);
					return;
				}			
			}
		} else{
			
			String dynamicUrl = httpRequest.getPathInfo();
			if(dynamicUrl == null ) 
				dynamicUrl = httpRequest.getRequestURI();
			
			//check access authorization
			String actionId = request.getParameter(CIPRuntimeConstants.CIP_ACTION_ID);
			CIPErrorCode error = CIPRuntime.authManager.checkAccess(loginUser, actionId, dynamicUrl);
			if( error != null ){
				httpResponse.setHeader("Content-type", "text/html;charset=UTF-8");
				httpResponse.setCharacterEncoding("utf-8");
				httpResponse.getWriter().write(
						"{\"errorCode\":"+error.code+",\"msg\":\""+error.name+"\"}");
				httpResponse.getWriter().flush();
				return;
			}
						
			//assign current operator info
			CIPRuntimeOperator operateSubject = new CIPRuntimeOperator();
			operateSubject.setSubject_id(loginUser.getUserId());
			operateSubject.setSubject_name(loginUser.getUserName());
			Date d = new Date();
			operateSubject.setOperate_tm(DateUtils.getDateTime(d));
			operateSubject.setSubject_ip(httpRequest.getRemoteAddr());
			operateSubject.setSubject_org(loginUser.getOrgCode());
			operateSubject.setSubject_org_name(loginUser.getOrgName());
			operateSubject.setSubject_org_type(loginUser.getOrgType());
			operateSubject.setSubject_roles(loginUser.getUserRoles());
			operateSubject.setLang(loginUser.getLang());
			
			CIPRuntime.setOperateSubject(operateSubject);
			
			
				chain.doFilter(request, response);
			
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
