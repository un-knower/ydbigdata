package com.yd.common.function.test.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.test.data.CIP_test_clazzData;
import com.yd.common.function.test.data.vo.CIP_test_clazzVO;
import com.yd.common.runtime.CIPRuntimeOperator;

/**
 * <p>Service类</p>
 * <p>Class: CIP_test_clazzService</p>
 *
 * @since 2015-10-10 02:52:52
 */

@Service
public interface CIP_test_clazzService {

	public void updateData(CIP_test_clazzVO d, CIPRuntimeOperator operateInf);
	
	public void addData(CIP_test_clazzVO d, CIPRuntimeOperator operateInf);
	
	public void deleteData(Object[] keys, CIPRuntimeOperator operateInf);
	
	public CIP_test_clazzVO getData(Object[] keys);
	
	public List<CIP_test_clazzData> searchData(CIPPageInfo page, CIPReqCondition[] conditions);


	/** 
	 * 导出系统测试类
	 */
	public List<Map<String, Object>> exportEntities(CIPPageInfo page,CIPReqCondition[] conditions,
			CIPRuntimeOperator operateInf, boolean xFirst);
	
}