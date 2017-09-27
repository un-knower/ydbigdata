package com.yd.ydbi.common.aspect;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**  
* ClassName: aoplog
* Function: 给要添加注释的方法定义注释：包含哪些注释
* Reason: TODO ADD REASON(可选).
* date: 2016年6月16日 下午12:49:10
*
* @author SHAOHUI
* @version 
* @since JDK 1.7
*/
@Target({ElementType.METHOD,ElementType.PARAMETER})    
@Retention(RetentionPolicy.RUNTIME)    
@Documented    
public  @interface AopLog {    
	//执行动作
    String action()  default ""; 
    //操作模块
    String module() default "";
    
}    
    