package com.yd.common.function.mdm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yd.common.data.CIPResponseMsg;
import com.yd.common.function.mdm.data.BigAreaData;
import com.yd.common.function.mdm.data.CityData;
import com.yd.common.function.mdm.data.CountyData;
import com.yd.common.function.mdm.data.FeeItemData;
import com.yd.common.function.mdm.data.OrgData;
import com.yd.common.function.mdm.data.ProvinceData;
import com.yd.common.function.mdm.data.YdServerGsData;
import com.yd.common.function.mdm.service.CIPMDMService;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntimeConfigure;

//公共模块，如获取省市数据等
@RestController
//@RequestMapping(value = "/public/mdm")
@RequestMapping(value = "/actions/mdm")
public class CIPMDMController {
	@Autowired
	private CIPMDMService cipmdmService;
//	@Autowired
//	private CIP_admin_user_role_settingService settingService;
	private Logger logger = Logger.getLogger(CIPMDMController.class);
	
	//获取大区
	@RequestMapping(value="/bigareas")
	public CIPResponseMsg getBigareas() {
		CIPResponseMsg msg = new CIPResponseMsg();
//		CIPReqCondition[] otherConds = settingService.getUserRoleConditions(CIPRuntime.getOperateSubject().getSubject_id(), CIPRuntime.getOperateSubject().getSubject_roles());
//		List<String> bigareas = new ArrayList<>();
//		if(otherConds!=null){
//			for(int i=0,length=otherConds.length; i<length; i++){
//				if("areaId".equals(otherConds[i].getFieldName())){
//					if(otherConds[i].getOperator()==CIPOperatorType.EQUAL.code){
//						bigareas.add(otherConds[i].getLowValue());
//					}else{
//						bigareas = Arrays.asList(otherConds[i].getValues());
//					}
//				}
//			}
//		}
		try{
			List<BigAreaData> datas = cipmdmService.getBigareas();
			msg.data = datas;
		}catch (RuntimeException e) {
			logger.error(e);
			msg.errorCode = CIPErrorCode.ERROR_TECHNICAL_ERROR.code;
			msg.msg = e.getMessage();
		}
		return msg;
	}
	/**从ydserver的公司表根据编码获得名称*/
	 
	@RequestMapping(value="/geGsDataByBM")
	public CIPResponseMsg getGsDataBy(HttpServletRequest request){
		CIPResponseMsg msg = new CIPResponseMsg();
		try{
			String bm = request.getParameter("bm");
			YdServerGsData data = cipmdmService.getGsDataBy(bm);
			msg.data = data;
		}catch (Exception e) {
			logger.error(e);
			msg.errorCode = CIPErrorCode.ERROR_TECHNICAL_ERROR.code;
			msg.msg = e.getMessage();
		}
		return msg;
	}
	
	//根据大区获取省
	@RequestMapping(value="/bigarea/provinces")
	public CIPResponseMsg getProvsByBigarea(HttpServletRequest request) {
		CIPResponseMsg msg = new CIPResponseMsg();
//		CIPReqCondition[] otherConds = settingService.getUserRoleConditions(CIPRuntime.getOperateSubject().getSubject_id(), CIPRuntime.getOperateSubject().getSubject_roles());
//		List<String> provinces = new ArrayList<>();
//		if(otherConds!=null){
//			for(int i=0,length=otherConds.length; i<length; i++){
//				if("provinceId".equals(otherConds[i].getFieldName())){
//					if(otherConds[i].getOperator()==CIPOperatorType.EQUAL.code){
//						provinces.add(otherConds[i].getLowValue());
//					}else{
//						provinces = Arrays.asList(otherConds[i].getValues());
//					}
//				}
//			}
//		}
		try{
			String bigarea = request.getParameter("bigareaId");
			List<ProvinceData> datas = cipmdmService.getProvinces(bigarea);
			msg.data = datas;
		}catch (Exception e) {
			logger.error(e);
			msg.errorCode = CIPErrorCode.ERROR_TECHNICAL_ERROR.code;
			msg.msg = e.getMessage();
		}
		return msg;
	}
	
	//获取省
	@RequestMapping(value="/provinces")
	public CIPResponseMsg getProvinces() {
		CIPResponseMsg msg = new CIPResponseMsg();
		try{
			List<ProvinceData> provinces = cipmdmService.getProvinces();
			msg.data = provinces;
		}catch (RuntimeException e) {
			logger.error(e);
			msg.errorCode = CIPErrorCode.ERROR_TECHNICAL_ERROR.code;
			msg.msg = e.getMessage();
		}
		return msg;
	}
	
