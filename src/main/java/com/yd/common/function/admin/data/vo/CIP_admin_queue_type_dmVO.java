package com.yd.common.function.admin.data.vo;

import com.yd.common.function.admin.data.po.CIP_admin_queue_type_dmPO;

/**
 * <p>实体类</p>
 * <p>Table: cip_admin_queue_type_dm - 系统队列配置</p>
 *
 * @since 2015-08-18 02:49:22
 */
public class CIP_admin_queue_type_dmVO {

	public Object[] getKeys(){
		return new Object[]{ 
		queue_type
		};
	}

    /** 
    * queue_type - 队列类型 
    */
    private String queue_type;
    
    /** 
    * queue_type_name - 队列名称 
    */
    private String queue_type_name;
    
    /** 
    * pojo_class - 队列类 
    */
    private String pojo_class;
    

	public String getQueue_type(){
        return this.queue_type;
    }
    public void setQueue_type(String queue_type){
        this.queue_type = queue_type;
    }
	public String getQueue_type_name(){
        return this.queue_type_name;
    }
    public void setQueue_type_name(String queue_type_name){
        this.queue_type_name = queue_type_name;
    }
	public String getPojo_class(){
        return this.pojo_class;
    }
    public void setPojo_class(String pojo_class){
        this.pojo_class = pojo_class;
    }

	public CIP_admin_queue_type_dmPO toPO(){
		CIP_admin_queue_type_dmPO po = new CIP_admin_queue_type_dmPO();
		
		po.setQueue_type(queue_type);
		po.setQueue_type_name(queue_type_name);
		po.setPojo_class(pojo_class);
		
		return po;
	}
		
}