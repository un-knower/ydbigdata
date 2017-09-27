package com.yd.common.function.test.data;


/**
 * <p>Data实体类</p>
 * <p>View: cip_test_scen_2_case - 测试场景用例关系</p>
 * 用于检索数据，建立在视图基础上
 * @since 2015-10-10 02:52:52
 */
public class CIP_test_scen_2_caseData {

    /** 
    * case_id - 测试用例id 
    */
	private String case_id;
    /** 
    * create_time - 系统时间 
    */
	private String create_time;
    /** 
    * operator - 操作人员 
    */
	private String operator;
    /** 
    * scenario_id - 测试场景id 
    */
	private String scenario_id;
    /** 
    * update_time - 修改时间 
    */
	private String update_time;


	public String getCase_id(){
        return this.case_id;
    }
    
    public void setCase_id(String case_id){
        this.case_id = case_id;
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
	public String getScenario_id(){
        return this.scenario_id;
    }
    
    public void setScenario_id(String scenario_id){
        this.scenario_id = scenario_id;
    }
	public String getUpdate_time(){
        return this.update_time;
    }
    
    public void setUpdate_time(String update_time){
        this.update_time = update_time;
    }

}