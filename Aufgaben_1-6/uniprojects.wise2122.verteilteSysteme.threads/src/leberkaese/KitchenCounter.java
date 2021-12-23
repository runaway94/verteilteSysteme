package leberkaese;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class KitchenCounter {

	private final int spaces;
	private int semmeln;

	private final Lock monitor;
	private final Condition stud;
	private final Condition kel;

	public KitchenCounter(int spaces) {
		this.spaces = spaces;
		this.monitor = new ReentrantLock();
		this.stud = monitor.newCondition();
		this.kel = monitor.newCondition();
	}

	public void put() {

		monitor.lock();
		try {
			while (semmeln == spaces) {
				try {
					System.out.println("No more space for Schnitzel.!!! Eat Kinders, eat!!");
					kel.await();
				} catch (InterruptedException e) {
				}
			}
			semmeln++;
			stud.signal();

		} finally {
			monitor.unlock();
		}
	}
	
	public void take() {
		monitor.lock();
		try {
			while (semmeln == 0) {
				try {
					System.out.println("No more Schnitzel.. Student needs to wait!");
					stud.await();
				} catch (InterruptedException e) {
				}
			}
			semmeln--;
			kel.signal();

		} finally {
			monitor.unlock();
		}
	}

}
