package com.bilgeadam.model.vehicle;

public abstract class Vehicle implements Comparable<Vehicle> {
	private String brand;
	private String name;
	private String color;
	private String engineType;
	private int    maxSpeed;

	public Vehicle (String brand, String engineType) {
		this.brand = brand;
		this.engineType = engineType;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getMaxSpeed() {
		return this.maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public String getBrand() {
		return this.brand;
	}

	public String getEngineType() {
		return this.engineType;
	}

	@Override
	public String toString() {
		return "Vehicle [brand=" + this.brand + ", name=" + this.name + ", color=" + this.color + ", engineType="
				+ this.engineType + ", maxSpeed=" + this.maxSpeed + "]";
	}

	/**
	 * This method accepts a string consisting of the definition of a vehicle data. Depending on the 
	 * type it creates a corresponding object.
	 * @param line	The line to be parsed
	 * @return		A concrete object of type Vehicle (Airplane, Helicopter, FireTruck, ServiceCar
	 * @throws UnrecognizedVehicleException	If line starts with an unexpected value listed below
	 */
	public static Vehicle parse(String line) throws UnrecognizedVehicleException {
		if (line.toUpperCase().startsWith("HEL"))
			return null;
		if (line.toUpperCase().startsWith("AIR"))
			return null;
		if (line.toUpperCase().startsWith("FIR"))
			return null;
		if (line.toUpperCase().startsWith("SER"))
			return null;
		else {
			throw new UnrecognizedVehicleException(line);
		}
	}
}
