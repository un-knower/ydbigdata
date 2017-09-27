package com.yd.common.tool.dbm;

import java.util.ArrayList;
import java.util.List;

public class CIPTable {

    private String            catalog = null;
    private String            schema = null;
    private String            tableName;
    private String            tableType;
    private String            tableAlias;
    private String            tableSuffix;
    private String            remarks;
    
    private String            className;
    private String            javaProperty;
    private String 			  dataType; //该表存储数据类型
    private String 			  appId;	//该表所属应用id
    private String 			  appName;	//应用名称
    private String  		  moduleId = null;
    private String 			  moduleName;
 
	private List<CIPColumn>   baseColumns = new ArrayList<CIPColumn>(); //表所有字段
    private List<CIPColumn>   primaryKeys = new ArrayList<CIPColumn>();  //表主键字段
    private List<CIPTxtField> txtColumns = new ArrayList<CIPTxtField>(); //表文本字段
    
    private List<String> 	  refJavaClasses = new ArrayList<String>();//java 数据类中需要引用的对象
    private List<CIPDomainInfo> domainTables = new ArrayList<CIPDomainInfo>(); //关联码表信息
    
    private String viewSql = null; //生成对应视图的sql代码
    
    
    
    public String getTableSuffix() {
		return tableSuffix;
	}

	public void setTableSuffix(String tableSuffix) {
		this.tableSuffix = tableSuffix;
	}

	public String getViewSql() {
		return viewSql;
	}

	public void setViewSql(String viewSql) {
		this.viewSql = viewSql;
	}

	public List<CIPDomainInfo> getDomainTables() {
		return domainTables;
	}

	public void setDomainTables(List<CIPDomainInfo> domainTables) {
		this.domainTables = domainTables;
	}

	public void addRefJavaClass(String javaClass){
		if( javaClass.startsWith("java.lang") )
			return;
    	for(String refCalss:refJavaClasses){
    		if(javaClass.equals(refCalss))
    			return;
    	}
    	
    	refJavaClasses.add(javaClass);
    }
    
    public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getTableName() {
		return tableName;
	}

	public String getAppId() {
		return appId;
	}

	public String getModuleId() {
		return moduleId;
	}

	public List<CIPColumn> getBaseColumns() {
		return baseColumns;
	}

	public List<CIPTxtField> getTxtColumns() {
		return txtColumns;
	}

	public List<String> getRefJavaClasses() {
		return refJavaClasses;
	}

	public void addTxtColumn(String columnId, String columnName){
		String columnTxt = columnId+"_name";
		CIPColumn c = getColumn(columnTxt);
		if( c != null )
			return;
		
    	CIPTxtField field = new CIPTxtField();
    	field.setTxtField(columnTxt);
    	field.setTxtFieldName(columnName+"名称");
    	txtColumns.add(field);
    }
        
    public void setTableName(String tableName) {
//    	tableName = tableName.toLowerCase();
        this.tableName = tableName;
        this.tableAlias = tableName;
        int index = tableName.indexOf('_');
        String tempStr = tableName.substring(index+1);
        appId = tableName.substring(0, index);
        className = appId.toUpperCase() + "_"+tempStr;
        tableSuffix = tempStr;
        this.javaProperty = className;
          
        if(tempStr.indexOf('_')>0 )
        	this.moduleId = tempStr.substring(0, tempStr.indexOf('_'));
        else 
        	this.moduleId = tempStr;
        
        //获取当前表存储的数据类型
        if(tableName.endsWith("_dm")){
        	//基础数据类型
        	dataType = CIPDBConstants.C_DATA_TYPE_DOMAIN;
        }
        else {
        	dataType = CIPDBConstants.C_DATA_TYPE_BUSINESS;
        }
        
    }

    public String getRemarks() {
        return remarks == null ? "" : remarks;
    }

    public String getRemarksUnicode() {
        return StringUtil.toUnicodeString(getRemarks());
    }

    public void setRemarks(String remarks) {
    	String[] tempStrs = remarks.split("/");
    	if( tempStrs.length > 1){
    		this.remarks = tempStrs[1];
    		this.moduleName = tempStrs[0];
    	}
    	else{
    		this.remarks = remarks;
    		this.moduleName = "";
    	}
        
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public CIPColumn getColumn(String columnName) {
        for (CIPColumn column : primaryKeys) {
            if (column.getColumnName().equals(columnName)) {
                return column;
            }
        }
        for (CIPColumn column : baseColumns) {
            if (column.getColumnName().equals(columnName)) {
                return column;
            }
        }
        return null;
    }

    public List<CIPColumn> getColumns() {
        List<CIPColumn> allColumns = new ArrayList<CIPColumn>();
        allColumns.addAll(primaryKeys);
        allColumns.addAll(baseColumns);
        return allColumns;
    }

    public void addColumn(CIPColumn column) {
        baseColumns.add(column);
    }

    public List<CIPColumn> getPrimaryKeys() {
        return primaryKeys;
    }

    public void addPrimaryKey(CIPColumn primaryKeyColumn) {
        primaryKeys.add(primaryKeyColumn);
    }

    public String getJavaProperty() {
        return javaProperty;
    }

    public void setJavaProperty(String javaProperty) {
        this.javaProperty = javaProperty;
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }

	public String getModuleName() {
		return moduleName;
	}
}
