package com.yd.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.yd.common.exception.CIPRuntimeException;
import com.yd.common.runtime.CIPErrorCode;

public class ReflectUtils {

	public static <T> List<Field> contrastDiff(T t1, T t2) throws Exception{
		List<Field> result = new ArrayList<>();
		Field[] fields = t1.getClass().getDeclaredFields();
		for(Field field : fields){
			field.setAccessible(true);
			Object val1 = field.get(t1);
			Object val2 = field.get(t2);
			if(!equals(val1, val2)){
				result.add(field);
			}
			
		}
		return result;
	}
	
	public static boolean equals(Object obj1, Object obj2) {  
		  
	    if (obj1 == obj2) {  
	        return true;  
	    }  
	    if(obj1==null &&  obj2 instanceof String && obj2.equals("") ){
	    	return true;
	    }
	    if(obj2==null &&  obj1 instanceof String && obj1.equals("") ){
	    	return true;
	    }
	    if (obj1 == null && obj2 == null) {  
	        return true;  
	    } 
	    if(obj1.equals(obj2)){
	    	return true;
	    }
	    return false;  
	}  
	
	public static <T> void setValue(T t, String field, String value){
		try {
			Class<? extends Object> clazz = t.getClass();
			Field f = t.getClass().getDeclaredField(field);
			f.setAccessible(true);
			Class<?> type = f.getType();
			Method method = clazz.getDeclaredMethod("set" + String.valueOf(field.charAt(0)).toUpperCase() + field.substring(1), type);
			String typeName = type.toString();
			if(typeName.endsWith("String")){
				method.invoke(t, value);
			}else if(typeName.endsWith("short")){
				method.invoke(t, Short.valueOf(value));
			}else if(typeName.endsWith("int")){
				method.invoke(t, Integer.valueOf(value));
			}else if(typeName.endsWith("long")) {
				method.invoke(t, Long.valueOf(value));
			}else if(typeName.endsWith("double")) {
				method.invoke(t, Double.valueOf(value));
			}else if(typeName.endsWith("float")) {
				method.invoke(t, Float.valueOf(value));
			}else if(typeName.endsWith("boolean")) {
				method.invoke(t, Boolean.valueOf(value));
			}else if(typeName.endsWith("char")) {
				method.invoke(t, Character.valueOf(value.charAt(0)));
			}else if(typeName.endsWith("char")) {
				method.invoke(t, Character.valueOf(value.charAt(0)));
			}else if(typeName.endsWith("Date")) {
				method.invoke(t, DateUtils.parseDate(value));
			}else if(typeName.endsWith("Timestamp")) {
				method.invoke(t, Timestamp.valueOf(value));
			}
		} catch (Exception e) {
			throw new CIPRuntimeException(new CIPErrorCode(-1, "字段值赋值失败"));
		}
	}
}
