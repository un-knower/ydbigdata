package com.yd.ydbi.ys.model;

import java.io.Serializable;

public class CanCar implements Serializable{
	private String startdot;
	private String enddot;
	public String getStartdot() {
		return startdot;
	}
	public void setStartdot(String startdot) {
		this.startdot = startdot;
	}
	public String getEnddot() {
		return enddot;
	}
	public void setEnddot(String enddot) {
		this.enddot = enddot;
	}
	public CanCar() {
		super();
	}
	public CanCar(String startdot, String enddot) {
		super();
		this.startdot = startdot;
		this.enddot = enddot;
	}
}
