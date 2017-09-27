package com.yd.common.function.test.data;


/**
 * <p>Data实体类</p>
 * <p>View: cip_test_clazz_med - 测试类方法</p>
 * 用于检索数据，建立在视图基础上
 * @since 2015-10-10 02:52:52
 */
public class CIP_test_clazz_medData {

	/**
	 * sys_id - 系统Id
	 */
	private String sys_id;
    public String getSys_id() {
		return sys_id;
	}

	public void setSys_id(String sys_id) {
		this.sys_id = sys_id;
	}

	/** 
    * action_id - 行为id 
    */
	private String action_id;
    /** 
    * action_name - 动作名称 
    */
	private String action_name;
    /** 
    * create_time - 系统时间 
    */
	private String create_time;
    /** 
    * in_data_clazz - 输入数据对象 
    */
	private String in_data_clazz;
    /** 
    * operator - 操作人员 
    */
	private String operator;
    /** 
    * out_data_clazz - 输出数据对象 
    */
	private String out_data_clazz;
    /** 
    * test_clazz - 测试类 
    */
	private String test_clazz;
    /** 
    * test_uri - 测试uri 
    */
	private String test_uri;
    /** 
    * update_time - 修改时间 
    */
	private String update_time;


	public String getAction_id(){
        return this.action_id;
    }
    
    public void setAction_id(String action_id){
        this.action_id = action_id;
    }
	public String getAction_name(){
        return this.action_name;
    }
    
    public void setAction_name(String action_name){
        this.action_name = action_name;
    }
	public String getCreate_time(){
        return this.create_time;
    }
    
    public void setCreate_time(String create_time){
        this.create_time = create_time;
    }
	public String getIn_data_clazz(){
        return this.in_data_clazz;
    }
    
    public void setIn_data_clazz(String in_data_clazz){
        this.in_data_clazz = in_data_clazz;
    }
	public String getOperator(){
        return this.operator;
    }
    
    public void setOperator(String operator){
        this.operator = operator;
    }
	public String getOut_data_clazz(){
        return this.out_data_clazz;
    }
    
    public void setOut_data_clazz(String out_data_clazz){
        this.out_data_clazz = out_data_clazz;
    }
	public String getTest_clazz(){
        return this.test_clazz;
    }
    
    public void setTest_clazz(String test_clazz){
        this.test_clazz = test_clazz;
    }
	public String getTest_uri(){
        return this.test_uri;
    }
    
    public void setTest_uri(String test_uri){
        this.test_uri = test_uri;
    }
	public String getUpdate_time(){
        return this.update_time;
    }
    
    public void setUpdate_time(String update_time){
        this.update_time = update_time;
    }

}