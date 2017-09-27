package com.yd.common.function.common.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPResponseQueryMsg;
import com.yd.common.exception.CIPRuntimeException;
import com.yd.common.function.admin.data.CIP_admin_commonqueryData;
import com.yd.common.function.common.data.CIPCommonQueryData;
import com.yd.common.function.common.service.CIPCommonQueryService;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntimeConfigure;

/**
 * ClassName:CIPAdminCommonQuery Function: 通用查询资源. Reason: TODO ADD
 * REASON. Date: 2016年8月4日 上午10:44:33
 * 
 * @author lyn
 * @version
 * @since JDK 1.7
 * @see
 */
@RestController
@RequestMapping(value = "/actions/common")
public class CIPCommonQueryController {

	@Autowired
	private CIPCommonQueryService dataService = null;
	//json转成map
	private static TypeReference<Map<String, CIP_admin_commonqueryData>> typeChange= new TypeReference<Map<String, CIP_admin_commonqueryData>>(){};
	@RequestMapping(value = "/query")
	public CIPResponseQueryMsg commonQuery(@RequestBody CIPCommonQueryData vos,CIPPageInfo page) {
		CIPResponseQueryMsg msg=new CIPResponseQueryMsg();
		try {
			String queryId=vos.getQueryId();
			String sys_id = CIPRuntimeConfigure.cip_system_id;
			// 拼接缓存cache中的主键 为 系统id+标识串
			String key = sys_id + "@commonQuery";
			// 通过主键，从缓存中拿到 数据的json字符串信息
			String jsonData = dataService.getQueryMsgById(key);
			// 解析数据字符串信息
			Map<String, CIP_admin_commonqueryData> map = JSON.parseObject(jsonData,typeChange);
			// 通过主键拿到 封装过的数据信息
			CIP_admin_commonqueryData data = map.get(queryId);
			// 拿到缓存中存放的tableName,满足下面条件，即从缓存读取，否则从查询数据库
			if (data != null) {
				//判断是否多行
				if (data.getSingleRec().equals("1")) {
					String pagination=data.getPagination();
					//判断是否分页
					if (pagination.equals("1")) {
					 page=new CIPPageInfo(Integer.parseInt(vos.getPage()),Integer.parseInt(vos.getRows()));
					}
					List<Map<String,Object>> list = dataService.doQuery(data,page,vos.getParam());
					msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
					msg.msg = CIPErrorCode.CALL_SUCCESS.name;
					msg.rows = list;
					msg.total = page.getTotal();
				} else {
					page=new CIPPageInfo(1,1);
					List<Map<String,Object>> datas = dataService.doQuery(data,page,vos.getParam());
					msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
					msg.msg = CIPErrorCode.CALL_SUCCESS.name;
					msg.data = datas;
				}
			} else {
				msg.errorCode = CIPErrorCode.ERROR_RECORD_NOT_EXISTS.code;
				msg.msg = CIPErrorCode.ERROR_RECORD_NOT_EXISTS.name;
			}
		} catch (CIPRuntimeException e) {
			msg.errorCode = CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION.code;
			msg.msg = CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION.name;	
		}catch(Exception e){
			msg.errorCode = CIPErrorCode.ERROR_TECHNICAL_ERROR.code;
			msg.msg = CIPErrorCode.ERROR_TECHNICAL_ERROR.name;
		}
			return msg;
	}
	
	/**
	* countQuery:(通用查询统计).
	*
	* @author lyn
	* @param vos
	* @param page
	* @return
	* @since JDK 1.7
	*/
	@RequestMapping(value = "/countquery")
	public CIPResponseQueryMsg countQuery(@RequestBody CIPCommonQueryData vos,CIPPageInfo page) {
		CIPResponseQueryMsg msg=new CIPResponseQueryMsg();
		try {
			String queryId=vos.getQueryId();
			String sys_id = CIPRuntimeConfigure.cip_system_id;
			// 拼接缓存cache中的主键 为 系统id+标识串
			String key = sys_id + "@commonQuery";
			// 通过主键，从缓存中拿到 数据的json字符串信息
			String jsonData = dataService.getQueryMsgById(key);
			// 解析数据字符串信息
			Map<String, CIP_admin_commonqueryData> map = JSON.parseObject(jsonData,typeChange);
			// 通过主键拿到 封装过的数据信息
			CIP_admin_commonqueryData data = map.get(queryId);
			// 拿到缓存中存放的tableName,满足下面条件，即从缓存读取，否则从查询数据库
			if (data != null) {
				//CIPPageInfo page;
					int count = dataService.doQueryCount(data,vos.getParam());
					msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
					msg.msg = CIPErrorCode.CALL_SUCCESS.name;
					msg.data = count;
			} else {
				msg.errorCode = CIPErrorCode.ERROR_RECORD_NOT_EXISTS.code;
				msg.msg = CIPErrorCode.ERROR_RECORD_NOT_EXISTS.name;
			}
		} catch (CIPRuntimeException e) {
			msg.errorCode = CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION.code;
			msg.msg = CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION.name;	
		}catch(Exception e){
			msg.errorCode = CIPErrorCode.ERROR_TECHNICAL_ERROR.code;
			msg.msg = CIPErrorCode.ERROR_TECHNICAL_ERROR.name;
		}
			return msg;
	}
}
