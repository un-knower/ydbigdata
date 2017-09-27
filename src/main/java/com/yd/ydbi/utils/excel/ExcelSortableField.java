package com.yd.ydbi.utils.excel;

import java.lang.reflect.Field;

public class ExcelSortableField {

	private ExcelTitle excelTitle;
	private Field field;
	public ExcelTitle getExcelTitle() {
		return excelTitle;
	}
	public void setExcelTitle(ExcelTitle excelTitle) {
		this.excelTitle = excelTitle;
	}
	public Field getField() {
		return field;
	}
	public void setField(Field field) {
		this.field = field;
	}
	public ExcelSortableField(ExcelTitle excelTitle, Field field) {
		super();
		this.excelTitle = excelTitle;
		this.field = field;
	}
	
}
