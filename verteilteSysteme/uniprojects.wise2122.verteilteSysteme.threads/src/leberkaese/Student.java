package leberkaese;

public class Student extends Thread {

	private final KitchenCounter counter;

	public Student(KitchenCounter counter, String name) {
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
			this.counter.take();
			System.out.println(getName() + " got Schnitzel!! :D");
		}

	}

}
