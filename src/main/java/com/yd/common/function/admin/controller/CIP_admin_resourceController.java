package com.yd.common.function.admin.controller;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.data.CIPReqParameter;
import com.yd.common.data.CIPResponseMsg;
import com.yd.common.data.CIPResponseQueryMsg;
import com.yd.common.exception.CIPDaoException;
import com.yd.common.exception.CIPRuntimeException;
import com.yd.common.exception.CIPServiceException;
import com.yd.common.function.admin.data.CIP_admin_iconData;
import com.yd.common.function.admin.data.CIP_admin_resourceData;
import com.yd.common.function.admin.data.mapper.CIP_admin_resourceMapper;
import com.yd.common.function.admin.data.vo.CIP_admin_resourceVO;
import com.yd.common.function.admin.service.CIP_admin_resourceService;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntime;
import com.yd.common.utils.DateUtils;
import com.yd.common.utils.ExcelSheetParser;
import com.yd.common.utils.JSONUtils;

/**
 * <p>控制层实现类</p>
 * <p>Class: CIP_admin_resourceController</p>
 *
 * @since 2015-07-27 02:40:52
 */
@RestController
@RequestMapping(value = "/actions/admin_resource")
public class CIP_admin_resourceController {
	
	@Autowired
	private CIP_admin_resourceService dataService  = null;
	
	private static final String C_ACTION_ADD_DATA = "CIP_admin_resource_addData";
	/**
	* 新增系统资源信息
	*/
	@RequestMapping(value="/addData")
	public CIPResponseMsg addData(@RequestBody CIP_admin_resourceVO vo) {
		CIPResponseMsg msg = new CIPResponseMsg();
		try {
			dataService.addData(vo,CIPRuntime.getOperateSubject());
			msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
			msg.msg = CIPErrorCode.CALL_SUCCESS.name;
		} catch (CIPServiceException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		} catch (CIPDaoException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		} catch (CIPRuntimeException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		}

		return msg;
	}

	private static final String C_ACTION_GET_DATA = "CIP_admin_resource_getData";
	/**
	* 查看系统资源信息
	*/
	@RequestMapping(value = "/getData")
	public CIPResponseMsg getData(@RequestBody CIP_admin_resourceVO vo) {
		CIPResponseMsg msg = new CIPResponseMsg();
		try {
			Object[] keys = vo.getKeys();
			CIP_admin_resourceVO vo0 = dataService.getData(keys);
			msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
			msg.msg = CIPErrorCode.CALL_SUCCESS.name;
			msg.data = vo0;
		} catch (CIPServiceException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		} catch (CIPDaoException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		} catch (CIPRuntimeException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		}

		return msg;
	}
	
	private static final String C_ACTION_DELETE_DATA = "CIP_admin_resource_deleteData";
	/**
	* 删除系统资源信息
	*/
	@RequestMapping(value = "/deleteData")
	public CIPResponseMsg deleteData(@RequestBody CIP_admin_resourceVO vo) {
		CIPResponseMsg msg = new CIPResponseMsg();
		try {
			Object[] keys = vo.getKeys();
			dataService.deleteData(keys,CIPRuntime.getOperateSubject());
			msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
			msg.msg = CIPErrorCode.CALL_SUCCESS.name;
		} catch (CIPServiceException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		} catch (CIPDaoException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		} catch (CIPRuntimeException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		}

		return msg;
	}
	
	private static final String C_ACTION_UPDATE_DATA = "CIP_admin_resource_updateData";
	/**
	* 更新系统资源信息
	*/
	@RequestMapping(value = "/updateData")
	public CIPResponseMsg updateData(@RequestBody CIP_admin_resourceVO vo) {
		CIPResponseMsg msg = new CIPResponseMsg();
		try {
			dataService.updateData(vo, CIPRuntime.getOperateSubject());
			msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
			msg.msg = CIPErrorCode.CALL_SUCCESS.name;
		} catch (CIPServiceException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		} catch (CIPDaoException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		} catch (CIPRuntimeException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		}

		return msg;
	}
	
	
	private static final String C_ACTION_SEARCH_DATA = "CIP_admin_resource_searchData";
	
