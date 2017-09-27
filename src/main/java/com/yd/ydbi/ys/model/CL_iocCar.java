package com.yd.ydbi.ys.model;

import java.io.Serializable;
import java.util.List;

public class CL_iocCar implements Serializable{
	private List<CanCar> canCars;
	private List<IncCar> incCars;
	
	public CL_iocCar() {
		super();
	}
	public List<CanCar> getCanCars() {
		return canCars;
	}

	public void setCanCars(List<CanCar> canCars) {
		this.canCars = canCars;
	}

	public List<IncCar> getIncCars() {
		return incCars;
	}

	public void setIncCars(List<IncCar> incCars) {
		this.incCars = incCars;
	}

	
	
	public void setOnecCar(String start,String end){
		CanCar canCar = new CanCar(start, end);
		canCars.add(canCar);
	}
	public void setOneiCar(String start,String end){
		IncCar incCar = new IncCar(start, end);
		incCars.add(incCar);
	}
}
