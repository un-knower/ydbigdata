package com.yd.ydbi.common.aspect;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yd.common.runtime.CIPRuntime;
import com.yd.common.utils.SerialNoUtils;

/**
 * @author kangyuanjia
 * @createTime 2017-04-05 11:44:13
 * @describe 将用户请求的日志通过Log4j单独输出
 */
@Aspect
@Component
public class WebLogLog4jAspect {

	private Logger logger = Logger.getLogger("webLog");
	
	ThreadLocal<Long> startTime = new ThreadLocal<Long>();

	@Pointcut("execution (* com.yd.ydbi..controller.*.*(..))")
	public void webLog() {
	}

	@Before("webLog()")
    public  void doBefore(JoinPoint joinPoint) {
    	startTime.set(System.currentTimeMillis());
    	
    }
    
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public  void doAfterReturning(JoinPoint joinPoint,Object ret) {
    	UlpLogBean logBean = new UlpLogBean();
    	String json= "";
    	
    		try {
    			String[] modNm = (joinPoint.getSignature().getDeclaringTypeName()).split("\\.");
    			Object[] args = joinPoint.getArgs();
    			json = JSON.toJSONString(args[0]);
				
				Timestamp ts = new Timestamp(System.currentTimeMillis());  
				String  record = String.valueOf(SerialNoUtils.getTimeSerialNo());
				
				logBean.setL_sys("monitor");
				logBean.setL_mod(modNm[3]);
				logBean.setL_opt(modNm[5]+"-"+joinPoint.getSignature().getName());
				logBean.setL_lvl("info");
				logBean.setL_par("");
				if (CIPRuntime.getOperateSubject() != null) {
					logBean.setL_emp(CIPRuntime.getOperateSubject().getSubject_id());
					logBean.setL_cmp(CIPRuntime.getOperateSubject().getSubject_org());
					logBean.setL_ipx(CIPRuntime.getOperateSubject().getSubject_ip());
				}else{
					logBean.setL_emp("");
					logBean.setL_cmp("");
					logBean.setL_ipx("");
				}
				logBean.setL_dev("");
				logBean.setL_beg(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime.get()));
				logBean.setL_end(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
				logBean.setL_est(String.valueOf(System.currentTimeMillis() - startTime.get()));
				logBean.setL_input(json);
				if(ret != null){
					logBean.setL_output(ret.toString());
					}else{
						logBean.setL_output("");
					}
				logBean.setL_inf("");
				
				 logger.info(JSON.toJSONString(logBean));
			} catch (Exception e) {
				e.printStackTrace();
			}
    }
    
}