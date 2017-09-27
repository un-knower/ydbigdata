package com.yd.common.function.meta.data.po;

import java.sql.Timestamp;

import com.yd.common.function.meta.data.vo.CIP_meta_field_logVO;
import com.yd.common.utils.DateUtils;

/**
 * <p>实体类</p>
 * <p>Table: cip_meta_field_log - 数据结构字段变更</p>
 *
 * @since 2015-10-08 09:03:37
 */
public class CIP_meta_field_logPO {

	public Object[] getKeys(){
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
    private Timestamp oper_time;

    /** 
    * str_id - 数据结构id
    */
    private String str_id;

    /** 
    * op_type - 操作类型
    */
    private String op_type;

    /** 
    * old_value - 原始值
    */
    private String old_value;

    /** 
    * new_value - 新值
    */
    private String new_value;

    /** 
    * operator - 操作人
    */
    private String operator;
    
    /**
     *op_flag - 操作标志 
     */
    private String op_flag;
    

    public String getOp_flag() {
		return op_flag;
	}
	public void setOp_flag(String op_flag) {
		this.op_flag = op_flag;
	}
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
    public Timestamp getOper_time(){
        return this.oper_time;
    }
    public void setOper_time(Timestamp oper_time){
        this.oper_time = oper_time;
    }
    public String getStr_id(){
        return this.str_id;
    }
    public void setStr_id(String str_id){
        this.str_id = str_id;
    }
    public String getOp_type(){
        return this.op_type;
    }
    public void setOp_type(String op_type){
        this.op_type = op_type;
    }
    public String getOld_value(){
        return this.old_value;
    }
    public void setOld_value(String old_value){
        this.old_value = old_value;
    }
    public String getNew_value(){
        return this.new_value;
    }
    public void setNew_value(String new_value){
        this.new_value = new_value;
    }
    public String getOperator(){
        return this.operator;
    }
    public void setOperator(String operator){
        this.operator = operator;
    }

	public CIP_meta_field_logVO toVO(){
		CIP_meta_field_logVO vo = new CIP_meta_field_logVO();
		
		vo.setField_id(field_id);
		vo.setOp_field(op_field);
		vo.setOper_time(DateUtils.getDateTime(oper_time));
		vo.setStr_id(str_id);
		vo.setOp_type(op_type);
		vo.setOld_value(old_value);
		vo.setNew_value(new_value);
		vo.setOp_flag(op_flag);
		
		return vo;
	}
		
}