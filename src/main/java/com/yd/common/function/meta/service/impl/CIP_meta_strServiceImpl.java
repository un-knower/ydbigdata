package com.yd.common.function.meta.service.impl;

import java.io.File;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.yd.common.auth.CIPResource;
import com.yd.common.call.CIPCallManager;
import com.yd.common.constants.OperateMode;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.data.CIPResponseMsg;
import com.yd.common.exception.CIPRuntimeException;
import com.yd.common.exception.CIPServiceException;
import com.yd.common.function.admin.dao.CIP_admin_domainDao;
import com.yd.common.function.admin.data.po.CIP_admin_domainPO;
import com.yd.common.function.meta.dao.CIP_meta_field_logDao;
import com.yd.common.function.meta.dao.CIP_meta_fieldsDao;
import com.yd.common.function.meta.dao.CIP_meta_moduleDao;
import com.yd.common.function.meta.dao.CIP_meta_strDao;
import com.yd.common.function.meta.dao.CIP_meta_str_logDao;
import com.yd.common.function.meta.data.CIPFileInfo;
import com.yd.common.function.meta.data.CIP_meta_strData;
import com.yd.common.function.meta.data.constant.CIPMetaConstants;
import com.yd.common.function.meta.data.po.CIP_meta_field_logPO;
import com.yd.common.function.meta.data.po.CIP_meta_fieldsPO;
import com.yd.common.function.meta.data.po.CIP_meta_modulePO;
import com.yd.common.function.meta.data.po.CIP_meta_strPO;
import com.yd.common.function.meta.data.po.CIP_meta_str_logPO;
import com.yd.common.function.meta.data.vo.CIP_meta_strVO;
import com.yd.common.function.meta.service.CIP_meta_strService;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntimeConfigure;
import com.yd.common.runtime.CIPRuntimeOperator;
import com.yd.common.tool.dbm.CIPDomainInfo;
import com.yd.common.tool.dbm.CIPTxtField;
import com.yd.common.utils.HttpUtils;
import com.yd.common.utils.JSONUtils;
import com.yd.common.utils.ReflectUtils;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * <p>Service实现类</p>
 * <p>Class: CIP_meta_strService</p>
 *
 * @since 2015-10-08 09:03:37
 */

@Service(value="CIP_meta_strService")
public class CIP_meta_strServiceImpl implements CIP_meta_strService {
	
	@Autowired
	private CIP_meta_strDao dataDao = null;
	@Autowired
	private CIP_meta_fieldsDao fieldDao = null;
	@Autowired
	private CIP_meta_str_logDao str_logDao = null;
	@Autowired
	private CIP_meta_field_logDao field_logDao = null;
	@Autowired
	private CIP_admin_domainDao domaindao = null;
	@Autowired
	private CIP_meta_moduleDao moduleDao = null;

	/** 
	 * 修改数据
	 */
	@Override
	public void updateData(CIP_meta_strVO vo, CIPRuntimeOperator operateInf){
		CIP_meta_strPO po = vo.toPO();
		Object[] keys = po.getKeys();
		CIP_meta_strPO po0 = dataDao.getSingle(keys);
		if( po0 == null ){
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
		}
		po.setUpdate_time(Timestamp.valueOf(operateInf.getOperate_tm()));
		po.setOperator(operateInf.getSubject_id());
		po.setCreate_time(po0.getCreate_time());
		po0.setUpdate_time(Timestamp.valueOf(operateInf.getOperate_tm()));
		po0.setOperator(operateInf.getSubject_id());
		
		List<Field> diffField;
		try {
			diffField = ReflectUtils.contrastDiff(po, po0);
		} catch (Exception e) {
			throw new CIPServiceException(new CIPErrorCode(-1, "获取字段变更失败"));
		}
		if(diffField!=null && diffField.size()>0){
			//修改记录前判断有无新增或修改记录，有新增记录则还为新增操作，有修改记录则为修改操作
			String str_id = po.getStr_id();
			Map<String, Object> conMap = new HashMap<String, Object>();
			conMap.put("str_id", str_id);
			conMap.put("op_type", OperateMode.CREATE.getCode());
			List<CIP_meta_str_logPO> logList = str_logDao.getByCondition(conMap);
			CIP_meta_str_logPO log = null;
			if(logList!=null && logList.size()>0){
				CIP_meta_str_logPO oldLog = logList.get(0);
				oldLog.setNew_value(JSON.toJSONString(po, false));
				str_logDao.update(oldLog);
			}else{
				List<CIP_meta_str_logPO> addLogs = new ArrayList<>();
				List<Object[]> delLogs = new ArrayList<>();
				for(Field field : diffField){
					String op_field = field.getName();
					String oldValue = null;
					String newValue = null;
					try {
						oldValue = field.get(po0)!=null?field.get(po0).toString():"";
						newValue = field.get(po)!=null?field.get(po).toString():"";
					} catch (Exception e) {
						throw new CIPServiceException(new CIPErrorCode(-1, "获取字段变更值失败"));
					}
					conMap.put("op_field", op_field);
					conMap.put("op_type", OperateMode.UPDATE.getCode());
					logList = str_logDao.getByCondition(conMap);
					if(logList!=null && logList.size()>0){
						CIP_meta_str_logPO oldLog = logList.get(0);
						delLogs.add(oldLog.getKeys());
						if(oldLog.getOld_value().equals(newValue)){
							continue;
						}
						//始终以上次修改的旧值作为此次修改的旧值
						oldValue = oldLog.getOld_value();
					}
					log = new CIP_meta_str_logPO();
					log.setStr_id(str_id);
					log.setOp_field(op_field);
					log.setOp_type(OperateMode.UPDATE.getCode());
					log.setOld_value(oldValue);
					log.setNew_value(newValue);
					log.setOper_time(Timestamp.valueOf(operateInf.getOperate_tm()));
					log.setOperator(operateInf.getSubject_id());
					log.setOp_flag("T");
					addLogs.add(log);
					
				}
				str_logDao.batchDelete(delLogs);
				str_logDao.batchAdd(addLogs);
				dataDao.update(po);
			}

		}
	}
	
