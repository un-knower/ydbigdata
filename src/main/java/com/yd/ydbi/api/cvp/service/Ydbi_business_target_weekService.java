package com.yd.ydbi.api.cvp.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yd.ydbi.api.cvp.model.YdbiBusinessTarget;
import com.yd.ydbi.api.cvp.model.YdbiBusinessTargetWeek;

/**
 * <p>
 * Service类
 * </p>
 * <p>
 * Class:  Ydbi_business_targetService
 * </p>
 *
 * @since 2017-07-12 14:21:00
 */

@Service
public interface Ydbi_business_target_weekService {

	public List<YdbiBusinessTargetWeek> searchData(Map paramsMap);

}