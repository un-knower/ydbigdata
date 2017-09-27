package com.yd.common.function.meta.data;


/**
 * <p>Data实体类</p>
 * <p>View: cip_meta_fields - 数据字段定义</p>
 * 用于检索数据，建立在视图基础上
 * @since 2015-10-08 09:03:37
 */
public class CIP_meta_fieldsData {


    /** 
    * field_id - 字段id 
    */
	private String field_id;

    /** 
    * str_id - 数据结构id 
    */
	private String str_id;

    /** 
    * field_memo - 字段备注 
    */
	private String field_memo;

    /** 
    * field_name - 字段名称 
    */
	private String field_name;

    /** 
    * search_flag - 查询位标识 
    */
	private String search_flag;

    /** 
    * domain_type - 数据域类型 
    */
	private String domain_type;

    /** 
    * java_type - 字段java类型 
    */
	private String java_type;

	/**
	 * jabc_type - 字段jdbc类型
	 */
	private int jdbc_type;
	
    public int getJdbc_type() {
		return jdbc_type;
	}

	public void setJdbc_type(int jdbc_type) {
		this.jdbc_type = jdbc_type;
	}

	/** 
    * field_length - 字段长度 
    */
	private int field_length;

    /** 
    * fprecision - 字段浮点长 
    */
	private int fprecision;

    /** 
    * field_default - 默认值 
    */
	private String field_default;

    /** 
    * key_flag - id标识 
    */
	private String key_flag;

    /** 
    * null_flag - 空值标识 
    */
	private String null_flag;

    /** 
    * field_use_type - 字段应用类型 
    */
	private String field_use_type;

    /** 
    * ui_control - 字段UI控件 
    */
	private String ui_control;

    /** 
    * ui_ctrl_clazz - easy-ui控件 
    */
	private String ui_ctrl_clazz;

    /** 
    * ui_length - UI允许长度 
    */
	private int ui_length;

    /** 
    * create_time - 创建时间 
    */
	private String create_time;

    /** 
    * update_time - 修改时间 
    */
	private String update_time;

    /** 
    * operator - 操作人员 
    */
	private String operator;

	/**
	* 文本描述信息
	*/
	private String search_flag_name;
	/**
	* 文本描述信息
	*/
	private String domain_type_name;
	/**
	* 文本描述信息
	*/
	private String key_flag_name;
	/**
	* 文本描述信息
	*/
	private String null_flag_name;
	/**
	* 文本描述信息
	*/
	private String field_use_type_name;

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
	public String getField_memo(){
        return this.field_memo;
    }
    
    public void setField_memo(String field_memo){
        this.field_memo = field_memo;
    }
	public String getField_name(){
        return this.field_name;
    }
    
    public void setField_name(String field_name){
        this.field_name = field_name;
    }
	public String getSearch_flag(){
        return this.search_flag;
    }
    
    public void setSearch_flag(String search_flag){
        this.search_flag = search_flag;
    }
	public String getDomain_type(){
        return this.domain_type;
    }
    
    public void setDomain_type(String domain_type){
        this.domain_type = domain_type;
    }
	public String getJava_type(){
        return this.java_type;
    }
    
    public void setJava_type(String java_type){
        this.java_type = java_type;
    }
	public int getField_length(){
        return this.field_length;
    }
    
    public void setField_length(int field_length){
        this.field_length = field_length;
    }
	public int getFprecision(){
        return this.fprecision;
    }
    
    public void setFprecision(int fprecision){
        this.fprecision = fprecision;
    }
	public String getField_default(){
        return this.field_default;
    }
    
    public void setField_default(String field_default){
        this.field_default = field_default;
    }
	public String getKey_flag(){
        return this.key_flag;
    }
    
    public void setKey_flag(String key_flag){
        this.key_flag = key_flag;
    }
	public String getNull_flag(){
        return this.null_flag;
    }
    
    public void setNull_flag(String null_flag){
        this.null_flag = null_flag;
    }
	public String getField_use_type(){
        return this.field_use_type;
    }
    
    public void setField_use_type(String field_use_type){
        this.field_use_type = field_use_type;
    }
	public String getUi_control(){
        return this.ui_control;
    }
    
    public void setUi_control(String ui_control){
        this.ui_control = ui_control;
    }
	public String getUi_ctrl_clazz(){
        return this.ui_ctrl_clazz;
    }
    
    public void setUi_ctrl_clazz(String ui_ctrl_clazz){
        this.ui_ctrl_clazz = ui_ctrl_clazz;
    }
	public int getUi_length(){
        return this.ui_length;
    }
    
    public void setUi_length(int ui_length){
        this.ui_length = ui_length;
    }
	public String getCreate_time(){
        return this.create_time;
    }
    
    public void setCreate_time(String create_time){
        this.create_time = create_time;
    }
	public String getUpdate_time(){
        return this.update_time;
    }
    
    public void setUpdate_time(String update_time){
        this.update_time = update_time;
    }
	public String getOperator(){
        return this.operator;
    }
    
    public void setOperator(String operator){
        this.operator = operator;
    }

	public String getSearch_flag_name(){
        return this.search_flag_name;
    }
    
    public void setSearch_flag_name(String search_flag_name){
        this.search_flag_name = search_flag_name;
    }
    
	public String getDomain_type_name(){
        return this.domain_type_name;
    }
    
    public void setDomain_type_name(String domain_type_name){
        this.domain_type_name = domain_type_name;
    }
    
	public String getKey_flag_name(){
        return this.key_flag_name;
    }
    
    public void setKey_flag_name(String key_flag_name){
        this.key_flag_name = key_flag_name;
    }
    
	public String getNull_flag_name(){
        return this.null_flag_name;
    }
    
    public void setNull_flag_name(String null_flag_name){
        this.null_flag_name = null_flag_name;
    }
    
	public String getField_use_type_name(){
        return this.field_use_type_name;
    }
    
    public void setField_use_type_name(String field_use_type_name){
        this.field_use_type_name = field_use_type_name;
    }
    
}