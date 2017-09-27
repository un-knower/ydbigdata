package com.yd.ydbi.matrix.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.ydbi.matrix.dao.Matrix_cross_portId_rtMapper;
import com.yd.ydbi.matrix.dao.Matrix_hei_portId_rtMapper;
import com.yd.ydbi.matrix.model.Matrix_hei_portId_rt;
import com.yd.ydbi.matrix.service.Matrix_hei_porRtService;

@Service
public class Matrix_hei_porRtServiceImpl implements Matrix_hei_porRtService {
	
	@Autowired
	private Matrix_hei_portId_rtMapper dataDao;
	
	@Override
	public List<Matrix_hei_portId_rt> searchData(Map<String, Object> paramsMap) {
		List<Matrix_hei_portId_rt>  datas = dataDao.searchData(paramsMap);
		List<Matrix_hei_portId_rt>  allCountryData = dataDao.searchSumData(paramsMap);
		if (datas.size() > 0) {
			datas.add(0,allCountryData.get(0));//全国数据
		}
		return datas;
	}

	@Override
	public List<Matrix_hei_portId_rt> searchFooterData(
			Map<String, Object> paramsMap) {
		return dataDao.searchFooterData(paramsMap);
	}

}
