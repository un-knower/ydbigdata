package com.yd.ydbi.hp.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yd.ydbi.hp.model.HomePage;
import com.yd.ydbi.hp.model.PerCapitaEffectFoot;
/**
 * 
 *首页服务接口 
 * 
 */
import com.yd.ydbi.hp.model.PerCapitaEffectHeader;
import com.yd.ydbi.hp.model.PerCaptaEffectTrend;

import javax.print.DocFlavor;

@Service
public interface Home_pageService {
	/**
	 * 主页查询
	 * @param paramsMap
	 * @return
	 */
	public HomePage searchData(Map<String, String> paramsMap);
	
	/**
	 * 人均效能头
	 * @param paramsMap
	 * @return
	 */
	public PerCapitaEffectHeader getPCEHeader(Map<String, String> paramsMap);
	
	/**
	 * 人均效能趋势
	 * @param paramsMap
	 * @return
	 */
	public List<PerCaptaEffectTrend> getPCETrend(Map<String, String> paramsMap);
	
	/**
	 * 人均效能脚
	 * @param paramsMap
	 * @return
	 */
	public PerCapitaEffectFoot getPCEFoot(Map<String, String> paramsMap);
	
	public List<String> PCN2(Map<String, String> params);
}
