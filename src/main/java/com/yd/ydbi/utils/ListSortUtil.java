package com.yd.ydbi.utils;

import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;


/**
 * @author kangyuanjia
 * @createTime 2017-03-21 09:31:15
 * @describe 根据List集合中对象的属性排序
 */
public class ListSortUtil<T> {
	/** 
	 * @param <T>
	 * @param targetList 目标排序List 
	 * @param sortField 排序字段(实体类属性名) 
	 * @param sortMode 排序方式（asc or  desc） 
	 */  
	@SuppressWarnings({ "unchecked", "rawtypes" })  
	public void sort(List<T> targetList, final String sortField, final String sortMode) {  
	  
	    Collections.sort(targetList, new Comparator() {  
	        public int compare(Object obj1, Object obj2) {   
	            int retVal = 0;  
	            try {  
	                //首字母转大写  
	                String newStr=sortField.substring(0, 1).toUpperCase()+sortField.replaceFirst("\\w","");   
	                String methodStr="get"+newStr;  
	                  
	                Method method1 = ((T)obj1).getClass().getMethod(methodStr, null);  
	                Method method2 = ((T)obj2).getClass().getMethod(methodStr, null);  
	                String temp1 =  method1.invoke(((T) obj1), null).toString();
	                String temp2 = method2.invoke(((T) obj2), null).toString();
	                
	                if (sortMode != null && "desc".equals(sortMode)) {
	                	if(isInteger(temp1) && isInteger(temp2)){
	                		/*Integer val1 = Integer.parseInt(temp1);
	                		Integer val2 = Integer.parseInt(temp2);*/
	                		BigInteger val1 =new BigInteger(temp1);
	                		BigInteger val2 =new BigInteger(temp2);
	                		retVal = val2.compareTo(val1);
	                	}else if(isDecimal(temp1) && isDecimal(temp2)){
	                		Double val1 = Double.parseDouble(temp1);
	                		Double val2 = Double.parseDouble(temp2);
	                		retVal = val2.compareTo(val1);
	                	}else{
	                		retVal = method2.invoke(((T) obj2), null).toString().compareTo(method1.invoke(((T) obj1), null).toString()); // 倒序  
	                	}
	                } else {  
	                	if(isInteger(temp1) && isInteger(temp2)){
	                		Integer val1 = Integer.parseInt(temp1);
	                		Integer val2 = Integer.parseInt(temp2);
	                		retVal = val1.compareTo(val2);
	                	}else if(isDecimal(temp1) && isDecimal(temp2)){
	                		Double val1 = Double.parseDouble(temp1);
	                		Double val2 = Double.parseDouble(temp2);
	                		retVal = val1.compareTo(val2);
	                	}else{
	                		retVal = method1.invoke(((T) obj1), null).toString().compareTo(method2.invoke(((T) obj2), null).toString()); // 正序  
	                	}
	                }  
	            } catch (Exception e) {
	            	e.printStackTrace();
	                throw new RuntimeException();  
	            }  
	            return retVal;  
	        }  
	    });  
	} 
	
	public static boolean isInteger(String str) {    
	  Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");    
	  return pattern.matcher(str).matches();    
	}

	public static boolean isDecimal(String str) {    
		Pattern pattern = Pattern.compile("[-+]{0,1}\\d+\\.\\d*|[-+]{0,1}\\d*\\.\\d+");  
	  return pattern.matcher(str).matches();    
	}

}
