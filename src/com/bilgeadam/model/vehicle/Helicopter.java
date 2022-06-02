package com.bilgeadam.model.vehicle;

public class Helicopter extends AirVehicle {
	
	public Helicopter(String brand, String engineType) {
		super(brand, engineType);
	}

	@Override
	public int compareTo(Vehicle o) {
		return this.getBrand().compareTo(o.getBrand());
	}
}
