package com.yd.common.function.test.data.vo;

import com.yd.common.function.test.data.po.CIP_test_template_formPO;

/**
 * <p>实体类</p>
 * <p>Table: cip_test_template_form - 测试数据模板字段</p>
 *
 * @since 2015-11-24 04:16:55
 */
public class CIP_test_template_formVO {

	public Object[] getKeys(){
		return new Object[]{ 
		field_id
		};
	}

    /** 
    * field_id - 字段ID 
    */
    private String field_id;
    
    /** 
    * template_id - 所属模板ID 
    */
    private String template_id;
    
    /** 
    * field_name - 字段名称 
    */
    private String field_name;
    
    /** 
    * isMandatory - 是否必输 
    */
    private String isMandatory;
    
    /** 
    * field_ui - 字段UI 
    */
    private String field_ui;
    

	public String getField_id(){
        return this.field_id;
    }
    public void setField_id(String field_id){
        this.field_id = field_id;
    }
	public String getTemplate_id(){
        return this.template_id;
    }
    public void setTemplate_id(String template_id){
        this.template_id = template_id;
    }
	public String getField_name(){
        return this.field_name;
    }
    public void setField_name(String field_name){
        this.field_name = field_name;
    }
	public String getIsMandatory(){
        return this.isMandatory;
    }
    public void setIsMandatory(String isMandatory){
        this.isMandatory = isMandatory;
    }
	public String getField_ui(){
        return this.field_ui;
    }
    public void setField_ui(String field_ui){
        this.field_ui = field_ui;
    }

	public CIP_test_template_formPO toPO(){
		CIP_test_template_formPO po = new CIP_test_template_formPO();
		
		po.setField_id(field_id);
		po.setTemplate_id(template_id);
		po.setField_name(field_name);
		po.setIsMandatory(isMandatory);
		po.setField_ui(field_ui);
		
		return po;
	}
		
}