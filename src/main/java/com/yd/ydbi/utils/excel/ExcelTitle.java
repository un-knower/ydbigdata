package com.yd.ydbi.utils.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention (RetentionPolicy.RUNTIME)
@Target (ElementType.FIELD)
public @interface ExcelTitle {

	String name();
	
	int order();
	
	//字段值不能为空
	boolean notNull() default true;
}
