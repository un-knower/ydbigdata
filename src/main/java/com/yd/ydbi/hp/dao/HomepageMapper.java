package com.yd.ydbi.hp.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.yd.ydbi.common.response.CustomReqParameter;
import com.yd.ydbi.common.response.CustomResponseMsg;
import com.yd.ydbi.hp.model.HomePage;
import com.yd.ydbi.hp.model.HomePage2zj;
import com.yd.ydbi.hp.model.PerCapitaEffectFoot;
import com.yd.ydbi.hp.model.PerCapitalEffectTopN;
/**
 * 
 * 首页Mapper
 *
 */
public interface HomepageMapper {
		//查全国某天首页
		List<HomePage2zj> homePageCountry(Map<String, String> paramsMap);
		//查分拨中心某天首页
		HomePage homePageFB(Map<String,String> paramsMap);
		
		/**
		 * 获取人均件量头部基础信息
		 * @param paramsMap
		 * @return
		 */
		List<Map<String,String>> getPCEHeader(Map<String, String> paramsMap);
		
		/**
		 * 获取人均件量全国基础信息
		 * @param paramsMap
		 * @return
		 */
		List<Map<String,String>> getPCETrend(Map<String, String> paramsMap);
		
		/**
		 * 人均效能top
		 * @param parameter
		 * @return
		 */
		List<PerCapitalEffectTopN> getPCEFoot(Map<String, String> paramsMap);
		
		
		/**
		 * 计算某天的分拨对应人均量
		 * @param paramsMap
		 * @return
		 */
		List<PerCapitalEffectTopN> getPCETop(Map<String, String> paramsMap);
}
