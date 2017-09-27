package com.yd.common.function.test.data;


/**
 * <p>Data实体类</p>
 * <p>View: cip_test_case - 系统测试用例</p>
 * 用于检索数据，建立在视图基础上
 * @since 2015-10-10 02:52:52
 */
public class CIP_test_caseData {

	/**
	 *scenario_id - 所属场景Id 
	 */
	private String scenario_id;
	
	
    public String getScenario_id() {
		return scenario_id;
	}

	public void setScenario_id(String scenario_id) {
		this.scenario_id = scenario_id;
	}

	/**
	 * sys_id - 系统ID
	 */
	private String sys_id;
	
	public String getSys_id() {
		return sys_id;
	}

	public void setSys_id(String sys_id) {
		this.sys_id = sys_id;
	}

	/** 
    * action_id - 测试行为id 
    */
	private String action_id;
    /** 
    * case_id - 测试用例id 
    */
	private String case_id;
    /** 
    * case_name - 测试用例名称 
    */
	private String case_name;
    /** 
    * create_time - 系统时间 
    */
	private String create_time;
    /** 
    * data_fmt_type - 测试数据格式 
    */
	private String data_fmt_type;
    /** 
    * expect_type - 测试期望类型 
    */
	private String expect_type;
    /** 
    * expect_value - 测试期望值 
    */
	private String expect_value;
    /** 
    * operator - 操作人员 
    */
	private String operator;
    /** 
    * test_data - 测试数据 
    */
	private String test_data;
    /** 
    * test_req_type - 必须测试标识 
    */
	private String test_req_type;
    /** 
    * update_time - 修改时间 
    */
	private String update_time;

	/**
	* 文本描述信息
	*/
	private String data_fmt_type_name;
	/**
	* 文本描述信息
	*/
	private String expect_type_name;
	/**
	* 文本描述信息
	*/
	private String test_req_type_name;

	public String getAction_id(){
        return this.action_id;
    }
    
    public void setAction_id(String action_id){
        this.action_id = action_id;
    }
	public String getCase_id(){
        return this.case_id;
    }
    
    public void setCase_id(String case_id){
        this.case_id = case_id;
    }
	public String getCase_name(){
        return this.case_name;
    }
    
    public void setCase_name(String case_name){
        this.case_name = case_name;
    }
	public String getCreate_time(){
        return this.create_time;
    }
    
    public void setCreate_time(String create_time){
        this.create_time = create_time;
    }
	public String getData_fmt_type(){
        return this.data_fmt_type;
    }
    
    public void setData_fmt_type(String data_fmt_type){
        this.data_fmt_type = data_fmt_type;
    }
	public String getExpect_type(){
        return this.expect_type;
    }
    
    public void setExpect_type(String expect_type){
        this.expect_type = expect_type;
    }
	public String getExpect_value(){
        return this.expect_value;
    }
    
    public void setExpect_value(String expect_value){
        this.expect_value = expect_value;
    }
	public String getOperator(){
        return this.operator;
    }
    
    public void setOperator(String operator){
        this.operator = operator;
    }
	public String getTest_data(){
        return this.test_data;
    }
    
    public void setTest_data(String test_data){
        this.test_data = test_data;
    }
	public String getTest_req_type(){
        return this.test_req_type;
    }
    
    public void setTest_req_type(String test_req_type){
        this.test_req_type = test_req_type;
    }
	public String getUpdate_time(){
        return this.update_time;
    }
    
    public void setUpdate_time(String update_time){
        this.update_time = update_time;
    }

	public String getData_fmt_type_name(){
        return this.data_fmt_type_name;
    }
    
    public void setData_fmt_type_name(String data_fmt_type_name){
        this.data_fmt_type_name = data_fmt_type_name;
    }
	public String getExpect_type_name(){
        return this.expect_type_name;
    }
    
    public void setExpect_type_name(String expect_type_name){
        this.expect_type_name = expect_type_name;
    }
	public String getTest_req_type_name(){
        return this.test_req_type_name;
    }
    
    public void setTest_req_type_name(String test_req_type_name){
        this.test_req_type_name = test_req_type_name;
    }
}