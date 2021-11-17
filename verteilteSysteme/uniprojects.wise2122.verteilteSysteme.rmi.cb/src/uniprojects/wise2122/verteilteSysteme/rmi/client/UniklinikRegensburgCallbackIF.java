package uniprojects.wise2122.verteilteSysteme.rmi.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

import uniprojects.wise2122.verteilteSysteme.rmi.server.entity.Bericht;

public interface UniklinikRegensburgCallbackIF extends Remote {
	public void setBericht(Bericht bericht) throws RemoteException;
}
