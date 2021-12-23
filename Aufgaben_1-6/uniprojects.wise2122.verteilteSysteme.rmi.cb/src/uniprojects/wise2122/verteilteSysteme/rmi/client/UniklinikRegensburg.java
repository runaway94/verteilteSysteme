package uniprojects.wise2122.verteilteSysteme.rmi.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

import uniprojects.wise2122.verteilteSysteme.rmi.server.FrueherkennungsIF;
import uniprojects.wise2122.verteilteSysteme.rmi.server.entity.Bericht;
import uniprojects.wise2122.verteilteSysteme.rmi.server.entity.R�ntgenbild;

public class UniklinikRegensburg implements UniklinikRegensburgCallbackIF {

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

	public void bildPr�fen(R�ntgenbild rtgBild, UniklinikRegensburgCallbackIF callbackStub) {
		try {
			erkennungsServer.analysieren(rtgBild, callbackStub);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		R�ntgenbild mock = new R�ntgenbild(new Date(), "Simon", new byte[] { 1, 1, 0, 1 });
		UniklinikRegensburg klinik = new UniklinikRegensburg();
		UniklinikRegensburgCallbackIF callbackStub;
		try {
			callbackStub = (UniklinikRegensburgCallbackIF) UnicastRemoteObject.exportObject(klinik, 0);
			klinik.bildPr�fen(mock, callbackStub);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setBericht(Bericht bericht) throws RemoteException {
		System.out.println(bericht.toString());
	}
}
