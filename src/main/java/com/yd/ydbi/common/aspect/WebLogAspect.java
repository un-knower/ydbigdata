package com.yd.ydbi.common.aspect;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.yd.common.runtime.CIPRuntime;

/**
 * @author kangyuanjia
 * @createTime 2017-03-14 11:32:12
 * @describe 保存请求数据到mongodb
 */
//@Aspect
@Component
public class WebLogAspect {

	private Logger logger = Logger.getLogger("mongodb");
	ThreadLocal<Long> startTime = new ThreadLocal<Long>();
	HttpServletRequest request;
	JoinPoint mJoinPoint;

	@Pointcut("execution (* com.yd.ydbi..controller.*.*(..))")
	public void webLog() {
	}

	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		startTime.set(System.currentTimeMillis());
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		request = attributes.getRequest();
		mJoinPoint = joinPoint;
	}

	private JSONObject getBasicDBObject(HttpServletRequest request, JoinPoint joinPoint) {
		JSONObject r = new JSONObject();
		// r.append("requestURL", request.getRequestURL().toString());
		r.put("requestURI", request.getRequestURI());
		r.put("queryString", request.getQueryString());
		// r.append("remoteAddr", request.getRemoteAddr());
		r.put("remoteHost", request.getRemoteHost());
		// r.append("remotePort", request.getRemotePort());
		// r.append("localAddr", request.getLocalAddr());
		// r.append("localName", request.getLocalName());
		r.put("method", request.getMethod());
		r.put("headers", getHeadersInfo(request));
		r.put("parameters", request.getParameterMap());
		r.put("classMethod",
				joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
		// r.append("args", Arrays.toString(joinPoint.getArgs()));
		if (CIPRuntime.getOperateSubject() != null) {
			r.put("userId", CIPRuntime.getOperateSubject().getSubject_id());
			r.put("userRoles", CIPRuntime.getOperateSubject().getSubject_roles());
			r.put("orgCd", CIPRuntime.getOperateSubject().getSubject_org());
			r.put("orgType", CIPRuntime.getOperateSubject().getSubject_org_type());
		}
		r.put("requestTm", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		return r;
	}

	private Map<String, String> getHeadersInfo(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			if (!"cookie".equals(key) && !"user-agent".equals(key)) {
				continue;
			}
			map.put(key, value);
		}
		return map;
	}

	@AfterReturning(returning = "ret", pointcut = "webLog()")
	public void doAfterReturning(Object ret) throws Throwable {
		JSONObject logInfo;
		logInfo = getBasicDBObject(request, mJoinPoint);
		// 处理完请求，返回内容
		logInfo.put("responseCode", ret.toString());
		logInfo.put("spendTime", (System.currentTimeMillis() - startTime.get()));
		logger.info(logInfo);
	}
}