package uniprojects.wise2122.verteilteSysteme.rmi.server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

import uniprojects.wise2122.verteilteSysteme.rmi.server.entity.Bericht;
import uniprojects.wise2122.verteilteSysteme.rmi.server.entity.BerichtIF;
import uniprojects.wise2122.verteilteSysteme.rmi.server.entity.RöntgenbildIF;

public class FrueherkennungsService implements FrueherkennungsIF {

	@Override
	public BerichtIF analysieren(RöntgenbildIF bild) throws RemoteException {
		Date today = new Date();
		Bericht bericht = new Bericht(today, "gebrochenes Herz", "Eiscreme und Netflix");
		BerichtIF berichtStub = (BerichtIF) UnicastRemoteObject.exportObject(bericht, 0);
		return berichtStub;
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
