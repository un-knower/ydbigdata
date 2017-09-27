package com.yd.ydbi.common.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntime;
import com.yd.common.runtime.CIPRuntimeConfigure;
import com.yd.common.runtime.CIPRuntimeConstants;
import com.yd.common.runtime.CIPRuntimeOperator;
import com.yd.common.session.CIPHttpSession;
import com.yd.common.session.CIPSessionManager;
import com.yd.common.session.CIPUser;
import com.yd.common.utils.DateUtils;


public class MobileSessionFilter implements Filter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MobileSessionFilter.class);
	
	@SuppressWarnings("unused")
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		
		String type = httpRequest.getHeader("X-Requested-With");
		if ("XMLHttpRequest".equalsIgnoreCase(type)) {//ajax请求
			httpResponse.setHeader("Pragma", "no-cache");
			httpResponse.setDateHeader("Expires", 0);
			httpResponse.setHeader("Cache-Control", "no-cache, must-revalidate");
		}
		
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
		
		loginUser = session.getAttribute(CIPRuntimeConstants.LOGIN_USER, CIPUser.class);
		//无手机端用户信息
		if(loginUser == null){
			if ("XMLHttpRequest".equalsIgnoreCase(type)) {
				httpResponse.setHeader("Content-type", "text/html;charset=UTF-8");
				httpResponse.setCharacterEncoding("utf-8");
				httpResponse.getWriter().write(
						"{\"errorCode\":"+0+",\"msg\":\""+0+"\"}");
				httpResponse.getWriter().flush();
				return;
			}else{
				httpResponse.sendRedirect(httpRequest.getContextPath() + "/" + "login.html");
				return;
			}
		}else{
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
				
			chain.doFilter(httpRequest, httpResponse);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
}
