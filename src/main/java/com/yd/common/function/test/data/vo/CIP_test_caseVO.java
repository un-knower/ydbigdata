package com.yd.common.function.test.data.vo;

import com.yd.common.function.test.data.po.CIP_test_casePO;

/**
 * <p>实体类</p>
 * <p>Table: cip_test_case - 系统测试用例</p>
 *
 * @since 2015-10-10 02:52:52
 */
public class CIP_test_caseVO {

	public Object[] getKeys(){
		return new Object[]{ 
		case_id
		};
	}

	/**
	 * scenario_id - 所属场景Id
	 */
	private String scenario_id;
	
	
    public String getScenario_id() {
		return scenario_id;
	}
	public void setScenario_id(String scenario_id) {
		this.scenario_id = scenario_id;
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
    * test_data - 测试数据 
    */
    private String test_data;
    
    /** 
    * test_req_type - 必须测试标识 
    */
    private String test_req_type;
    

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

	public CIP_test_casePO toPO(){
		CIP_test_casePO po = new CIP_test_casePO();
		
		po.setScenario_id(scenario_id);
		po.setAction_id(action_id);
		po.setCase_id(case_id);
		po.setCase_name(case_name);
		po.setData_fmt_type(data_fmt_type);
		po.setExpect_type(expect_type);
		po.setExpect_value(expect_value);
		po.setTest_data(test_data);
		po.setTest_req_type(test_req_type);
		
		return po;
	}
		
}