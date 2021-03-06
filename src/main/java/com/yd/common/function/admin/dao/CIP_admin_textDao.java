package com.yd.common.function.admin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yd.common.dao.IDao;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.admin.data.CIP_admin_textData;
import com.yd.common.function.admin.data.po.CIP_admin_textPO;

/**
 * <p>Dao类</p>
 * <p>Class: CIP_admin_text - 系统文本管理</p>
 *
 * @since 2015-08-18 02:49:22
 */
 
@Repository
public interface CIP_admin_textDao extends IDao<CIP_admin_textPO> {
	
	public List<CIP_admin_textData> searchData(CIPPageInfo page, CIPReqCondition[] conditions);
	
	public List<Map<String, Object>> exportEntities(CIPPageInfo page, CIPReqCondition[] conditions, boolean xFirst);
}
