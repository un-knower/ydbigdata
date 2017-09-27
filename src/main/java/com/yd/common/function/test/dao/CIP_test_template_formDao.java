package com.yd.common.function.test.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yd.common.dao.IDao;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.test.data.CIP_test_template_formData;
import com.yd.common.function.test.data.po.CIP_test_template_formPO;

/**
 * <p>Dao类</p>
 * <p>Class: CIP_test_template_form - 测试数据模板字段</p>
 *
 * @since 2015-11-24 04:16:55
 */
 
@Repository
public interface CIP_test_template_formDao extends IDao<CIP_test_template_formPO> {
	
	public List<CIP_test_template_formData> searchData(CIPPageInfo page, CIPReqCondition[] conditions);
	
	public List<Map<String, Object>> exportEntities(CIPPageInfo page, CIPReqCondition[] conditions, boolean xFirst);
}
