package com.yd.common.function.admin.data;


/**
 * <p>Data实体类</p>
 * <p>View: cip_admin_access_ctrl - 系统访问控制</p>
 * 用于检索数据，建立在视图基础上
 * @since 2015-08-20 10:26:56
 */
public class CIP_admin_access_ctrlData {


    /** 
    * access_flag - 访问标识 
    */
	private String access_flag;

    /** 
    * access_ip - 访问ip 
    */
	private String access_ip;

    /** 
    * sys_id - 系统id 
    */
	private String sys_id;

    /** 
    * access_type - 访问类型 
    */
	private String access_type;

    /** 
    * auth_code - 访问授权码 
    */
	private String auth_code;

    /** 
    * remark - 操作备注 
    */
	private String remark;

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

	/**
	* 文本描述信息
	*/
	private String access_flag_name;
	/**
	* 文本描述信息
	*/
	private String access_type_name;
	
	private String other_params;
	
	public String getOther_params() {
		return other_params;
	}

	public void setOther_params(String other_params) {
		this.other_params = other_params;
	}

	public String getAccess_flag(){
        return this.access_flag;
    }
    
    public void setAccess_flag(String access_flag){
        this.access_flag = access_flag;
    }
	public String getAccess_ip(){
        return this.access_ip;
    }
    
    public void setAccess_ip(String access_ip){
        this.access_ip = access_ip;
    }
	public String getSys_id(){
        return this.sys_id;
    }
    
    public void setSys_id(String sys_id){
        this.sys_id = sys_id;
    }
	public String getAccess_type(){
        return this.access_type;
    }
    
    public void setAccess_type(String access_type){
        this.access_type = access_type;
    }
	public String getAuth_code(){
        return this.auth_code;
    }
    
    public void setAuth_code(String auth_code){
        this.auth_code = auth_code;
    }
	public String getRemark(){
        return this.remark;
    }
    
    public void setRemark(String remark){
        this.remark = remark;
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

	public String getAccess_flag_name(){
        return this.access_flag_name;
    }
    
    public void setAccess_flag_name(String access_flag_name){
        this.access_flag_name = access_flag_name;
    }
    
	public String getAccess_type_name(){
        return this.access_type_name;
    }
    
    public void setAccess_type_name(String access_type_name){
        this.access_type_name = access_type_name;
    }
    
}