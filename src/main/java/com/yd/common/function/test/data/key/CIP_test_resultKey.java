package com.yd.common.function.test.data.key;


/**
 * <p>实体key类</p>
 * <p>Table: cip_test_result - 系统测试结果</p>
 *
 * @since 2015-10-10 02:52:52
 */
public class CIP_test_resultKey {	
	
	public Object[] getKeys(){
		return new Object[]{ 
		test_serial_no
		};
	}
	
	public Object[] setKeys(long test_serial_no){
		return new Object[]{ 
			test_serial_no
		};
	}


    /** 
    * test_serial_no - 测试流水号 
    */
	private long test_serial_no;

	private String remark;
	
	private String operateCode;
	
	public long getTest_serial_no(){
        return this.test_serial_no;
    }
    
    public void setTest_serial_no(long test_serial_no){
        this.test_serial_no = test_serial_no;
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