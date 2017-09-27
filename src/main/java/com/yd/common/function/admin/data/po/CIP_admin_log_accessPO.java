package com.yd.common.function.admin.data.po;

import java.sql.Timestamp;

import com.yd.common.function.admin.data.vo.CIP_admin_log_accessVO;
import com.yd.common.utils.DateUtils;

/**
 * <p>实体类</p>
 * <p>Table: cip_admin_log_access - 本地日志-系统访问</p>
 *
 * @since 2015-08-26 08:45:49
 */
public class CIP_admin_log_accessPO {

	public Object[] getKeys(){
		return new Object[]{ 
			log_id
		};
	}


    /** 
    * log_id - 日志id
    */
    private long log_id;

    /** 
    * resource_id - 系统资源id
    */
    private String resource_id;

    /** 
    * visitor_id - 访问对象id
    */
    private String visitor_id;

    /** 
    * visitor_type - 访问对象类型
    */
    private String visitor_type;

    /** 
    * occur_time - 记录时间
    */
    private Timestamp occur_time;

    /** 
    * ip - ip
    */
    private String ip;

    /** 
    * remark - 访问备注
    */
    private String remark;

    public long getLog_id(){
        return this.log_id;
    }
    public void setLog_id(long log_id){
        this.log_id = log_id;
    }
    public String getResource_id(){
        return this.resource_id;
    }
    public void setResource_id(String resource_id){
        this.resource_id = resource_id;
    }
    public String getVisitor_id(){
        return this.visitor_id;
    }
    public void setVisitor_id(String visitor_id){
        this.visitor_id = visitor_id;
    }
    public String getVisitor_type(){
        return this.visitor_type;
    }
    public void setVisitor_type(String visitor_type){
        this.visitor_type = visitor_type;
    }
    public Timestamp getOccur_time(){
        return this.occur_time;
    }
    public void setOccur_time(Timestamp occur_time){
        this.occur_time = occur_time;
    }
    public String getIp(){
        return this.ip;
    }
    public void setIp(String ip){
        this.ip = ip;
    }
    public String getRemark(){
        return this.remark;
    }
    public void setRemark(String remark){
        this.remark = remark;
    }

	public CIP_admin_log_accessVO toVO(){
		CIP_admin_log_accessVO vo = new CIP_admin_log_accessVO();
		
		vo.setLog_id(log_id);
		vo.setResource_id(resource_id);
		vo.setVisitor_id(visitor_id);
		vo.setVisitor_type(visitor_type);
		vo.setOccur_time(DateUtils.getDateTime(occur_time));
		vo.setRemark(remark);
		
		return vo;
	}
		
}