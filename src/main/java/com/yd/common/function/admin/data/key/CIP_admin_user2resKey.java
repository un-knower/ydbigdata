package com.yd.common.function.admin.data.key;


/**
 * <p>实体key类</p>
 * <p>Table: cip_admin_user2res - 主页快速工具表</p>
 *
 * @since 2016-08-31 04:49:06
 */
public class CIP_admin_user2resKey {	
	
	public Object[] getKeys(){
		return new Object[]{ 
		resource_id,
				user_id
		};
	}
	
	public Object[] setKeys(String resource_id,String user_id){
		return new Object[]{ 
			resource_id,
					user_id
		};
	}


    /** 
    * resource_id - 资源id 
    */
	private String resource_id;

    /** 
    * user_id - 用户id 
    */
	private String user_id;

	private String remark;
	
	private String operateCode;
	
	public String getResource_id(){
        return this.resource_id;
    }
    
    public void setResource_id(String resource_id){
        this.resource_id = resource_id;
    }
	public String getUser_id(){
        return this.user_id;
    }
    
    public void setUser_id(String user_id){
        this.user_id = user_id;
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