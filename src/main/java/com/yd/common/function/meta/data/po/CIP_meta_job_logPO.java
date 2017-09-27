package com.yd.common.function.meta.data.po;

import java.sql.Timestamp;

import com.yd.common.function.meta.data.vo.CIP_meta_job_logVO;

/**
 * <p>实体类</p>
 * <p>Table: cip_meta_job_log - 元数据作业志</p>
 *
 * @since 2015-10-08 09:03:37
 */
public class CIP_meta_job_logPO {

	public Object[] getKeys(){
		return new Object[]{ 
			item_no,
					serail_no
		};
	}


    /** 
    * item_no - 执行序号
    */
    private int item_no;

    /** 
    * serail_no - 作业任务流水号
    */
    private long serail_no;

    /** 
    * msg_txt - 执行消息
    */
    private String msg_txt;

    /** 
    * op_time - 操作时间
    */
    private Timestamp op_time;

    /** 
    * error_code - 错误码
    */
    private String error_code;

    public int getItem_no(){
        return this.item_no;
    }
    public void setItem_no(int item_no){
        this.item_no = item_no;
    }
    public long getSerail_no(){
        return this.serail_no;
    }
    public void setSerail_no(long serail_no){
        this.serail_no = serail_no;
    }
    public String getMsg_txt(){
        return this.msg_txt;
    }
    public void setMsg_txt(String msg_txt){
        this.msg_txt = msg_txt;
    }
    public Timestamp getOp_time(){
        return this.op_time;
    }
    public void setOp_time(Timestamp op_time){
        this.op_time = op_time;
    }
    public String getError_code(){
        return this.error_code;
    }
    public void setError_code(String error_code){
        this.error_code = error_code;
    }

	public CIP_meta_job_logVO toVO(){
		CIP_meta_job_logVO vo = new CIP_meta_job_logVO();
		
		vo.setItem_no(item_no);
		vo.setSerail_no(serail_no);
		vo.setMsg_txt(msg_txt);
		vo.setError_code(error_code);
		
		return vo;
	}
		
}