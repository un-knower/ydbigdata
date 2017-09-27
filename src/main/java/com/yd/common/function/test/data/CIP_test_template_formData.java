package com.yd.common.function.test.data;


/**
 * <p>Data实体类</p>
 * <p>View: cip_test_template_form - 测试数据模板字段</p>
 * 用于检索数据，建立在视图基础上
 * @since 2015-11-24 04:16:55
 */
public class CIP_test_template_formData {

    /** 
    * field_id - 字段ID 
    */
	private String field_id;
    /** 
    * template_id - 所属模板ID 
    */
	private String template_id;
    /** 
    * field_name - 字段名称 
    */
	private String field_name;
    /** 
    * isMandatory - 是否必输 
    */
	private String isMandatory;
    /** 
    * field_ui - 字段UI 
    */
	private String field_ui;
    /** 
    * create_time - 系统时间 
    */
	private String create_time;
    /** 
    * update_time - 修改时间 
    */
	private String update_time;
    /** 
    * operator - 操作人员 
    */
	private String operator;


	public String getField_id(){
        return this.field_id;
    }
    
    public void setField_id(String field_id){
        this.field_id = field_id;
    }
	public String getTemplate_id(){
        return this.template_id;
    }
    
    public void setTemplate_id(String template_id){
        this.template_id = template_id;
    }
	public String getField_name(){
        return this.field_name;
    }
    
    public void setField_name(String field_name){
        this.field_name = field_name;
    }
	public String getIsMandatory(){
        return this.isMandatory;
    }
    
    public void setIsMandatory(String isMandatory){
        this.isMandatory = isMandatory;
    }
	public String getField_ui(){
        return this.field_ui;
    }
    
    public void setField_ui(String field_ui){
        this.field_ui = field_ui;
    }
	public String getCreate_time(){
        return this.create_time;
    }
    
    public void setCreate_time(String create_time){
        this.create_time = create_time;
    }
	public String getUpdate_time(){
        return this.update_time;
    }
    
    public void setUpdate_time(String update_time){
        this.update_time = update_time;
    }
	public String getOperator(){
        return this.operator;
    }
    
    public void setOperator(String operator){
        this.operator = operator;
    }

}