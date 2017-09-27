package com.yd.common.function.admin.data;


/**
 * <p>Data实体类</p>
 * <p>View: cip_admin_text - 系统文本管理</p>
 * 用于检索数据，建立在视图基础上
 * @since 2015-08-18 02:49:22
 */
public class CIP_admin_textData {


    /** 
    * lang_type - 语言类型 
    */
	private String lang_type;


    /** 
    * msg_no - 文本id 
    */
	private int msg_no;

    /** 
    * msg_txt - 文本内容 
    */
	private String msg_txt;

    /** 
    * create_time - 系统时间 
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
	private String lang_type_name;


	public String getLang_type(){
        return this.lang_type;
    }
    
    public void setLang_type(String lang_type){
        this.lang_type = lang_type;
    }

	public int getMsg_no(){
        return this.msg_no;
    }
    
    public void setMsg_no(int msg_no){
        this.msg_no = msg_no;
    }

	public String getMsg_txt(){
        return this.msg_txt;
    }
    
    public void setMsg_txt(String msg_txt){
        this.msg_txt = msg_txt;
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

	public String getLang_type_name(){
        return this.lang_type_name;
    }
    
    public void setLang_type_name(String lang_type_name){
        this.lang_type_name = lang_type_name;
    }
    
}