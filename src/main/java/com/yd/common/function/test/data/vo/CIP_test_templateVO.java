package com.yd.common.function.test.data.vo;

import com.yd.common.function.test.data.po.CIP_test_templatePO;

/**
 * <p>实体类</p>
 * <p>Table: cip_test_template - 测试数据模板</p>
 *
 * @since 2015-11-24 04:16:55
 */
public class CIP_test_templateVO {

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

	public CIP_test_templatePO toPO(){
		CIP_test_templatePO po = new CIP_test_templatePO();
		
		po.setTemplate_id(template_id);
		po.setSys_id(sys_id);
		po.setTemplate_desc(template_desc);
		
		return po;
	}
		
}