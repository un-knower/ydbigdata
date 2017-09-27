package com.yd.common.function.domain.data;

import java.util.List;

import com.yd.common.function.admin.data.CIP_admin_codesData;
/**
* ClassName: CIPAdmincCodesData
* Function: 封装CIP_admin_codesData 和  ref_table_id数据的实体类.
* Reason: TODO ADD REASON(可选).
* date: 2016-7-19 下午3:17:14
*
* @author jh
* @version 
* @since JDK 1.7
*/
public class CIPAdminCodesData {
	/**
	 * datas - 数据编码信息
	 */
	private List<CIP_admin_codesData> datas;
	/**
	 * ref_table_id - 参考引用数据表
	 */
	private String ref_table_id;
	
	public List<CIP_admin_codesData> getDatas() {
		return datas;
	}

	public void setDatas(List<CIP_admin_codesData> datas) {
		this.datas = datas;
	}

	public String getRef_table_id() {
		return ref_table_id;
	}

	public void setRef_table_id(String ref_table_id) {
		this.ref_table_id = ref_table_id;
	}
	

}