package com.yd.common.function.test.data.vo;

import com.yd.common.function.test.data.po.CIP_test_scen_2_casePO;

/**
 * <p>实体类</p>
 * <p>Table: cip_test_scen_2_case - 测试场景用例关系</p>
 *
 * @since 2015-10-10 02:52:52
 */
public class CIP_test_scen_2_caseVO {

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
    * scenario_id - 测试场景id 
    */
    private String scenario_id;
    

	public String getCase_id(){
        return this.case_id;
    }
    public void setCase_id(String case_id){
        this.case_id = case_id;
    }
	public String getScenario_id(){
        return this.scenario_id;
    }
    public void setScenario_id(String scenario_id){
        this.scenario_id = scenario_id;
    }

	public CIP_test_scen_2_casePO toPO(){
		CIP_test_scen_2_casePO po = new CIP_test_scen_2_casePO();
		
		po.setCase_id(case_id);
		po.setScenario_id(scenario_id);
		
		return po;
	}
		
}