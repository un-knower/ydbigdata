package com.yd.common.function.admin.data.po;

import java.sql.Timestamp;

import com.yd.common.function.admin.data.vo.CIP_admin_queueVO;

/**
 * <p>实体类</p>
 * <p>Table: cip_admin_queue - 系统队列信息</p>
 *
 * @since 2015-08-18 05:02:08
 */
public class CIP_admin_queuePO {

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

    /** 
    * create_time - 系统时间
    */
    private Timestamp create_time;

    /** 
    * update_time - 修改时间
    */
    private Timestamp update_time;

    /** 
    * operator - 操作人员
    */
    private String operator;

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
    public Timestamp getCreate_time(){
        return this.create_time;
    }
    public void setCreate_time(Timestamp create_time){
        this.create_time = create_time;
    }
    public Timestamp getUpdate_time(){
        return this.update_time;
    }
    public void setUpdate_time(Timestamp update_time){
        this.update_time = update_time;
    }
    public String getOperator(){
        return this.operator;
    }
    public void setOperator(String operator){
        this.operator = operator;
    }

	public CIP_admin_queueVO toVO(){
		CIP_admin_queueVO vo = new CIP_admin_queueVO();
		
		vo.setQueue_id(queue_id);
		vo.setQueue_type(queue_type);
		vo.setQueue_data(queue_data);
		vo.setQueue_flag(queue_flag);
		
		return vo;
	}
		
}