	/** 
	 * 添加数据
	 */
	@Override
	public void addData(CIP_meta_strVO vo, CIPRuntimeOperator operateInf){
		CIP_meta_strPO po = vo.toPO();
		Object[] keys = po.getKeys();
		CIP_meta_strPO po0 = dataDao.getSingle(keys);
		if( po0!= null ){
			throw new CIPServiceException(CIPErrorCode.ERROR_DUMPLICATE_PRIMARY_KEY);
		}
		po.setCreate_time(Timestamp.valueOf(operateInf.getOperate_tm()));
		po.setOperator(operateInf.getSubject_id());
		dataDao.add(po);
		
		//获取删除变更记录
		Map<String, Object> conMap = new HashMap<String, Object>();
		conMap.put("str_id", po.getStr_id());
		conMap.put("op_type", OperateMode.DELETE.getCode());
		List<CIP_meta_str_logPO> logList = str_logDao.getByCondition(conMap);
		
		if(logList==null||logList.size()==0){		
			CIP_meta_str_logPO log = new CIP_meta_str_logPO();
			log.setStr_id(po.getStr_id());
			log.setOp_field("");
			log.setOp_type(OperateMode.CREATE.getCode());
			log.setOld_value("");
			log.setNew_value(JSON.toJSONString(po, false));
			log.setOper_time(Timestamp.valueOf(operateInf.getOperate_tm()));
			log.setOperator(operateInf.getSubject_id());
			log.setOp_flag("T");
			str_logDao.add(log);
		}else{
			CIP_meta_str_logPO delLogPO = logList.get(0);
			CIP_meta_strPO strPO =JSON.parseObject(delLogPO.getOld_value(),CIP_meta_strPO.class);
			po.setCreate_time(strPO.getCreate_time());
			po.setOperator(strPO.getOperator());
			po.setUpdate_time(strPO.getUpdate_time());
			List<Field> diffField;
			try {
				diffField = ReflectUtils.contrastDiff(strPO, po);
			} catch (Exception e) {
				throw new CIPServiceException(new CIPErrorCode(-1, "获取字段变更失败"));
			}
			List<CIP_meta_str_logPO> addLogs = new ArrayList<>();
			for(Field field : diffField){
				String op_field = field.getName();
				String oldValue = null;
				String newValue = null;
				try {
					oldValue = field.get(strPO)!=null?field.get(strPO).toString():"";
					newValue = field.get(po)!=null?field.get(po).toString():"";
				} catch (Exception e) {
					throw new CIPServiceException(new CIPErrorCode(-1, "获取字段变更值失败"));
				}
				CIP_meta_str_logPO log = new CIP_meta_str_logPO();
				log.setStr_id(po.getStr_id());
				log.setOp_field(op_field);
				log.setOp_type(OperateMode.UPDATE.getCode());
				log.setOld_value(oldValue);
				log.setNew_value(newValue);
				log.setOper_time(Timestamp.valueOf(operateInf.getOperate_tm()));
				log.setOperator(operateInf.getSubject_id());
				log.setOp_flag("T");
				addLogs.add(log);
			}
			str_logDao.deleteByStrId(po.getStr_id());
			str_logDao.batchAdd(addLogs);
		}
	}
	
