package com.yd.common.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yd.common.cipher.CIPDesUtils;
import com.yd.common.data.CIPResponseMsg;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntime;
import com.yd.common.runtime.CIPRuntimeConfigure;
import com.yd.common.runtime.CIPRuntimeConstants;
import com.yd.common.runtime.CIPRuntimeOperator;
import com.yd.common.utils.DateUtils;
import com.yd.common.utils.JSONUtils;



/**
 *
 * 登录过滤器
 * 注：解决请求合法性问题，用auth_code识别当前用户的合法性
 */
public class CIPRemoteLoginFilter implements Filter {
	
    /**
     * Default constructor. 
     */
    public CIPRemoteLoginFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		httpResponse.setHeader("Pragma","No-cache");  
		httpResponse.setHeader("Cache-Control","no-cache");  
		httpResponse.setDateHeader("Expires", 0); 
		httpResponse.setCharacterEncoding("UTF-8");
		httpResponse.setContentType("application/json;charset=UTF-8");
		
		//1. 获取auth_code信息 
		String authCode = request.getParameter(CIPRuntimeConstants.AUTH_CODE);
		String srcSystemId = request.getParameter(CIPRuntimeConstants.AUTH_SOURCE_SYSTEM_ID);
		String srcIp = request.getRemoteAddr();
		CIPResponseMsg msg;
		PrintWriter writer;
		if(authCode == null||"".equals(authCode)||srcSystemId==null||srcSystemId.equals("")){
			msg = new CIPResponseMsg();
			msg.errorCode = CIPErrorCode.ERROR_INVALID_AUTHORIZATION.code;
			msg.msg = CIPErrorCode.ERROR_INVALID_AUTHORIZATION.name;
			writer = httpResponse.getWriter();
			writer.write(JSONUtils.convertObject2Json(msg));
			writer.flush();
			writer.close();
			return;
		}
		
		//2.验证授权码
		String time = request.getParameter(CIPRuntimeConstants.CIP_TIME);
		boolean xValid = false;
		if(time!=null){
			long t = Long.parseLong(time);
			authCode = CIPDesUtils.decrypt(authCode, t);
			xValid = CIPRuntime.authManager.checkAuthCode(authCode,srcSystemId,srcIp);
		}	
		if(!xValid){
			msg = new CIPResponseMsg();
			msg.errorCode = CIPErrorCode.ERROR_INVALID_AUTHORIZATION.code;
			msg.msg = CIPErrorCode.ERROR_INVALID_AUTHORIZATION.name;
			writer = httpResponse.getWriter();
			writer.write(JSONUtils.convertObject2Json(msg));
			writer.flush();
			writer.close();
			return;
		}
		
		//3.验证资源访问授权
		String resUri = httpRequest.getPathInfo();
		if(resUri == null ) 
			resUri = httpRequest.getRequestURI();

		String resourceId = request.getParameter(CIPRuntimeConstants.CIP_ACTION_ID);

		xValid = CIPRuntime.authManager.checkSystemAccess(srcSystemId, resourceId, resUri);
		if(!xValid){
			msg = new CIPResponseMsg();
			msg.errorCode = CIPErrorCode.ERROR_ILLEGAL_ACCESS.code;
			msg.msg = CIPErrorCode.ERROR_ILLEGAL_ACCESS.name;
			writer = httpResponse.getWriter();
			writer.write(JSONUtils.convertObject2Json(msg));
			writer.flush();
			writer.close();
			return;
		}
		
		//4. 确认合法auth_code,则从session中获取system user对象
		String sessionId = CIPRuntime.runtimeInfo.get(CIPRuntimeConstants.SYSTEM_SESSION_ID);
		CIPHttpSession session = CIPSessionManager.getSystemSession(sessionId);
		CIPUser loginUser = session.getAttribute(CIPRuntimeConstants.LOGIN_USER, CIPUser.class);
		if(loginUser == null){
			//不能做系统集成
			msg = new CIPResponseMsg();
			msg.errorCode = CIPErrorCode.ERROR_NOT_SUPPORT_REMOTE_CALL.code;
			msg.msg = CIPErrorCode.ERROR_NOT_SUPPORT_REMOTE_CALL.name;
			writer = httpResponse.getWriter();
			writer.write(JSONUtils.convertObject2Json(msg));
			writer.flush();
			writer.close();
			return;		
		}else{
			CIPRuntimeOperator operateSubject = new CIPRuntimeOperator();
			operateSubject.setSubject_id(loginUser.getUserId());
			Date d = new Date();
			operateSubject.setOperate_tm(DateUtils.getDateTime(d));
			operateSubject.setOperate_dt(DateUtils.getDate(d));
			operateSubject.setSubject_ip(request.getRemoteAddr());
			operateSubject.setLang(CIPRuntimeConfigure.cip_default_lang);
			
			CIPRuntime.setOperateSubject(operateSubject);
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
