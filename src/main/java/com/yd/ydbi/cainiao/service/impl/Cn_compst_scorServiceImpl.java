package com.yd.ydbi.cainiao.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.common.runtime.CIPRuntime;
import com.yd.ydbi.cainiao.dao.Cn_compst_scorMapper;
import com.yd.ydbi.cainiao.model.Cn_compst_scor;
import com.yd.ydbi.cainiao.service.Cn_compst_scorService;

/**
 * 
 * 菜鸟信息维护业务处理实现类. <br/>
 *
 * @date:2017-08-07 10:09:29 <br/>
 * @author 康元佳
 * @version:
 * @since:JDK 1.7
 */
@Service
public class Cn_compst_scorServiceImpl implements Cn_compst_scorService {

	@Autowired
	private Cn_compst_scorMapper cn_compst_scorMapper;

	@Override
	public List<Cn_compst_scor> searchData(Map<String, Object> paramsMap) {
		return cn_compst_scorMapper.searchData(paramsMap);
	}

	@Override
	public List<Cn_compst_scor> searchFooterData(Map<String, Object> paramsMap) {
		return cn_compst_scorMapper.searchFooterData(paramsMap);
	}

	@Override
	public int addData(Map<String, Object> paramsMap) {
		return cn_compst_scorMapper.addData(paramsMap);
	}

	@Override
	public int updateData(Map<String, Object> paramsMap) {
		return cn_compst_scorMapper.updateData(paramsMap);
	}

}