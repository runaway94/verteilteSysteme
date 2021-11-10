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
import uniprojects.wise2122.verteilteSysteme.rmi.server.entity.R�ntgenbild;
import uniprojects.wise2122.verteilteSysteme.rmi.server.entity.R�ntgenbildIF;

public class UniklinikRegensburg {

	private FrueherkennungsIF erkennungsServer;

	public UniklinikRegensburg() {
		registrieren();
	}

	private void registrieren() {
		Registry r;
		try {
			r = LocateRegistry.getRegistry("localhost", 1099);
			this.erkennungsServer = (FrueherkennungsIF) r.lookup("fr�herkennungs-service");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	public BerichtIF bildPr�fen(R�ntgenbild rtgBild) {
		try {
			R�ntgenbildIF r�ntgenbildStub = (R�ntgenbildIF) UnicastRemoteObject.exportObject(rtgBild, 0);
			return erkennungsServer.analysieren(r�ntgenbildStub);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String args[]) {
		String name = "Simon";
		R�ntgenbild mock = new R�ntgenbild(new Date(), name, new byte[] { 1, 1, 0, 1 });
		UniklinikRegensburg klinik = new UniklinikRegensburg();
		BerichtIF bericht = klinik.bildPr�fen(mock);
		try {
			System.out.println("Die Diagnose f�r " + name + " ist: " + bericht.getDiagnose());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
