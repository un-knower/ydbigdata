package com.yd.common.function.admin.data.vo;

import com.yd.common.function.admin.data.po.CIP_admin_access_ctrlPO;

/**
 * <p>实体类</p>
 * <p>Table: cip_admin_access_ctrl - 系统访问控制</p>
 *
 * @since 2015-08-20 08:58:39
 */
public class CIP_admin_access_ctrlVO {

	public Object[] getKeys(){
		return new Object[]{ 
		access_flag,
				sys_id
		};
	}

    /** 
    * access_flag - 访问标识 
    */
    private String access_flag;
    
    /** 
    * sys_id - 系统id 
    */
    private String sys_id;
    
    /** 
    * access_type - 访问类型 
    */
    private String access_type;
    
    /** 
    * access_ip - 访问ip 
    */
    private String access_ip;
    
    /** 
    * auth_code - 访问授权码 
    */
    private String auth_code;
    
    /** 
    * remark - 操作备注 
    */
    private String remark;
    
    private String other_params;
	
	public String getOther_params() {
		return other_params;
	}

	public void setOther_params(String other_params) {
		this.other_params = other_params;
	}

	public String getAccess_flag(){
        return this.access_flag;
    }
    public void setAccess_flag(String access_flag){
        this.access_flag = access_flag;
    }
	public String getSys_id(){
        return this.sys_id;
    }
    public void setSys_id(String sys_id){
        this.sys_id = sys_id;
    }
	public String getAccess_type(){
        return this.access_type;
    }
    public void setAccess_type(String access_type){
        this.access_type = access_type;
    }
	public String getAccess_ip(){
        return this.access_ip;
    }
    public void setAccess_ip(String access_ip){
        this.access_ip = access_ip;
    }
	public String getAuth_code(){
        return this.auth_code;
    }
    public void setAuth_code(String auth_code){
        this.auth_code = auth_code;
    }
	public String getRemark(){
        return this.remark;
    }
    public void setRemark(String remark){
        this.remark = remark;
    }

	public CIP_admin_access_ctrlPO toPO(){
		CIP_admin_access_ctrlPO po = new CIP_admin_access_ctrlPO();
		
		po.setOther_params(other_params);
		po.setAccess_flag(access_flag);
		po.setSys_id(sys_id);
		po.setAccess_type(access_type);
		po.setAccess_ip(access_ip);
		po.setAuth_code(auth_code);
		po.setRemark(remark);
		
		return po;
	}
		
}