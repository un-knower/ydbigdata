package com.yd.common.function.admin.data;


/**
* ClassName: CIP_admin_run_logData
* Function: 系统运行日志数据对象.
* Reason: TODO ADD REASON(可选).
* date: 2016-7-7 下午12:53:41
*
* @author jh
* @version 
* @since JDK 1.7
*/
public class CIP_admin_run_logData {

    /** 
    * file_obj - 文件对象
    */
	private String file_obj;

    /** 
    * file_name - 日志文件名称
    */
	private String file_name;
    
	public String getFile_obj() {
		return file_obj;
	}

	public void setFile_obj(String file_obj) {
		this.file_obj = file_obj;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}	
    
}