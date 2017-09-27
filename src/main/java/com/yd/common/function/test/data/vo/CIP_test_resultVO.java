package com.yd.common.function.test.data.vo;

import java.sql.Timestamp;

import com.yd.common.function.test.data.po.CIP_test_resultPO;

/**
 * <p>实体类</p>
 * <p>Table: cip_test_result - 系统测试结果</p>
 *
 * @since 2015-10-10 02:52:52
 */
public class CIP_test_resultVO {

	public Object[] getKeys(){
		return new Object[]{ 
		test_serial_no
		};
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
    * case_id - 测试用例id 
    */
    private String case_id;
    
    /** 
    * real_value - 测试实际值 
    */
    private String real_value;
    
    /** 
    * scenario_id - 数据场景 
    */
    private String scenario_id;
    
    /** 
    * test_acpt_flag - 测试通过标识 
    */
    private String test_acpt_flag;
    
    /** 
    * test_serial_no - 测试流水号 
    */
    private long test_serial_no;
    
    /** 
    * test_time - 测试时间 
    */
    private String test_time;
    
    /** 
    * test_user - 测试用户 
    */
    private String test_user;
    

	public String getCase_id(){
        return this.case_id;
    }
    public void setCase_id(String case_id){
        this.case_id = case_id;
    }
	public String getReal_value(){
        return this.real_value;
    }
    public void setReal_value(String real_value){
        this.real_value = real_value;
    }
	public String getScenario_id(){
        return this.scenario_id;
    }
    public void setScenario_id(String scenario_id){
        this.scenario_id = scenario_id;
    }
	public String getTest_acpt_flag(){
        return this.test_acpt_flag;
    }
    public void setTest_acpt_flag(String test_acpt_flag){
        this.test_acpt_flag = test_acpt_flag;
    }
	public long getTest_serial_no(){
        return this.test_serial_no;
    }
    public void setTest_serial_no(long test_serial_no){
        this.test_serial_no = test_serial_no;
    }
	public String getTest_time(){
        return this.test_time;
    }
    public void setTest_time(String test_time){
        this.test_time = test_time;
    }
	public String getTest_user(){
        return this.test_user;
    }
    public void setTest_user(String test_user){
        this.test_user = test_user;
    }

	public CIP_test_resultPO toPO(){
		CIP_test_resultPO po = new CIP_test_resultPO();
		
		po.setSys_id(sys_id);
		po.setCase_id(case_id);
		po.setReal_value(real_value);
		po.setScenario_id(scenario_id);
		po.setTest_acpt_flag(test_acpt_flag);
		po.setTest_serial_no(test_serial_no);
		po.setTest_time(Timestamp.valueOf(test_time));
		po.setTest_user(test_user);
		
		return po;
	}
	
	@Override
	public String toString() {
		return "CIP_test_resultVO [test_serial_no=" + test_serial_no
				+ ", case_id=" + case_id + ", scenario_id=" + scenario_id
				+ ", test_time=" + test_time + ", real_value=" + real_value
				+ ", test_user=" + test_user + ", test_acpt_flag="
				+ test_acpt_flag + "]";
	}
		
}