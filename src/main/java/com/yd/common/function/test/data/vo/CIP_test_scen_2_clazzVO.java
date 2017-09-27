package com.yd.common.function.test.data.vo;

import com.yd.common.function.test.data.po.CIP_test_scen_2_clazzPO;

/**
 * <p>实体类</p>
 * <p>Table: cip_test_scen_2_clazz - 测试类与场景关系</p>
 *
 * @since 2015-10-10 02:52:52
 */
public class CIP_test_scen_2_clazzVO {

	public Object[] getKeys(){
		return new Object[]{ 
		scenario_id,
				test_clazz
		};
	}

    /** 
    * scenario_id - 测试场景id 
    */
    private String scenario_id;
    
    /** 
    * test_clazz - 测试类 
    */
    private String test_clazz;
    

	public String getScenario_id(){
        return this.scenario_id;
    }
    public void setScenario_id(String scenario_id){
        this.scenario_id = scenario_id;
    }
	public String getTest_clazz(){
        return this.test_clazz;
    }
    public void setTest_clazz(String test_clazz){
        this.test_clazz = test_clazz;
    }

	public CIP_test_scen_2_clazzPO toPO(){
		CIP_test_scen_2_clazzPO po = new CIP_test_scen_2_clazzPO();
		
		po.setScenario_id(scenario_id);
		po.setTest_clazz(test_clazz);
		
		return po;
	}
		
}