package com.yd.common.function.meta.data;


/**
 * <p>Data实体类</p>
 * <p>View: cip_meta_module - 应用模块信息</p>
 * 用于检索数据，建立在视图基础上
 * @since 2015-10-08 09:03:37
 */
public class CIP_meta_moduleData {


    /** 
    * module_id - 模块id 
    */
	private String module_id;

    /** 
    * module_name - 模块名称 
    */
	private String module_name;

    /** 
    * module_memo - 模块描述 
    */
	private String module_memo;

    /** 
    * update_time - 修改时间 
    */
	private String update_time;

    /** 
    * create_time - 创建时间 
    */
	private String create_time;

    /** 
    * operator - 操作人员 
    */
	private String operator;


	public String getModule_id(){
        return this.module_id;
    }
    
    public void setModule_id(String module_id){
        this.module_id = module_id;
    }
	public String getModule_name(){
        return this.module_name;
    }
    
    public void setModule_name(String module_name){
        this.module_name = module_name;
    }
	public String getModule_memo(){
        return this.module_memo;
    }
    
    public void setModule_memo(String module_memo){
        this.module_memo = module_memo;
    }
	public String getUpdate_time(){
        return this.update_time;
    }
    
    public void setUpdate_time(String update_time){
        this.update_time = update_time;
    }
	public String getCreate_time(){
        return this.create_time;
    }
    
    public void setCreate_time(String create_time){
        this.create_time = create_time;
    }
	public String getOperator(){
        return this.operator;
    }
    
    public void setOperator(String operator){
        this.operator = operator;
    }

}