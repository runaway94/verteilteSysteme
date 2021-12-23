package parkingCars;

public class ParkingGarage {

	private final int amountParkingSpaces;
	private int parkingCars;
	private final Object monitor;

	public ParkingGarage(int size) {
		this.amountParkingSpaces = size;
		this.parkingCars = 0;
		this.monitor = new Object();
	}

	public void parkCar() {
		this.parkingCars++;
	}

	public void carLeaves(Car car) {
		synchronized (monitor) {
			while (this.parkingCars < 3) {
				try {
					System.out.println("Staying in garage: " + car.getName());
					printFreeSpaces();
					monitor.wait();
				} catch (InterruptedException e) {

				}
			}
			this.parkingCars--;
			System.out.println("Leaving: " + car.getName());
			monitor.notifyAll();
			printFreeSpaces();
		}
	}

	private void printFreeSpaces() {
		int empty = amountParkingSpaces - parkingCars;
		int parking = parkingCars;
		System.out.println(parking + " Plätze belegt, " + empty + " Plätze frei!");
		String emptySpace = "[ ] ";
		String takenSpace = "[x] ";
		for (int i = parking; i > 0; i--) {
			System.out.print(takenSpace);
		}
		for (int i = empty; i > 0; i--) {
			System.out.print(emptySpace);
		}
		System.out.print("\n\n");
	}

	public void parkCar(Car car) {
		synchronized (monitor) {
			while (this.parkingCars == this.amountParkingSpaces) {
				try {
					System.out.println("Waiting: " + car.getName());
					printFreeSpaces();
					monitor.wait();
				} catch (InterruptedException e) {
					// TODO: handle exception
				}
			}
			monitor.notifyAll();
			this.parkingCars++;
			System.out.println("Parking: " + car.getName());
			printFreeSpaces();

		}
	}

}
