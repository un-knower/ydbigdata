package com.yd.common.function.admin.data.key;


/**
 * <p>实体key类</p>
 * <p>Table: cip_admin_access_rel - 系统与资源关系配置</p>
 *
 * @since 2015-08-20 09:11:16
 */
public class CIP_admin_access_relKey {	
	
	public Object[] getKeys(){
		return new Object[]{ 
		resource_id,
				sys_id
		};
	}
	
	public Object[] setKeys(String resource_id,String sys_id){
		return new Object[]{ 
			resource_id,
					sys_id
		};
	}


    /** 
    * resource_id - 资源id 
    */
	private String resource_id;

    /** 
    * sys_id - 系统id 
    */
	private String sys_id;

	private String remark;
	
	private String operateCode;
	
	public String getResource_id(){
        return this.resource_id;
    }
    
    public void setResource_id(String resource_id){
        this.resource_id = resource_id;
    }
	public String getSys_id(){
        return this.sys_id;
    }
    
    public void setSys_id(String sys_id){
        this.sys_id = sys_id;
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