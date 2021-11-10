package uniprojects.wise2122.verteilteSysteme.rmi.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;

import uniprojects.wise2122.verteilteSysteme.rmi.server.FrueherkennungsIF;
import uniprojects.wise2122.verteilteSysteme.rmi.server.entity.Bericht;
import uniprojects.wise2122.verteilteSysteme.rmi.server.entity.R�ntgenbild;

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

	public Bericht bildPr�fen(R�ntgenbild rtgBild) {
		try {
			return erkennungsServer.analysieren(rtgBild);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String args[]) {
		R�ntgenbild mock = new R�ntgenbild(new Date(), "Simon", new byte[] { 1, 1, 0, 1 });
		UniklinikRegensburg klinik = new UniklinikRegensburg();
		Bericht bericht = klinik.bildPr�fen(mock);
		System.out.println(bericht.toString());
	}
}
