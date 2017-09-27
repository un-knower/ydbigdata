package com.yd.ydbi.matrix.service;

import java.util.List;
import java.util.Map;

import com.yd.ydbi.matrix.model.Matrix_cross_portId_rt;

public interface Matrix_cross_porRtService {
	public List<Matrix_cross_portId_rt> searchData(Map<String, Object> paramsMap);

	
	public List<Matrix_cross_portId_rt> searchFooterData(Map<String, Object> paramsMap);
}
