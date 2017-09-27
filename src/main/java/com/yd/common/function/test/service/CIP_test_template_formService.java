package com.yd.common.function.test.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.test.data.CIP_test_template_formData;
import com.yd.common.function.test.data.vo.CIP_test_template_formVO;
import com.yd.common.runtime.CIPRuntimeOperator;

/**
 * <p>Service类</p>
 * <p>Class: CIP_test_template_formService</p>
 *
 * @since 2015-11-24 04:16:55
 */

@Service
public interface CIP_test_template_formService {

	public void updateData(CIP_test_template_formVO d, CIPRuntimeOperator operateInf);
	
	public void addData(CIP_test_template_formVO d, CIPRuntimeOperator operateInf);
	
	public void deleteData(Object[] keys, CIPRuntimeOperator operateInf);
	
	public CIP_test_template_formVO getData(Object[] keys);
	
	public List<CIP_test_template_formData> searchData(CIPPageInfo page, CIPReqCondition[] conditions);
	
	public List<CIP_test_template_formVO> searchTemplateData(String templateID);


	/** 
	 * 导出测试数据模板字段
	 */
	public List<Map<String, Object>> exportEntities(CIPPageInfo page,CIPReqCondition[] conditions,
			CIPRuntimeOperator operateInf, boolean xFirst);
	
}