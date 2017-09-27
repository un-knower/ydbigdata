package com.yd.common.function.admin.data.key;


/**
 * <p>实体key类</p>
 * <p>Table: cip_admin_user_setting - 用户信息设置</p>
 *
 * @since 2015-07-27 02:40:52
 */
public class CIP_admin_user_settingKey {	
	
	public Object[] getKeys(){
		return new Object[]{ 
		user_id,
				user_key
		};
	}
	
	public Object[] setKeys(String user_id,String user_key){
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

	private String remark;
	
	private String operateCode;
	
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

	public void setOperateCode(String operateCode){
		this.operateCode = operateCode;
	}
	
	public String getOperateCode(){
		return operateCode;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}
		
}