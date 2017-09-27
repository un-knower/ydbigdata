package com.yd.ydbi.hp.model;

import java.io.Serializable;

/**
 * 菜鸟评分
 * @author Administrator
 *
 */
public class NewbeeMark implements Serializable{
	private String rank; //行业排名
	private String total_mark; //评分分数
	private String sum_mark; //行业平均分
	private String flag;//升降
	public NewbeeMark() {
		super();
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getTotal_mark() {
		return total_mark;
	}
	public void setTotal_mark(String total_mark) {
		this.total_mark = total_mark;
	}
	public String getSum_mark() {
		return sum_mark;
	}
	public void setSum_mark(String sum_mark) {
		this.sum_mark = sum_mark;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
}
