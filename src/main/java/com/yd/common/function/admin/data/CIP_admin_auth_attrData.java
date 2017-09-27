package com.yd.common.function.admin.data;


/**
 * <p>Data实体类</p>
 * <p>View: cip_admin_auth_attr - 权限对象属性信息</p>
 * 用于检索数据，建立在视图基础上
 * @since 2015-07-27 02:40:51
 */
public class CIP_admin_auth_attrData {


    /** 
    * obj_attr_id - 权限对象属性id 
    */
	private String obj_attr_id;

    /** 
    * obj_id - 权限对象id 
    */
	private String obj_id;

    /** 
    * obj_attr_name - 权限对象属性描述 
    */
	private String obj_attr_name;

    /** 
    * attr_val_table - 属性取值表 
    */
	private String attr_val_table;

    /** 
    * sup_col_id - 上级字段id 
    */
	private String sup_col_id;

    /** 
    * sub_col_id - 下级字段id 
    */
	private String sub_col_id;

    /** 
    * rel_col_id - 层级关联字段id 
    */
	private String rel_col_id;


	public String getObj_attr_id(){
        return this.obj_attr_id;
    }
    
    public void setObj_attr_id(String obj_attr_id){
        this.obj_attr_id = obj_attr_id;
    }
	public String getObj_id(){
        return this.obj_id;
    }
    
    public void setObj_id(String obj_id){
        this.obj_id = obj_id;
    }
	public String getObj_attr_name(){
        return this.obj_attr_name;
    }
    
    public void setObj_attr_name(String obj_attr_name){
        this.obj_attr_name = obj_attr_name;
    }
	public String getAttr_val_table(){
        return this.attr_val_table;
    }
    
    public void setAttr_val_table(String attr_val_table){
        this.attr_val_table = attr_val_table;
    }
	public String getSup_col_id(){
        return this.sup_col_id;
    }
    
    public void setSup_col_id(String sup_col_id){
        this.sup_col_id = sup_col_id;
    }
	public String getSub_col_id(){
        return this.sub_col_id;
    }
    
    public void setSub_col_id(String sub_col_id){
        this.sub_col_id = sub_col_id;
    }
	public String getRel_col_id(){
        return this.rel_col_id;
    }
    
    public void setRel_col_id(String rel_col_id){
        this.rel_col_id = rel_col_id;
    }

}