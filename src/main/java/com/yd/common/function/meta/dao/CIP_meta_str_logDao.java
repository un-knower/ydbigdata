package com.yd.common.function.meta.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yd.common.dao.IDao;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.meta.data.CIP_meta_str_logData;
import com.yd.common.function.meta.data.po.CIP_meta_str_logPO;

/**
 * <p>Dao类</p>
 * <p>Class: CIP_meta_str_log - 元数据结构变更</p>
 *
 * @since 2015-10-08 09:03:37
 */
 
@Repository
public interface CIP_meta_str_logDao extends IDao<CIP_meta_str_logPO> {
	
	public List<CIP_meta_str_logData> searchData(CIPPageInfo page, CIPReqCondition[] conditions);
	
	
	public List<Map<String, Object>> exportEntities(CIPPageInfo page, CIPReqCondition[] conditions, boolean xFirst);

	void deleteByStrId(String str_id);
}
