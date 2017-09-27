package com.yd.common.function.auth.data;

public class CIPRole2ResourceData{

	private String resource_id;
	private String resource_name;
	private String sys_uri;
	private String resource_type;
	private String res_node_sup;
    private int res_level;
    private int leaf_flag;
    private String icon;
    
    public int getRes_level() {
		return res_level;
	}
	public void setRes_level(int res_level) {
		this.res_level = res_level;
	}
	public int getLeaf_flag() {
		return leaf_flag;
	}
	public void setLeaf_flag(int leaf_flag) {
		this.leaf_flag = leaf_flag;
	}
	
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
	public String getRes_node_sup() {
		return res_node_sup;
	}
	public void setRes_node_sup(String res_node_sup) {
		this.res_node_sup = res_node_sup;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}

	
}
