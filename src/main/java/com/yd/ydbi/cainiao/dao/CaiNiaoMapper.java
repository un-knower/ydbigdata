package com.yd.ydbi.cainiao.dao;

import java.util.List;
import java.util.Map;

import com.yd.ydbi.cainiao.model.CaiNiao;
import com.yd.ydbi.cainiao.model.CaiNiaoDxl;

public interface CaiNiaoMapper {
	
	/*查询*/
	List<CaiNiao> searchData(Map<String,  Object> paramsMap);
	List<CaiNiao> searchFooterData(Map<String, Object> paramsMap);
	/*根据日期查询*/
	CaiNiao getCaiNiaoBystatsDt(Map<String,  Object> paramsMap);
	
	/*新增*/
	void insertCn(Map<String,  Object> paramsMap);
	
	int updateData(Map<String, Object> paramsMap);
	int deleteData(List<String> list);
	
	
}
