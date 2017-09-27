package com.yd.common.function.test.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.yd.common.exception.CIPServiceException;
import com.yd.common.function.test.dao.CIP_test_caseDao;
import com.yd.common.function.test.dao.CIP_test_clazz_medDao;
import com.yd.common.function.test.dao.CIP_test_resultDao;
import com.yd.common.function.test.dao.CIP_test_scenarioDao;
import com.yd.common.function.test.data.CIPContextData;
import com.yd.common.function.test.data.po.CIP_test_casePO;
import com.yd.common.function.test.data.po.CIP_test_clazz_medPO;
import com.yd.common.function.test.data.po.CIP_test_resultPO;
import com.yd.common.function.test.data.po.CIP_test_scenarioPO;
import com.yd.common.function.test.data.vo.CIP_test_resultVO;
import com.yd.common.function.test.service.CIP_test_getAllService;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.utils.JSONUtils;
@Service(value="CIP_test_getAllService")
public class CIP_test_getAllServiceImpl implements CIP_test_getAllService {

	@Autowired
	private CIP_test_caseDao caseDao = null;
	@Autowired
	private CIP_test_scenarioDao scenarioDao = null;
	@Autowired
	private CIP_test_clazz_medDao clazz_medDao = null;
	@Autowired
	private CIP_test_resultDao resultDao = null;
	
	
	@Override
	public String getTestContext(String sysid) {
		List<CIP_test_scenarioPO> scenarioPOs = null;
		List<CIP_test_clazz_medPO> clazz_medPOs = new ArrayList<CIP_test_clazz_medPO>();;
		Set<String> controllerSet = new HashSet<String>();
		List<CIP_test_casePO> casePOs = new ArrayList<CIP_test_casePO>();
		
		if((sysid == null)||sysid.equals(""))
			throw new CIPServiceException(new CIPErrorCode(-1,"系统ID为空"));
		//找出该系统的所有场景
		Map<String, Object> scenarioCondation = new HashMap<String, Object>(1);
		scenarioCondation.put("test_sys_id", sysid);
		scenarioPOs = scenarioDao.getByCondition(scenarioCondation);
		if(scenarioPOs.isEmpty())
			throw new CIPServiceException(new CIPErrorCode(-1,"没有检测到场景"));	
		//获取所有场景下的用例
		for(CIP_test_scenarioPO PO:scenarioPOs)
		{
			Map<String, Object> caseCondition = new HashMap<String, Object>(1);
			caseCondition.put("scenario_id", PO.getScenario_id());
			List<CIP_test_casePO> casePO = caseDao.getByCondition(caseCondition);	
			casePOs.addAll(casePOs.size(),casePO);
		}
		//根据用例获得行为
		Set<String> actionids = new HashSet<String>();
		for(CIP_test_casePO casePO:casePOs)
		{						
			actionids.add(casePO.getAction_id());				
		}
		Iterator<String> iterator = actionids.iterator();
		while (iterator.hasNext()) {
			String actionid = iterator.next();
			CIP_test_clazz_medPO clazz_medPO = clazz_medDao.getSingle(actionid);
			clazz_medPOs.add(clazz_medPO);				
		}
		//用例排序
		Collections.sort(casePOs,new Comparator<CIP_test_casePO>() {
			 public int compare(CIP_test_casePO arg0, CIP_test_casePO arg1) {
	                return arg0.getCreate_time().compareTo(arg1.getCreate_time());
	            }
		});
		
	
		//获取controller
		for(CIP_test_clazz_medPO medPO:clazz_medPOs){
			controllerSet.add(medPO.getTest_clazz());
		}
		
		
		
		CIPContextData cipContext = new CIPContextData();
		cipContext.scenarios =JSON.toJSONString(scenarioPOs, false);
		cipContext.cases = JSON.toJSONString(casePOs, false);
		cipContext.actions = JSON.toJSONString(clazz_medPOs, false);
		cipContext.controllers = JSON.toJSONString(controllerSet.toArray(), false);
		
		return JSON.toJSONString(cipContext,false);
	}
	

@Override
public String saveTestResult(String result) {
	List<CIP_test_resultPO> resultPOs = new ArrayList<CIP_test_resultPO>();
	Map<String, JSONArray> testResult = null;
	List<Object[]> resultkeys = new ArrayList<Object[]>();
	Object re = JSONUtils.convertDataJson2Object(result, Object.class);
	testResult = (Map<String, JSONArray>) re;
	Set<String> scenarioIds = testResult.keySet();
	JSONArray rs = null;
	Map<String, Object> resultCondation = new HashMap<String, Object>();
	for(String scenarioId:scenarioIds){
		resultCondation.put("scenario_id", scenarioId);
		List<CIP_test_resultPO> pos = resultDao.getByCondition(resultCondation);
		for(CIP_test_resultPO resultPO :pos){
			resultkeys.add(resultPO.getKeys());
		}
		 rs = testResult.get(scenarioId);
		 for(int i=0 ;i<rs.size();i++){
			 CIP_test_resultVO vo = JSONUtils.convertDataJson2Object(rs.getString(i), CIP_test_resultVO.class);
			 resultPOs.add(vo.toPO());
		 }
	}
	resultDao.batchDelete(resultkeys);
	resultDao.batchAdd(resultPOs);
	return "{\"errorCode\":\"0\",\"msg\":\"操作成功\"}";
}
 
}
