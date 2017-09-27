package com.yd.common.function.admin.data;


/**
 * <p>Data实体类</p>
 * <p>View: cip_admin_access_rel - 系统与资源关系配置</p>
 * 用于检索数据，建立在视图基础上
 * @since 2015-08-20 09:11:16
 */
public class CIP_admin_access_relData {


    /** 
    * resource_id - 资源id 
    */
	private String resource_id;

    /** 
    * sys_id - 系统id 
    */
	private String sys_id;

    /** 
    * create_time - 系统生成时间 
    */
	private String create_time;

    /** 
    * operator - 操作人员 
    */
	private String operator;


	public String getResource_id(){
        return this.resource_id;
    }
    
    public void setResource_id(String resource_id){
        this.resource_id = resource_id;
    }
	public String getSys_id(){
        return this.sys_id;
    }
    
    public void setSys_id(String sys_id){
        this.sys_id = sys_id;
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