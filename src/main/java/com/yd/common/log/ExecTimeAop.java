package com.yd.common.log;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

//@Component
//@Aspect
public class ExecTimeAop {
	private Logger log = Logger.getLogger(ExecTimeAop.class);
	@Pointcut("execution (* com.yd.common..*.*(..))")  
    public void pointcut(){}  
      
    //方法执行前调用  
    @Before(value="pointcut()")  
    public void before() {   
    }  
    @After (value="pointcut()")  
    public void after(){
    }
    //方法执行的前后调用  
    @Around("pointcut()")  
    public Object around(ProceedingJoinPoint point) throws Throwable{  
        long time=System.currentTimeMillis();
        Object retVal=point.proceed();
        log.info(point.getSignature().getDeclaringTypeName()+"."+point.getSignature().getName()+" 方法运行时间："+ ( (System.currentTimeMillis()-time))+"ms");
        return retVal;  
    }    
}
