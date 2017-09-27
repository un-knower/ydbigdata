package com.yd.common.function.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yd.common.dao.IDao;
import com.yd.common.data.CIPPageInfo;


/**
 * ClassName:CIPCommonQueryDao
 * Function: TODO ADD FUNCTION.
 * Reason: TODO ADD REASON.
 * Date: 2016年8月4日 下午1:04:28
 * @author lyn
 * @version
 * @since JDK 1.7
 * @see
 */
@Repository
public interface CIPCommonQueryDao extends IDao<Map<String,Object>>{
	
	public List<Map<String,Object>> doQuery(String querySql,String countSql,Map<String, String> paramMap,CIPPageInfo page);
	
	public int doQueryCount(String countSql,Map<String, String> paramMap);
}
