package com.yd.common.function.meta.data.vo;

import com.yd.common.function.meta.data.po.CIP_meta_modulePO;

/**
 * <p>实体类</p>
 * <p>Table: cip_meta_module - 应用模块信息</p>
 *
 * @since 2015-10-08 09:03:37
 */
public class CIP_meta_moduleVO {

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

	public CIP_meta_modulePO toPO(){
		CIP_meta_modulePO po = new CIP_meta_modulePO();
		
		po.setModule_id(module_id);
		po.setModule_name(module_name);
		po.setModule_memo(module_memo);
		
		return po;
	}
		
}