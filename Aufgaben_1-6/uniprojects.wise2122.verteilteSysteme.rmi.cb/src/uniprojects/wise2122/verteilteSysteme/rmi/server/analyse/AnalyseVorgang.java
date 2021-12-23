package uniprojects.wise2122.verteilteSysteme.rmi.server.analyse;

import java.rmi.RemoteException;
import java.util.Date;

import uniprojects.wise2122.verteilteSysteme.rmi.client.UniklinikRegensburgCallbackIF;
import uniprojects.wise2122.verteilteSysteme.rmi.server.entity.Bericht;
import uniprojects.wise2122.verteilteSysteme.rmi.server.entity.Röntgenbild;

public class AnalyseVorgang implements Runnable {
	
	private Röntgenbild bild;
	private UniklinikRegensburgCallbackIF referenz;
	

	public AnalyseVorgang(Röntgenbild bild, UniklinikRegensburgCallbackIF referenz) {
		super();
		this.bild = bild;
		this.referenz = referenz;
	}


	@Override
	public void run() {
		// . . . 
		// work! analyze!! 
		//. . . 
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Date today = new Date();
		Bericht ergebnis = new Bericht(today, "gebrochenes Herz", "Eiscreme und Netflix");
		System.out.println("Bericht erstellt, wird rausgeschickt..");
		try {
			this.referenz.setBericht(ergebnis);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
