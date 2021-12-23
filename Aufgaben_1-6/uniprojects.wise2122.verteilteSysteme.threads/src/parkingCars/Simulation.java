package parkingCars;

public class Simulation {

	public static void main(String[] args) throws InterruptedException {
		ParkingGarage garage = new ParkingGarage(10);
		for (int i = 1; i <= 20; i++) {
			new Car("R-FH " + i, garage).start();
		}

		Thread.sleep(30000);
		System.out.println("end of simualtion.");
	}

}
