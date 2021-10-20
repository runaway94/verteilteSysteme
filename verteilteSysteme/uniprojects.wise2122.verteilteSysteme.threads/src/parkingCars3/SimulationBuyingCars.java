package parkingCars3;

import java.util.Random;

public class SimulationBuyingCars {
	
	public static int TIME_LIMIT = 20;

	public static void main(String[] args) throws InterruptedException {
		
		Parkhaus ph = new Parkhaus((short)10);
		new Fahrzeughersteller(ph).start();
		
		//gib dem hersteller etwas zeit zum vorproduzieren...
		Thread.sleep(30000);
		char nachname = 'A';
		for(int i = 0; i < 5; i++) {
			new Kunde(ph, "Anna " + ++nachname).start();
		}
		
		Thread.sleep(30000);
		System.out.println("end of simualtion.");
	}
	
	public static int getRandTimeLimit() {
		return new Random().nextInt(TIME_LIMIT) * 1000;
	}

}
