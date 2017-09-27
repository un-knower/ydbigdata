package com.yd.common.function.meta.data.key;

import java.sql.Date;

import com.yd.common.utils.DateUtils;

/**
 * <p>实体key类</p>
 * <p>Table: cip_meta_str_his - 系统数据历史结构</p>
 *
 * @since 2015-10-08 09:03:37
 */
public class CIP_meta_str_hisKey {	
	
	public Object[] getKeys(){
		return new Object[]{ 
		str_id,
				new Date(DateUtils.parseDate(version_date).getTime())
		};
	}
	
	public Object[] setKeys(String str_id,Date version_date){
		return new Object[]{ 
			str_id,
					version_date
		};
	}


    /** 
    * str_id - 数据结构id 
    */
	private String str_id;

    /** 
    * version_date - 定稿日期 
    */
	private String version_date;

	private String remark;
	
	private String operateCode;
	
	public String getStr_id(){
        return this.str_id;
    }
    
    public void setStr_id(String str_id){
        this.str_id = str_id;
    }
	public String getVersion_date(){
        return this.version_date;
    }
    
    public void setVersion_date(String version_date){
        this.version_date = version_date;
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