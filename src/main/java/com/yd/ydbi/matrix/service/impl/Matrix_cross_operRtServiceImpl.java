package com.yd.ydbi.matrix.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.ydbi.matrix.dao.Matrix_cross_oper_rtMapper;
import com.yd.ydbi.matrix.model.Matrix_cross_oper_rt;
import com.yd.ydbi.matrix.service.Matrix_cross_operRtService;

@Service
public class Matrix_cross_operRtServiceImpl implements
	Matrix_cross_operRtService {
	
	@Autowired
	private Matrix_cross_oper_rtMapper Matrix_cross_oper_rtMapper;
	
	@Override
	public List<Matrix_cross_oper_rt> searchData(Map<String, Object> paramsMap) {
		return Matrix_cross_oper_rtMapper.searchData(paramsMap);
	}

	@Override
	public List<Matrix_cross_oper_rt> searchFooterData(
			Map<String, Object> paramsMap) {
		return Matrix_cross_oper_rtMapper.searchFooterData(paramsMap);
	}

}
