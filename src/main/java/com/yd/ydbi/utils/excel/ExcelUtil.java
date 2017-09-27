package com.yd.ydbi.utils.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtil <T> {
	//默认列宽
	private static int columnWidth = 21;
	private Logger logger = Logger.getLogger(ExcelUtil.class);

	private Workbook workbook;// 工作簿
	
	public ExcelUtil() {
		
	}

	/**
	 * 导入时通过文件初始化
	 * @param file
	 */
	public ExcelUtil(File file,String exclType) {
		initWorkBook(file,exclType);
	}

	/**
	 * 获取sheet
	 * @param sheetNumber
	 * @return
	 */
	public Sheet getSheet(int sheetNumber){
		return workbook.getSheetAt(sheetNumber);
	}
	
	public Workbook getHSSFWorkbook(){
		return workbook;
	}
	/**
	 * 判断workbook 是否为空
	 * @return
	 */
	public boolean isNullWorkBook(){
		return workbook==null?true:false;
	}
	
	/**
	 * 初始化workbook方法
	 * @param file
	 */
	public void initWorkBook(File file ,String exclType){
		try {
			if(".xlsx".equals(exclType)){
				workbook = new XSSFWorkbook(new FileInputStream(file));
			}
			else if(".xls".equals(exclType)){
				workbook = new HSSFWorkbook(new FileInputStream(file));
			}else{
				throw new RuntimeException("不是Excel文件");
			}
		} catch (IOException e) {
			throw new RuntimeException("Excel创建失败");
		} 
	}
	
	public void initWorkBook(InputStream input,String exclType){
		
		try {
			if(".xlsx".equals(exclType)){
				workbook = new XSSFWorkbook(input);
			}
			else if(".xls".equals(exclType)){
				workbook = new HSSFWorkbook(input);
			}else{
				throw new RuntimeException("不是Excel文件");
			}
		} catch (IOException e) {
			throw new RuntimeException("Excel创建失败");
		} 
	}
	
	/**
	 * 获取sheet 标题部分
	 * @param sheetNumber
	 * @param titleRow
	 * @return
	 */
	public List<String> getTitleFromSheet(int sheetNumber, int titleRow){
		// 获得指定的sheet
		Sheet sheet =  workbook.getSheetAt(sheetNumber);
		return getTitleFromSheet(sheet,titleRow);
	}
	
	
	/**
	 * 根据sheet获取sheet的标题部分
	 * @param sheet
	 * @param titleRow
	 * @return
	 */
	public List<String> getTitleFromSheet(Sheet sheet, int titleRow){
		List<String> titles = new ArrayList<String>();
		// 获得指定的sheet
		int rowCount = sheet.getLastRowNum();
		if (rowCount < titleRow ) {
			return null;
		}
		//获取一行
		Row row = sheet.getRow(titleRow);
		if (null != row) {
			int cellCount = row.getLastCellNum();
			// 遍历列cell
			for (int cellIndex = 0; cellIndex < cellCount; cellIndex++) {
				Cell cell = row.getCell(cellIndex);
				// 获得指定单元格中的数据
				Object cellStr = this.getCellString(cell);
				if(cellStr!= null){
					titles.add(cellStr.toString());
				}
				
			}
		}
		
		return titles;
	}
	
	/**
	 * 获取第sheetNumber个sheet的数据，从rowIndex 开始
	 * @param sheetNumber
	 * @param rowStart
	 * @return
	 */
	public List<List<Object>> getDatasInSheet(int sheetNumber, int rowStart) {
		List<List<Object>> result = new ArrayList<List<Object>>();

		// 获得指定的sheet
	    Sheet sheet = workbook.getSheetAt(sheetNumber);
		// 获得sheet总行数
		int rowCount = sheet.getLastRowNum();
		logger.info("found excel rows count:" + rowCount);
		if (rowCount < 1) {
			return result;
		}
		if(rowStart<0){
			rowStart =0;
		}
		// 遍历行row
		for ( ; rowStart <= rowCount; rowStart++) {
			// 获得行对象
			Row row = sheet.getRow(rowStart);
			if (null != row) {
				List<Object> rowData = new ArrayList<Object>();
				// 获得本行中单元格的个数
				int cellCount = row.getLastCellNum();
				int flag = 0;  //记录为空的数量
				// 遍历列cell
				for (int cellIndex = 0; cellIndex < cellCount; cellIndex++) {
					Cell cell = row.getCell(cellIndex);
					// 获得指定单元格中的数据
					Object cellStr = this.getCellString(cell);
					if(cellStr==null){
						flag++ ;
					}
					rowData.add(cellStr);
				}
				if(flag != cellCount){  //如果数据不全部为空则添加到列表中
					result.add(rowData);
				}
				
			}
		}

		return result;
	}
	
	/**
	 * 获取第sheetNumber个sheet的数据，从rowIndex 开始
	 * @param sheetNumber
	 * @param rowStart
	 * @return
	 */
	public List<Map<String, String>> getDataInSheet(int sheetNumber, int rowStart){
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		
		// 获得指定的sheet
		Sheet sheet = workbook.getSheetAt(sheetNumber);
		// 获得sheet总行数
		int rowCount = sheet.getLastRowNum();
		logger.info("found excel rows count:" + rowCount);
		if (rowCount < 1) {
			return result;
		}
		if(rowStart<0){
			rowStart =0;
		}
		//获取列头
		List<String> titleList = this.getTitleFromSheet(0, 0);
		
		// 遍历行row
		for ( ; rowStart <= rowCount; rowStart++) {
			// 获得行对象
			Row row = sheet.getRow(rowStart);
			if (null != row) {
				Map<String, String> rowData = new HashMap<String, String>();
				// 获得本行中单元格的个数
				int cellCount = titleList.size();
				int flag = 0;  //记录为空的数量
				// 遍历列cell
				for (int cellIndex = 0; cellIndex < cellCount; cellIndex++) {
					Cell cell = row.getCell(cellIndex);
					// 获得指定单元格中的数据
					Object cellStr = this.getCellString(cell);
					if(cellStr==null){
						flag++ ;
					}
					rowData.put(titleList.get(cellIndex),cellStr!=null?cellStr.toString().trim():"");
				}
				if(flag != cellCount){  //如果数据不全部为空则添加到列表中
					result.add(rowData);
				}
				
			}
		}

		return result;
	}
	
	public List<T> getDataInSheet(int sheetNumber, int rowStart, Class<T> clazz) throws Exception{
		List<T> result = new ArrayList<T>();
		
		// 获得指定的sheet
		Sheet sheet = workbook.getSheetAt(sheetNumber);
		// 获得sheet总行数
		int rowCount = sheet.getLastRowNum();
		logger.info("found excel rows count:" + rowCount);
		if (rowCount < 1) {
			return result;
		}
		if(rowStart<0){
			rowStart =0;
		}
		//获取列头
		List<String> titleList = this.getTitleFromSheet(0, 0);
		Map<String, String> excelNameMap = ExcelReflectUtil.getFieldExcelAnnotationMap(clazz);
		System.out.println(excelNameMap.keySet());
		System.out.println(excelNameMap.keySet().size());
		System.out.println(excelNameMap.keySet()+":"+excelNameMap.keySet().containsAll(titleList));
		if(titleList.size()<excelNameMap.keySet().size() || !excelNameMap.keySet().containsAll(titleList)){
			throw new RuntimeException("Excel表头为空");
		}
		Constructor<?> constructor = clazz.getConstructor();
		
		// 遍历行row
		for ( ; rowStart <= rowCount; rowStart++) {
			// 获得行对象
			Row row = sheet.getRow(rowStart);
			if (null != row) {
				Object obj = constructor.newInstance();
				// 获得本行中单元格的个数
				int cellCount = titleList.size();
				int flag = 0;  //记录为空的数量
				// 遍历列cell
				for (int cellIndex = 0; cellIndex < cellCount; cellIndex++) {
				    Cell cell = row.getCell(cellIndex);
				    
					// 获得指定单元格中的数据
					Object cellStr = this.getCellString(cell);
					if(cellStr==null){
						flag++ ;
					}
					ExcelReflectUtil.invokeSetMethod4Field(obj, excelNameMap.get(titleList.get(cellIndex)), cellStr!=null?cellStr.toString().trim():"");
				}
				if(flag != cellCount){  //如果数据不全部为空则添加到列表中
					result.add((T) obj);
				}
				
			}
		}

		return result;
	}
	
	/**
	 * 获取单元格的数据
	 * @param cell
	 * @return
	 */
	private Object getCellString(Cell cell) {
		Object result = null;
		if (cell != null) {
			// 单元格类型：Numeric:0,String:1,Formula:2,Blank:3,Boolean:4,Error:5
			int cellType = cell.getCellType();
			switch (cellType) {
			case Cell.CELL_TYPE_STRING:
				result = cell.getRichStringCellValue().getString();
				break;
			case Cell.CELL_TYPE_NUMERIC: 
				if(DateUtil.isCellDateFormatted(cell)){ //判断是否为日期格式
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					result = dateFormat.format(cell.getDateCellValue());
				}else{
					BigDecimal big=new BigDecimal(cell.getNumericCellValue());  
					result = big.toString(); 
				}
//				result = cell.getNumericCellValue();
				break;
			case Cell.CELL_TYPE_FORMULA:
				result = cell.getNumericCellValue();
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				result = cell.getBooleanCellValue();
				break;
			case Cell.CELL_TYPE_BLANK:
				result = null;
				break;
			case Cell.CELL_TYPE_ERROR:
				result = null;
				break;
			default:
				System.out.println("枚举了所有类型");
				break;
			}
		}
		return result;
	}
	
	
	
	
	/**
	 * 根据value 生成workBook
	 * @param sheetName
	 * @param value
	 * @return
	 * @throws IOException 
	 */
	public static SXSSFWorkbook createWorkBook(String sheetName, LinkedHashMap<String, String> excelTitle, List<Map<String,Object>> value) throws IOException {
		SXSSFWorkbook wb = new SXSSFWorkbook();
		// 创建一个SHEET
		Sheet sheet1 = wb.createSheet(sheetName);
		if(value== null  || value.isEmpty()){
			return wb;
		}
		Object[] title =  excelTitle.keySet().toArray();
//		String[] title = { "编号", "产品名称", "产品价格", "产品数量", "生产日期", "产地", "是否出口" };
		int i = 0;
		// 创建一行
		Row row = sheet1.createRow(0);
/*		// 生成一个样式  
        CellStyle style = wb.createCellStyle();
        // 设置这些样式  
        style.setAlignment(SXSSFCellStyle.ALIGN_CENTER);  
        // 生成一个字体  
        Font font = wb.createFont();   
        font.setBoldweight(SXSSFFont.BOLDWEIGHT_BOLD);  
        // 把字体应用到当前的样式  
        style.setFont(font);  */
		// 填充标题
		for (Object s : title) {
			Cell cell = row.createCell(i);
			cell.setCellType(SXSSFCell.CELL_TYPE_STRING); 
			cell.setCellStyle(getTitleStyle(wb));
			cell.setCellValue(excelTitle.get(s));
			sheet1.setColumnWidth(i, 20*256);
			i++;
		}

		int indexRow = 1;  //行号
		int indexCell = 0;  //列
		for(Map<String,Object> map: value){
			Row row1 = sheet1.createRow(indexRow);
			indexRow ++;
			indexCell = 0;
			for(Object cellTitle : title){
				Cell cell = row1.createCell(indexCell);
				Object obj = map.get(cellTitle.toString());
				String cellValue = "";
				if(obj!=null){
					cellValue = obj.toString();
				}
				cell.setCellValue(cellValue);
				indexCell++;
			}
			if(indexRow % 1000 == 0) {
				((SXSSFSheet)sheet1).flushRows(1000); // retain 100 last rows and flush all others
		            // ((SXSSFSheet)sh).flushRows() is a shortcut for ((SXSSFSheet)sh).flushRows(0),
					// this method flushes all rows
	        }
		}
		return wb;
	}
	
	public static SXSSFWorkbook appendWorkBook(SXSSFWorkbook wb, String sheetName, LinkedHashMap<String,String> excelTitle, List<Map<String,Object>> value) throws IOException {
		Sheet sheet = wb.getSheet(sheetName);
		if(value== null  || value.isEmpty()){
			return wb;
		}
		Object[] title =  excelTitle.keySet().toArray();
		
		int indexRow = sheet.getLastRowNum()+1;  //行号
		int indexCell = 0;  //列
		for(Map<String,Object> map: value){
			Row row1 = sheet.createRow(indexRow);
			indexRow ++;
			 indexCell = 0;
			for(Object cellTitle : title){
				Cell cell = row1.createCell(indexCell);
				Object obj = map.get(cellTitle.toString());
				String cellValue = "";
				if(obj!=null){
					cellValue = obj.toString();
				}
				cell.setCellValue(cellValue);
				indexCell++;
			}
			if(indexRow % 1000 == 0) {
				((SXSSFSheet)sheet).flushRows(1000); // retain 100 last rows and flush all others
					// ((SXSSFSheet)sh).flushRows() is a shortcut for ((SXSSFSheet)sh).flushRows(0),
					// this method flushes all rows
			}
		}
		return wb;
	}
	
	public SXSSFWorkbook createWorkBook(String sheetName, List<T> value, Class clazz) throws Exception {
		SXSSFWorkbook wb = new SXSSFWorkbook();
		// 创建一个SHEET
		Sheet sheet1 = wb.createSheet(sheetName);
		if(value== null  || value.isEmpty()){
			return wb;
		}
		Map<String, String> excelTitle = ExcelReflectUtil.getFieldExcelAnnotationMap(clazz);
		Object[] title =  excelTitle.keySet().toArray();
//		String[] title = { "编号", "产品名称", "产品价格", "产品数量", "生产日期", "产地", "是否出口" };
		int i = 0;
		// 创建一行
		Row row = sheet1.createRow(0);
/*		// 生成一个样式  
        CellStyle style = wb.createCellStyle();
        // 设置这些样式  
        style.setAlignment(SXSSFCellStyle.ALIGN_CENTER);  
        // 生成一个字体  
        Font font = wb.createFont();   
        font.setBoldweight(SXSSFFont.BOLDWEIGHT_BOLD);  
        // 把字体应用到当前的样式  
        style.setFont(font); */ 
		// 填充标题
		for (Object s : title) {
			Cell cell = row.createCell(i);
			cell.setCellType(SXSSFCell.CELL_TYPE_STRING); 
			cell.setCellStyle(getTitleStyle(wb));
			cell.setCellValue((String)s);
			sheet1.setColumnWidth(i, 20*256);
			i++;
		}

		int indexRow = 1;  //行号
		int indexCell = 0;  //列
		for(T t: value){
			Row row1 = sheet1.createRow(indexRow);
			indexRow ++;
			indexCell = 0;
			for(Object cellTitle : title){
				Cell cell = row1.createCell(indexCell);
				Object obj = ExcelReflectUtil.invokeGetMethod4Field(t, excelTitle.get(cellTitle.toString()));
				String cellValue = "";
				if(obj!=null){
					cellValue = obj.toString();
				}
				cell.setCellValue(cellValue);
				indexCell++;
			}
			if(indexRow % 1000 == 0) {
				((SXSSFSheet)sheet1).flushRows(1000); // retain 1000 last rows and flush all others
		            // ((SXSSFSheet)sh).flushRows() is a shortcut for ((SXSSFSheet)sh).flushRows(0),
					// this method flushes all rows
	        }
		}
		return wb;
	}
	
	public SXSSFWorkbook appendWorkBook(SXSSFWorkbook wb, String sheetName, List<T> value, Class clazz) throws Exception {
		Sheet sheet = wb.getSheet(sheetName);
		if(value== null  || value.isEmpty()){
			return wb;
		}
		Map<String, String> excelTitle = ExcelReflectUtil.getFieldExcelAnnotationMap(clazz);
		Object[] title =  excelTitle.keySet().toArray();
		
		int indexRow = sheet.getLastRowNum()+1;  //行号
		int indexCell = 0;  //列
		for(T t: value){
			Row row1 = sheet.createRow(indexRow);
			indexRow ++;
			 indexCell = 0;
			for(Object cellTitle : title){
				Cell cell = row1.createCell(indexCell);
				Object obj = ExcelReflectUtil.invokeGetMethod4Field(t, excelTitle.get(cellTitle.toString()));
				String cellValue = "";
				if(obj!=null){
					cellValue = obj.toString();
				}
				cell.setCellValue(cellValue);
				indexCell++;
			}
			if(indexRow % 1000 == 0) {
				((SXSSFSheet)sheet).flushRows(1000); // retain 1000 last rows and flush all others
					// ((SXSSFSheet)sh).flushRows() is a shortcut for ((SXSSFSheet)sh).flushRows(0),
					// this method flushes all rows
			}
		}
		return wb;
	}
	
	public SXSSFWorkbook createWorkBook(String sheetName, List<T> value, Map<String, String> excelTitle) throws Exception {
		SXSSFWorkbook wb = new SXSSFWorkbook();
		// 创建一个SHEET
		Sheet sheet1 = wb.createSheet(sheetName);
		if(value== null  || value.isEmpty()){
			return wb;
		}
		//Map<String, String> excelTitle = ExcelReflectUtil.getFieldExcelAnnotationMap(clazz);
		Object[] title =  excelTitle.keySet().toArray();
//		String[] title = { "编号", "产品名称", "产品价格", "产品数量", "生产日期", "产地", "是否出口" };
		int i = 0;
		// 创建一行
		Row row = sheet1.createRow(0);
/*		// 生成一个样式  
        CellStyle style = wb.createCellStyle();
        // 设置这些样式  
        style.setAlignment(SXSSFCellStyle.ALIGN_CENTER);  
        // 生成一个字体  
        Font font = wb.createFont();   
        font.setBoldweight(SXSSFFont.BOLDWEIGHT_BOLD);  
        // 把字体应用到当前的样式  
        style.setFont(font); */ 
		// 填充标题
		for (Object s : title) {
			Cell cell = row.createCell(i);
			cell.setCellType(SXSSFCell.CELL_TYPE_STRING); 
			cell.setCellStyle(getTitleStyle(wb));
			cell.setCellValue(excelTitle.get(s));
			sheet1.setColumnWidth(i, 20*256);
			i++;
		}

		int indexRow = 1;  //行号
		int indexCell = 0;  //列
		for(T t: value){
			Row row1 = sheet1.createRow(indexRow);
			indexRow ++;
			indexCell = 0;
			for(Object cellTitle : title){
				Cell cell = row1.createCell(indexCell);
				Object obj = ExcelReflectUtil.invokeGetMethod4Field(t, cellTitle.toString());
				String cellValue = "";
				if(obj!=null){
					cellValue = obj.toString();
				}
				cell.setCellValue(cellValue);
				indexCell++;
			}
			if(indexRow % 1000 == 0) {
				((SXSSFSheet)sheet1).flushRows(1000); // retain 100 last rows and flush all others
		            // ((SXSSFSheet)sh).flushRows() is a shortcut for ((SXSSFSheet)sh).flushRows(0),
					// this method flushes all rows
	        }
		}
		return wb;
	}
	
	public SXSSFWorkbook appendWorkBook(SXSSFWorkbook wb, String sheetName, List<T> value, Map<String, String> excelTitle) throws Exception {
		Sheet sheet = wb.getSheet(sheetName);
		if(value== null  || value.isEmpty()){
			return wb;
		}
		//Map<String, String> excelTitle = ExcelReflectUtil.getFieldExcelAnnotationMap(clazz);
		Object[] title =  excelTitle.keySet().toArray();
		
		int indexRow = sheet.getLastRowNum()+1;  //行号
		int indexCell = 0;  //列
		for(T t: value){
			Row row1 = sheet.createRow(indexRow);
			indexRow ++;
			 indexCell = 0;
			for(Object cellTitle : title){
				Cell cell = row1.createCell(indexCell);
				Object obj = ExcelReflectUtil.invokeGetMethod4Field(t, cellTitle.toString());
				String cellValue = "";
				if(obj!=null){
					cellValue = obj.toString();
				}
				cell.setCellValue(cellValue);
				indexCell++;
			}
			if(indexRow % 1000 == 0) {
				((SXSSFSheet)sheet).flushRows(1000); // retain 100 last rows and flush all others
					// ((SXSSFSheet)sh).flushRows() is a shortcut for ((SXSSFSheet)sh).flushRows(0),
					// this method flushes all rows
			}
		}
		return wb;
	}
	
	/**
	 * exce表头单元格样式处理
	 * @param workbook
	 * @return
	 */
	public static CellStyle getTitleStyle(Workbook workbook) {
		// 产生Excel表头
		CellStyle titleStyle = workbook.createCellStyle();
		titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置边框样式
		titleStyle.setBorderLeft((short) 1); // 左边框
		titleStyle.setBorderRight((short) 1); // 右边框
		titleStyle.setBorderTop((short) 1); // 左边框
		titleStyle.setBorderBottom((short) 1); // 右边框
		titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); // 顶边框
		titleStyle.setFillForegroundColor(HSSFColor.YELLOW.index); // 填充的背景颜色
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); // 填充图案

		return titleStyle;
	}
	
	/**
	 * 根据workbook生成excel 文件
	 * @param wb
	 * @param path ["d:\\test.xls"]
	 */
	public void createExcel(HSSFWorkbook wb,String path){
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream(path);
			wb.write(fileOut);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				fileOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 为单元格创建一个样式
	 * @param wb
	 * @param style  ("#.##")
	 */
	public  CellStyle setCellStyle(Workbook wb,String style){
		if(wb == null){
			return null;
		}
		CellStyle cellStyle = wb.createCellStyle();
		DataFormat format = wb.createDataFormat();
		cellStyle.setDataFormat(format.getFormat(style));
//		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		// 设定样式
//		cell.setCellStyle(cellStyle);
//		HSSFCellStyle cellStyle = wb.createCellStyle();  
//		cellStyle.setFont(font);  
//		cellStyle.setAlignment(CellStyle.ALIGN_CENTER); // 水平布局：居中 
//		cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); 
//		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);  
////		cellStyle.setFillForegroundColor(CellStyle..LIGHT_YELLOW.index);  
//		cellStyle.setBorderBottom(CellStyle.BORDER_THIN);  
//		cellStyle.setBorderTop(CellStyle.BORDER_THIN);  
//		cellStyle.setBorderRight(CellStyle.BORDER_THIN);  
//		cellStyle.setBorderLeft(CellStyle.BORDER_THIN);  
//		cellStyle.setBottomBorderColor(CellStyle.DARK_RED.index);
		return cellStyle;
	}
	
	
	
	

	// test
	public static void main(String[] args) {
		File file = new File("d:\\导出KPI定义_2015-12-29.xls");
		ExcelUtil parser = new ExcelUtil(file,".xls");
		List<List<Object>> datas = parser.getDatasInSheet(0,0);
		for (int i = 0; i < datas.size(); i++) {
			List<Object> row = datas.get(i);
			for (short j = 0; j < row.size(); j++) {
				Object value = row.get(j);
				String data = String.valueOf(value);
				System.out.println(data + "\t");
			}
			System.out.println();
		}
		
		/*ExcelUtil parser = new ExcelUtil();
		parser.initWorkBook(new File("E:\\template_prising.xls"));
		try {
			List list = parser.getDataInSheet(0, 1, C9F_mkt1_prisingVO.class);
			File file = new File("E:\\template_prising1.xls");
			if(!file.exists()){
				file.createNewFile();
			}
			ExcelUtil parser2 = new ExcelUtil();
			SXSSFWorkbook wb = parser2.createWorkBook("sheet1", list, C9F_mkt1_prisingVO.class);
			wb.write(new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
//		List<Map<String,Object>> value = new ArrayList<Map<String,Object>> ();
//		Map<String,Object> map = new HashMap<String ,Object>();
//		map.put("coll1", "ssss");
//		map.put("coll2", "bbbb");
//		value.add(map);
//		
//		Map<String,Object> map1 = new HashMap<String ,Object>();
//		map1.put("coll1", "aaaa");
//		map1.put("coll2", "cccc");
//		value.add(map);
//		
//		//导出
//		ExcelSheetParser parser = new ExcelSheetParser( );
//		FileOutputStream fileOut = null;
//		try {
//			fileOut = new FileOutputStream("d:\\test.xls");
//			Workbook wb = parser.createWorkBook("ouy", value);
//			wb.write(fileOut);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		finally{
//			try {
//				fileOut.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
	}
	
}
