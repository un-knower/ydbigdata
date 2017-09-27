package com.yd.common.function.meta.data.po;

import java.sql.Timestamp;

import com.yd.common.function.meta.data.vo.CIP_meta_moduleVO;

/**
 * <p>实体类</p>
 * <p>Table: cip_meta_module - 应用模块信息</p>
 *
 * @since 2015-10-08 09:03:37
 */
public class CIP_meta_modulePO {

	public Object[] getKeys(){
		return new Object[]{ 
			module_id
		};
	}


    /** 
    * module_id - 模块id
    */
    private String module_id;

    /** 
    * module_name - 模块名称
    */
    private String module_name;

    /** 
    * module_memo - 模块描述
    */
    private String module_memo;

    /** 
    * update_time - 修改时间
    */
    private Timestamp update_time;

    /** 
    * create_time - 创建时间
    */
    private Timestamp create_time;

    /** 
    * operator - 操作人员
    */
    private String operator;

    public String getModule_id(){
        return this.module_id;
    }
    public void setModule_id(String module_id){
        this.module_id = module_id;
    }
    public String getModule_name(){
        return this.module_name;
    }
    public void setModule_name(String module_name){
        this.module_name = module_name;
    }
    public String getModule_memo(){
        return this.module_memo;
    }
    public void setModule_memo(String module_memo){
        this.module_memo = module_memo;
    }
    public Timestamp getUpdate_time(){
        return this.update_time;
    }
    public void setUpdate_time(Timestamp update_time){
        this.update_time = update_time;
    }
    public Timestamp getCreate_time(){
        return this.create_time;
    }
    public void setCreate_time(Timestamp create_time){
        this.create_time = create_time;
    }
    public String getOperator(){
        return this.operator;
    }
    public void setOperator(String operator){
        this.operator = operator;
    }

	public CIP_meta_moduleVO toVO(){
		CIP_meta_moduleVO vo = new CIP_meta_moduleVO();
		
		vo.setModule_id(module_id);
		vo.setModule_name(module_name);
		vo.setModule_memo(module_memo);
		
		return vo;
	}
		
}