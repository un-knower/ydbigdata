package com.yd.common.function.admin.service.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.exception.CIPServiceException;
import com.yd.common.function.admin.dao.CIP_admin_op_logDao;
import com.yd.common.function.admin.data.CIP_admin_op_logData;
import com.yd.common.function.admin.data.CIP_admin_run_logData;
import com.yd.common.function.admin.data.po.CIP_admin_op_logPO;
import com.yd.common.function.admin.data.vo.CIP_admin_op_logVO;
import com.yd.common.function.admin.service.CIP_admin_op_logService;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntimeOperator;

/**
 * <p>Service实现类</p>
 * <p>Class: CIP_admin_op_logService</p>
 *
 * @since 2015-07-27 02:40:51
 */

@Service(value="cipOpLogService")
public class CIP_admin_op_logServiceImpl implements CIP_admin_op_logService {
	
	@Autowired
	private CIP_admin_op_logDao dataDao = null;
	@Autowired
	private CIP_admin_op_logDao opLogDao =null;
	
	/** 
	 * 修改数据
	 */
	@Override
	public void updateData(CIP_admin_op_logVO vo, CIPRuntimeOperator operateInf){
		//TODO
		CIP_admin_op_logPO po = vo.toPO();
		Object[] keys = po.getKeys();
		CIP_admin_op_logPO po0 = dataDao.getSingle(keys);
		if( po0 == null ){
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
		}
		
		po.setOperator(operateInf.getSubject_id());
		
		
		dataDao.update(po);
		
	}
	
	/** 
	 * 添加数据
	 */
	@Override
	public void addData(CIP_admin_op_logVO vo, CIPRuntimeOperator operateInf){
		CIP_admin_op_logPO po = vo.toPO();
		Object[] keys = po.getKeys();
		CIP_admin_op_logPO po0 = dataDao.getSingle(keys);
		if( po0!= null ){
			throw new CIPServiceException(CIPErrorCode.ERROR_DUMPLICATE_PRIMARY_KEY);
		}
		
		//TODO　添加记录基本判断
		po.setCreate_time(Timestamp.valueOf(operateInf.getOperate_tm()));
		po.setOperator(operateInf.getSubject_id());
		
		dataDao.add(po);
	}
	
	/** 
	 * 删除数据
	 */
	@Override
	public void deleteData(Object[] keys, CIPRuntimeOperator operateInf){
		dataDao.delete(keys);
		
	}
	
	/** 
	 * 获取数据
	 */		
	@Override
	public CIP_admin_op_logVO getData(Object[] keys ){
		CIP_admin_op_logPO  po = dataDao.getSingle(keys);
		if(po == null)
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
			
		return po.toVO();
	}
	
	/** 
	 * 检索数据
	 */		
	@Override
	public List<CIP_admin_op_logData> searchData(CIPPageInfo page, CIPReqCondition[] conditions){
		
		List<CIP_admin_op_logData> datas = dataDao.searchData(page, conditions);
		return datas;
	}

	
	public List<Map<String, Object>> exportEntities(CIPPageInfo page,CIPReqCondition[] conditions,
			CIPRuntimeOperator operateInf, boolean xFirst){
		if(xFirst){
			CIP_admin_op_logPO log = new CIP_admin_op_logPO();
			log.setOp_seq_no(System.currentTimeMillis());
			log.setOp_table_id("cip_admin_op_log");
			log.setOp_obj_id("");
			log.setOp_type("E");
			log.setRemark("批量导出数据");
			log.setOperator(operateInf.getSubject_id());
			log.setCreate_time(Timestamp.valueOf(operateInf.getOperate_tm()));
			opLogDao.add(log);
		}
		return dataDao.exportEntities(page, conditions, xFirst);
	}

	/**
	* TODO 获取系统运行日志信息 .
	* @see com.yd.common.function.admin.service.CIP_admin_op_logService#getLogData()
	*/
	@Override
	public List<CIP_admin_run_logData> getLogData() {
		List<CIP_admin_run_logData> datas = new ArrayList<CIP_admin_run_logData>();
		    Logger logger = Logger.getRootLogger();
		    Enumeration enumeration = logger.getAllAppenders();//拿到所有的appender
		    List<String> objList = new ArrayList<>();
		    while (enumeration.hasMoreElements()) {//遍历appender集合
				Appender appender = (Appender) enumeration.nextElement();
				//拿到appender类型      筛选出file类型
				String clasString = appender.getClass().toString();
				if(clasString.contains("FileAppender")){
					String name = appender.getName();
					objList.add(name);					
				}		
			}
            int index;
		    for(int i=0;i<objList.size();i++){
		    	String name = objList.get(i);
		    	FileAppender appender = (FileAppender)logger.getAppender(name);
		    	String filePath = appender.getFile();//拿到文件的路径
		 	    index = filePath.lastIndexOf("/");
		 	    //截取路径获取 文件信息
		 	    String text = filePath.substring(index+1).trim();
		 	    CIP_admin_run_logData data = new CIP_admin_run_logData();
		 	    data.setFile_obj(name);
		 	    data.setFile_name(text);
		 	    datas.add(data);
		    }
		   
		return datas;
	}

	/**
	* TODO 查看运行日志详情.
	* @see com.yd.common.function.admin.service.CIP_admin_op_logService#getLogMessage()
	*/
	@Override
	public List<String> getLogMessage(String obj) {
		//通过文件appender对象拿到配置文件下的文件内容
		Logger logger = Logger.getRootLogger();
		FileAppender appender = (FileAppender)logger.getAppender(obj);
    	String filePath = appender.getFile();//拿到文件路径下文件
    	List<String> list = new ArrayList<String>();
    	BufferedReader reader = null;
		try {
			// 指定读取文件
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
			String str = null;
			while ((str = reader.readLine()) != null) {
				list.add(str);
			}
		} catch (FileNotFoundException e) {
			CIPErrorCode fileError = new CIPErrorCode(1, "日志文件不存在");
			throw new CIPServiceException(fileError);			
		} catch (IOException e) {
			e.printStackTrace();
			CIPErrorCode ioError = new CIPErrorCode(2, "文件读取异常");
			throw new CIPServiceException(ioError);
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				CIPErrorCode ioError = new CIPErrorCode(3, "io流异常");
				throw new CIPServiceException(ioError);
			}
		}
		return list;
	}

	/**
	* TODO 简单描述该方法的实现功能（可选）.
	* @see com.yd.common.function.admin.service.CIP_admin_op_logService#logOp(com.yd.common.function.admin.data.vo.CIP_admin_op_logVO)
	*/
	@Override
	public void logOp(CIP_admin_op_logVO log) {
		// TODO Auto-generated method stub
		
	}

}