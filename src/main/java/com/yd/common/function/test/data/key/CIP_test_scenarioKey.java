package com.yd.common.function.test.data.key;


/**
 * <p>实体key类</p>
 * <p>Table: cip_test_scenario - 系统测试场景</p>
 *
 * @since 2015-10-10 02:52:52
 */
public class CIP_test_scenarioKey {	
	
	public Object[] getKeys(){
		return new Object[]{ 
		scenario_id
		};
	}
	
	public Object[] setKeys(String scenario_id){
		return new Object[]{ 
			scenario_id
		};
	}


    /** 
    * scenario_id - 测试场景id 
    */
	private String scenario_id;

	private String remark;
	
	private String operateCode;
	
	public String getScenario_id(){
        return this.scenario_id;
    }
    
    public void setScenario_id(String scenario_id){
        this.scenario_id = scenario_id;
    }

	public void setOperateCode(String operateCode){
		this.operateCode = operateCode;
	}
	
	public String getOperateCode(){
		return operateCode;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}
		
}