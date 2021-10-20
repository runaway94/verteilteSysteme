package downloads;

import java.util.concurrent.CountDownLatch;

import javax.swing.JButton;

public class ButtonChanger extends Thread{
	
	private final CountDownLatch latch;
	private final JButton startButton;
	
	public ButtonChanger(CountDownLatch downloadLatch, JButton startButton) {
		this.latch = downloadLatch;
		this.startButton = startButton;
	}
	
	public void run() {
		try {
			this.latch.await();
			this.startButton.setText("ENDE");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
