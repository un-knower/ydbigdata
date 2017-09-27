package com.yd.common.function.test.data.po;

import java.sql.Timestamp;

import com.yd.common.function.test.data.vo.CIP_test_scenarioVO;

/**
 * <p>实体类</p>
 * <p>Table: cip_test_scenario - 系统测试场景</p>
 *
 * @since 2015-10-10 02:52:52
 */
public class CIP_test_scenarioPO {

	public Object[] getKeys(){
		return new Object[]{ 
			scenario_id
		};
	}


    /** 
    * create_time - 系统时间
    */
    private Timestamp create_time;

    /** 
    * operator - 操作人员
    */
    private String operator;

    /** 
    * scenario_id - 测试场景id
    */
    private String scenario_id;

    /** 
    * scenario_name - 测试场景名称
    */
    private String scenario_name;

    /** 
    * test_pwd - 测试用户密码
    */
    private String test_pwd;

    /** 
    * test_sys_id - 测试系统Id
    */
    private String test_sys_id;

    /** 
    * test_user - 测试用户
    */
    private String test_user;

    /** 
    * update_time - 修改时间
    */
    private Timestamp update_time;

    public Timestamp getCreate_time(){
        return this.create_time;
    }
    public void setCreate_time(Timestamp create_time){
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
    public String getScenario_name(){
        return this.scenario_name;
    }
    public void setScenario_name(String scenario_name){
        this.scenario_name = scenario_name;
    }
    public String getTest_pwd(){
        return this.test_pwd;
    }
    public void setTest_pwd(String test_pwd){
        this.test_pwd = test_pwd;
    }
    public String getTest_sys_id(){
        return this.test_sys_id;
    }
    public void setTest_sys_id(String test_sys_id){
        this.test_sys_id = test_sys_id;
    }
    public String getTest_user(){
        return this.test_user;
    }
    public void setTest_user(String test_user){
        this.test_user = test_user;
    }
    public Timestamp getUpdate_time(){
        return this.update_time;
    }
    public void setUpdate_time(Timestamp update_time){
        this.update_time = update_time;
    }

	public CIP_test_scenarioVO toVO(){
		CIP_test_scenarioVO vo = new CIP_test_scenarioVO();
		
		vo.setScenario_id(scenario_id);
		vo.setScenario_name(scenario_name);
		vo.setTest_pwd(test_pwd);
		vo.setTest_sys_id(test_sys_id);
		vo.setTest_user(test_user);
		
		return vo;
	}
		
}