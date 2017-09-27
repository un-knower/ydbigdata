package com.yd.common.function.admin.data.vo;

import com.yd.common.function.admin.data.po.CIP_admin_queuePO;

/**
 * <p>实体类</p>
 * <p>Table: cip_admin_queue - 系统队列信息</p>
 *
 * @since 2015-08-18 05:02:08
 */
public class CIP_admin_queueVO {

	public Object[] getKeys(){
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
    
    /** 
    * queue_data - 队列数据 
    */
    private String queue_data;
    
    /** 
    * queue_flag - 数据提取标识 
    */
    private String queue_flag;
    

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
	public String getQueue_data(){
        return this.queue_data;
    }
    public void setQueue_data(String queue_data){
        this.queue_data = queue_data;
    }
	public String getQueue_flag(){
        return this.queue_flag;
    }
    public void setQueue_flag(String queue_flag){
        this.queue_flag = queue_flag;
    }

	public CIP_admin_queuePO toPO(){
		CIP_admin_queuePO po = new CIP_admin_queuePO();
		
		po.setQueue_id(queue_id);
		po.setQueue_type(queue_type);
		po.setQueue_data(queue_data);
		po.setQueue_flag(queue_flag);
		
		return po;
	}
		
}