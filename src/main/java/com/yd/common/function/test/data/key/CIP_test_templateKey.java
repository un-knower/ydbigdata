package com.yd.common.function.test.data.key;


/**
 * <p>实体key类</p>
 * <p>Table: cip_test_template - 测试数据模板</p>
 *
 * @since 2015-11-24 04:16:55
 */
public class CIP_test_templateKey {	
	
	public Object[] getKeys(){
		return new Object[]{ 
		template_id
		};
	}
	
	public Object[] setKeys(String template_id){
		return new Object[]{ 
			template_id
		};
	}


    /** 
    * template_id - 模板ID 
    */
	private String template_id;

	private String remark;
	
	private String operateCode;
	
	public String getTemplate_id(){
        return this.template_id;
    }
    
    public void setTemplate_id(String template_id){
        this.template_id = template_id;
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