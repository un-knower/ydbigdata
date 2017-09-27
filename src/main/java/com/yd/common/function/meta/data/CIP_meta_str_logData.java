package com.yd.common.function.meta.data;


/**
 * <p>Data实体类</p>
 * <p>View: cip_meta_str_log - 元数据结构变更</p>
 * 用于检索数据，建立在视图基础上
 * @since 2015-10-08 09:03:37
 */
public class CIP_meta_str_logData {


    /** 
    * op_field - 操作字段 
    */
	private String op_field;

    /** 
    * oper_time - 操作时间 
    */
	private String oper_time;

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
	* 文本描述信息
	*/
	private String op_type_name;

	/**
	 * op_flag - 处理标志
	 */
	private String op_flag;
	
	public String getOp_flag() {
		return op_flag;
	}

	public void setOp_flag(String op_flag) {
		this.op_flag = op_flag;
	}

	public String getOp_field(){
        return this.op_field;
    }
    
    public void setOp_field(String op_field){
        this.op_field = op_field;
    }
	public String getOper_time(){
        return this.oper_time;
    }
    
    public void setOper_time(String oper_time){
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

	public String getOp_type_name(){
        return this.op_type_name;
    }
    
    public void setOp_type_name(String op_type_name){
        this.op_type_name = op_type_name;
    }
    
}