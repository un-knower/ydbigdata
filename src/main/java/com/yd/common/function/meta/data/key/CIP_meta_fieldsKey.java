package com.yd.common.function.meta.data.key;


/**
 * <p>实体key类</p>
 * <p>Table: cip_meta_fields - 数据字段定义</p>
 *
 * @since 2015-10-08 09:03:37
 */
public class CIP_meta_fieldsKey {	
	
	public Object[] getKeys(){
		return new Object[]{ 
		field_id,
				str_id
		};
	}
	
	public Object[] setKeys(String field_id,String str_id){
		return new Object[]{ 
			field_id,
					str_id
		};
	}


    /** 
    * field_id - 字段id 
    */
	private String field_id;

    /** 
    * str_id - 数据结构id 
    */
	private String str_id;

	private String remark;
	
	private String operateCode;
	
	public String getField_id(){
        return this.field_id;
    }
    
    public void setField_id(String field_id){
        this.field_id = field_id;
    }
	public String getStr_id(){
        return this.str_id;
    }
    
    public void setStr_id(String str_id){
        this.str_id = str_id;
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