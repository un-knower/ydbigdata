package com.yd.common.function.admin.data;


/**
 * <p>Data实体类</p>
 * <p>View: cip_admin_question - 系统问题反馈</p>
 * 用于检索数据，建立在视图基础上
 * @since 2015-12-25 01:45:43
 */
public class CIP_admin_questionData {

    /** 
    * question_id - 问题ID 
    */
	private String question_id;
    /** 
    * question_title - 问题标题 
    */
	private String question_title;
    /** 
    * queston_type - 问题类型 
    */
	private String queston_type;
    /** 
    * question_desc - 问题描述 
    */
	private String question_desc;
    /** 
    * creater - 提出人 
    */
	private String creater;
    /** 
    * question_reply - 问题答复 
    */
	private String question_reply;
    /** 
    * handle_flag - 处理标志 
    */
	private String handle_flag;
    /** 
    * update_time - 操作时间 
    */
	private String update_time;
    /** 
    * operater - 操作人 
    */
	private String operater;
    /** 
    * create_time - 生成时间 
    */
	private String create_time;

	/**
	* 文本描述信息
	*/
	private String queston_type_name;
	/**
	* 文本描述信息
	*/
	private String handle_flag_name;

	public String getQuestion_id(){
        return this.question_id;
    }
    
    public void setQuestion_id(String question_id){
        this.question_id = question_id;
    }
	public String getQuestion_title(){
        return this.question_title;
    }
    
    public void setQuestion_title(String question_title){
        this.question_title = question_title;
    }
	public String getQueston_type(){
        return this.queston_type;
    }
    
    public void setQueston_type(String queston_type){
        this.queston_type = queston_type;
    }
	public String getQuestion_desc(){
        return this.question_desc;
    }
    
    public void setQuestion_desc(String question_desc){
        this.question_desc = question_desc;
    }
	public String getCreater(){
        return this.creater;
    }
    
    public void setCreater(String creater){
        this.creater = creater;
    }
	public String getQuestion_reply(){
        return this.question_reply;
    }
    
    public void setQuestion_reply(String question_reply){
        this.question_reply = question_reply;
    }
	public String getHandle_flag(){
        return this.handle_flag;
    }
    
    public void setHandle_flag(String handle_flag){
        this.handle_flag = handle_flag;
    }
	public String getUpdate_time(){
        return this.update_time;
    }
    
    public void setUpdate_time(String update_time){
        this.update_time = update_time;
    }
	public String getOperater(){
        return this.operater;
    }
    
    public void setOperater(String operater){
        this.operater = operater;
    }
	public String getCreate_time(){
        return this.create_time;
    }
    
    public void setCreate_time(String create_time){
        this.create_time = create_time;
    }

	public String getQueston_type_name(){
        return this.queston_type_name;
    }
    
    public void setQueston_type_name(String queston_type_name){
        this.queston_type_name = queston_type_name;
    }
	public String getHandle_flag_name(){
        return this.handle_flag_name;
    }
    
    public void setHandle_flag_name(String handle_flag_name){
        this.handle_flag_name = handle_flag_name;
    }
}