package com.yd.common.function.admin.data.po;

import com.yd.common.function.admin.data.vo.CIP_admin_commonqueryVO;


/**
 * <p>实体类</p>
 * <p>Table: cip_cadmin_commonquery - 通用查询配置表</p>
 *
 * @since 2016-08-04 01:17:07
 */
public class CIP_admin_commonqueryPO {

	public Object[] getKeys(){
		return new Object[]{ 
			queryId
		};
	}


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

	public CIP_admin_commonqueryVO toVO(){
		CIP_admin_commonqueryVO vo = new CIP_admin_commonqueryVO();
		
		vo.setQueryId(queryId);
		vo.setDescription(description);
		vo.setParamlist(paramlist);
		vo.setStatement(statement);
		vo.setCount_statement(count_statement);
		vo.setPagination(pagination);
		vo.setSingleRec(singleRec);
		
		return vo;
	}
		
}