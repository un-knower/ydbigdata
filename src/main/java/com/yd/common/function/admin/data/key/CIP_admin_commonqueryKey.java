package com.yd.common.function.admin.data.key;


/**
 * <p>实体key类</p>
 * <p>Table: cip_cadmin_commonquery - 通用查询配置表</p>
 *
 * @since 2016-08-04 01:17:07
 */
public class CIP_admin_commonqueryKey {	
	
	public Object[] getKeys(){
		return new Object[]{ 
		queryId
		};
	}
	
	public Object[] setKeys(String queryId){
		return new Object[]{ 
			queryId
		};
	}


    /** 
    * queryId - 查询id 
    */
	private String queryId;

	private String remark;
	
	private String operateCode;
	
	public String getQueryId(){
        return this.queryId;
    }
    
    public void setQueryId(String queryId){
        this.queryId = queryId;
    }

	public void setOperateCode(String operateCode){
		this.operateCode = operateCode;
	}
	
	public String getOperateCode(){
		return operateCode;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}
		
}