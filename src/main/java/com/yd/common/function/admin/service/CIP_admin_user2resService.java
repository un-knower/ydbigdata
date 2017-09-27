package com.yd.common.function.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.admin.data.CIP_admin_user2resData;
import com.yd.common.function.admin.data.vo.CIP_admin_user2resVO;
import com.yd.common.runtime.CIPRuntimeOperator;
import com.yd.common.tag.easyui.vo.TreeData;

/**
 * <p>Service类</p>
 * <p>Class: CIP_admin_user2resService</p>
 *
 * @since 2016-08-31 04:49:06
 */

@Service
public interface CIP_admin_user2resService {

	public void updateData(CIP_admin_user2resVO d, CIPRuntimeOperator operateInf);
	
	public void addData(CIP_admin_user2resVO d, CIPRuntimeOperator operateInf);
	
	public void deleteData(Object[] keys, CIPRuntimeOperator operateInf);
	
	public CIP_admin_user2resVO getData(Object[] keys);
	
	public List<CIP_admin_user2resData> searchData(CIPPageInfo page, CIPReqCondition[] conditions);

    /**
    * getToolData:(获取主页常用工具信息).
    *
    * @author jh
    * @param userId 用户id
    * @return
    * @since JDK 1.7
    */
    public List<CIP_admin_user2resData> getToolData(String userId);
    
	/** 
	 * 导出主页快速工具表
	 */
	public List<Map<String, Object>> exportEntities(CIPPageInfo page,CIPReqCondition[] conditions,
			CIPRuntimeOperator operateInf, boolean xFirst);

	/**
	* getResMenu:(获取当前角色下的动态菜单.).
	*
	* @author jh
	* @param userId
	* @return
	* @since JDK 1.7
	*/
	public List<TreeData> getResMenu(List<String> roleId);
	
}