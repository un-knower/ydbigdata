package com.yd.common.function.admin.data.vo;

import com.yd.common.function.admin.data.po.CIP_admin_resource_2_resourcePO;

/**
 * <p>实体类</p>
 * <p>Table: cip_admin_resource_2_resource - 资源与资源关系</p>
 *
 * @since 2015-07-27 02:40:52
 */
public class CIP_admin_resource_2_resourceVO {

	public Object[] getKeys(){
		return new Object[]{ 
		res_node_id,
				res_node_sup,
				root_node_id
		};
	}

    /** 
    * res_node_id - 资源节点id 
    */
    private String res_node_id;
    
    /** 
    * res_node_sup - 资源上级节点id 
    */
    private String res_node_sup;
    
    /** 
    * root_node_id - 根节点 
    */
    private String root_node_id;
    
    /** 
    * node_order - 同级节点序列 
    */
    private int node_order;
    
    /** 
    * root_flag - 根节点标识 
    */
    private int root_flag;
    
    /** 
    * level - 层级数 
    */
    private int level;
    
    /** 
    * leaf_flag - 叶节点标识 
    */
    private int leaf_flag;
    

	public String getRes_node_id(){
        return this.res_node_id;
    }
    public void setRes_node_id(String res_node_id){
        this.res_node_id = res_node_id;
    }
	public String getRes_node_sup(){
        return this.res_node_sup;
    }
    public void setRes_node_sup(String res_node_sup){
        this.res_node_sup = res_node_sup;
    }
	public String getRoot_node_id(){
        return this.root_node_id;
    }
    public void setRoot_node_id(String root_node_id){
        this.root_node_id = root_node_id;
    }
	public int getNode_order(){
        return this.node_order;
    }
    public void setNode_order(int node_order){
        this.node_order = node_order;
    }
	public int getRoot_flag(){
        return this.root_flag;
    }
    public void setRoot_flag(int root_flag){
        this.root_flag = root_flag;
    }
	public int getLevel(){
        return this.level;
    }
    public void setLevel(int level){
        this.level = level;
    }
	public int getLeaf_flag(){
        return this.leaf_flag;
    }
    public void setLeaf_flag(int leaf_flag){
        this.leaf_flag = leaf_flag;
    }

	public CIP_admin_resource_2_resourcePO toPO(){
		CIP_admin_resource_2_resourcePO po = new CIP_admin_resource_2_resourcePO();
		
		po.setRes_node_id(res_node_id);
		po.setRes_node_sup(res_node_sup);
		po.setRoot_node_id(root_node_id);
		po.setNode_order(node_order);
		po.setRoot_flag(root_flag);
		po.setRes_level(level);
		po.setLeaf_flag(leaf_flag);
		
		return po;
	}
		
}