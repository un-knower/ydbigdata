package com.yd.common.function.admin.data;


/**
 * <p>Data实体类</p>
 * <p>View: cip_admin_log_mdm - 本地日志-主数据变更</p>
 * 用于检索数据，建立在视图基础上
 * @since 2015-08-26 08:45:49
 */
public class CIP_admin_log_mdmData {


    /** 
    * log_id - 日志id 
    */
	private long log_id;

    /** 
    * table_id - 数据表id 
    */
	private String table_id;

    /** 
    * record_id - 记录id 
    */
	private String record_id;

    /** 
    * old_values - 原始值 
    */
	private String old_values;

    /** 
    * new_values - 新设置值 
    */
	private String new_values;

    /** 
    * user_id - 变更人 
    */
	private String user_id;

    /** 
    * operate_type - 操作类型 
    */
	private int operate_type;

    /** 
    * occur_time - 记录时间 
    */
	private String occur_time;


	public long getLog_id(){
        return this.log_id;
    }
    
    public void setLog_id(long log_id){
        this.log_id = log_id;
    }
	public String getTable_id(){
        return this.table_id;
    }
    
    public void setTable_id(String table_id){
        this.table_id = table_id;
    }
	public String getRecord_id(){
        return this.record_id;
    }
    
    public void setRecord_id(String record_id){
        this.record_id = record_id;
    }
	public String getOld_values(){
        return this.old_values;
    }
    
    public void setOld_values(String old_values){
        this.old_values = old_values;
    }
	public String getNew_values(){
        return this.new_values;
    }
    
    public void setNew_values(String new_values){
        this.new_values = new_values;
    }
	public String getUser_id(){
        return this.user_id;
    }
    
    public void setUser_id(String user_id){
        this.user_id = user_id;
    }
	public int getOperate_type(){
        return this.operate_type;
    }
    
    public void setOperate_type(int operate_type){
        this.operate_type = operate_type;
    }
	public String getOccur_time(){
        return this.occur_time;
    }
    
    public void setOccur_time(String occur_time){
        this.occur_time = occur_time;
    }

}