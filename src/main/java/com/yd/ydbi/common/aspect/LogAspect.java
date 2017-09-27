package com.yd.ydbi.common.aspect;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.yd.common.runtime.CIPRuntime;
import com.yd.common.utils.SerialNoUtils;

/**
 * ClassName:logaspect
 * Function: 给添加注释的方法添加操作日志
 * Date: 2016年6月15日 下午12:58:05
 * @author SHAOHUI
 * @version
 * @since JDK 1.7
 * @see
 */
@Aspect    
@Component    
public class LogAspect {
	private Logger logger = Logger.getLogger("logAspect");	
	ThreadLocal<Long> startTime = new ThreadLocal<Long>();
	
	//切点    
    @Pointcut("@annotation(com.yd.ydbi.common.aspect.AopLog)")    
     public  void logAspect() {  
    	
    }  
    
    
    @Before("logAspect()")    
    public  void doBefore(JoinPoint joinPoint) {
    	startTime.set(System.currentTimeMillis());
    	
    }
    
    @AfterReturning(returning = "ret", pointcut = "logAspect()")
    public  void doAfterReturning(JoinPoint joinPoint,Object ret) {
    	UlpLogBean logBean = new UlpLogBean();
    	String json= "";
    	
    		try {
				List<Map<String, Object>> des = getServiceMthodDescription(joinPoint);
				for (Object method : joinPoint.getArgs()) {   
					 try {
						json = JSON.toJSONString(method);
					} catch (Exception e) {
					} 
				}
				Timestamp ts = new Timestamp(System.currentTimeMillis());  
				String  record = String.valueOf(SerialNoUtils.getTimeSerialNo());
				
				logBean.setL_sys("ydbi");
				logBean.setL_mod((String)des.get(1).get("module"));
				logBean.setL_opt(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
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
    
    public  static List<Map<String, Object>> getServiceMthodDescription(JoinPoint joinPoint)    
            throws Exception {    
       String targetName = joinPoint.getTarget().getClass().getName();    
       String methodName = joinPoint.getSignature().getName();    
       Object[] arguments = joinPoint.getArgs();    
       Class targetClass = Class.forName(targetName);    
       Method[] methods = targetClass.getMethods(); 
       List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        for (Method method : methods) {    
            if (method.getName().equals(methodName)) {    
               Class[] clazzs = method.getParameterTypes();    
                if (clazzs.length == arguments.length) {  
                	Map<String, Object> action = new HashMap<>();
                	Map<String, Object> model = new HashMap<>();
                	action.put("action",method.getAnnotation(AopLog. class).action() );
                	model.put("module",method.getAnnotation(AopLog. class).module() );
                	data.add(action);
                	data.add(model);
                    break;    
               }    
           }    
       }    
        return data;    
   }    

}
