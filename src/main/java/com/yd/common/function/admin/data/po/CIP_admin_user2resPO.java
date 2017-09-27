package com.yd.common.function.admin.data.po;

import com.yd.common.function.admin.data.vo.CIP_admin_user2resVO;

/**
 * <p>实体类</p>
 * <p>Table: cip_admin_user2res - 主页快速工具表</p>
 *
 * @since 2016-08-31 04:49:06
 */
public class CIP_admin_user2resPO {

	public Object[] getKeys(){
		return new Object[]{ 
			resource_id,
					user_id
		};
	}


    /** 
    * resource_id - 资源id
    */
    private String resource_id;

    /** 
    * user_id - 用户id
    */
    private String user_id;

    /** 
    * resource_name - 资源名称
    */
    private String resource_name;

    /** 
    * resource_image - 资源背景图
    */
    private String resource_image;

    public String getResource_id(){
        return this.resource_id;
    }
    public void setResource_id(String resource_id){
        this.resource_id = resource_id;
    }
    public String getUser_id(){
        return this.user_id;
    }
    public void setUser_id(String user_id){
        this.user_id = user_id;
    }
    public String getResource_name(){
        return this.resource_name;
    }
    public void setResource_name(String resource_name){
        this.resource_name = resource_name;
    }
    public String getResource_image(){
        return this.resource_image;
    }
    public void setResource_image(String resource_image){
        this.resource_image = resource_image;
    }

	public CIP_admin_user2resVO toVO(){
		CIP_admin_user2resVO vo = new CIP_admin_user2resVO();
		
		vo.setResource_id(resource_id);
		vo.setUser_id(user_id);
		vo.setResource_name(resource_name);
		vo.setResource_image(resource_image);
		
		return vo;
	}
		
}