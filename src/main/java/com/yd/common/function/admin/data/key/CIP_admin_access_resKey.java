package com.yd.common.function.admin.data.key;


/**
 * <p>实体key类</p>
 * <p>Table: cip_admin_access_res - 系统访问资源配置</p>
 *
 * @since 2015-08-23 03:44:37
 */
public class CIP_admin_access_resKey {	
	
	public Object[] getKeys(){
		return new Object[]{ 
		access_flag,
				resource_id
		};
	}
	
	public Object[] setKeys(String access_flag,String resource_id){
		return new Object[]{ 
			access_flag,
					resource_id
		};
	}


    /** 
    * access_flag - 访问标识 
    */
	private String access_flag;

    /** 
    * resource_id - 资源id 
    */
	private String resource_id;

	private String remark;
	
	private String operateCode;
	
	public String getAccess_flag(){
        return this.access_flag;
    }
    
    public void setAccess_flag(String access_flag){
        this.access_flag = access_flag;
    }
	public String getResource_id(){
        return this.resource_id;
    }
    
    public void setResource_id(String resource_id){
        this.resource_id = resource_id;
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