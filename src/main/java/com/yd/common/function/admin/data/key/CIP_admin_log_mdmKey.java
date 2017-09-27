package com.yd.common.function.admin.data.key;


/**
 * <p>实体key类</p>
 * <p>Table: cip_admin_log_mdm - 本地日志-主数据变更</p>
 *
 * @since 2015-08-26 08:45:49
 */
public class CIP_admin_log_mdmKey {	
	
	public Object[] getKeys(){
		return new Object[]{ 
		log_id
		};
	}
	
	public Object[] setKeys(long log_id){
		return new Object[]{ 
			log_id
		};
	}


    /** 
    * log_id - 日志id 
    */
	private long log_id;

	private String remark;
	
	private String operateCode;
	
	public long getLog_id(){
        return this.log_id;
    }
    
    public void setLog_id(long log_id){
        this.log_id = log_id;
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