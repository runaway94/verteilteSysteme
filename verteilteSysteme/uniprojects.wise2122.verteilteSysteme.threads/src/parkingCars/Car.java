package parkingCars;

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

		lookForSpace();
		park();

	}

	private void lookForSpace() throws InterruptedException {
		sleep(getRandTimeLimit(10));
		boolean parkCar;
		synchronized (garage) {
			parkCar = this.garage.parkCar(this);
		}
		if (!parkCar) {
			drive();
		}
	}

	private void park() throws InterruptedException {
		sleep(getRandTimeLimit(10));
		boolean leaveGarage;
		synchronized (garage) {
			leaveGarage = this.garage.leave(this);
		}
		if (!leaveGarage) {
			park();
		}
	}

	private int getRandTimeLimit(int limit) {
		return new Random().nextInt(limit) * 1000;
	}

}
