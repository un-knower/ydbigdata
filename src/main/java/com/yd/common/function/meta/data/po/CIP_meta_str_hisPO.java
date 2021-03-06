package com.yd.common.function.meta.data.po;

import java.sql.Date;
import java.sql.Timestamp;

import com.yd.common.function.meta.data.vo.CIP_meta_str_hisVO;
import com.yd.common.utils.DateUtils;

/**
 * <p>实体类</p>
 * <p>Table: cip_meta_str_his - 系统数据历史结构</p>
 *
 * @since 2015-10-08 09:03:37
 */
public class CIP_meta_str_hisPO {

	public Object[] getKeys(){
		return new Object[]{ 
			str_id,
					version_date
		};
	}


    /** 
    * str_id - 数据结构id
    */
    private String str_id;

    /** 
    * version_date - 定稿日期
    */
    private Date version_date;

    /** 
    * str_name - 数据结构名称
    */
    private String str_name;

    /** 
    * str_memo - 数据结构备注
    */
    private String str_memo;

    /** 
    * str_type - 数据结构类型
    */
    private String str_type;

    /** 
    * str_prefix - 结构前缀
    */
    private String str_prefix;

    /** 
    * str_module - 结构模块id
    */
    private String str_module;

    /** 
    * func_set_id - 功能集id
    */
    private String func_set_id;

    /** 
    * ref_str_id - 参考结构id
    */
    private String ref_str_id;

    /** 
    * str_data_type - 存储数据类型
    */
    private String str_data_type;

    /** 
    * prd_date - 落地时间
    */
    private Date prd_date;

    /** 
    * form_template - 表单模板
    */
    private String form_template;

    /** 
    * list_template - 列表模板
    */
    private String list_template;

    /** 
    * src_sys_id - 源系统id
    */
    private String src_sys_id;

    /** 
    * resp_id - 责任人
    */
    private String resp_id;

    /** 
    * resp_org - 责任部门id
    */
    private String resp_org;

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

    public String getStr_id(){
        return this.str_id;
    }
    public void setStr_id(String str_id){
        this.str_id = str_id;
    }
    public Date getVersion_date(){
        return this.version_date;
    }
    public void setVersion_date(Date version_date){
        this.version_date = version_date;
    }
    public String getStr_name(){
        return this.str_name;
    }
    public void setStr_name(String str_name){
        this.str_name = str_name;
    }
    public String getStr_memo(){
        return this.str_memo;
    }
    public void setStr_memo(String str_memo){
        this.str_memo = str_memo;
    }
    public String getStr_type(){
        return this.str_type;
    }
    public void setStr_type(String str_type){
        this.str_type = str_type;
    }
    public String getStr_prefix(){
        return this.str_prefix;
    }
    public void setStr_prefix(String str_prefix){
        this.str_prefix = str_prefix;
    }
    public String getStr_module(){
        return this.str_module;
    }
    public void setStr_module(String str_module){
        this.str_module = str_module;
    }
    public String getFunc_set_id(){
        return this.func_set_id;
    }
    public void setFunc_set_id(String func_set_id){
        this.func_set_id = func_set_id;
    }
    public String getRef_str_id(){
        return this.ref_str_id;
    }
    public void setRef_str_id(String ref_str_id){
        this.ref_str_id = ref_str_id;
    }
    public String getStr_data_type(){
        return this.str_data_type;
    }
    public void setStr_data_type(String str_data_type){
        this.str_data_type = str_data_type;
    }
    public Date getPrd_date(){
        return this.prd_date;
    }
    public void setPrd_date(Date prd_date){
        this.prd_date = prd_date;
    }
    public String getForm_template(){
        return this.form_template;
    }
    public void setForm_template(String form_template){
        this.form_template = form_template;
    }
    public String getList_template(){
        return this.list_template;
    }
    public void setList_template(String list_template){
        this.list_template = list_template;
    }
    public String getSrc_sys_id(){
        return this.src_sys_id;
    }
    public void setSrc_sys_id(String src_sys_id){
        this.src_sys_id = src_sys_id;
    }
    public String getResp_id(){
        return this.resp_id;
    }
    public void setResp_id(String resp_id){
        this.resp_id = resp_id;
    }
    public String getResp_org(){
        return this.resp_org;
    }
    public void setResp_org(String resp_org){
        this.resp_org = resp_org;
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

	public CIP_meta_str_hisVO toVO(){
		CIP_meta_str_hisVO vo = new CIP_meta_str_hisVO();
		
		vo.setStr_id(str_id);
		vo.setVersion_date(DateUtils.getDate(version_date));
		vo.setStr_name(str_name);
		vo.setStr_memo(str_memo);
		vo.setStr_type(str_type);
		vo.setStr_prefix(str_prefix);
		vo.setStr_module(str_module);
		vo.setFunc_set_id(func_set_id);
		vo.setRef_str_id(ref_str_id);
		vo.setStr_data_type(str_data_type);
		vo.setPrd_date(DateUtils.getDate(prd_date));
		vo.setForm_template(form_template);
		vo.setList_template(list_template);
		vo.setSrc_sys_id(src_sys_id);
		vo.setResp_id(resp_id);
		vo.setResp_org(resp_org);
		
		return vo;
	}
		
}