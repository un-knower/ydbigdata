package com.yd.common.function.admin.data.vo;

import java.sql.Date;
import java.sql.Timestamp;

import com.yd.common.function.admin.data.po.CIP_admin_userPO;
import com.yd.common.utils.DateUtils;

/**
 * <p>实体类</p>
 * <p>Table: cip_admin_user - 用户信息</p>
 *
 * @since 2015-07-27 02:40:52
 */
public class CIP_admin_userVO {

	public Object[] getKeys(){
		return new Object[]{ 
		user_id
		};
	}

    /** 
    * user_id - 用户id 
    */
    private String user_id;
    
    /** 
    * user_name - 用户名称 
    */
    private String user_name;
    
    /** 
    * start_date - 开始时间 
    */
    private String start_date;
    
    /** 
    * end_date - 结束时间 
    */
    private String end_date;
    
    /** 
    * user_pwd - 密码 
    */
    private String user_pwd;
    
    /** 
    * pwd_init_flag - 初始密码标识 
    */
    private int pwd_init_flag;
    
    /** 
    * user_his1_pwd - 历史密码1 
    */
    private String user_his1_pwd;
    
    /** 
    * user_his2_pwd - 历史密码2 
    */
    private String user_his2_pwd;
    
    /** 
    * user_his3_pwd - 历史密码3 
    */
    private String user_his3_pwd;
    
    /** 
    * pwd_set_time - 密码设置时间 
    */
    private String pwd_set_time;
    
    /** 
    * pwd_reset_days - 密码重置天数 
    */
    private int pwd_reset_days;
    
    /** 
    * pwd_reset_flag - 重置密码标识 
    */
    private int pwd_reset_flag;
    
    /** 
    * user_status - 用户状态 
    */
    private int user_status;
    
    /** 
    * user_type - 用户类型 
    */
    private int user_type;
    
    /** 
    * person_id - 使用人id 
    */
    private String person_id;
    
    /** 
    * org_id - 使用机构id 
    */
    private String org_id;
    
    /** 
    * position_id - 岗位id 
    */
    private String position_id;
    
	/**
	 * 用户语言
	 */
	private String lang;
	
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getUser_id(){
        return this.user_id;
    }
    public void setUser_id(String user_id){
        this.user_id = user_id;
    }
	public String getUser_name(){
        return this.user_name;
    }
    public void setUser_name(String user_name){
        this.user_name = user_name;
    }
	public String getStart_date(){
        return this.start_date;
    }
    public void setStart_date(String start_date){
        this.start_date = start_date;
    }
	public String getEnd_date(){
        return this.end_date;
    }
    public void setEnd_date(String end_date){
        this.end_date = end_date;
    }
	public String getUser_pwd(){
        return this.user_pwd;
    }
    public void setUser_pwd(String user_pwd){
        this.user_pwd = user_pwd;
    }
	public int getPwd_init_flag(){
        return this.pwd_init_flag;
    }
    public void setPwd_init_flag(int pwd_init_flag){
        this.pwd_init_flag = pwd_init_flag;
    }
	public String getUser_his1_pwd(){
        return this.user_his1_pwd;
    }
    public void setUser_his1_pwd(String user_his1_pwd){
        this.user_his1_pwd = user_his1_pwd;
    }
	public String getUser_his2_pwd(){
        return this.user_his2_pwd;
    }
    public void setUser_his2_pwd(String user_his2_pwd){
        this.user_his2_pwd = user_his2_pwd;
    }
	public String getUser_his3_pwd(){
        return this.user_his3_pwd;
    }
    public void setUser_his3_pwd(String user_his3_pwd){
        this.user_his3_pwd = user_his3_pwd;
    }
	public String getPwd_set_time(){
        return this.pwd_set_time;
    }
    public void setPwd_set_time(String pwd_set_time){
        this.pwd_set_time = pwd_set_time;
    }
	public int getPwd_reset_days(){
        return this.pwd_reset_days;
    }
    public void setPwd_reset_days(int pwd_reset_days){
        this.pwd_reset_days = pwd_reset_days;
    }
	public int getPwd_reset_flag(){
        return this.pwd_reset_flag;
    }
    public void setPwd_reset_flag(int pwd_reset_flag){
        this.pwd_reset_flag = pwd_reset_flag;
    }
	public int getUser_status(){
        return this.user_status;
    }
    public void setUser_status(int user_status){
        this.user_status = user_status;
    }
	public int getUser_type(){
        return this.user_type;
    }
    public void setUser_type(int user_type){
        this.user_type = user_type;
    }
	public String getPerson_id(){
        return this.person_id;
    }
    public void setPerson_id(String person_id){
        this.person_id = person_id;
    }
	public String getOrg_id(){
        return this.org_id;
    }
    public void setOrg_id(String org_id){
        this.org_id = org_id;
    }
	public String getPosition_id(){
        return this.position_id;
    }
    public void setPosition_id(String position_id){
        this.position_id = position_id;
    }

	public CIP_admin_userPO toPO(){
		CIP_admin_userPO po = new CIP_admin_userPO();
		
		po.setUser_id(user_id);
		po.setUser_name(user_name);
		po.setStart_date(new Date(DateUtils.parseDate(start_date).getTime()));
		po.setEnd_date(new Date(DateUtils.parseDate(end_date).getTime()));
		po.setUser_pwd(user_pwd);
		po.setPwd_init_flag(pwd_init_flag);
		po.setUser_his1_pwd(user_his1_pwd);
		po.setUser_his2_pwd(user_his2_pwd);
		po.setUser_his3_pwd(user_his3_pwd);
		po.setPwd_set_time(Timestamp.valueOf(pwd_set_time));
		po.setPwd_reset_days(pwd_reset_days);
		po.setPwd_reset_flag(pwd_reset_flag);
		po.setUser_status(user_status);
		po.setUser_type(user_type);
		po.setPerson_id(person_id);
		po.setOrg_id(org_id);
		po.setPosition_id(position_id);
		po.setLang(lang);
		
		return po;
	}
		
}