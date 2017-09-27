package com.yd.common.function.test.data.key;


/**
 * <p>实体key类</p>
 * <p>Table: cip_test_template_form - 测试数据模板字段</p>
 *
 * @since 2015-11-24 04:16:55
 */
public class CIP_test_template_formKey {	
	
	public Object[] getKeys(){
		return new Object[]{ 
		field_id
		};
	}
	
	public Object[] setKeys(String field_id){
		return new Object[]{ 
			field_id
		};
	}


    /** 
    * field_id - 字段ID 
    */
	private String field_id;

	private String remark;
	
	private String operateCode;
	
	public String getField_id(){
        return this.field_id;
    }
    
    public void setField_id(String field_id){
        this.field_id = field_id;
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