package com.yd.common.function.admin.data.key;


/**
 * <p>实体key类</p>
 * <p>Table: cip_admin_text - 系统文本管理</p>
 *
 * @since 2015-08-23 08:09:11
 */
public class CIP_admin_textKey {	
	
	public Object[] getKeys(){
		return new Object[]{ 
		lang_type,
				msg_no
		};
	}
	
	public Object[] setKeys(String lang_type,int msg_no){
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

	private String remark;
	
	private String operateCode;
	
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