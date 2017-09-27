package com.yd.common.function.meta.data.key;


/**
 * <p>实体key类</p>
 * <p>Table: cip_meta_job_log - 元数据作业志</p>
 *
 * @since 2015-10-08 09:03:37
 */
public class CIP_meta_job_logKey {	
	
	public Object[] getKeys(){
		return new Object[]{ 
		item_no,
				serail_no
		};
	}
	
	public Object[] setKeys(int item_no,long serail_no){
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

	private String remark;
	
	private String operateCode;
	
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