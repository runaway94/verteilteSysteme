package uniprojects.wise2122.verteilteSysteme.rmi.server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import uniprojects.wise2122.verteilteSysteme.rmi.client.UniklinikRegensburgCallbackIF;
import uniprojects.wise2122.verteilteSysteme.rmi.server.analyse.AnalyseVorgang;
import uniprojects.wise2122.verteilteSysteme.rmi.server.entity.Röntgenbild;

public class FrueherkennungsService implements FrueherkennungsIF {

	@Override
	public void analysieren(Röntgenbild bild, UniklinikRegensburgCallbackIF referenz) throws RemoteException {
		System.out.println("Bild eingegangen! Neue Analyse wird gestartet...");
		new Thread(new AnalyseVorgang(bild, referenz)).start();
	}

	public static void main(String args[]) {
		try {
			FrueherkennungsIF erkennung = new FrueherkennungsService();
			FrueherkennungsIF stub = (FrueherkennungsIF) UnicastRemoteObject.exportObject(erkennung, 0);

			Registry r = LocateRegistry.createRegistry(1099);
			r.bind("früherkennungs-service", stub);

			System.out.println("Früherkennungsservice hört zu. .");
		} catch (RemoteException r) {

		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
	}

}
