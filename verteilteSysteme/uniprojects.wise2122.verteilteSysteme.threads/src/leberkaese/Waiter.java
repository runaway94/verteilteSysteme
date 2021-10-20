package leberkaese;

public class Waiter extends Thread {
	
	private final KitchenCounter counter;
	
	public Waiter(KitchenCounter counter, String name) {
		super(name);
		this.counter = counter;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				sleep(Simulation.getRandTimeLimit(10));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.counter.put();
			System.out.println(getName() + " put Schnitzel on counter!");
		}

	}

}
