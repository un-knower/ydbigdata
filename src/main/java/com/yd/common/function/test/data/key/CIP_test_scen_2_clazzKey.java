package com.yd.common.function.test.data.key;


/**
 * <p>实体key类</p>
 * <p>Table: cip_test_scen_2_clazz - 测试类与场景关系</p>
 *
 * @since 2015-10-10 02:52:52
 */
public class CIP_test_scen_2_clazzKey {	
	
	public Object[] getKeys(){
		return new Object[]{ 
		scenario_id,
				test_clazz
		};
	}
	
	public Object[] setKeys(String scenario_id,String test_clazz){
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

	private String remark;
	
	private String operateCode;
	
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