package com.yd.ydbi.matrix.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.ydbi.matrix.dao.Matrix_cross_ship_dtlMapper;
import com.yd.ydbi.matrix.model.Matrix_cross_ship_dtl;
import com.yd.ydbi.matrix.service.Matrix_cross_ship_dtlService;

@Service
public class Matrix_cross_ship_dtlServiceImpl implements
	Matrix_cross_ship_dtlService {
	
	@Autowired
	private Matrix_cross_ship_dtlMapper Matrix_cross_ship_dtlMapper;
	
	@Override
	public List<Matrix_cross_ship_dtl> searchData(Map<String, Object> paramsMap) {
		return Matrix_cross_ship_dtlMapper.searchData(paramsMap);
	}

	@Override
	public List<Matrix_cross_ship_dtl> searchFooterData(
			Map<String, Object> paramsMap) {
		return Matrix_cross_ship_dtlMapper.searchFooterData(paramsMap);
	}

}
