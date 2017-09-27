package com.yd.ydbi.matrix.dao;

import java.util.List;
import java.util.Map;

import com.yd.ydbi.matrix.model.Matrix_cross_comboxBean;
import com.yd.ydbi.matrix.model.Matrix_cross_ship_dtl;

public interface Matrix_cross_ship_dtlMapper {
	List<Matrix_cross_ship_dtl> searchData(Map<String, Object> paramsMap);

	List<Matrix_cross_ship_dtl> searchFooterData(Map<String, Object> paramsMap);
	
	List<Matrix_cross_comboxBean> getShipTypName();
}
