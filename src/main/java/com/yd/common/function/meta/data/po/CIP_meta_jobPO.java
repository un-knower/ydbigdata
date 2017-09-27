package com.yd.common.function.meta.data.po;

import java.sql.Timestamp;

import com.yd.common.function.meta.data.vo.CIP_meta_jobVO;
import com.yd.common.utils.DateUtils;

/**
 * <p>实体类</p>
 * <p>Table: cip_meta_job - 元数据作业</p>
 *
 * @since 2015-10-08 09:03:37
 */
public class CIP_meta_jobPO {

	public Object[] getKeys(){
		return new Object[]{ 
			serail_no
		};
	}


    /** 
    * serail_no - 作业任务流水号
    */
    private long serail_no;

    /** 
    * sys_id - 系统id
    */
    private String sys_id;

    /** 
    * exec_time - 执行时间
    */
    private Timestamp exec_time;

    /** 
    * meta_job_type - 作业类型
    */
    private String meta_job_type;

    /** 
    * file_url - 生成文件url
    */
    private String file_url;

    /** 
    * op_time - 操作时间
    */
    private Timestamp op_time;

    /** 
    * operator - 操作人
    */
    private String operator;

    public long getSerail_no(){
        return this.serail_no;
    }
    public void setSerail_no(long serail_no){
        this.serail_no = serail_no;
    }
    public String getSys_id(){
        return this.sys_id;
    }
    public void setSys_id(String sys_id){
        this.sys_id = sys_id;
    }
    public Timestamp getExec_time(){
        return this.exec_time;
    }
    public void setExec_time(Timestamp exec_time){
        this.exec_time = exec_time;
    }
    public String getMeta_job_type(){
        return this.meta_job_type;
    }
    public void setMeta_job_type(String meta_job_type){
        this.meta_job_type = meta_job_type;
    }
    public String getFile_url(){
        return this.file_url;
    }
    public void setFile_url(String file_url){
        this.file_url = file_url;
    }
    public Timestamp getOp_time(){
        return this.op_time;
    }
    public void setOp_time(Timestamp op_time){
        this.op_time = op_time;
    }
    public String getOperator(){
        return this.operator;
    }
    public void setOperator(String operator){
        this.operator = operator;
    }

	public CIP_meta_jobVO toVO(){
		CIP_meta_jobVO vo = new CIP_meta_jobVO();
		
		vo.setSerail_no(serail_no);
		vo.setSys_id(sys_id);
		vo.setExec_time(DateUtils.getDateTime(exec_time));
		vo.setMeta_job_type(meta_job_type);
		vo.setFile_url(file_url);
		
		return vo;
	}
		
}