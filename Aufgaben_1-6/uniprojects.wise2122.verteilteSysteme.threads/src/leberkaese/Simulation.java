package leberkaese;

import java.util.Random;

public class Simulation {

	public static void main(String[] args) {
		KitchenCounter theke = new KitchenCounter(4);
		new Thread(new Waiter(theke, "Kellner-1")).start();
		new Thread(new Waiter(theke, "Kellner-2")).start();
		for (int i = 1; i <= 8; i++)
			new Thread(new Student(theke, "Student-" + i)).start();
	}

	public static int getRandTimeLimit(int limit) {
		return new Random().nextInt(limit) * 1000;
	}
}
