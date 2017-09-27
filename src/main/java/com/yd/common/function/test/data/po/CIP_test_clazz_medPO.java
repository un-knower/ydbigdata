package com.yd.common.function.test.data.po;

import java.sql.Timestamp;

import com.yd.common.function.test.data.vo.CIP_test_clazz_medVO;

/**
 * <p>实体类</p>
 * <p>Table: cip_test_clazz_med - 测试类方法</p>
 *
 * @since 2015-10-10 02:52:52
 */
public class CIP_test_clazz_medPO {

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
    * create_time - 系统时间
    */
    private Timestamp create_time;

    /** 
    * in_data_clazz - 输入数据对象
    */
    private String in_data_clazz;

    /** 
    * operator - 操作人员
    */
    private String operator;

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

    /** 
    * update_time - 修改时间
    */
    private Timestamp update_time;

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
    public Timestamp getCreate_time(){
        return this.create_time;
    }
    public void setCreate_time(Timestamp create_time){
        this.create_time = create_time;
    }
    public String getIn_data_clazz(){
        return this.in_data_clazz;
    }
    public void setIn_data_clazz(String in_data_clazz){
        this.in_data_clazz = in_data_clazz;
    }
    public String getOperator(){
        return this.operator;
    }
    public void setOperator(String operator){
        this.operator = operator;
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
    public Timestamp getUpdate_time(){
        return this.update_time;
    }
    public void setUpdate_time(Timestamp update_time){
        this.update_time = update_time;
    }

	public CIP_test_clazz_medVO toVO(){
		CIP_test_clazz_medVO vo = new CIP_test_clazz_medVO();
		
		vo.setSys_id(sys_id);
		vo.setAction_id(action_id);
		vo.setAction_name(action_name);
		vo.setIn_data_clazz(in_data_clazz);
		vo.setOut_data_clazz(out_data_clazz);
		vo.setTest_clazz(test_clazz);
		vo.setTest_uri(test_uri);
		
		return vo;
	}
		
}