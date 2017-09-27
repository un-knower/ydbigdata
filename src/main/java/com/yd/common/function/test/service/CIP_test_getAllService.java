package com.yd.common.function.test.service;

import org.springframework.stereotype.Service;

@Service
public interface CIP_test_getAllService {

	public String getTestContext(String sysid);
	
	public String saveTestResult(String result);
}
