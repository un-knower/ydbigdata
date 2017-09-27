package com.yd.common.function.admin.data.vo;

import java.sql.Timestamp;

import com.yd.common.function.admin.data.po.CIP_admin_log_jobPO;

/**
 * <p>实体类</p>
 * <p>Table: cip_admin_log_job - 本地日志-作业日志</p>
 *
 * @since 2015-08-26 08:45:48
 */
public class CIP_admin_log_jobVO {

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
    * task_id - 批量任务id 
    */
    private String task_id;
    
    /** 
    * step_id - 步骤id 
    */
    private int step_id;
    
    /** 
    * step_msg - 步骤信息 
    */
    private String step_msg;
    
    /** 
    * error_code - 错误代码 
    */
    private int error_code;
    
    /** 
    * occur_time - 记录时间 
    */
    private String occur_time;
    

	public long getLog_id(){
        return this.log_id;
    }
    public void setLog_id(long log_id){
        this.log_id = log_id;
    }
	public String getTask_id(){
        return this.task_id;
    }
    public void setTask_id(String task_id){
        this.task_id = task_id;
    }
	public int getStep_id(){
        return this.step_id;
    }
    public void setStep_id(int step_id){
        this.step_id = step_id;
    }
	public String getStep_msg(){
        return this.step_msg;
    }
    public void setStep_msg(String step_msg){
        this.step_msg = step_msg;
    }
	public int getError_code(){
        return this.error_code;
    }
    public void setError_code(int error_code){
        this.error_code = error_code;
    }
	public String getOccur_time(){
        return this.occur_time;
    }
    public void setOccur_time(String occur_time){
        this.occur_time = occur_time;
    }

	public CIP_admin_log_jobPO toPO(){
		CIP_admin_log_jobPO po = new CIP_admin_log_jobPO();
		
		po.setLog_id(log_id);
		po.setTask_id(task_id);
		po.setStep_id(step_id);
		po.setStep_msg(step_msg);
		po.setError_code(error_code);
		po.setOccur_time(Timestamp.valueOf(occur_time));
		
		return po;
	}
		
}