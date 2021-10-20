package parkingCars3;

public class Kunde extends Thread {

	private final Parkhaus parkhaus;
	private final String name;

	public Kunde(Parkhaus parkhaus, String name) {
		this.parkhaus = parkhaus;
		this.name = name;
		this.setDaemon(true);
	}

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(SimulationBuyingCars.getRandTimeLimit());
				this.parkhaus.autoVerkauft(name);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
