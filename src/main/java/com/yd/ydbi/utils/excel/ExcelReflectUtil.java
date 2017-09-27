package com.yd.ydbi.utils.excel;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelReflectUtil {

	public static void invokeSetMethod4Field(Object obj, String fieldName, String fieldValue) throws Exception{
		Class clazz = obj.getClass();
		//获取并设置参数
		Field field = clazz.getDeclaredField(fieldName);
		ExcelTitle excelTitle = field.getAnnotation(ExcelTitle.class);  
		if(excelTitle!=null && excelTitle.notNull() && (fieldValue==null || fieldValue.equals(""))){
			throw new RuntimeException("数据格式不符合要求");
		}
		field.setAccessible(true);
		Class<?> type = field.getType();
		@SuppressWarnings("unchecked")
		Method method = clazz.getDeclaredMethod("set" + String.valueOf(fieldName.charAt(0)).toUpperCase() + fieldName.substring(1), type);
		try {
			if(type.toString().endsWith("String")){
				method.invoke(obj, fieldValue);
			}else if(type.toString().endsWith("short") || type.toString().endsWith("Short")){
				method.invoke(obj, Short.valueOf(fieldValue.equals("") ? "0" : fieldValue));
			}else if(type.toString().endsWith("int") || type.toString().endsWith("Integer")){
				method.invoke(obj, Integer.valueOf(fieldValue.equals("") ? "0" : fieldValue));
			}else if(type.toString().endsWith("long") || type.toString().endsWith("Long")) {
				method.invoke(obj, Long.valueOf(fieldValue.equals("") ? "0" : fieldValue));
			}else if(type.toString().endsWith("double") || type.toString().endsWith("Double")) {
				method.invoke(obj, Double.valueOf(fieldValue.equals("") ? "0" : fieldValue));
			}else if(type.toString().endsWith("float") || type.toString().endsWith("Float")) {
				method.invoke(obj, Float.valueOf(fieldValue.equals("") ? "0" : fieldValue));
			}else if(type.toString().endsWith("boolean") || type.toString().endsWith("Boolean")) {
				method.invoke(obj, Boolean.valueOf(fieldValue));
			}else if(type.toString().endsWith("char") || type.toString().endsWith("Character")) {
				method.invoke(obj, Character.valueOf(fieldValue.charAt(0)));
			}else if(type.toString().endsWith("BigDecimal")){
				method.invoke(obj, new BigDecimal(fieldValue.equals("") ? "0" : fieldValue));
			}
		} catch (NumberFormatException e) {
			throw new RuntimeException("数据格式不符合要求");
		}
	}

	public static Object invokeGetMethod4Field(Object obj, String fieldName) throws Exception{
		Class clazz = obj.getClass();
		//获取并设置参数
		Field field = clazz.getDeclaredField(fieldName);
		field.setAccessible(true);
		@SuppressWarnings("unchecked")
		Method method = clazz.getDeclaredMethod("get" + String.valueOf(fieldName.charAt(0)).toUpperCase() + fieldName.substring(1));
		return method.invoke(obj);
	}
	
	public static Map<String, String> getFieldExcelAnnotationMap(Class<?> clazz){
		Field[] fields = clazz.getDeclaredFields(); 
		List<ExcelSortableField> ExcelSortableFields = new ArrayList<ExcelSortableField>();
		Map<String, String> map = new LinkedHashMap<String, String>();
		for(Field field : fields){  
            //获取字段中包含ExcelTitle的注解  
			ExcelTitle excelTitle = field.getAnnotation(ExcelTitle.class);  
            if(excelTitle!=null){  
            	ExcelSortableField ExcelSortableField = new ExcelSortableField(excelTitle, field);
            	ExcelSortableFields.add(ExcelSortableField);
            }  
        }
		Collections.sort(ExcelSortableFields, new Comparator<ExcelSortableField>() {  
            @Override  
            public int compare(ExcelSortableField s1,ExcelSortableField s2) {  
                return s1.getExcelTitle().order()-s2.getExcelTitle().order();  
            }  
        }); 
		for(ExcelSortableField ExcelSortableField : ExcelSortableFields){
			map.put(ExcelSortableField.getExcelTitle().name(), ExcelSortableField.getField().getName());
		}
		return map;
	}
	
	public static void main(String[] args) {
//		Map<String, String> excelNameMap = ExcelReflectUtil.getFieldExcelAnnotationMap(Wgt_diff_tot.class);

	}
}
