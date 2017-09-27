package com.yd.ydbi.api.quality.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yd.ydbi.api.quality.model.YdbiTrendChart;

/**
 * <p>
 * Service类
 * </p>
 * <p>
 * Class:  Ydbi_quality_controlService
 * </p>
 *
 * @since 2017-07-12 14:21:00
 */

@Service
public interface Ydbi_trend_chartService {

	public List<YdbiTrendChart> searchData(Map paramsMap);

}