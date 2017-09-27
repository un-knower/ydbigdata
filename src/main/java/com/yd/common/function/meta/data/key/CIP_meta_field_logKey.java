package com.yd.common.function.meta.data.key;


/**
 * <p>实体key类</p>
 * <p>Table: cip_meta_field_log - 数据结构字段变更</p>
 *
 * @since 2015-10-08 09:03:37
 */
public class CIP_meta_field_logKey {	
	
	public Object[] getKeys(){
		return new Object[]{ 
			field_id,
			op_field,
			op_type,
			str_id
		};
	}
	
	public Object[] setKeys(String field_id,String op_field,String op_type,String str_id){
		return new Object[]{ 
			field_id,
			op_field,
			op_type,
			str_id
		};
	}


    /** 
    * field_id - 字段id 
    */
	private String field_id;

    /** 
    * op_field - 操作字段 
    */
	private String op_field;

    /** 
    * oper_time - 操作时间 
    */
	private String op_type;

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
	public String getOp_field(){
        return this.op_field;
    }
    
    public void setOp_field(String op_field){
        this.op_field = op_field;
    }

	public String getOp_type() {
		return op_type;
	}

	public void setOp_type(String op_type) {
		this.op_type = op_type;
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