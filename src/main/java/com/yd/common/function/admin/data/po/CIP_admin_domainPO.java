package com.yd.common.function.admin.data.po;

import java.sql.Timestamp;

import com.yd.common.function.admin.data.vo.CIP_admin_domainVO;

/**
 * <p>实体类</p>
 * <p>Table: cip_admin_domain - 数据域信息</p>
 *
 * @since 2015-07-27 02:40:51
 */
public class CIP_admin_domainPO {

	public Object[] getKeys(){
		return new Object[]{ 
			domain_id
		};
	}


    /** 
    * domain_id - 数据域id
    */
    private String domain_id;

    /** 
    * domain_name - 数据域名称
    */
    private String domain_name;

    /** 
    * domain_type - 数据域类型
    */
    private int domain_type;

    /** 
    * is_null_flag - 允许为空标识
    */
    private int is_null_flag;

    /** 
    * data_length - 数据长度
    */
    private int data_length;

    /** 
    * default_value - 默认值
    */
    private String default_value;

    /** 
    * float_length - 小数位长度
    */
    private int float_length;

    /** 
    * data_type - 数据类型
    */
    private String data_type;

    /** 
    * create_time - 创建时间
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

    /** 
    * resp_id - 责任人
    */
    private String resp_id;

    /** 
    * domain_desc - 数据域描述
    */
    private String domain_desc;

    /** 
    * ref_table_id - 参考引用数据表
    */
    private String ref_table_id;

    /** 
    * ref_domain_id - 引用数据域id
    */
    private String ref_domain_id;

    public String getDomain_id(){
        return this.domain_id;
    }
    public void setDomain_id(String domain_id){
        this.domain_id = domain_id;
    }
    public String getDomain_name(){
        return this.domain_name;
    }
    public void setDomain_name(String domain_name){
        this.domain_name = domain_name;
    }
    public int getDomain_type(){
        return this.domain_type;
    }
    public void setDomain_type(int domain_type){
        this.domain_type = domain_type;
    }
    public int getIs_null_flag(){
        return this.is_null_flag;
    }
    public void setIs_null_flag(int is_null_flag){
        this.is_null_flag = is_null_flag;
    }
    public int getData_length(){
        return this.data_length;
    }
    public void setData_length(int data_length){
        this.data_length = data_length;
    }
    public String getDefault_value(){
        return this.default_value;
    }
    public void setDefault_value(String default_value){
        this.default_value = default_value;
    }
    public int getFloat_length(){
        return this.float_length;
    }
    public void setFloat_length(int float_length){
        this.float_length = float_length;
    }
    public String getData_type(){
        return this.data_type;
    }
    public void setData_type(String data_type){
        this.data_type = data_type;
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
    public String getResp_id(){
        return this.resp_id;
    }
    public void setResp_id(String resp_id){
        this.resp_id = resp_id;
    }
    public String getDomain_desc(){
        return this.domain_desc;
    }
    public void setDomain_desc(String domain_desc){
        this.domain_desc = domain_desc;
    }
    public String getRef_table_id(){
        return this.ref_table_id;
    }
    public void setRef_table_id(String ref_table_id){
        this.ref_table_id = ref_table_id;
    }
    public String getRef_domain_id(){
        return this.ref_domain_id;
    }
    public void setRef_domain_id(String ref_domain_id){
        this.ref_domain_id = ref_domain_id;
    }

	public CIP_admin_domainVO toVO(){
		CIP_admin_domainVO vo = new CIP_admin_domainVO();
		
		vo.setDomain_id(domain_id);
		vo.setDomain_name(domain_name);
		vo.setDomain_type(domain_type);
		vo.setIs_null_flag(is_null_flag);
		vo.setData_length(data_length);
		vo.setDefault_value(default_value);
		vo.setFloat_length(float_length);
		vo.setData_type(data_type);
		vo.setResp_id(resp_id);
		vo.setDomain_desc(domain_desc);
		vo.setRef_table_id(ref_table_id);
		vo.setRef_domain_id(ref_domain_id);
		
		return vo;
	}
		
}