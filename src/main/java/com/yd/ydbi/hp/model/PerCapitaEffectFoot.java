package com.yd.ydbi.hp.model;

import java.io.Serializable;
import java.util.List;

/**
 * 人均效能脚
 * @author Administrator
 *
 */
public class PerCapitaEffectFoot implements Serializable{
	private List<PerCapitalEffectTopN> top5;//top5
	private List<PerCapitalEffectTopN> low5;//low5
	private List<PerCapitalEffectTopN> all;//all
	public PerCapitaEffectFoot() {
		super();
	}
	public List<PerCapitalEffectTopN> getTop5() {
		return top5;
	}
	public void setTop5(List<PerCapitalEffectTopN> top5) {
		this.top5 = top5;
	}
	public List<PerCapitalEffectTopN> getLow5() {
		return low5;
	}
	public void setLow5(List<PerCapitalEffectTopN> low5) {
		this.low5 = low5;
	}
	public List<PerCapitalEffectTopN> getAll() {
		return all;
	}
	public void setAll(List<PerCapitalEffectTopN> all) {
		this.all = all;
	}
	
}
