package com.yd.common.function.admin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yd.common.dao.IDao;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.admin.data.CIP_admin_questionData;
import com.yd.common.function.admin.data.po.CIP_admin_questionPO;

/**
 * <p>Dao类</p>
 * <p>Class: CIP_admin_question - 系统问题反馈</p>
 *
 * @since 2015-12-25 01:45:43
 */
 
@Repository
public interface CIP_admin_questionDao extends IDao<CIP_admin_questionPO> {
	
	public List<CIP_admin_questionData> searchData(CIPPageInfo page, CIPReqCondition[] conditions);
	
	public List<Map<String, Object>> exportEntities(CIPPageInfo page, CIPReqCondition[] conditions, boolean xFirst);
	
	public int getAllDealThingsCount();
	
	public int getDealThingCount(String user_id);
}
