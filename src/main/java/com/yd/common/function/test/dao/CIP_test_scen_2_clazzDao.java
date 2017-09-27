package com.yd.common.function.test.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yd.common.dao.IDao;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.test.data.CIP_test_scen_2_clazzData;
import com.yd.common.function.test.data.po.CIP_test_scen_2_clazzPO;

/**
 * <p>Dao类</p>
 * <p>Class: CIP_test_scen_2_clazz - 测试类与场景关系</p>
 *
 * @since 2015-10-10 02:52:52
 */
 
@Repository
public interface CIP_test_scen_2_clazzDao extends IDao<CIP_test_scen_2_clazzPO> {
	
	public List<CIP_test_scen_2_clazzData> searchData(CIPPageInfo page, CIPReqCondition[] conditions);
	
	public List<Map<String, Object>> exportEntities(CIPPageInfo page, CIPReqCondition[] conditions, boolean xFirst);
}
