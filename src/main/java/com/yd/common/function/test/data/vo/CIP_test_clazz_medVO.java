package com.yd.common.function.test.data.vo;

import com.yd.common.function.test.data.po.CIP_test_clazz_medPO;

/**
 * <p>实体类</p>
 * <p>Table: cip_test_clazz_med - 测试类方法</p>
 *
 * @since 2015-10-10 02:52:52
 */
public class CIP_test_clazz_medVO {

	public Object[] getKeys(){
		return new Object[]{ 
		action_id
		};
	}

	/**
	 * sys_id - 系统Id
	 */
	private String sys_id;
	
    public String getSys_id() {
		return sys_id;
	}
	public void setSys_id(String sys_id) {
		this.sys_id = sys_id;
	}

	/** 
    * action_id - 行为id 
    */
    private String action_id;
    
    /** 
    * action_name - 动作名称 
    */
    private String action_name;
    
    /** 
    * in_data_clazz - 输入数据对象 
    */
    private String in_data_clazz;
    
    /** 
    * out_data_clazz - 输出数据对象 
    */
    private String out_data_clazz;
    
    /** 
    * test_clazz - 测试类 
    */
    private String test_clazz;
    
    /** 
    * test_uri - 测试uri 
    */
    private String test_uri;
    

	public String getAction_id(){
        return this.action_id;
    }
    public void setAction_id(String action_id){
        this.action_id = action_id;
    }
	public String getAction_name(){
        return this.action_name;
    }
    public void setAction_name(String action_name){
        this.action_name = action_name;
    }
	public String getIn_data_clazz(){
        return this.in_data_clazz;
    }
    public void setIn_data_clazz(String in_data_clazz){
        this.in_data_clazz = in_data_clazz;
    }
	public String getOut_data_clazz(){
        return this.out_data_clazz;
    }
    public void setOut_data_clazz(String out_data_clazz){
        this.out_data_clazz = out_data_clazz;
    }
	public String getTest_clazz(){
        return this.test_clazz;
    }
    public void setTest_clazz(String test_clazz){
        this.test_clazz = test_clazz;
    }
	public String getTest_uri(){
        return this.test_uri;
    }
    public void setTest_uri(String test_uri){
        this.test_uri = test_uri;
    }

	public CIP_test_clazz_medPO toPO(){
		CIP_test_clazz_medPO po = new CIP_test_clazz_medPO();
		
		po.setSys_id(sys_id);
		po.setAction_id(action_id);
		po.setAction_name(action_name);
		po.setIn_data_clazz(in_data_clazz);
		po.setOut_data_clazz(out_data_clazz);
		po.setTest_clazz(test_clazz);
		po.setTest_uri(test_uri);
		
		return po;
	}
		
}