	/**
	* 查询系统资源信息
	*/
	@RequestMapping(value = "/searchData")
	public CIPResponseQueryMsg searchData(CIPReqParameter parameter) {
		CIPResponseQueryMsg msg = new CIPResponseQueryMsg();
		try {
			CIPPageInfo page = new CIPPageInfo(parameter.getPage(),parameter.getRows());
			
			String conditonStr = parameter.getSearch_condition();
			
			CIPReqCondition[] conditions = null;
			if(conditonStr!=null){
				Map reqCondtions = JSONUtils.convertJson2Object(conditonStr, HashMap.class);
				//CIPConfigureData0 data = JSONUtils.convertJson2Object(msg.data.toString(), CIPConfigureData0.class);
				Set<String> keys = reqCondtions.keySet();
				CIPReqCondition req = null;
				CIPReqCondition[] tempConditions = new CIPReqCondition[keys.size()];
				int i = 0;
				String value;
				for( String key : keys ){
					value = (String) reqCondtions.get(key);
					if(value==null || value.trim().equals(""))
						continue;
					req = new CIPReqCondition();
					req.setFieldName(key);
					req.setLowValue(value);
					tempConditions[i++] = req;
				}
				if( i > 0 ){
					conditions = new CIPReqCondition[i];
					System.arraycopy(tempConditions, 0, conditions, 0, i);						
					/*String findValue = conditions[i-1].getLowValue();
					if(findValue.contains("%")){
						conditions[i-1].setOperator(8);
					}*/
				}
				
			}
			
			List<CIP_admin_resourceData> vos = dataService.searchData(page,conditions);
			
			msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
			msg.msg = CIPErrorCode.CALL_SUCCESS.name;
			msg.rows = vos;
			msg.total = page.getTotal();
		} catch (CIPServiceException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		} catch (CIPDaoException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		} catch (CIPRuntimeException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		}

		return msg;
	}
	

	
	private static final String C_ACTION_EXPORT_EXCEL = "CIP_admin_resource_exportEntities";
	
	/** 
	 * 导出Excel
	 */
	@RequestMapping(value="/exportEntities")
	public void exportEntities(CIPReqParameter parameter, HttpServletRequest request, HttpServletResponse response) throws Exception {
		OutputStream os = null;
		try {
			//初始化检索条件
			//CIPPageInfo page = new CIPPageInfo(parameter.getPage(),parameter.getRows());
			String conditonStr = parameter.getSearch_condition();
			
			CIPReqCondition[] conditions = null;
			if(conditonStr!=null){
				Map reqCondtions = JSONUtils.convertJson2Object(conditonStr, HashMap.class);
				//CIPConfigureData0 data = JSONUtils.convertJson2Object(msg.data.toString(), CIPConfigureData0.class);
				Set<String> keys = reqCondtions.keySet();
				CIPReqCondition req = null;
				CIPReqCondition[] tempConditions = new CIPReqCondition[keys.size()];
				int i = 0;
				String value;
				for( String key : keys ){
					value = (String) reqCondtions.get(key);
					if(value==null || value.trim().equals(""))
						continue;
					req = new CIPReqCondition();
					req.setFieldName(key);
					req.setLowValue(value);
					tempConditions[i++] = req;
				}
				if( i > 0 ){
					conditions = new CIPReqCondition[i];
					System.arraycopy(tempConditions, 0, conditions, 0, i);
				}
			}
			
			//导出excel
			String as = "导出系统资源信息_"+DateUtils.getDate(new Date());

			String fileName = new String(as.getBytes("GB2312"), "ISO_8859_1");
			response.setCharacterEncoding("utf-8");  
			response.setContentType("application/vnd.ms-excel");  
			response.setHeader("Content-Disposition", "attachment;fileName="+fileName+".xls");  
			String sheetName = "系统资源信息";
			CIPPageInfo page = new CIPPageInfo(1,10000);

			//获取订单，每次最多导出1万条记录
			List<Map<String, Object>> entities = dataService.exportEntities(page, conditions, CIPRuntime.getOperateSubject(), true);
			SXSSFWorkbook wb = ExcelSheetParser.createWorkBook(sheetName,
					CIP_admin_resourceMapper.getExcelTitle(), entities);
			
			int total = page.getTotal();
			int rows = page.getRows();
			if (total > rows) {
				int count = total / rows + 1;
				for (int i = 2; i <= count; i++) {
					page.setPage(i);
					entities = dataService.exportEntities(page, conditions, CIPRuntime.getOperateSubject(), false);
					ExcelSheetParser.appendWorkBook(wb, sheetName, CIP_admin_resourceMapper.getExcelTitle(), entities);
				}
			}
			os = response.getOutputStream();
			wb.write(os);
		}
		catch (Exception e) {
			throw e;
		}finally {
			try {
				if(os!=null){
					os.flush();
					os.close();
				}
			} catch (IOException e) {
				
			}				
		}
	}
	