	//获取省下辖市
	@RequestMapping(value="/cities")
	public CIPResponseMsg getCities(HttpServletRequest request) {
		CIPResponseMsg msg = new CIPResponseMsg();
		try{
			String provinceId = request.getParameter("provinceId");
			List<CityData> cities = cipmdmService.getCities(provinceId);
			msg.data = cities;
		}catch (RuntimeException e) {
			logger.error(e);
			msg.errorCode = CIPErrorCode.ERROR_TECHNICAL_ERROR.code;
			msg.msg = e.getMessage();
		}
		return msg;
	}
	
	//获取市下属区县
	@RequestMapping(value="/counties")
	public CIPResponseMsg getCounties(HttpServletRequest request) {
		CIPResponseMsg msg = new CIPResponseMsg();
		try{
			String cityId = request.getParameter("cityId");
			List<CountyData> counties = cipmdmService.getCounties(cityId);
			msg.data = counties;
		}catch (RuntimeException e) {
			logger.error(e);
			msg.errorCode = CIPErrorCode.ERROR_TECHNICAL_ERROR.code;
			msg.msg = e.getMessage();
		}
		return msg;
	}
	
	//获取指定城市的网点和服务部列表(省市公司三级联动)
	@RequestMapping(value="/orgs")
	public CIPResponseMsg getOrgs(HttpServletRequest request) {
		
		CIPResponseMsg msg = new CIPResponseMsg();
		try{
			String cityId = request.getParameter("cityId");
			List<OrgData> orgs = cipmdmService.getOrgs(cityId, CIPRuntimeConfigure.defaultOrgTypes);
			msg.data = orgs;
		}catch (RuntimeException e) {
			logger.error(e);
			msg.errorCode = CIPErrorCode.ERROR_TECHNICAL_ERROR.code;
			msg.msg = e.getMessage();
		}
		return msg;
	}
	
	//获取指定省机构列表(省市公司三级联动)
	@RequestMapping(value="/province/orgs")
	public CIPResponseMsg getOrgsByProv(HttpServletRequest request) {
		CIPResponseMsg msg = new CIPResponseMsg();
		try{
			String provinceId = request.getParameter("provinceId");
			List<OrgData> datas = cipmdmService.getOrgs4Prov(provinceId, CIPRuntimeConfigure.defaultOrgTypes);
			msg.data = datas;
		}catch (RuntimeException e) {
			logger.error(e);
			msg.errorCode = CIPErrorCode.ERROR_TECHNICAL_ERROR.code;
			msg.msg = e.getMessage();
		}
		return msg;
	}
	
	//获取指定市机构列表(省市公司三级联动)
	@RequestMapping(value="/city/orgs")
	public CIPResponseMsg getOrgsByCity(HttpServletRequest request) {
		CIPResponseMsg msg = new CIPResponseMsg();
		try{
			String cityId = request.getParameter("cityId");
			List<OrgData> datas = cipmdmService.getOrgs(cityId, CIPRuntimeConfigure.defaultOrgTypes);
			msg.data = datas;
		}catch (RuntimeException e) {
			logger.error(e);
			msg.errorCode = CIPErrorCode.ERROR_TECHNICAL_ERROR.code;
			msg.msg = e.getMessage();
		}
		return msg;
	}
	
	//获取所有收费部门
	@RequestMapping(value = "/get_fee_departs")
	public Object getAllDepartments(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		boolean xSuccess = true;
		String xMessage = "OK";
		try{
			String cityId = request.getParameter("cityId");
			List<String> cityIds = new ArrayList<String>();
			cityIds.add(cityId);
			
			//收费部门机构类型：分拨中心和公司管理机构
			List<Integer> orgTypes = new ArrayList<Integer>();
			orgTypes.add(3); //分拨中心
			orgTypes.add(50);//公司管理机构
			
			List<OrgData> departs = cipmdmService.getOrgs(orgTypes);
			resultMap.put("data", departs);
		}catch (RuntimeException e) {
			xSuccess = false;
			xMessage = e.getMessage();
		}
		resultMap.put("xSuccess", xSuccess);
		resultMap.put("xMessage", xMessage);
		return resultMap;
	}
	
	//获取所有收费项目
	@RequestMapping(value = "/get_fee_items")
	public Object getAllFeeItems(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		boolean xSuccess = true;
		String xMessage = "OK";
		try{
			List<FeeItemData> items = cipmdmService.getFeeItems();
			resultMap.put("data", items);
		}catch (RuntimeException e) {
			xSuccess = false;
			xMessage = e.getMessage();
		}
		resultMap.put("xSuccess", xSuccess);
		resultMap.put("xMessage", xMessage);
		return resultMap;
	}
	
}
