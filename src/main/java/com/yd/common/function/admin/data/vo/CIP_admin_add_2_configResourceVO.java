package com.yd.common.function.admin.data.vo;

/**
* ClassName: CIP_admin_add_2_configResourceVO
* Function: 系统资源关系与配置表(新增系统资源的同时配置资源与资源关系).
* Reason: TODO ADD REASON(可选).
* date: 2016-6-28 上午11:06:53
*
* @author jh
* @version 
* @since JDK 1.7
*/
public class CIP_admin_add_2_configResourceVO {

    /** 
    * resource_id - 资源id 
    */
    private String resource_id;
    
    /** 
    * resource_name - 资源名称 
    */
    private String resource_name;
    
    /** 
    * resource_desc - 资源描述 
    */
    private String resource_desc;
    
    /** 
    * sys_uri - 访问URI 
    */
    private String sys_uri;
    
    /** 
    * resource_type - 资源类型 
    */
    private String resource_type;
    
    /** 
    * icon_id - 图标id 
    */
    private String icon_id;
     
     /** 
     * res_node_sup - 资源上级节点id 
     */
     private String res_node_sup;
     
     /** 
     * root_node_id - 根节点 
     */
     private String root_node_id;
     
	public String getResource_id() {
		return resource_id;
	}

	public void setResource_id(String resource_id) {
		this.resource_id = resource_id;
	}

	public String getResource_name() {
		return resource_name;
	}

	public void setResource_name(String resource_name) {
		this.resource_name = resource_name;
	}

	public String getResource_desc() {
		return resource_desc;
	}

	public void setResource_desc(String resource_desc) {
		this.resource_desc = resource_desc;
	}

	public String getSys_uri() {
		return sys_uri;
	}

	public void setSys_uri(String sys_uri) {
		this.sys_uri = sys_uri;
	}

	public String getResource_type() {
		return resource_type;
	}

	public void setResource_type(String resource_type) {
		this.resource_type = resource_type;
	}

	public String getIcon_id() {
		return icon_id;
	}

	public void setIcon_id(String icon_id) {
		this.icon_id = icon_id;
	}

	public String getRes_node_sup() {
		return res_node_sup;
	}

	public void setRes_node_sup(String res_node_sup) {
		this.res_node_sup = res_node_sup;
	}

	public String getRoot_node_id() {
		return root_node_id;
	}

	public void setRoot_node_id(String root_node_id) {
		this.root_node_id = root_node_id;
	}

			  
}