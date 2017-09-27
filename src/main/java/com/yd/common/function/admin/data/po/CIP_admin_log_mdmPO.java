package com.yd.common.function.admin.data.po;

import java.sql.Timestamp;

import com.yd.common.function.admin.data.vo.CIP_admin_log_mdmVO;
import com.yd.common.utils.DateUtils;

/**
 * <p>实体类</p>
 * <p>Table: cip_admin_log_mdm - 本地日志-主数据变更</p>
 *
 * @since 2015-08-26 08:45:49
 */
public class CIP_admin_log_mdmPO {

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
    * table_id - 数据表id
    */
    private String table_id;

    /** 
    * record_id - 记录id
    */
    private String record_id;

    /** 
    * old_values - 原始值
    */
    private String old_values;

    /** 
    * new_values - 新设置值
    */
    private String new_values;

    /** 
    * user_id - 变更人
    */
    private String user_id;

    /** 
    * operate_type - 操作类型
    */
    private String operate_type;

    /** 
    * occur_time - 记录时间
    */
    private Timestamp occur_time;

    public long getLog_id(){
        return this.log_id;
    }
    public void setLog_id(long log_id){
        this.log_id = log_id;
    }
    public String getTable_id(){
        return this.table_id;
    }
    public void setTable_id(String table_id){
        this.table_id = table_id;
    }
    public String getRecord_id(){
        return this.record_id;
    }
    public void setRecord_id(String record_id){
        this.record_id = record_id;
    }
    public String getOld_values(){
        return this.old_values;
    }
    public void setOld_values(String old_values){
        this.old_values = old_values;
    }
    public String getNew_values(){
        return this.new_values;
    }
    public void setNew_values(String new_values){
        this.new_values = new_values;
    }
    public String getUser_id(){
        return this.user_id;
    }
    public void setUser_id(String user_id){
        this.user_id = user_id;
    }
    public String getOperate_type(){
        return this.operate_type;
    }
    public void setOperate_type(String operate_type){
        this.operate_type = operate_type;
    }
    public Timestamp getOccur_time(){
        return this.occur_time;
    }
    public void setOccur_time(Timestamp occur_time){
        this.occur_time = occur_time;
    }

	public CIP_admin_log_mdmVO toVO(){
		CIP_admin_log_mdmVO vo = new CIP_admin_log_mdmVO();
		
		vo.setLog_id(log_id);
		vo.setTable_id(table_id);
		vo.setRecord_id(record_id);
		vo.setOld_values(old_values);
		vo.setNew_values(new_values);
		vo.setUser_id(user_id);
		vo.setOperate_type(operate_type);
		vo.setOccur_time(DateUtils.getDateTime(occur_time));
		
		return vo;
	}
		
}