package com.yd.common.function.admin.data.po;

import java.sql.Timestamp;

import com.yd.common.function.admin.data.vo.CIP_admin_access_relVO;

/**
 * <p>实体类</p>
 * <p>Table: cip_admin_access_rel - 系统与资源关系配置</p>
 *
 * @since 2015-08-20 09:11:16
 */
public class CIP_admin_access_relPO {

	public Object[] getKeys(){
		return new Object[]{ 
			resource_id,
					sys_id
		};
	}


    /** 
    * resource_id - 资源id
    */
    private String resource_id;

    /** 
    * sys_id - 系统id
    */
    private String sys_id;

    /** 
    * create_time - 系统生成时间
    */
    private Timestamp create_time;

    /** 
    * operator - 操作人员
    */
    private String operator;

    public String getResource_id(){
        return this.resource_id;
    }
    public void setResource_id(String resource_id){
        this.resource_id = resource_id;
    }
    public String getSys_id(){
        return this.sys_id;
    }
    public void setSys_id(String sys_id){
        this.sys_id = sys_id;
    }
    public Timestamp getCreate_time(){
        return this.create_time;
    }
    public void setCreate_time(Timestamp create_time){
        this.create_time = create_time;
    }
    public String getOperator(){
        return this.operator;
    }
    public void setOperator(String operator){
        this.operator = operator;
    }

	public CIP_admin_access_relVO toVO(){
		CIP_admin_access_relVO vo = new CIP_admin_access_relVO();
		
		vo.setResource_id(resource_id);
		vo.setSys_id(sys_id);
		
		return vo;
	}
		
}