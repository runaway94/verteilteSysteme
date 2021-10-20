package parkingCars2;

import java.util.Random;

public class Car extends Thread {

	private static final int TIME_LIMIT = 10;

	private final ParkingGarage garage;

	public Car(String licence, ParkingGarage garage) {
		super(licence);
		setDaemon(true);
		this.garage = garage;
	}

	@Override
	public void run() {
		try {
			while (true) {
				drive();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void drive() throws InterruptedException {
		
		sleep(getRandTimeLimit(10));
		this.garage.parkCar(this);
		sleep(getRandTimeLimit(10));
		this.garage.leave(this);

	}

	private int getRandTimeLimit(int limit) {
		return new Random().nextInt(limit) * 1000;
	}

}
