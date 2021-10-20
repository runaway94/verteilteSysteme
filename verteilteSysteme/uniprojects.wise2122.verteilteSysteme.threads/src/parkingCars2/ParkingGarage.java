package parkingCars2;

public class ParkingGarage {

	private final int amountParkingSpaces;
	private int parkingCars;
	private final Object monitior;

	public ParkingGarage(int size) {
		this.amountParkingSpaces = size;
		this.parkingCars = 0;
		this.monitior = new Object();
	}

	public void parkCar() {
		this.parkingCars++;
	}

	public int getFreeSpaces() {
		return this.amountParkingSpaces - parkingCars;
	}

	public void leave(Car car) {
		synchronized (monitior) {
			while (this.parkingCars < 3) {
				try {
					System.out.println("Staying in garage: " + car.getName());
					printFreeSpaces();
					monitior.wait();
				} catch (InterruptedException e) {
				}
			}
			monitior.notify();
			this.parkingCars--;
			System.out.println("Leaving: " + car.getName());
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
		
		synchronized (monitior) {
			while (this.amountParkingSpaces == this.parkingCars) {
				try {
					System.out.println("Waiting: " + car.getName());
					printFreeSpaces();
					monitior.wait();
				} catch (InterruptedException e) {
				}
			}
			monitior.notify();
			this.parkingCars++;
			System.out.println("Parking: " + car.getName());
			printFreeSpaces();
		}		
	}

}
