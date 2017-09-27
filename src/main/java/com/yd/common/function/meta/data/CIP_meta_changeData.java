package com.yd.common.function.meta.data;

public class CIP_meta_changeData {

	/**
	 * str_id - 数据结构id
	 */
	private String  str_id;
	
	/**
	 * field_id - 字段id
	 */
	private String field_id;
	
	/**
	 * operate_field - 操作字段
	 */
	private String operate_field;
	
	/**
	 * field_domain - 字段所属域
	 */
	private String field_domain;
	
	/**
	 *new_value - 变更后的新值
	 */
	private String new_value;
	
	/**
	 * old_value - 变更前的旧值
	 */
	private String old_value;
	
	/**
	 * operate_type - 操作类型
	 */
	private String operate_type;
	
	/**
	 * operate_time - 操作时间
	 */
	private String operate_time;
	
	/**
	 * operator - 操作人
	 */
	private String operator;
	
	/**
	 *op_flag - 处理标志 
	 */
	private String op_flag;
	

	public String getOp_flag() {
		return op_flag;
	}

	public void setOp_flag(String op_flag) {
		this.op_flag = op_flag;
	}

	public String getStr_id() {
		return str_id;
	}

	public void setStr_id(String str_id) {
		this.str_id = str_id;
	}

	public String getField_id() {
		return field_id;
	}

	public void setField_id(String field_id) {
		this.field_id = field_id;
	}

	public String getOperate_field() {
		return operate_field;
	}

	public void setOperate_field(String operate_field) {
		this.operate_field = operate_field;
	}

	public String getField_domain() {
		return field_domain;
	}

	public void setField_domain(String field_domain) {
		this.field_domain = field_domain;
	}

	public String getNew_value() {
		return new_value;
	}

	public void setNew_value(String new_value) {
		this.new_value = new_value;
	}

	public String getOld_value() {
		return old_value;
	}

	public void setOld_value(String old_value) {
		this.old_value = old_value;
	}

	public String getOperate_type() {
		return operate_type;
	}

	public void setOperate_type(String operate_type) {
		this.operate_type = operate_type;
	}

	public String getOperate_time() {
		return operate_time;
	}

	public void setOperate_time(String operate_time) {
		this.operate_time = operate_time;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Override
	public String toString() {
		return "CIP_meta_changeData [str_id=" + str_id + ", field_id="
				+ field_id + ", operate_field=" + operate_field
				+ ", field_domain=" + field_domain + ", new_value=" + new_value
				+ ", old_value=" + old_value + ", operate_type=" + operate_type
				+ ", operate_time=" + operate_time + ", operator=" + operator
				+ ", op_flag=" + op_flag + "]";
	}

	
	
	
	
}