	/** 
	 * 删除数据
	 */
	@Override
	public void deleteData(Object[] keys, CIPRuntimeOperator operateInf){
		CIP_meta_strPO  po = dataDao.getSingle(keys);
		if(po == null)
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
		dataDao.delete(keys);
		
		//获取新增变更记录
		Map<String, Object> conMap = new HashMap<String, Object>();
		conMap.put("str_id", po.getStr_id());
		conMap.put("op_type", OperateMode.CREATE.getCode());
		List<CIP_meta_str_logPO> logList = str_logDao.getByCondition(conMap);
		
		if(logList==null||logList.size()==0){
			//获取修改变更记录,如果有修改记录则删除记录旧值为修改前旧值
			conMap.put("op_type", OperateMode.UPDATE.getCode());
			logList = str_logDao.getByCondition(conMap);
			if(logList!=null&&logList.size()>0){
				List<Object[]> keysArr = new ArrayList<>();
				for(CIP_meta_str_logPO updateLog : logList){
					String field = updateLog.getOp_field();
					String oldValue = updateLog.getOld_value();
					ReflectUtils.setValue(po, field, oldValue);
					keysArr.add(updateLog.getKeys());
				}
				str_logDao.batchDelete(keysArr);
			}
			String oldValue = JSON.toJSONString(po, false);
			
			CIP_meta_str_logPO log = new CIP_meta_str_logPO();
			log.setStr_id(po.getStr_id());
			log.setOp_field("");
			log.setOp_type(OperateMode.DELETE.getCode());
			log.setOld_value(oldValue);
			log.setNew_value("");
			log.setOper_time(Timestamp.valueOf(operateInf.getOperate_tm()));
			log.setOperator(operateInf.getSubject_id());
			log.setOp_flag("T");
			str_logDao.add(log);
			//获取该表字段
			Map<String, Object> conMap1 = new HashMap<String, Object>();
			conMap1.put("str_id", po.getStr_id());
			List<CIP_meta_fieldsPO> fieldList = fieldDao.getByCondition(conMap1);
			//添加删除字段变更
			for(int i = 0 ;i<fieldList.size();i++){
				CIP_meta_fieldsPO fieldsPO = fieldList.get(i);
				//获取该表字段新增变更
				Map<String, Object> conMap2 = new HashMap<String, Object>();
				conMap2.put("str_id", po.getStr_id());
				conMap2.put("field_id", fieldsPO.getField_id());
				conMap2.put("op_type", OperateMode.CREATE.getCode());
				List<CIP_meta_field_logPO> fieldlogList = field_logDao.getByCondition(conMap2);
				if(fieldlogList==null||fieldlogList.size()==0){
					CIP_meta_field_logPO fieldlog = new CIP_meta_field_logPO();
					fieldlog.setStr_id(po.getStr_id());
					fieldlog.setField_id(fieldsPO.getField_id());
					fieldlog.setOp_field("");
					fieldlog.setOp_type(OperateMode.DELETE.getCode());
					fieldlog.setOld_value(JSON.toJSONString(fieldsPO, false));
					fieldlog.setNew_value("");
					fieldlog.setOper_time(Timestamp.valueOf(operateInf.getOperate_tm()));
					fieldlog.setOperator(operateInf.getSubject_id());
					fieldlog.setOp_flag("T");
					field_logDao.add(fieldlog);
				}else{
					field_logDao.delete(fieldlogList.get(0).getKeys());
				}
				//删除字段
				fieldDao.delete(fieldsPO.getKeys());
			}
			
		}else{//有新增变更记录
			CIP_meta_str_logPO addLogPO = logList.get(0);
			str_logDao.delete(addLogPO.getKeys());
			//删除相关变更记录
			str_logDao.deleteByStrId(po.getStr_id());
			//删除字段
			fieldDao.deleteByStrId(po.getStr_id());
			//删除字段变更
			field_logDao.deleteByStrId(po.getStr_id());
		}
	}
	
	/** 
	 * 获取数据
	 */		
	@Override
	public CIP_meta_strVO getData(Object[] keys ){
		CIP_meta_strPO  po = dataDao.getSingle(keys);
		if(po == null)
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
			
		return po.toVO();
	}
	
	/** 
	 * 检索数据
	 */		
	@Override
	public List<CIP_meta_strData> searchData(CIPPageInfo page, CIPReqCondition[] conditions){
		
		List<CIP_meta_strData> datas = dataDao.searchData(page, conditions);
		return datas;
	}

	
	public List<Map<String, Object>> exportEntities(CIPPageInfo page,CIPReqCondition[] conditions,
			CIPRuntimeOperator operateInf, boolean xFirst){
		if(xFirst){
			//do something 
		}
		return dataDao.exportEntities(page, conditions, xFirst);
	}

	@Override
	public void sync2Meta(List<CIP_meta_strVO> vos) {
		CIPResponseMsg rep = CIPCallManager.getCallManager().call("", "", vos, CIPResponseMsg.class);
		
	}

