package com.yd.ydbi.matrix.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.ydbi.matrix.dao.Matrix_shipment_detailMapper;
import com.yd.ydbi.matrix.model.Matrix_shipment_detail;
import com.yd.ydbi.matrix.service.Matrix_shipment_detailService;

@Service
public class Matrix_shipment_detailServiceImpl implements
		Matrix_shipment_detailService {
	
	@Autowired
	private Matrix_shipment_detailMapper matrix_shipment_detailMapper;
	
	@Override
	public List<Matrix_shipment_detail> searchData(Map<String, Object> paramsMap) {
		return matrix_shipment_detailMapper.searchData(paramsMap);
	}

	@Override
	public List<Matrix_shipment_detail> searchFooterData(
			Map<String, Object> paramsMap) {
		return matrix_shipment_detailMapper.searchFooterData(paramsMap);
	}

}
