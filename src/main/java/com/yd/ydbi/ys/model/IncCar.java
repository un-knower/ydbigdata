package com.yd.ydbi.ys.model;

import java.io.Serializable;

public class IncCar implements Serializable{
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
	public IncCar() {
		super();
	}
	public IncCar(String startdot, String enddot) {
		super();
		this.startdot = startdot;
		this.enddot = enddot;
	}
}
