package com.yd.ydbi.api.quality.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yd.ydbi.hp.service.Home_pageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.ydbi.api.quality.dao.YdbiQualityControlMapper;
import com.yd.ydbi.api.quality.model.YdbiQualityControl;
import com.yd.ydbi.api.quality.service.Ydbi_quality_controlService;

/**
 * <p>
 * Service实现类
 * </p>
 * <p>
 * Class: Ydbi_quality_controlServiceImpl
 * </p>
 *
 * @since 2017-07-12 14:47:00
 */

@Service
public class Ydbi_quality_controlServiceImpl implements Ydbi_quality_controlService {

	@Autowired
	YdbiQualityControlMapper ydbiQualityControlMapper;
	@Override
	public List<YdbiQualityControl> searchData(Map paramsMap) {
		List<YdbiQualityControl> businesstargetList = new ArrayList<YdbiQualityControl>();
		businesstargetList = ydbiQualityControlMapper.selectAll(paramsMap);
		return businesstargetList;
	}

}