package com.yd.common.function.admin.data.po;

import java.sql.Timestamp;

import com.yd.common.function.admin.data.vo.CIP_admin_codesVO;

/**
 * <p>实体类</p>
 * <p>Table: cip_admin_codes - 数据编码</p>
 *
 * @since 2015-07-27 02:40:51
 */
public class CIP_admin_codesPO {

	public Object[] getKeys(){
		return new Object[]{ 
			code_type,
					domain_id
		};
	}


    /** 
    * code_type - 编码类型
    */
    private String code_type;

    /** 
    * domain_id - 数据域id
    */
    private String domain_id;

    /** 
    * code_name - 编码名称
    */
    private String code_name;

    /** 
    * create_time - 系统生成时间
    */
    private Timestamp create_time;

    /** 
    * update_time - 修改时间
    */
    private Timestamp update_time;

    /** 
    * operator - 操作人
    */
    private String operator;

    public String getCode_type(){
        return this.code_type;
    }
    public void setCode_type(String code_type){
        this.code_type = code_type;
    }
    public String getDomain_id(){
        return this.domain_id;
    }
    public void setDomain_id(String domain_id){
        this.domain_id = domain_id;
    }
    public String getCode_name(){
        return this.code_name;
    }
    public void setCode_name(String code_name){
        this.code_name = code_name;
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

	public CIP_admin_codesVO toVO(){
		CIP_admin_codesVO vo = new CIP_admin_codesVO();
		
		vo.setCode_type(code_type);
		vo.setDomain_id(domain_id);
		vo.setCode_name(code_name);
		
		return vo;
	}
		
}