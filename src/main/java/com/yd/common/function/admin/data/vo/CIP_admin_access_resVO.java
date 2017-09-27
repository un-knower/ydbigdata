package com.yd.common.function.admin.data.vo;

import com.yd.common.function.admin.data.po.CIP_admin_access_resPO;

/**
 * <p>实体类</p>
 * <p>Table: cip_admin_access_res - 系统访问资源配置</p>
 *
 * @since 2015-08-23 03:44:37
 */
public class CIP_admin_access_resVO {

	public Object[] getKeys(){
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
    
    /** 
    * resource_name - 资源名称 
    */
    private String resource_name;
    
    /** 
    * resource_desc - 资源描述 
    */
    private String resource_desc;
    
    /** 
    * sys_uri - 访问URI 
    */
    private String sys_uri;
    
    /** 
    * icon_id - 图标id 
    */
    private String icon_id;
    

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
	public String getResource_name(){
        return this.resource_name;
    }
    public void setResource_name(String resource_name){
        this.resource_name = resource_name;
    }
	public String getResource_desc(){
        return this.resource_desc;
    }
    public void setResource_desc(String resource_desc){
        this.resource_desc = resource_desc;
    }
	public String getSys_uri(){
        return this.sys_uri;
    }
    public void setSys_uri(String sys_uri){
        this.sys_uri = sys_uri;
    }
	public String getIcon_id(){
        return this.icon_id;
    }
    public void setIcon_id(String icon_id){
        this.icon_id = icon_id;
    }

	public CIP_admin_access_resPO toPO(){
		CIP_admin_access_resPO po = new CIP_admin_access_resPO();
		
		po.setAccess_flag(access_flag);
		po.setResource_id(resource_id);
		po.setResource_name(resource_name);
		po.setResource_desc(resource_desc);
		po.setSys_uri(sys_uri);
		po.setIcon_id(icon_id);
		
		return po;
	}
		
}