package parkingCars;

public class ParkingGarage {

	private final int amountParkingSpaces;
	private int parkingCars;

	public ParkingGarage(int size) {
		this.amountParkingSpaces = size;
		this.parkingCars = 0;
	}

	public void parkCar() {
		this.parkingCars++;
	}

	public int getFreeSpaces() {
		return this.amountParkingSpaces - parkingCars;
	}

	public boolean leave(Car car) {
		if(this.parkingCars < 3) {
			System.out.println("Staying in garage: " + car.getName());
			printFreeSpaces();
			return false;
		}
		this.parkingCars--;
		System.out.println("Leaving: " + car.getName());
		printFreeSpaces();
		return true;
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

	public boolean parkCar(Car car) {
		if (this.parkingCars < this.amountParkingSpaces) {
			this.parkingCars++;
			System.out.println("Parking: " + car.getName());
			printFreeSpaces();
			return true;
		}
		
		System.out.println("Waiting: " + car.getName());
		printFreeSpaces();
		return false;
	}

}