	@Override
	public void changeSubmit() {
		
		List<CIP_meta_str_logPO> str_logPOs = str_logDao.getAll();
		List<CIP_meta_field_logPO> field_logPOs = field_logDao.getAll();
		String[] sys_id = null;
		
		if (((str_logPOs == null)||str_logPOs.isEmpty()) && ((field_logPOs == null)||field_logPOs.isEmpty()))
		{
			throw new CIPRuntimeException(new CIPErrorCode(-1, "未发现变更信息！"));

		}
		for(CIP_meta_str_logPO str_logPO:str_logPOs){
			if (str_logPO.getOp_flag().equals("R")) {
				throw new CIPRuntimeException(new CIPErrorCode(-1, "发现拒绝的变更，请检查变更信息"));
			}
		}
		for(CIP_meta_field_logPO field_logPO:field_logPOs){
			if (field_logPO.getOp_flag().equals("R")) {
				throw new CIPRuntimeException(new CIPErrorCode(-1, "发现拒绝的变更，请检查变更信息"));
			}
		}
		
		Document change = DocumentHelper.createDocument();
		
		Element request = change.addElement("request");
		
		if (!(str_logPOs.isEmpty())) {
			sys_id = str_logPOs.get(0).getStr_id().split("_");
			Element strlog =request.addElement("strlog");
			for(CIP_meta_str_logPO str_logPO:str_logPOs){
				String	data="";
				if(str_logPO.getOp_type().equals("I"))data = str_logPO.getNew_value();
				if (str_logPO.getOp_type().equals("D"))data = str_logPO.getOld_value();
				if (str_logPO.getOp_type().equals("M"))data = str_logPO.getOld_value()+";"+str_logPO.getNew_value();
				Element slog = strlog.addElement("log");
				slog.addAttribute("str_id", str_logPO.getStr_id());
				slog.addAttribute("op_field", str_logPO.getOp_field());
				slog.addAttribute("op_type", str_logPO.getOp_type());
				slog.addAttribute("sys_id", sys_id[0]);
				slog.setText(data);
			}
		}
		if(!(field_logPOs.isEmpty())){
			sys_id = field_logPOs.get(0).getStr_id().split("_");
		Element fieldlog = request.addElement("fieldslog");
		for(CIP_meta_field_logPO field_logPO:field_logPOs){
			String data = "";
			if(field_logPO.getOp_type().equals("I")) data = field_logPO.getNew_value();
			if(field_logPO.getOp_type().equals("D")) data = field_logPO.getOld_value();
			if(field_logPO.getOp_type().equals("M"))
			data = field_logPO.getOld_value()+";"+field_logPO.getNew_value();
		Element flog = fieldlog.addElement("log");
		flog.addAttribute("str_id", field_logPO.getStr_id());
		flog.addAttribute("op_field", field_logPO.getOp_field());
		flog.addAttribute("op_type", field_logPO.getOp_type());
		flog.addAttribute("field_id", field_logPO.getField_id());
		flog.addAttribute("sys_id", sys_id[0]);
		flog.setText(data);
		}
	}
		
		String xmldoc = change.asXML();
		
		if ((xmldoc !=null) || (!(xmldoc.equals("")))) {		
		
			try {
				String responseString = HttpUtils.postJSON(CIPRuntimeConfigure.cip_conf_changesync_url, xmldoc);
				if(responseString.equals("200")){
					for(CIP_meta_str_logPO str:str_logPOs)
						str_logDao.deleteByStrId(str.getStr_id());
					for(CIP_meta_field_logPO field:field_logPOs)
						field_logDao.deleteByStrIdAndFieldId(field.getStr_id(), field.getField_id());
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new CIPServiceException(new CIPErrorCode(-1, "同步变更失败"));
			}
		}else {
			throw new CIPServiceException(new CIPErrorCode(-1, "未能封装变更信息，可能没有变更"));
		}
		
	}


	

	@Override
	public List<CIPFileInfo> regenerate(String str_id, List<String> modules) throws Exception {
		CIP_meta_strPO strPO = dataDao.getSingle(str_id);
		Map<String, Object> conditions = new HashMap<String,Object>();
		conditions.put("str_id", strPO.getStr_id());
		List<CIP_meta_fieldsPO> fields = fieldDao.getByCondition(conditions);
		Configuration conf = initConfiguration();
		return generateFileInfo(conf, strPO, fields, modules);
	}
	private List<CIPFileInfo> generateFileInfo(
			Configuration conf, CIP_meta_strPO str, List<CIP_meta_fieldsPO> fields, List<String> modules) throws Exception {

		
		String javaPackage = str.getStr_prefix();
	
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("basePackage", javaPackage);

		CIPResource res;
		List<CIPResource> resL = new ArrayList<CIPResource>();
		CIPFileInfo javaFile = null;
		List<CIPFileInfo> javaFiles = new ArrayList<CIPFileInfo>();
		StringBuffer viewInfo = new StringBuffer();
		String viewContent = null;
		String actionName;
		String className;
		String str_id;
		int index;
		String tempStr;
		String appId;
		List<CIP_meta_fieldsPO> keyFields = new ArrayList<CIP_meta_fieldsPO>();
		
			str_id = str.getStr_id();
		
			model.remove("fields");
			model.remove("txtFields");
			model.remove("structure");			
			model.put("structure", str);
			keyFields.clear();
			model.put("fields", fields);
			for (CIP_meta_fieldsPO field : fields) {
				if (field.getKey_flag().equals(CIPMetaConstants.KEY_FLAG_YES)) {
					keyFields.add(field);
				}
			}
			model.put("keyFields", keyFields);
			index = str_id.indexOf('_');
			tempStr = str_id.substring(index + 1);
			appId = str_id.substring(0, index);
			className = appId.toUpperCase() + "_" + tempStr;

			actionName = str_id.toLowerCase();
			model.put("actionName", actionName);
			model.put("className", className);
			model.put("appId", appId);
			generateAdditionParams(model, fields);
			// 生成当前表的视图信息
			if (!str_id.endsWith("_dm"))
				viewContent = getViewSql(model, str, fields);
			if (viewContent != null) {
				viewInfo.append(viewContent);
				viewContent = null;
			}
			CIPFileInfo viewFile = new CIPFileInfo();
			viewFile.filePackage =  File.separator
					+ "resources";
			viewFile.fileName = "ViewInfo.sql";
			viewFile.fileContent = viewInfo.toString();
			javaFiles.add(viewFile);
			
			for(String module : modules){
				switch (module) {
				case "data":
					javaFile = new CIPFileInfo();
					javaFile.filePackage =  File.separator
							+ str.getStr_module() + File.separator + "data"
							+ File.separator + "po";
					javaFile.fileName = className + "PO.java";
					javaFile.fileContent = generateJavaInfo("po.ftl", conf, model);
					javaFiles.add(javaFile);

					javaFile = new CIPFileInfo();
					javaFile.filePackage =  File.separator
							+ str.getStr_module() + File.separator + "data"
							+ File.separator + "mapper";
					javaFile.fileName = className + "Mapper.java";
					javaFile.fileContent = generateJavaInfo("mapper.ftl", conf, model);
					javaFiles.add(javaFile);

					javaFile = new CIPFileInfo();
					javaFile.filePackage =  File.separator
							+ str.getStr_module() + File.separator + "data"
							+ File.separator + "key";
					javaFile.fileName = className + "Key.java";
					javaFile.fileContent = generateJavaInfo("key.ftl", conf, model);
					javaFiles.add(javaFile);
					
					javaFile = new CIPFileInfo();
					javaFile.filePackage =  File.separator
							+ str.getStr_module() + File.separator + "data"
							+ File.separator + "vo";
					javaFile.fileName = className + "VO.java";
					javaFile.fileContent = generateJavaInfo("vo.ftl", conf, model);
					javaFiles.add(javaFile);
					
					javaFile = new CIPFileInfo();
					javaFile.filePackage =  File.separator
							+ str.getStr_module() + File.separator + "data";
					javaFile.fileName = className + "Data.java";
					javaFile.fileContent = generateJavaInfo("data.ftl", conf, model);
					javaFiles.add(javaFile);
					
					break;
				case "dao":
					javaFile = new CIPFileInfo();
					javaFile.filePackage =  File.separator
							+ str.getStr_module() + File.separator + "dao";
					javaFile.fileName = className + "Dao.java";
					javaFile.fileContent = generateJavaInfo("dao.ftl", conf, model);
					javaFiles.add(javaFile);

					javaFile = new CIPFileInfo();
					javaFile.filePackage =  File.separator
							+ str.getStr_module() + File.separator + "dao"
							+ File.separator + "impl";
					javaFile.fileName = className + "DaoImpl.java";
					javaFile.fileContent = generateJavaInfo("daoImpl.ftl", conf, model);
					javaFiles.add(javaFile);
					break;
				case "service":
					javaFile = new CIPFileInfo();
					javaFile.filePackage =  File.separator
							+ str.getStr_module() + File.separator + "service";
					javaFile.fileName = className + "Service.java";
					javaFile.fileContent = generateJavaInfo("service.ftl", conf, model);
					javaFiles.add(javaFile);

					javaFile = new CIPFileInfo();
					javaFile.filePackage =  File.separator
							+ str.getStr_module() + File.separator + "service"
							+ File.separator + "impl";
					javaFile.fileName = className + "ServiceImpl.java";
					javaFile.fileContent = generateJavaInfo("serviceImpl.ftl", conf,
							model);
					javaFiles.add(javaFile);	
					break;
				case "controller":
					javaFile = new CIPFileInfo();
					javaFile.filePackage =  File.separator
							+ str.getStr_module() + File.separator + "controller";
					javaFile.fileName = className + "Controller.java";
					javaFile.fileContent = generateJavaInfo("controller.ftl", conf,
							model);
					javaFiles.add(javaFile);
					
					// 按钮信息在这里生成
					// get data
					res = new CIPResource();
					res.id = str.getStr_id() + "_getData";
					res.url = "/actions/" + str.getFunc_set_id() + "/getData.do";
					res.name = "获取" + str.getStr_name();
					res.level = 2;
					res.type = "B";
					res.group = str.getStr_id() + "_list";
					resL.add(res);

					// delete data
					res = new CIPResource();
					res.id = str.getStr_id() + "_deleteData";
					res.url = "/actions/" + str.getFunc_set_id() + "/deleteData.do";
					res.name = "删除" + str.getStr_name();
					res.level = 2;
					res.type = "B";
					res.group = str.getStr_id() + "_list";
					resL.add(res);

					// update data
					res = new CIPResource();
					res.id = str.getStr_id() + "_updateData";
					res.url = "/actions/" + str.getFunc_set_id() + "/updateData.do";
					res.name = "修改" + str.getStr_name();
					res.level = 2;
					res.type = "B";
					res.group = str.getStr_id() + "_list";
					resL.add(res);

					// insert data
					res = new CIPResource();
					res.id = str.getStr_id() + "_addData";
					res.url = "/actions/" + str.getFunc_set_id() + "/addData.do";
					res.name = "新增" + str.getStr_name();
					res.level = 2;
					res.type = "B";
					res.group = str.getStr_id() + "_list";
					resL.add(res);

					// search data
					res = new CIPResource();
					res.id = str.getStr_id() + "_searchData";
					res.url = "/actions/" + str.getFunc_set_id() + "/searchData.do";
					res.name = "检索" + str.getStr_name();
					res.level = 2;
					res.type = "B";
					res.group = str.getStr_id() + "_list";
					resL.add(res);

					// export excel
					res = new CIPResource();
					res.id = str.getStr_id() + "_exportEntities";
					res.url = "/actions/" + str.getFunc_set_id()
							+ "/exportEntities.do";
					res.name = "导出" + str.getStr_name();
					res.level = 2;
					res.type = "B";
					res.group = str.getStr_id() + "_list";
					resL.add(res);
				

					StringBuffer sb = new StringBuffer();
					index = 0;
					for (CIPResource resource : resL) {
						resource.xNotOutput = false;
						sb.append(resource.toString() + "\n");
						sb.append(resource.getSupResource2Resource(index++) + "\n");
					}
	
					CIPFileInfo resFile = new CIPFileInfo();
					resFile.filePackage =  File.separator
							+ "resources";
					resFile.fileName = "ActionResource.sql";
					resFile.fileContent = sb.toString();
					javaFiles.add(resFile);
					break;
				case "ui":
					// list.html
					CIPResource menu = null;
					String listPageId;
					CIPResource mainMenu;
					Map<String,CIPResource> groups = new HashMap<String,CIPResource>();
					List<String> resources = new ArrayList<String>();
					List<String> res2resources = new ArrayList<String>();
					int mainMenuIndex = 1;
					int menuIndex = 1;
					CIPFileInfo htmlFile = new CIPFileInfo();
					htmlFile.filePackage = "ui/view/" + str.getStr_module();
					htmlFile.fileName = str.getStr_id() + "_list.html";
					htmlFile.fileId = str.getStr_id() + "_list";
					htmlFile.fileContent = generateJavaInfo("list.ftl", conf, model);
					javaFiles.add(htmlFile);
					
					
					menu = new CIPResource();
					menu.xNotOutput = false;
					menu.id = str.getStr_id() + "_list";
					listPageId = menu.id;
					menu.url = "ui/view/" + str.getStr_module() + "/"
							+ htmlFile.fileName + "?actionId=" + htmlFile.fileId;
					menu.name = str.getStr_name();
					menu.type = "A";
					menu.level = 1;
					menu.group = str.getStr_module();
					mainMenu = groups.get(menu.group);
					
					if( mainMenu == null ){
						mainMenu = new CIPResource();
						mainMenu.xNotOutput = false;
						mainMenu.type = "F";
						mainMenu.level = 0;
						mainMenu.id = menu.group;
						mainMenu.group = "default";
						CIP_meta_modulePO modulePO = moduleDao.getSingle(new Object[]{str.getStr_module()});
						mainMenu.name = modulePO.getModule_name();
						mainMenu.menus = new ArrayList<CIPResource>();
						groups.put(menu.group, mainMenu);
						resources.add(mainMenu.toString());
						res2resources.add(mainMenu.getSupResource2Resource(mainMenuIndex++));
						menuIndex = 1;
					}
					mainMenu.menus.add(menu);
					resources.add(menu.toString());
					res2resources.add(menu.getSupResource2Resource(menuIndex++));
					
					// form.html
					htmlFile = new CIPFileInfo();
					htmlFile.filePackage = "ui/view" + "/"	+ str.getStr_module();
					htmlFile.fileName = str.getStr_id() + "_form.html";
					htmlFile.fileId = str.getStr_id() + "_form";
					htmlFile.fileContent = generateJavaInfo("form.ftl", conf, model);
					javaFiles.add(htmlFile);
					
					menu = new CIPResource();
					menu.xNotOutput = false;
					menu.id = str.getStr_id() + "_form"; 
					menu.url = "ui/view/" + str.getStr_module() + "/"
							+ htmlFile.fileName + "?actionId=" + htmlFile.fileId;
					menu.name = str.getStr_name()+" 表单";
					menu.type = "P"; //R： root；F：module；A：meanu item；P：page（非菜单页面）；B：button
					menu.level = 2;
					menu.group = listPageId;
					resources.add(menu.toString());
					res2resources.add(menu.getSupResource2Resource(1));
				
				
					//resource.txt
					htmlFile = new CIPFileInfo();
					htmlFile.filePackage =  File.separator
							+ "resources";
					htmlFile.fileName = "resource.sql";
					StringBuffer sb1 = new StringBuffer();
					for(String resource:resources){
						sb1.append(resource);
						sb1.append("\n");
					}
					htmlFile.fileContent = sb1.toString();
					javaFiles.add(htmlFile);
					
					//resource2resource.txt
					htmlFile = new CIPFileInfo();
					htmlFile.filePackage =  File.separator
							+ "resources";
					htmlFile.fileName = "resource2resource.sql";
					sb = new StringBuffer();
					for(String resource:res2resources){
						sb.append(resource);
						sb.append("\n");
					}
					htmlFile.fileContent = sb.toString();
					javaFiles.add(htmlFile);

					break;
				default:
					break;
				}
			}

		return javaFiles;
	}
	private void generateAdditionParams(Map<String, Object> model,
			List<CIP_meta_fieldsPO> fields) {
		//1 java Classes
		//2 set/getMap
		Set<String> keys =new HashSet();
		Map<String,String> setMap = new HashMap<String,String>(fields.size());
		Map<String,String> getMap = new HashMap<String,String>(fields.size());
		
		String getMethod,setMethod,javaClass,field_id,tempStr;
		for(CIP_meta_fieldsPO field:fields){
			javaClass = field.getJava_type();
			if(javaClass.indexOf('.')>0){
				keys.add(javaClass);
				field.setJava_type(javaClass.substring(javaClass.lastIndexOf('.')+1));
			}
			
			field_id = field.getField_id();
			tempStr = Character.toUpperCase(field_id.charAt(0))+field_id.substring(1);
			getMethod = "get" + tempStr;
			setMethod = "set" + tempStr;
			
			setMap.put(field_id, setMethod);
			getMap.put(field_id, getMethod);
			
		}
		List<String> javaClasses = new ArrayList<String>();
		for(String key:keys){
			javaClasses.add(key);
		}
		model.put("javaClasses", javaClasses);
		model.put("setMap", setMap);
		model.put("getMap", getMap);

	}
	private String generateJavaInfo(String templateName, Configuration config,
			Map<String, Object> model) throws Exception {
		Template template = config.getTemplate(templateName, "UTF-8");
		StringWriter writer = new StringWriter();
		template.process(model, writer);
		return writer.toString();
	}
	private Configuration initConfiguration() throws Exception {
		Configuration config = null;
		config = new Configuration(Configuration.VERSION_2_3_21);
		config.setClassForTemplateLoading(this.getClass(),"");
		config.setDefaultEncoding("UTF-8");
		config.setObjectWrapper(new DefaultObjectWrapper(
				Configuration.VERSION_2_3_21));

		config.setSetting("classic_compatible", "true");
		config.setSetting("whitespace_stripping", "true");
		config.setSetting("template_update_delay", "1");
		config.setSetting("locale", "zh_CN");
		config.setSetting("default_encoding", "utf-8");
		config.setSetting("url_escaping_charset", "utf-8");
		config.setSetting("datetime_format", "yyyy-MM-dd hh:mm:ss");
		config.setSetting("date_format", "yyyy-MM-dd");
		config.setSetting("time_format", "HH:mm:ss");
		config.setSetting("number_format", "0.######;");

		return config;
	}
	private String getViewSql(Map<String, Object> model, CIP_meta_strPO str, List<CIP_meta_fieldsPO> fields) {
		String viewContent = null;
		StringBuffer outColumns = new StringBuffer();
		StringBuffer selectBody = new StringBuffer();
		//获取数据域信息及文本字段信息
		List<CIPDomainInfo> domainTables = getDomainTables(model, fields);
	
		
			if(domainTables .size()>0){
				outColumns.append("DROP VIEW IF EXISTS ").append(str.getStr_id()).append("_v;\n");
				outColumns.append("create ALGORITHM=MERGE view ").append(str.getStr_id()).append("_v as \n").append("select m.*\n");
				
				selectBody.append(" from ").append(str.getStr_id()).append(" m ");
				for(CIPDomainInfo d:domainTables){
					
					if(!d.refTableId.equals("cip_admin_codes")){
						outColumns.append(",").append(d.domainId).append(".").append(d.domainId).append("_name ");
						selectBody.append("\n left join ").append(d.refTableId).append(" ").append(d.domainId);
						selectBody.append("\n on m.").append(d.domainId).append("=").append(d.domainId).append(".").append(d.domainId);
					}
					else {
						outColumns.append(",").append(d.domainId).append(".code_name as ").append(d.domainId).append("_name ");
						selectBody.append("\n left join ").append(d.refTableId).append(" ").append(d.domainId);
						selectBody.append("\n on m.").append(d.domainId).append("=").append(d.domainId).append(".code_type \n and ");
						selectBody.append(d.domainId).append(".domain_id='").append(d.domainId).append("' ");
					}
				}
			}
			else {
				outColumns.append("DROP VIEW IF EXISTS ").append(str.getStr_id()).append("_v;\n");
				outColumns.append("create ALGORITHM=MERGE view ").append(str.getStr_id()).append("_v as ").append("select m.*");
				
				selectBody.append(" from ").append(str.getStr_id()).append(" m ");
			}
		
		
		outColumns.append(selectBody).append(";\n");
		viewContent = outColumns.toString();
	
		return viewContent;
	}
	private List<CIPDomainInfo> getDomainTables(Map<String, Object> model, List<CIP_meta_fieldsPO> fields) {
		List<CIPDomainInfo> domainTables = new ArrayList<CIPDomainInfo>();
		List<CIPTxtField> txtFields = new ArrayList<CIPTxtField>();
		CIPDomainInfo domain =null;
		
		// 1、获取要关联的码表信息
		for (CIP_meta_fieldsPO field : fields) {
			if (field.getField_use_type().equals(CIPMetaConstants.FIELD_USE_TYPE_CODE)) {
				// 关联码表信息，获取文本
				CIP_admin_domainPO domainPO = domaindao.getSingle(new Object[]{field.getDomain_type()});
				if(domainPO!=null){
					domain = new CIPDomainInfo();
					domain.domainId = field.getDomain_type();
					domain.refDomainId = domainPO.getRef_domain_id();
					domain.refTableId = domainPO.getRef_table_id();
				}
				if (domain != null) {
					domain.domainId = field.getField_id();
					if (domain.refDomainId != null
							&& !domain.refDomainId.equals("")) {
						domainPO =domaindao.getSingle(new Object[]{domain.refDomainId}); 
						if(domainPO!=null){
							domain.domainId = field.getDomain_type();
							domain.refDomainId = domainPO.getRef_domain_id();
							domain.refTableId = domainPO.getRef_table_id();
						}
					}
					if (domain == null)
						continue;
					domain.domainId = field.getField_id();
					
					//table.addTxtColumn(field.getField_id(), field.getField_name()+"名称");
					CIPTxtField txtField = new CIPTxtField();
					String columnTxt = field.getField_id()+"_name";
					boolean xMatch = false;
					for (CIP_meta_fieldsPO f : fields) {
						if(f.getField_id().equals(columnTxt)){
							xMatch = true;
							break;
						}
					}
					if(xMatch){
						continue;
					}
					txtField.setTxtField(columnTxt);
					txtField.setTxtFieldName(field.getField_name()+"名称");
					txtFields.add(txtField);
					domainTables.add(domain);
				}
			}
		}
		model.put("txtFields", txtFields);
		
		return domainTables;
	}

	@Override
	public void changeRefuse(String selectedRows) {
		Map<String,Object> map = JSONUtils.convertJson2Map(selectedRows);
		List<Map<String, Object>>  rows= (List<Map<String, Object>>)map.get("selectedRows");
		
		List<CIP_meta_str_logPO> str_logPOs = new ArrayList<CIP_meta_str_logPO>();
		for(int i =0;i<rows.size();i++){
			Map<String, Object> row = rows.get(i); 
			
			if (row.get("field_domain").equals("T")) {
				String str_id = (String) row.get("str_id");
				String op_field = (String) row.get("operate_field");
				String op_type = (String) row.get("operate_type");
				Map<String, Object> arg0 = new HashMap<String, Object>();
				arg0.put("str_id", str_id);
				arg0.put("op_field",op_field);
				arg0.put("op_type",op_type );
				
				List<CIP_meta_str_logPO> str_logPO = str_logDao.getByCondition(arg0);
				for(CIP_meta_str_logPO logPO:str_logPO){
					logPO.setOp_flag("R");
					str_logDao.update(logPO);
				}
			}
			if (row.get("field_domain").equals("C")) {
				String str_id = (String) row.get("str_id");
				String op_field = (String) row.get("operate_field");
				String op_type = (String) row.get("operate_type");
				String field_id = (String) row.get("field_id");
				Map<String, Object> arg0 = new HashMap<String, Object>();
				arg0.put("str_id", str_id);
				arg0.put("op_field", op_field);
				arg0.put("op_type", op_type);
				arg0.put("field_id", field_id);
				
				List<CIP_meta_field_logPO> field_logPOs = field_logDao.getByCondition(arg0);
				for(CIP_meta_field_logPO logPO:field_logPOs){
					logPO.setOp_flag("R");
					field_logDao.update(logPO);
				}
			}
		}
	}

	@Override
	public void changeRecovery(String selectedRows) {
		Map<String,Object> map = JSONUtils.convertJson2Map(selectedRows);
		List<Map<String, Object>>  rows= (List<Map<String, Object>>)map.get("selectedRows");
		
		List<CIP_meta_str_logPO> str_logPOs = new ArrayList<CIP_meta_str_logPO>();
		for(int i =0;i<rows.size();i++){
			Map<String, Object> row = rows.get(i); 
			
			if (row.get("field_domain").equals("T")) {
				String str_id = (String) row.get("str_id");
				String op_field = (String) row.get("operate_field");
				String op_type = (String) row.get("operate_type");
				Map<String, Object> arg0 = new HashMap<String, Object>();
				arg0.put("str_id", str_id);
				arg0.put("op_field",op_field);
				arg0.put("op_type",op_type );
				
				List<CIP_meta_str_logPO> str_logPO = str_logDao.getByCondition(arg0);
				for(CIP_meta_str_logPO logPO:str_logPO){
					if (logPO.getOp_type().equals("I")) {
						CIP_meta_strPO strPO = JSONUtils.convertDataJson2Object(logPO.getNew_value(), CIP_meta_strPO.class);
						Object[] keys = strPO.getKeys();
						dataDao.delete(keys);
					}
					if (logPO.getOp_type().equals("D")) {
						CIP_meta_strPO strPO = JSONUtils.convertDataJson2Object(logPO.getOld_value(), CIP_meta_strPO.class);
						/*Map<String, Object> fieldMap = new HashMap<String, Object>();
						fieldMap.put("str_id", strPO.getStr_id());
						List<CIP_meta_field_logPO> field_logPOs = field_logDao.getByCondition(fieldMap);
							for(CIP_meta_field_logPO field_logPO:field_logPOs){
								CIP_meta_fieldsPO fieldsPO = JSONUtils.convertDataJson2Object(field_logPO.getOld_value(), CIP_meta_fieldsPO.class);
								Object[] keys = fieldsPO.getKeys();
								fieldDao.add(fieldsPO);
								field_logDao.delete(field_logPO.getKeys());
							}*/
						dataDao.add(strPO);
					}
					if (logPO.getOp_type().equals("M")) {
						CIP_meta_strPO strPO = dataDao.getSingle(logPO.getStr_id());
						Field field = null;
						try {
						    field = strPO.getClass().getDeclaredField(logPO.getOp_field());
							field.setAccessible(true);
							
						} catch (NoSuchFieldException e) {
							e.printStackTrace();
						} catch (SecurityException e) {
							e.printStackTrace();
						}
						try {
								field.set(strPO, logPO.getOld_value());
							
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
						dataDao.update(strPO);
					}
					
					Object[] keys = logPO.getKeys();
					str_logDao.delete(keys);
				}
				
			}
			if (row.get("field_domain").equals("C")) {
				String str_id = (String) row.get("str_id");
				String op_field = (String) row.get("operate_field");
				String op_type = (String) row.get("operate_type");
				String field_id = (String) row.get("field_id");
				Map<String, Object> arg0 = new HashMap<String, Object>();
				arg0.put("str_id", str_id);
				arg0.put("op_field", op_field);
				arg0.put("op_type", op_type);
				arg0.put("field_id", field_id);
				
				List<CIP_meta_field_logPO> field_logPOs = field_logDao.getByCondition(arg0);
				
				for(CIP_meta_field_logPO logPO:field_logPOs){
					if (logPO.getOp_type().equals("I")) {
						CIP_meta_fieldsPO fieldsPO = JSONUtils.convertDataJson2Object(logPO.getNew_value(), CIP_meta_fieldsPO.class);
						Object[] keys = fieldsPO.getKeys();
						fieldDao.delete(keys);
					}
					if (logPO.getOp_type().equals("D")) {
						CIP_meta_fieldsPO fieldsPO = JSONUtils.convertDataJson2Object(logPO.getOld_value(), CIP_meta_fieldsPO.class);
						Object[] keys = fieldsPO.getKeys();
						fieldDao.add(fieldsPO);
					}
					if (logPO.getOp_type().equals("M")) {
						CIP_meta_fieldsPO fieldsPO = fieldDao.getSingle(logPO.getField_id(),logPO.getStr_id());
						Field field = null;
						try {
							field= fieldsPO.getClass().getDeclaredField(logPO.getOp_field());
						} catch (NoSuchFieldException | SecurityException e) {
							e.printStackTrace();
						}
						field.setAccessible(true);
						Class class1 = field.getType();
						try {
						if (class1.toString().equals("int")) {
								field.set(fieldsPO, Integer.getInteger(logPO.getOld_value()));
						}else {
							field.set(fieldsPO, logPO.getOld_value());
						}
						} catch (IllegalArgumentException
								| IllegalAccessException e) {
							e.printStackTrace();
						}
						fieldDao.update(fieldsPO);
					}
					
					Object[] keys = logPO.getKeys();
					field_logDao.delete(keys);
				}
			}
		}
	}
}


