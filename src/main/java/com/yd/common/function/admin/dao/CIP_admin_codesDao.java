package com.yd.common.function.admin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yd.common.dao.IDao;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.admin.data.CIP_admin_codesData;
import com.yd.common.function.admin.data.po.CIP_admin_codesPO;

/**
 * <p>Dao类</p>
 * <p>Class: CIP_admin_codes - 数据编码</p>
 *
 * @since 2015-07-27 02:40:51
 */
 
@Repository
public interface CIP_admin_codesDao extends IDao<CIP_admin_codesPO> {
	
	public List<CIP_admin_codesData> searchData(CIPPageInfo page, CIPReqCondition[] conditions);
	
	public List<Map<String, Object>> exportEntities(CIPPageInfo page, CIPReqCondition[] conditions, boolean xFirst);
	
	/**
	* getData:通过数据域id 查询到所有的编码信息.
	*
	* @author jh
	* @param id 数据域id
	* @return
	* @since JDK 1.7
	*/
	public List<CIP_admin_codesData> getData(String id);
}
