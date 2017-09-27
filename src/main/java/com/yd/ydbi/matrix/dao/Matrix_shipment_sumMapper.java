package com.yd.ydbi.matrix.dao;

import java.util.List;
import java.util.Map;

import com.yd.ydbi.matrix.model.Matrix_shipment_sum;



public interface Matrix_shipment_sumMapper {
	List<Matrix_shipment_sum> searchData(Map<String, Object> paramsMap);

	List<Matrix_shipment_sum> searchFooterData(Map<String, Object> paramsMap);
}
