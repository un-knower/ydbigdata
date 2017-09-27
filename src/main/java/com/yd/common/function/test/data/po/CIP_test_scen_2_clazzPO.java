package com.yd.common.function.test.data.po;

import java.sql.Timestamp;

import com.yd.common.function.test.data.vo.CIP_test_scen_2_clazzVO;

/**
 * <p>实体类</p>
 * <p>Table: cip_test_scen_2_clazz - 测试类与场景关系</p>
 *
 * @since 2015-10-10 02:52:52
 */
public class CIP_test_scen_2_clazzPO {

	public Object[] getKeys(){
		return new Object[]{ 
			scenario_id,
					test_clazz
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
    * test_clazz - 测试类
    */
    private String test_clazz;

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
    public String getTest_clazz(){
        return this.test_clazz;
    }
    public void setTest_clazz(String test_clazz){
        this.test_clazz = test_clazz;
    }
    public Timestamp getUpdate_time(){
        return this.update_time;
    }
    public void setUpdate_time(Timestamp update_time){
        this.update_time = update_time;
    }

	public CIP_test_scen_2_clazzVO toVO(){
		CIP_test_scen_2_clazzVO vo = new CIP_test_scen_2_clazzVO();
		
		vo.setScenario_id(scenario_id);
		vo.setTest_clazz(test_clazz);
		
		return vo;
	}
		
}