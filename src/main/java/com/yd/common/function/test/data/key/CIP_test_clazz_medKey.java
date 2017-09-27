package com.yd.common.function.test.data.key;


/**
 * <p>实体key类</p>
 * <p>Table: cip_test_clazz_med - 测试类方法</p>
 *
 * @since 2015-10-10 02:52:52
 */
public class CIP_test_clazz_medKey {	
	
	public Object[] getKeys(){
		return new Object[]{ 
		action_id
		};
	}
	
	public Object[] setKeys(String action_id){
		return new Object[]{ 
			action_id
		};
	}


    /** 
    * action_id - 行为id 
    */
	private String action_id;

	private String remark;
	
	private String operateCode;
	
	public String getAction_id(){
        return this.action_id;
    }
    
    public void setAction_id(String action_id){
        this.action_id = action_id;
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