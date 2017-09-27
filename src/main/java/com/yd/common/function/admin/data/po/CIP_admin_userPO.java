package com.yd.common.function.admin.data.po;

import java.sql.Date;
import java.sql.Timestamp;

import com.yd.common.auth.CIPUserProfileData;
import com.yd.common.function.admin.data.vo.CIP_admin_userVO;
import com.yd.common.utils.DateUtils;

/**
 * <p>实体类</p>
 * <p>Table: cip_admin_user - 用户信息</p>
 *
 * @since 2015-07-27 02:40:52
 */
public class CIP_admin_userPO {

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
    private Date start_date;

    /** 
    * end_date - 结束时间
    */
    private Date end_date;

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
    private Timestamp pwd_set_time;

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
    * create_time - 系统生成时间
    */
    private Timestamp create_time;

    /** 
    * update_time - 修改时间
    */
    private Timestamp update_time;

    /** 
    * operator - 操作人
    */
    private String operator;
    
	/**
	 * 用户语言
	 */
	private String lang;
	
	
	
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public String getUser_pwd() {
		return user_pwd;
	}

	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}

	public int getPwd_init_flag() {
		return pwd_init_flag;
	}

	public void setPwd_init_flag(int pwd_init_flag) {
		this.pwd_init_flag = pwd_init_flag;
	}

	public String getUser_his1_pwd() {
		return user_his1_pwd;
	}

	public void setUser_his1_pwd(String user_his1_pwd) {
		this.user_his1_pwd = user_his1_pwd;
	}

	public String getUser_his2_pwd() {
		return user_his2_pwd;
	}

	public void setUser_his2_pwd(String user_his2_pwd) {
		this.user_his2_pwd = user_his2_pwd;
	}

	public String getUser_his3_pwd() {
		return user_his3_pwd;
	}

	public void setUser_his3_pwd(String user_his3_pwd) {
		this.user_his3_pwd = user_his3_pwd;
	}

	public Timestamp getPwd_set_time() {
		return pwd_set_time;
	}

	public void setPwd_set_time(Timestamp pwd_set_time) {
		this.pwd_set_time = pwd_set_time;
	}

	public int getPwd_reset_days() {
		return pwd_reset_days;
	}

	public void setPwd_reset_days(int pwd_reset_days) {
		this.pwd_reset_days = pwd_reset_days;
	}

	public int getPwd_reset_flag() {
		return pwd_reset_flag;
	}

	public void setPwd_reset_flag(int pwd_reset_flag) {
		this.pwd_reset_flag = pwd_reset_flag;
	}

	public int getUser_status() {
		return user_status;
	}

	public void setUser_status(int user_status) {
		this.user_status = user_status;
	}

	public int getUser_type() {
		return user_type;
	}

	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}

	public String getPerson_id() {
		return person_id;
	}

	public void setPerson_id(String person_id) {
		this.person_id = person_id;
	}

	public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}

	public String getPosition_id() {
		return position_id;
	}

	public void setPosition_id(String position_id) {
		this.position_id = position_id;
	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

	public Timestamp getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public CIP_admin_userVO toVO(){
		CIP_admin_userVO vo = new CIP_admin_userVO();
		
		vo.setUser_id(user_id);
		vo.setUser_name(user_name);
		vo.setStart_date(DateUtils.getDate(start_date));
		vo.setEnd_date(DateUtils.getDate(end_date));
		vo.setUser_pwd(user_pwd);
		vo.setPwd_init_flag(pwd_init_flag);
		vo.setUser_his1_pwd(user_his1_pwd);
		vo.setUser_his2_pwd(user_his2_pwd);
		vo.setUser_his3_pwd(user_his3_pwd);
		vo.setPwd_set_time(DateUtils.getDateTime(pwd_set_time));
		vo.setPwd_reset_days(pwd_reset_days);
		vo.setPwd_reset_flag(pwd_reset_flag);
		vo.setUser_status(user_status);
		vo.setUser_type(user_type);
		vo.setPerson_id(person_id);
		vo.setOrg_id(org_id);
		vo.setPosition_id(position_id);
		vo.setLang(lang);
		
		return vo;
	}
		
	public CIPUserProfileData toProfileData(){
		CIPUserProfileData userProfile = new CIPUserProfileData();
		userProfile.user_id = user_id;
		userProfile.start_date = start_date.toString();
		userProfile.end_date = end_date.toString();
		userProfile.pwd_reset_flag = pwd_reset_flag;
		userProfile.persion_id = person_id;
		userProfile.org_id = org_id;
		userProfile.user_name = user_name;
		userProfile.lang = lang;
		userProfile.pwd_reset_days = pwd_reset_days;
		userProfile.user_pwd_init = pwd_init_flag;
		userProfile.pwd_set_time = pwd_set_time.toString();
		
		return userProfile;
	}
}