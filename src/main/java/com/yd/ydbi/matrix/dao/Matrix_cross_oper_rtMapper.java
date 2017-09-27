package com.yd.ydbi.matrix.dao;

import java.util.List;
import java.util.Map;

import com.yd.ydbi.matrix.model.Matrix_cross_oper_rt;

public interface Matrix_cross_oper_rtMapper {
	List<Matrix_cross_oper_rt> searchData(Map<String, Object> paramsMap);

	List<Matrix_cross_oper_rt> searchFooterData(Map<String, Object> paramsMap);
}