	/*@RequestMapping(value = "/selectIcon")
	public CIPResponseQueryMsg selectIcon(HttpServletRequest request) throws FileNotFoundException {
		CIPResponseQueryMsg msg = new CIPResponseQueryMsg();		
		String iconPath = request.getRealPath("/ui/js/jquery-easyui/icons");//获取存放图片的文件夹路径
		try {	
			InputStream inBuff = null;
			byte[] data = null;
			List<CIP_admin_iconData> iconList= new ArrayList<CIP_admin_iconData>();	
              File file = new File(iconPath);
              File[] array = file.listFiles();//把file文件下的所有文件及文件夹放在数组中
              for(int i=0;i<array.length;i++){
            	 CIP_admin_iconData iconData = new CIP_admin_iconData();
            	 String iconName = array[i].getName();  
            	 String name = iconName.replaceAll("[.][^.]+$", "");//获取文件名          	   
            	 iconData.setIcon_id(name);//将图标名称设为图标id
                 inBuff = new FileInputStream(array[i]);
              try {
				data = new byte[inBuff.available()];
				inBuff.read(data);
	            inBuff.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new CIPServiceException(CIPErrorCode.ERROR_TECHNICAL_ERROR);
			}
           // 对字节数组Base64编码
            String code =  Base64.encodeBase64String(data);// 返回Base64编码过的字节数组字符串          
            iconData.setIcon_code(code);
            iconList.add(iconData);     
          }             
			msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
			msg.msg = CIPErrorCode.CALL_SUCCESS.name;
			msg.data = iconList;
		} catch (CIPServiceException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		} catch (CIPDaoException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		} catch (CIPRuntimeException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		}
		return msg;
	}*/
	//将图片进行转码并存放在txt文件中(生成的txt文件将会在服务器根目录下)
	/*@RequestMapping(value = "/selectIcon")
	public CIPResponseQueryMsg selectIcon(HttpServletRequest request) throws FileNotFoundException {
		CIPResponseQueryMsg msg = new CIPResponseQueryMsg();		
		String iconPath = request.getRealPath("/ui/js/jquery-easyui/icons");//获取存放图片的文件夹路径
		try {	
			InputStream inBuff = null;
			byte[] data = null;
			List<CIP_admin_iconData> iconList= new ArrayList<CIP_admin_iconData>();	
              File file = new File(iconPath);
              File[] array = file.listFiles();//把file文件下的所有文件及文件夹放在数组中
              for(int i=0;i<array.length;i++){
            	 CIP_admin_iconData iconData = new CIP_admin_iconData();
            	 String iconName = array[i].getName();  
            	 String name = iconName.replaceAll("[.][^.]+$", "");//获取文件名          	   
            	 iconData.setIcon_id(name);//将图标名称设为图标id
                 inBuff = new FileInputStream(array[i]);
              try {
				data = new byte[inBuff.available()];
				inBuff.read(data);
	            inBuff.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new CIPServiceException(CIPErrorCode.ERROR_TECHNICAL_ERROR);
			}
           // 对字节数组Base64编码
            String code =  Base64.encodeBase64String(data);// 返回Base64编码过的字节数组字符串          
            iconData.setIcon_code(code);
            iconList.add(iconData);     
          }             
			msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
			msg.msg = CIPErrorCode.CALL_SUCCESS.name;
			msg.data = iconList;
			String jsonText = JSON.toJSONString(iconList, true);
			byte[] icondata=jsonText.getBytes();
			String path2="../icons.txt";
			try {
				File txtfile=new File(path2);
				if (!file.exists()) {
					file.createNewFile();
				}
				FileOutputStream fos=new FileOutputStream(txtfile);
				BufferedOutputStream bom=new BufferedOutputStream(fos);
				bom.write(icondata);
				bom.flush();
				bom.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (CIPServiceException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		} catch (CIPDaoException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		} catch (CIPRuntimeException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		}
		return msg;
	}*/
	private static final String C_ACTION_SELECT_ICON = "CIP_admin_resource_updateData";
	/**
	* selectIcon:(这里用一句话描述这个方法的作用).
	*
	* @author lyn
	* @param request
	* @return
	* @throws FileNotFoundException
	* @since JDK 1.7
	*/
	@RequestMapping(value = "/selectIcon")
	public CIPResponseQueryMsg selectIcon(HttpServletRequest request) throws FileNotFoundException {
		CIPResponseQueryMsg msg = new CIPResponseQueryMsg();
		byte[] icondata = null;
		//String iconPath="D:"+File.separator+"icons.txt";
		//File file1=new File(iconPath);
		InputStream fis=this.getClass().getResourceAsStream("/com/yd/common/icons.txt");
		//FileInputStream fis= new FileInputStream(file1);
		BufferedInputStream bim=new BufferedInputStream(fis);
		try {
			icondata=new byte[bim.available()];
			int len=bim.read(icondata);
			bim.read(icondata, 0, len);
			String iconStr=new String(icondata,"GB2312");
			bim.close();
			List<CIP_admin_iconData>  iconList = JSON.parseArray(iconStr,CIP_admin_iconData.class);            
			msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
			msg.msg = CIPErrorCode.CALL_SUCCESS.name;
			msg.data = iconList;
		} catch (CIPServiceException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		} catch (CIPDaoException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		} catch (CIPRuntimeException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		}catch (IOException e) {
			msg.errorCode = CIPErrorCode.ERROR_RESOURCE_IS_NOT_READY.code;
			msg.msg = CIPErrorCode.ERROR_RESOURCE_IS_NOT_READY.name;
		}
		return msg;
	}
	
	
}