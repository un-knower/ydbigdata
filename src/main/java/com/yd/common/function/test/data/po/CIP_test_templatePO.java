package com.yd.common.function.test.data.po;

import java.sql.Timestamp;

import com.yd.common.function.test.data.vo.CIP_test_templateVO;

/**
 * <p>实体类</p>
 * <p>Table: cip_test_template - 测试数据模板</p>
 *
 * @since 2015-11-24 04:16:55
 */
public class CIP_test_templatePO {

	public Object[] getKeys(){
		return new Object[]{ 
			template_id
		};
	}


    /** 
    * template_id - 模板ID
    */
    private String template_id;

    /** 
    * sys_id - 系统ID
    */
    private String sys_id;

    /** 
    * template_desc - 模板描述
    */
    private String template_desc;

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

    public String getTemplate_id(){
        return this.template_id;
    }
    public void setTemplate_id(String template_id){
        this.template_id = template_id;
    }
    public String getSys_id(){
        return this.sys_id;
    }
    public void setSys_id(String sys_id){
        this.sys_id = sys_id;
    }
    public String getTemplate_desc(){
        return this.template_desc;
    }
    public void setTemplate_desc(String template_desc){
        this.template_desc = template_desc;
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

	public CIP_test_templateVO toVO(){
		CIP_test_templateVO vo = new CIP_test_templateVO();
		
		vo.setTemplate_id(template_id);
		vo.setSys_id(sys_id);
		vo.setTemplate_desc(template_desc);
		
		return vo;
	}
		
}