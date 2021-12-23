package uniprojects.wise2122.verteilteSysteme.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import uniprojects.wise2122.verteilteSysteme.rmi.client.UniklinikRegensburgCallbackIF;
import uniprojects.wise2122.verteilteSysteme.rmi.server.entity.R�ntgenbild;

public interface FrueherkennungsIF extends Remote{
	void analysieren(R�ntgenbild bild, UniklinikRegensburgCallbackIF referenz) throws RemoteException;	
}
