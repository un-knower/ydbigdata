package com.yd.ydbi.matrix.dao;

import java.util.List;
import java.util.Map;

import com.yd.ydbi.matrix.model.Matrix_cross_portId_rt;

public interface Matrix_cross_portId_rtMapper {
	List<Matrix_cross_portId_rt> searchData(Map<String, Object> paramsMap);
	List<Matrix_cross_portId_rt> searchSumData(Map<String, Object> paramsMap);

	List<Matrix_cross_portId_rt> searchFooterData(Map<String, Object> paramsMap);
}
