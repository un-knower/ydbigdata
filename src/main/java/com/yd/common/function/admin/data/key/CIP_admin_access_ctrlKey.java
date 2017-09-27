package com.yd.common.function.admin.data.key;


/**
 * <p>实体key类</p>
 * <p>Table: cip_admin_access_ctrl - 系统访问控制</p>
 *
 * @since 2015-08-20 08:58:38
 */
public class CIP_admin_access_ctrlKey {	
	
	public Object[] getKeys(){
		return new Object[]{ 
		access_flag,
				sys_id
		};
	}
	
	public Object[] setKeys(String access_flag,String sys_id){
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

	private String remark;
	
	private String operateCode;
	
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