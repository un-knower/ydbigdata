package com.yd.common.function.admin.data;


/**
 * <p>Data实体类</p>
 * <p>View: cip_admin_auth_act2obj - 权限对象授权配置</p>
 * 用于检索数据，建立在视图基础上
 * @since 2015-07-27 02:40:51
 */
public class CIP_admin_auth_act2objData {


    /** 
    * obj_attr_id - 权限对象属性id 
    */
	private String obj_attr_id;

    /** 
    * obj_id - 权限对象id 
    */
	private String obj_id;

    /** 
    * resource_id - 行为资源id 
    */
	private String resource_id;

    /** 
    * root_node_id - 资源根节点 
    */
	private String root_node_id;
	/**
	 * field_id - 表字段id
	 */
	private String field_id;
    /** 
    * attr_value - 权限对象属性值 
    */
	private String attr_value;

    /** 
    * val_src_type - 属性值来源 
    */
	private String val_src_type;

    /** 
    * incl_sub_flag - 包含属性下级标识 
    */
	private String incl_sub_flag;

	/**
	* 文本描述信息
	*/
	private String val_src_type_name;
	/**
	* 文本描述信息
	*/
	private String incl_sub_flag_name;

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
	public String getResource_id(){
        return this.resource_id;
    }
    
    public void setResource_id(String resource_id){
        this.resource_id = resource_id;
    }
	public String getRoot_node_id(){
        return this.root_node_id;
    }
    
    public void setRoot_node_id(String root_node_id){
        this.root_node_id = root_node_id;
    }
	public String getAttr_value(){
        return this.attr_value;
    }
    
    public void setAttr_value(String attr_value){
        this.attr_value = attr_value;
    }
	public String getVal_src_type(){
        return this.val_src_type;
    }
    
    public void setVal_src_type(String val_src_type){
        this.val_src_type = val_src_type;
    }
	public String getIncl_sub_flag(){
        return this.incl_sub_flag;
    }
    
    public void setIncl_sub_flag(String incl_sub_flag){
        this.incl_sub_flag = incl_sub_flag;
    }

	public String getVal_src_type_name(){
        return this.val_src_type_name;
    }
    
    public void setVal_src_type_name(String val_src_type_name){
        this.val_src_type_name = val_src_type_name;
    }
    
	public String getIncl_sub_flag_name(){
        return this.incl_sub_flag_name;
    }
    
    public void setIncl_sub_flag_name(String incl_sub_flag_name){
        this.incl_sub_flag_name = incl_sub_flag_name;
    }

	public String getField_id() {
		return field_id;
	}

	public void setField_id(String field_id) {
		this.field_id = field_id;
	}
    
}