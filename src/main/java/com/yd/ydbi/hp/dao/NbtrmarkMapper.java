package com.yd.ydbi.hp.dao;

import java.util.Map;

import com.yd.ydbi.hp.model.NewbeeMark;

/**
 * 菜鸟评分
 * @author Administrator
 *
 */
public interface NbtrmarkMapper {
	/**
	 * 根据日期查菜鸟评分
	 * @param paramsMap
	 * @return
	 */
	public NewbeeMark getNbm(Map<String,String> paramsMap);
}
