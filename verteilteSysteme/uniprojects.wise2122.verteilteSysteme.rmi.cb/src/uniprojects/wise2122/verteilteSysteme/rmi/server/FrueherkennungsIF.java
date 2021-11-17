package uniprojects.wise2122.verteilteSysteme.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import uniprojects.wise2122.verteilteSysteme.rmi.client.UniklinikRegensburgCallbackIF;
import uniprojects.wise2122.verteilteSysteme.rmi.server.entity.Röntgenbild;

public interface FrueherkennungsIF extends Remote{
	void analysieren(Röntgenbild bild, UniklinikRegensburgCallbackIF referenz) throws RemoteException;	
}
