package downloads;


import java.util.Random;
import java.util.concurrent.CountDownLatch;

import javax.swing.JProgressBar;

// aktive Klasse
public class Download extends Thread
{
	
	private final JProgressBar balken;
	private final CountDownLatch startLatch;
	private final CountDownLatch downloadLatch;
	private final int millis;
    // weitere Attribute zur Synchronisation hier definieren
    
	public Download(JProgressBar balken, CountDownLatch startLatch, CountDownLatch downloadLatch) {
		this.balken = balken;
		this.startLatch = startLatch;
		this.downloadLatch = downloadLatch;
		this.millis = getRandTimeLimit(10);
        // ...
    }
	
	@Override
	public void run() {
		try {
			this.startLatch.await();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(int i = 100; i > 0; i--) {
			try {
				sleep(millis);
				int value = this.balken.getValue();
				this.balken.setValue(++value);
			
				System.out.println("Balken wurde gesetzt auf " + this.balken.getValue());
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		downloadLatch.countDown();
	}


    /*  hier die Methode definieren, die jeweils den Balken weiterschiebt
     *  Mit balken.getMaximum() bekommt man den Wert fuer 100 % gefuellt
     *  Mit balken.setValue(...) kann man den Balken einstellen (wieviel gefuellt) dargestellt wird
     *  Setzen Sie den value jeweils und legen Sie die Methode dann für eine zufaellige Zeit schlafen
     */
	
	
	private static int getRandTimeLimit(int limit) {
		return new Random().nextInt(limit) * 10;
	}


}
