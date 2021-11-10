package uniprojects.wise2122.verteilteSysteme.rmi.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

import uniprojects.wise2122.verteilteSysteme.rmi.server.FrueherkennungsIF;
import uniprojects.wise2122.verteilteSysteme.rmi.server.entity.Bericht;
import uniprojects.wise2122.verteilteSysteme.rmi.server.entity.BerichtIF;
import uniprojects.wise2122.verteilteSysteme.rmi.server.entity.Röntgenbild;
import uniprojects.wise2122.verteilteSysteme.rmi.server.entity.RöntgenbildIF;

public class UniklinikRegensburg {

	private FrueherkennungsIF erkennungsServer;

	public UniklinikRegensburg() {
		registrieren();
	}

	private void registrieren() {
		Registry r;
		try {
			r = LocateRegistry.getRegistry("localhost", 1099);
			this.erkennungsServer = (FrueherkennungsIF) r.lookup("früherkennungs-service");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	public BerichtIF bildPrüfen(Röntgenbild rtgBild) {
		try {
			RöntgenbildIF röntgenbildStub = (RöntgenbildIF) UnicastRemoteObject.exportObject(rtgBild, 0);
			return erkennungsServer.analysieren(röntgenbildStub);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String args[]) {
		String name = "Simon";
		Röntgenbild mock = new Röntgenbild(new Date(), name, new byte[] { 1, 1, 0, 1 });
		UniklinikRegensburg klinik = new UniklinikRegensburg();
		BerichtIF bericht = klinik.bildPrüfen(mock);
		try {
			System.out.println("Die Diagnose für " + name + " ist: " + bericht.getDiagnose());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
