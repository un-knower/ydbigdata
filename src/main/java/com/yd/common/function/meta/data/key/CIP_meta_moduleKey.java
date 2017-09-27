package com.yd.common.function.meta.data.key;


/**
 * <p>实体key类</p>
 * <p>Table: cip_meta_module - 应用模块信息</p>
 *
 * @since 2015-10-08 09:03:37
 */
public class CIP_meta_moduleKey {	
	
	public Object[] getKeys(){
		return new Object[]{ 
		module_id
		};
	}
	
	public Object[] setKeys(String module_id){
		return new Object[]{ 
			module_id
		};
	}


    /** 
    * module_id - 模块id 
    */
	private String module_id;

	private String remark;
	
	private String operateCode;
	
	public String getModule_id(){
        return this.module_id;
    }
    
    public void setModule_id(String module_id){
        this.module_id = module_id;
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