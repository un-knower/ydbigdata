package com.yd.common.function.meta.data.key;

import java.sql.Date;

import com.yd.common.utils.DateUtils;

/**
 * <p>实体key类</p>
 * <p>Table: cip_meta_fields_his - 数据字段历史</p>
 *
 * @since 2015-10-08 09:03:37
 */
public class CIP_meta_fields_hisKey {	
	
	public Object[] getKeys(){
		return new Object[]{ 
		field_id,
				str_id,
				sys_type,
				new Date(DateUtils.parseDate(version_date).getTime())
		};
	}
	
	public Object[] setKeys(String field_id,String str_id,String sys_type,Date version_date){
		return new Object[]{ 
			field_id,
					str_id,
					sys_type,
					version_date
		};
	}


    /** 
    * field_id - 字段id 
    */
	private String field_id;

    /** 
    * str_id - 数据结构id 
    */
	private String str_id;

    /** 
    * sys_type - 系统类型 
    */
	private String sys_type;

    /** 
    * version_date - 定稿日期 
    */
	private String version_date;

	private String remark;
	
	private String operateCode;
	
	public String getField_id(){
        return this.field_id;
    }
    
    public void setField_id(String field_id){
        this.field_id = field_id;
    }
	public String getStr_id(){
        return this.str_id;
    }
    
    public void setStr_id(String str_id){
        this.str_id = str_id;
    }
	public String getSys_type(){
        return this.sys_type;
    }
    
    public void setSys_type(String sys_type){
        this.sys_type = sys_type;
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