package com.yd.common.function.meta.data.key;


/**
 * <p>实体key类</p>
 * <p>Table: cip_meta_str - 系统数据结构表</p>
 *
 * @since 2015-10-08 09:03:37
 */
public class CIP_meta_strKey {	
	
	public Object[] getKeys(){
		return new Object[]{ 
		str_id
		};
	}
	
	public Object[] setKeys(String str_id){
		return new Object[]{ 
			str_id
		};
	}


    /** 
    * str_id - 数据结构id 
    */
	private String str_id;

	private String remark;
	
	private String operateCode;
	
	public String getStr_id(){
        return this.str_id;
    }
    
    public void setStr_id(String str_id){
        this.str_id = str_id;
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