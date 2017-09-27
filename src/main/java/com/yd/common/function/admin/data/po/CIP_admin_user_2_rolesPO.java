package com.yd.common.function.admin.data.po;

import java.sql.Timestamp;

import com.yd.common.function.admin.data.vo.CIP_admin_user_2_rolesVO;

/**
 * <p>实体类</p>
 * <p>Table: cip_admin_user_2_roles - 用户角色关联</p>
 *
 * @since 2015-07-27 02:40:52
 */
public class CIP_admin_user_2_rolesPO {

	public Object[] getKeys(){
		return new Object[]{ 
			role_id,
					user_id
		};
	}


    /** 
    * role_id - 角色id
    */
    private String role_id;

    /** 
    * user_id - 用户id
    */
    private String user_id;

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
    * default_flag - 默认角色标识
    */
    private int default_flag;

    public String getRole_id(){
        return this.role_id;
    }
    public void setRole_id(String role_id){
        this.role_id = role_id;
    }
    public String getUser_id(){
        return this.user_id;
    }
    public void setUser_id(String user_id){
        this.user_id = user_id;
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
    public int getDefault_flag(){
        return this.default_flag;
    }
    public void setDefault_flag(int default_flag){
        this.default_flag = default_flag;
    }

	public CIP_admin_user_2_rolesVO toVO(){
		CIP_admin_user_2_rolesVO vo = new CIP_admin_user_2_rolesVO();
		
		vo.setRole_id(role_id);
		vo.setUser_id(user_id);
		vo.setDefault_flag(default_flag);
		
		return vo;
	}
		
}