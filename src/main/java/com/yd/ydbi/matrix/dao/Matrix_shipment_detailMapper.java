package com.yd.ydbi.matrix.dao;

import java.util.List;
import java.util.Map;

import com.yd.ydbi.matrix.model.Matrix_shipment_detail;

public interface Matrix_shipment_detailMapper {
	List<Matrix_shipment_detail> searchData(Map<String, Object> paramsMap);

	List<Matrix_shipment_detail> searchFooterData(Map<String, Object> paramsMap);
}
