package com.yd.common.function.test.data.po;

import java.sql.Timestamp;

import com.yd.common.function.test.data.vo.CIP_test_scen_2_caseVO;

/**
 * <p>实体类</p>
 * <p>Table: cip_test_scen_2_case - 测试场景用例关系</p>
 *
 * @since 2015-10-10 02:52:52
 */
public class CIP_test_scen_2_casePO {

	public Object[] getKeys(){
		return new Object[]{ 
			case_id,
					scenario_id
		};
	}


    /** 
    * case_id - 测试用例id
    */
    private String case_id;

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
    * update_time - 修改时间
    */
    private Timestamp update_time;

    public String getCase_id(){
        return this.case_id;
    }
    public void setCase_id(String case_id){
        this.case_id = case_id;
    }
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
    public Timestamp getUpdate_time(){
        return this.update_time;
    }
    public void setUpdate_time(Timestamp update_time){
        this.update_time = update_time;
    }

	public CIP_test_scen_2_caseVO toVO(){
		CIP_test_scen_2_caseVO vo = new CIP_test_scen_2_caseVO();
		
		vo.setCase_id(case_id);
		vo.setScenario_id(scenario_id);
		
		return vo;
	}
		
}