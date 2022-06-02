package com.bilgeadam.airport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.bilgeadam.model.airport.Airport;
import com.bilgeadam.model.vehicle.Airplane;
import com.bilgeadam.model.vehicle.Helicopter;
import com.bilgeadam.model.vehicle.UnrecognizedVehicleException;
import com.bilgeadam.model.vehicle.Vehicle;

public class AirportManager {
	private Airport            airport;
	private ArrayList<Vehicle> vehicles;

	public AirportManager() {
		super();
		this.vehicles = new ArrayList<>();
	}

	public static void main(String[] args) {
		System.out.println("Havaalanı Yönetim Uygulaması");

		AirportManager manager = new AirportManager();
		manager.airport = new Airport("İstanbul Airport", "İstanbul");
		// manager.registerVehicles();
		manager.readVehicleData();
		System.out.println(manager);

		System.out.println("Havaalanı Yönetim Uygulamasını kullandığınız için teşekkürler");
	}

	private void readVehicleData() {
		final String source = "C:\\Users\\babur.somer\\boost-02-workspace\\Airport\\data\\vehicles.csv";
		File         file   = new File(source);
		if (!file.exists()) {
			System.err.println("Eksik kaynak. Program çalışmaya devam edemez. Bye...");
		}

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			while (true) {
				String line = reader.readLine();
				if (line == null) {
					break;
				}
				else {
					System.out.println("==> " + line);
					if (line.startsWith("﻿VehicleType")) {
						continue;
					}
					try {
						Vehicle vehicle = Vehicle.parse(line);  // dosyadan her okunan satırı yorumlayıp doğru türden bir obje 
																// yaratmamız gerekiyor. Vehicle.parse(line) yorumlama ve obje 
																// yaratma işini yapacak
					}
					catch (UnrecognizedVehicleException ex) {
						System.err.println("Tanımsız araç: " + ex.getMessage());
						continue;
					}
				}
			}
		}
		catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private void registerVehicles() {
		System.out.println("Lütfen araç bilgilerini giriniz");
		System.out.println("===============================\n");
		Scanner in   = new Scanner(System.in);
		boolean exit = false;

		Vehicle vehicle;
		while (!exit) {
			System.out.print("Lütfen araç tipini giriniz (heli, air, fire, service, exit): ");
			String type = in.nextLine();
			if (type.equalsIgnoreCase("exit")) {
				exit = true;
				continue;
			}
			System.out.print("Lütfen markayı giriniz: ");
			String brand = in.nextLine();
			System.out.print("Lütfen ismini giriniz: ");
			String name = in.nextLine();
			System.out.print("Lütfen motor tipini giriniz: ");
			String engineType = in.nextLine();

			if (type.equalsIgnoreCase("heli")) {
				vehicle = new Helicopter(brand, engineType);
			}
			else { // if (type.equalsIgnoreCase("air")) {
				System.out.print("Lütfen kanat uzunluğunu giriniz: ");
				int wingLength = in.nextInt();
				System.out.print("Lütfen ağırlık giriniz: ");
				int weight = in.nextInt();
				System.out.print("Lütfen maksimum ağırlık giriniz: ");
				int maxWeight = in.nextInt();
				in.nextLine();
				vehicle = new Airplane(brand, engineType, wingLength, weight, maxWeight);
			}
			// else if (type.equalsIgnoreCase("fire")) {
			//
			// }
			// else {
			//
			// }
			vehicle.setName(name);
			this.vehicles.add(vehicle);
		}
		in.close();
	}

	@Override
	public String toString() {
		return "AirportManager [airport=" + this.airport + ", vehicles=" + this.vehicles + "]";
	}
}
