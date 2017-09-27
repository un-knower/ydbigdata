package com.yd.common.function.admin.data.key;


/**
 * <p>实体key类</p>
 * <p>Table: cip_admin_queue_type_dm - 系统队列配置</p>
 *
 * @since 2015-08-18 02:49:22
 */
public class CIP_admin_queue_type_dmKey {	
	
	public Object[] getKeys(){
		return new Object[]{ 
		queue_type
		};
	}
	
	public Object[] setKeys(String queue_type){
		return new Object[]{ 
			queue_type
		};
	}


    /** 
    * queue_type - 队列类型 
    */
	private String queue_type;

	private String remark;
	
	private String operateCode;
	
	public String getQueue_type(){
        return this.queue_type;
    }
    
    public void setQueue_type(String queue_type){
        this.queue_type = queue_type;
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