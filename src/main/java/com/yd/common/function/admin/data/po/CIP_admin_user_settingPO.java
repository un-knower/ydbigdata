package com.yd.common.function.admin.data.po;

import java.sql.Timestamp;

import com.yd.common.function.admin.data.vo.CIP_admin_user_settingVO;

/**
 * <p>实体类</p>
 * <p>Table: cip_admin_user_setting - 用户信息设置</p>
 *
 * @since 2015-07-27 02:40:52
 */
public class CIP_admin_user_settingPO {

	public Object[] getKeys(){
		return new Object[]{ 
			user_id,
					user_key
		};
	}


    /** 
    * user_id - 用户id
    */
    private String user_id;

    /** 
    * user_key - 属性id
    */
    private String user_key;

    /** 
    * user_value - 属性配置值
    */
    private String user_value;

    /** 
    * remark - 属性说明
    */
    private String remark;

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

    public String getUser_id(){
        return this.user_id;
    }
    public void setUser_id(String user_id){
        this.user_id = user_id;
    }
    public String getUser_key(){
        return this.user_key;
    }
    public void setUser_key(String user_key){
        this.user_key = user_key;
    }
    public String getUser_value(){
        return this.user_value;
    }
    public void setUser_value(String user_value){
        this.user_value = user_value;
    }
    public String getRemark(){
        return this.remark;
    }
    public void setRemark(String remark){
        this.remark = remark;
    }
    public Timestamp getCreate_time(){
        return this.create_time;
    }
    public void setCreate_time(Timestamp create_time){
        this.create_time = create_time;
    }
    public Timestamp getUpdate_time(){
        return this.update_time;
    }
    public void setUpdate_time(Timestamp update_time){
        this.update_time = update_time;
    }
    public String getOperator(){
        return this.operator;
    }
    public void setOperator(String operator){
        this.operator = operator;
    }

	public CIP_admin_user_settingVO toVO(){
		CIP_admin_user_settingVO vo = new CIP_admin_user_settingVO();
		
		vo.setUser_id(user_id);
		vo.setUser_key(user_key);
		vo.setUser_value(user_value);
		vo.setRemark(remark);
		
		return vo;
	}
		
}