package com.yd.common.function.admin.data.key;


/**
 * <p>实体key类</p>
 * <p>Table: cip_admin_question - 系统问题反馈</p>
 *
 * @since 2015-12-25 01:45:43
 */
public class CIP_admin_questionKey {	
	
	public Object[] getKeys(){
		return new Object[]{ 
		question_id
		};
	}
	
	public Object[] setKeys(String question_id){
		return new Object[]{ 
			question_id
		};
	}


    /** 
    * question_id - 问题ID 
    */
	private String question_id;

	private String remark;
	
	private String operateCode;
	
	public String getQuestion_id(){
        return this.question_id;
    }
    
    public void setQuestion_id(String question_id){
        this.question_id = question_id;
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