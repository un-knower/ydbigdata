package com.yd.common.function.admin.data;


/**
 * <p>Data实体类</p>
 * <p>View: cip_cadmin_commonquery - 通用查询配置表</p>
 * 用于检索数据，建立在视图基础上
 * @since 2016-08-04 01:17:07
 */
public class CIP_admin_commonqueryData {

    /** 
    * queryId - 查询id 
    */
	private String queryId;
    /** 
    * description - 查询说明 
    */
	private String description;
    /** 
    * paramlist - 查询参数列表 
    */
	private String paramlist;
    /** 
    * statement - 查询语句 
    */
	private String statement;
    /** 
    * count_statement - 统计语句 
    */
	private String count_statement;
    /** 
    * pagination - 是否分页 
    */
	private String pagination;
    /** 
    * singleRec - 是否多条 
    */
	private String singleRec;
	/**
	 * pagination - 是否分页文本描述信息
	 */
	private String is_pagination;
	/**
	 * singleRec - 是否多条文本描述信息
	 */
	private String is_singleRec;

	public String getQueryId(){
        return this.queryId;
    }
    
    public void setQueryId(String queryId){
        this.queryId = queryId;
    }
	public String getDescription(){
        return this.description;
    }
    
    public void setDescription(String description){
        this.description = description;
    }
	public String getParamlist(){
        return this.paramlist;
    }
    
    public void setParamlist(String paramlist){
        this.paramlist = paramlist;
    }
	public String getStatement(){
        return this.statement;
    }
    
    public void setStatement(String statement){
        this.statement = statement;
    }
	public String getCount_statement(){
        return this.count_statement;
    }
    
    public void setCount_statement(String count_statement){
        this.count_statement = count_statement;
    }
	public String getPagination(){
        return this.pagination;
    }
    
    public void setPagination(String pagination){
        this.pagination = pagination;
    }
	public String getSingleRec(){
        return this.singleRec;
    }
    
    public void setSingleRec(String singleRec){
        this.singleRec = singleRec;
    }

	public String getIs_pagination() {
		return is_pagination;
	}

	public void setIs_pagination(String is_pagination) {
		this.is_pagination = is_pagination;
	}

	public String getIs_singleRec() {
		return is_singleRec;
	}

	public void setIs_singleRec(String is_singleRec) {
		this.is_singleRec = is_singleRec;
	}

}