package com.yd.common.function.admin.data.key;


/**
 * <p>实体key类</p>
 * <p>Table: cip_admin_queue - 系统队列信息</p>
 *
 * @since 2015-08-18 02:49:22
 */
public class CIP_admin_queueKey {	
	
	public Object[] getKeys(){
		return new Object[]{ 
		queue_id,
				queue_type
		};
	}
	
	public Object[] setKeys(long queue_id,String queue_type){
		return new Object[]{ 
			queue_id,
					queue_type
		};
	}


    /** 
    * queue_id - 队列流水号 
    */
	private long queue_id;

    /** 
    * queue_type - 队列类型 
    */
	private String queue_type;

	private String remark;
	
	private String operateCode;
	
	public long getQueue_id(){
        return this.queue_id;
    }
    
    public void setQueue_id(long queue_id){
        this.queue_id = queue_id;
    }
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