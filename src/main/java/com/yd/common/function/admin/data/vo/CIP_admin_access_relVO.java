package com.yd.common.function.admin.data.vo;

import com.yd.common.function.admin.data.po.CIP_admin_access_relPO;

/**
 * <p>实体类</p>
 * <p>Table: cip_admin_access_rel - 系统与资源关系配置</p>
 *
 * @since 2015-08-20 09:11:16
 */
public class CIP_admin_access_relVO {

	public Object[] getKeys(){
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

	public CIP_admin_access_relPO toPO(){
		CIP_admin_access_relPO po = new CIP_admin_access_relPO();
		
		po.setResource_id(resource_id);
		po.setSys_id(sys_id);
		
		return po;
	}
		
}