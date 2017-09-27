package com.yd.common.function.test.data.po;

import java.sql.Timestamp;

import com.yd.common.function.test.data.vo.CIP_test_template_formVO;

/**
 * <p>实体类</p>
 * <p>Table: cip_test_template_form - 测试数据模板字段</p>
 *
 * @since 2015-11-24 04:16:55
 */
public class CIP_test_template_formPO {

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

    /** 
    * create_time - 系统时间
    */
    private Timestamp create_time;

    /** 
    * update_time - 修改时间
    */
    private Timestamp update_time;

    /** 
    * operator - 操作人员
    */
    private String operator;

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
    public Timestamp getCreate_time(){
        return this.create_time;
    }
    public void setCreate_time(Timestamp create_time){
        this.create_time = create_time;
    }
    public Timestamp getUpdate_time(){
        return this.update_time;
    }
    public void setUpdate_time(Timestamp update_time){
        this.update_time = update_time;
    }
    public String getOperator(){
        return this.operator;
    }
    public void setOperator(String operator){
        this.operator = operator;
    }

	public CIP_test_template_formVO toVO(){
		CIP_test_template_formVO vo = new CIP_test_template_formVO();
		
		vo.setField_id(field_id);
		vo.setTemplate_id(template_id);
		vo.setField_name(field_name);
		vo.setIsMandatory(isMandatory);
		vo.setField_ui(field_ui);
		
		return vo;
	}
		
}