package com.yd.common.function.admin.data.po;

import java.sql.Timestamp;

import com.yd.common.function.admin.data.vo.CIP_admin_textVO;

/**
 * <p>实体类</p>
 * <p>Table: cip_admin_text - 系统文本管理</p>
 *
 * @since 2015-08-23 08:09:11
 */
public class CIP_admin_textPO {

	public Object[] getKeys(){
		return new Object[]{ 
			lang_type,
					msg_no
		};
	}


    /** 
    * lang_type - 语言类型
    */
    private String lang_type;

    /** 
    * msg_no - 文本id
    */
    private int msg_no;

    /** 
    * msg_txt - 文本内容
    */
    private String msg_txt;

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

    public String getLang_type(){
        return this.lang_type;
    }
    public void setLang_type(String lang_type){
        this.lang_type = lang_type;
    }
    public int getMsg_no(){
        return this.msg_no;
    }
    public void setMsg_no(int msg_no){
        this.msg_no = msg_no;
    }
    public String getMsg_txt(){
        return this.msg_txt;
    }
    public void setMsg_txt(String msg_txt){
        this.msg_txt = msg_txt;
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

	public CIP_admin_textVO toVO(){
		CIP_admin_textVO vo = new CIP_admin_textVO();
		
		vo.setLang_type(lang_type);
		vo.setMsg_no(msg_no);
		vo.setMsg_txt(msg_txt);
		
		return vo;
	}
		
}