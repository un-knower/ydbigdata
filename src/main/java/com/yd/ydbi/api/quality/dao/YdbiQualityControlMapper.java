package com.yd.ydbi.api.quality.dao;

import java.util.List;
import java.util.Map;

import com.yd.ydbi.api.quality.model.YdbiQualityControl;

public interface YdbiQualityControlMapper {
	
	// 质控
	List<YdbiQualityControl> selectAll(Map paramsMap);
}