package com.yd.common.function.admin.data.vo;

import com.yd.common.function.admin.data.po.CIP_admin_user_settingPO;

/**
 * <p>实体类</p>
 * <p>Table: cip_admin_user_setting - 用户信息设置</p>
 *
 * @since 2015-07-27 02:40:52
 */
public class CIP_admin_user_settingVO {

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

	public CIP_admin_user_settingPO toPO(){
		CIP_admin_user_settingPO po = new CIP_admin_user_settingPO();
		
		po.setUser_id(user_id);
		po.setUser_key(user_key);
		po.setUser_value(user_value);
		po.setRemark(remark);
		
		return po;
	}
		
}