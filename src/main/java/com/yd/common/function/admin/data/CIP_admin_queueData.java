package com.yd.common.function.admin.data;


/**
 * <p>Data实体类</p>
 * <p>View: cip_admin_queue - 系统队列信息</p>
 * 用于检索数据，建立在视图基础上
 * @since 2015-08-18 05:02:08
 */
public class CIP_admin_queueData {


    /** 
    * queue_id - 队列流水号 
    */
	private String queue_id;

    /** 
    * queue_type - 队列类型 
    */
	private String queue_type;

    /** 
    * queue_data - 队列数据 
    */
	private String queue_data;

    /** 
    * queue_flag - 数据提取标识 
    */
	private String queue_flag;

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
	private String queue_type_name;
	/**
	* 文本描述信息
	*/
	private String queue_flag_name;

	public String getQueue_id(){
        return this.queue_id;
    }
    
    public void setQueue_id(String queue_id){
        this.queue_id = queue_id;
    }
	public String getQueue_type(){
        return this.queue_type;
    }
    
    public void setQueue_type(String queue_type){
        this.queue_type = queue_type;
    }
	public String getQueue_data(){
        return this.queue_data;
    }
    
    public void setQueue_data(String queue_data){
        this.queue_data = queue_data;
    }
	public String getQueue_flag(){
        return this.queue_flag;
    }
    
    public void setQueue_flag(String queue_flag){
        this.queue_flag = queue_flag;
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

	public String getQueue_type_name(){
        return this.queue_type_name;
    }
    
    public void setQueue_type_name(String queue_type_name){
        this.queue_type_name = queue_type_name;
    }
    
	public String getQueue_flag_name(){
        return this.queue_flag_name;
    }
    
    public void setQueue_flag_name(String queue_flag_name){
        this.queue_flag_name = queue_flag_name;
    }
    
}