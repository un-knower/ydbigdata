package com.yd.common.function.admin.data.po;

import java.sql.Timestamp;

import com.yd.common.function.admin.data.vo.CIP_admin_resource_2_resourceVO;

/**
 * <p>实体类</p>
 * <p>Table: cip_admin_resource_2_resource - 资源与资源关系</p>
 *
 * @since 2015-07-27 02:40:52
 */
public class CIP_admin_resource_2_resourcePO {

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
    private int res_level;

    /** 
    * leaf_flag - 叶节点标识
    */
    private int leaf_flag;

    /** 
    * create_time - 系统生成时间
    */
    private Timestamp create_time;

    /** 
    * update_time - 修改时间
    */
    private Timestamp update_time;

    /** 
    * operator - 操作人员
    */
    private String operator;

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
    public int getRes_level(){
        return this.res_level;
    }
    public void setRes_level(int res_level){
        this.res_level = res_level;
    }
    public int getLeaf_flag(){
        return this.leaf_flag;
    }
    public void setLeaf_flag(int leaf_flag){
        this.leaf_flag = leaf_flag;
    }
    public Timestamp getCreate_time(){
        return this.create_time;
    }
    public void setCreate_time(Timestamp create_time){
        this.create_time = create_time;
    }
    public Timestamp getUpdate_time(){
        return this.update_time;
    }
    public void setUpdate_time(Timestamp update_time){
        this.update_time = update_time;
    }
    public String getOperator(){
        return this.operator;
    }
    public void setOperator(String operator){
        this.operator = operator;
    }

	public CIP_admin_resource_2_resourceVO toVO(){
		CIP_admin_resource_2_resourceVO vo = new CIP_admin_resource_2_resourceVO();
		
		vo.setRes_node_id(res_node_id);
		vo.setRes_node_sup(res_node_sup);
		vo.setRoot_node_id(root_node_id);
		vo.setNode_order(node_order);
		vo.setRoot_flag(root_flag);
		vo.setLevel(res_level);
		vo.setLeaf_flag(leaf_flag);
		
		return vo;
	}
		
}