package com.yd.common.function.test.data.key;


/**
 * <p>实体key类</p>
 * <p>Table: cip_test_clazz - 系统测试类</p>
 *
 * @since 2015-10-10 02:52:52
 */
public class CIP_test_clazzKey {	
	
	public Object[] getKeys(){
		return new Object[]{ 
		test_clazz
		};
	}
	
	public Object[] setKeys(String test_clazz){
		return new Object[]{ 
			test_clazz
		};
	}


    /** 
    * test_clazz - 测试类 
    */
	private String test_clazz;

	private String remark;
	
	private String operateCode;
	
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