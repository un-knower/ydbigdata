package com.yd.ydbi.cainiao.dao;

import java.util.List;
import java.util.Map;

import com.yd.ydbi.cainiao.model.CaiNiaoDxl;
import com.yd.ydbi.cainiao.model.CaiNiaoWeek;
import com.yd.ydbi.cainiao.model.LineName;
import com.yd.ydbi.common.function.model.ComboxBean;

public interface CaiNiaoDxlMapper {
	/*线路日明细报表*/
	List<CaiNiaoDxl> searchDxlData(Map<String,Object> paramsMap);
	List<CaiNiaoDxl> searchFooterData(Map<String, Object> paramsMap);
	
	/*线路周报表*/
	List<CaiNiaoWeek> searchWeekData(Map<String,Object> paramsMap);
	List<CaiNiaoWeek> searchWeekFooterData(Map<String, Object> paramsMap);
	List<CaiNiaoWeek> searchSumWeekData(Map<String,Object> paramsMap);
	
	/*批量导入数据*/
	void insertData(List<CaiNiaoDxl> lists);
	
	/*批量删除*/
	int deleteData(List<String> list);
	List<ComboxBean> getLineRegion(Map<String, String> paramsMap);
}
