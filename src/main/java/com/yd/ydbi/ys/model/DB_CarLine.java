package com.yd.ydbi.ys.model;

import java.io.Serializable;

public class DB_CarLine implements Serializable{
	private String start_dot;
	private String end_dot;
	private String car_line;
	private String iod_flag;
	public DB_CarLine() {
		super();
	}
	public String getStart_dot() {
		return start_dot;
	}
	public void setStart_dot(String start_dot) {
		this.start_dot = start_dot;
	}
	public String getEnd_dot() {
		return end_dot;
	}
	public void setEnd_dot(String end_dot) {
		this.end_dot = end_dot;
	}
	public String getCar_line() {
		return car_line;
	}
	public void setCar_line(String car_line) {
		this.car_line = car_line;
	}
	public String getIod_flag() {
		return iod_flag;
	}
	public void setIod_flag(String iod_flag) {
		this.iod_flag = iod_flag;
	}
	
	
}
