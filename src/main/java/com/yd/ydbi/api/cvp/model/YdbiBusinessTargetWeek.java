package com.yd.ydbi.api.cvp.model;

import java.io.Serializable;

public class YdbiBusinessTargetWeek implements Serializable {
    private static final long serialVersionUID = 1L;

    private String data_date;            		// 日期  
    private String t_data_date;            		// 返回日期  
    private String org_id;           	 		// 机构id  
	private String org_name;           	 		// 机构名称           
	private String goal_values;             	// 目标值       
	private String actual_values;               // 实际值          
	private String actual_residual_quantity;    // 较上周实际值的差量  
	private String residual_quantity;           // 差量            
	private String residual_quantity_percent;   // 差量百分比    
	private String period_finish_percent; 		// 上期完成率        
	private String finish_percent;     			// 完成率     
	private String day_percent;         		// 日环比        
	private String volatility_values;           // 波动率      
	private String superior_org_name;    		// 上级机构    
	private String week_percent;                // 周同比            
	private String month_even;    				// 月均
	private String week_even;    				// 周均
	public String getData_date() {
		return data_date;
	}
	public void setData_date(String data_date) {
		this.data_date = data_date;
	}
	
	public String getT_data_date() {
		return t_data_date;
	}
	public void setT_data_date(String t_data_date) {
		this.t_data_date = t_data_date;
	}
	public String getOrg_id() {
		return org_id;
	}
	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}
	public String getOrg_name() {
		return org_name;
	}
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}
	public String getGoal_values() {
		return goal_values;
	}
	public void setGoal_values(String goal_values) {
		this.goal_values = goal_values;
	}
	public String getActual_values() {
		return actual_values;
	}
	public void setActual_values(String actual_values) {
		this.actual_values = actual_values;
	}
	public String getActual_residual_quantity() {
		return actual_residual_quantity;
	}
	public void setActual_residual_quantity(String actual_residual_quantity) {
		this.actual_residual_quantity = actual_residual_quantity;
	}
	public String getResidual_quantity() {
		return residual_quantity;
	}
	public void setResidual_quantity(String residual_quantity) {
		this.residual_quantity = residual_quantity;
	}
	public String getResidual_quantity_percent() {
		return residual_quantity_percent;
	}
	public void setResidual_quantity_percent(String residual_quantity_percent) {
		this.residual_quantity_percent = residual_quantity_percent;
	}
	public String getPeriod_finish_percent() {
		return period_finish_percent;
	}
	public void setPeriod_finish_percent(String period_finish_percent) {
		this.period_finish_percent = period_finish_percent;
	}
	public String getFinish_percent() {
		return finish_percent;
	}
	public void setFinish_percent(String finish_percent) {
		this.finish_percent = finish_percent;
	}
	public String getDay_percent() {
		return day_percent;
	}
	public void setDay_percent(String day_percent) {
		this.day_percent = day_percent;
	}
	public String getVolatility_values() {
		return volatility_values;
	}
	public void setVolatility_values(String volatility_values) {
		this.volatility_values = volatility_values;
	}
	public String getSuperior_org_name() {
		return superior_org_name;
	}
	public void setSuperior_org_name(String superior_org_name) {
		this.superior_org_name = superior_org_name;
	}
	public String getWeek_percent() {
		return week_percent;
	}
	public void setWeek_percent(String week_percent) {
		this.week_percent = week_percent;
	}
	public String getMonth_even() {
		return month_even;
	}
	public void setMonth_even(String month_even) {
		this.month_even = month_even;
	}
	public String getWeek_even() {
		return week_even;
	}
	public void setWeek_even(String week_even) {
		this.week_even = week_even;
	}
	
	
}