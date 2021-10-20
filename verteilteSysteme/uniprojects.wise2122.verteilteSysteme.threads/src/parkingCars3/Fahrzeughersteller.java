package parkingCars3;

public class Fahrzeughersteller extends Thread {

	private final Parkhaus parkhaus;
	private int anzahlProduzierterAutos;

	public Fahrzeughersteller(Parkhaus p) {
		this.parkhaus = p;
		this.setDaemon(true);
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(SimulationBuyingCars.getRandTimeLimit());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			anzahlProduzierterAutos++;
			Auto auto = new Auto("Auto Nr." + anzahlProduzierterAutos);
			this.parkhaus.parkeNeuesAutoInHalle(auto);
		}
	}

}
