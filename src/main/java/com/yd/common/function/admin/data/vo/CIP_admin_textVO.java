package com.yd.common.function.admin.data.vo;


import com.yd.common.function.admin.data.po.CIP_admin_textPO;

/**
 * <p>实体类</p>
 * <p>Table: cip_admin_text - 系统文本管理</p>
 *
 * @since 2015-08-23 08:09:11
 */
public class CIP_admin_textVO {

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

	public CIP_admin_textPO toPO(){
		CIP_admin_textPO po = new CIP_admin_textPO();
		
		po.setLang_type(lang_type);
		po.setMsg_no(msg_no);
		po.setMsg_txt(msg_txt);
		
		return po;
	}
		
}