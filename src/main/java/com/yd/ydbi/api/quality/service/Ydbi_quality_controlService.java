package com.yd.ydbi.api.quality.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yd.ydbi.api.quality.model.YdbiQualityControl;

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
public interface Ydbi_quality_controlService {

	public List<YdbiQualityControl> searchData(Map paramsMap);

}