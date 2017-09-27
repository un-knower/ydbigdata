package com.yd.common.function.test.data.vo;

import com.yd.common.function.test.data.po.CIP_test_clazzPO;

/**
 * <p>实体类</p>
 * <p>Table: cip_test_clazz - 系统测试类</p>
 *
 * @since 2015-10-10 02:52:52
 */
public class CIP_test_clazzVO {

	public Object[] getKeys(){
		return new Object[]{ 
		test_clazz
		};
	}

    /** 
    * clazz_name - 测试类名称 
    */
    private String clazz_name;
    
    /** 
    * test_clazz - 测试类 
    */
    private String test_clazz;
    

	public String getClazz_name(){
        return this.clazz_name;
    }
    public void setClazz_name(String clazz_name){
        this.clazz_name = clazz_name;
    }
	public String getTest_clazz(){
        return this.test_clazz;
    }
    public void setTest_clazz(String test_clazz){
        this.test_clazz = test_clazz;
    }

	public CIP_test_clazzPO toPO(){
		CIP_test_clazzPO po = new CIP_test_clazzPO();
		
		po.setClazz_name(clazz_name);
		po.setTest_clazz(test_clazz);
		
		return po;
	}
		
}