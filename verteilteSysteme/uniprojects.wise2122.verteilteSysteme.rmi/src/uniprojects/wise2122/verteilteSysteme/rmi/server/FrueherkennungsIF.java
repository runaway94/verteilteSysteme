package uniprojects.wise2122.verteilteSysteme.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import uniprojects.wise2122.verteilteSysteme.rmi.server.entity.Bericht;
import uniprojects.wise2122.verteilteSysteme.rmi.server.entity.R�ntgenbild;

public interface FrueherkennungsIF extends Remote{
	Bericht analysieren(R�ntgenbild bild) throws RemoteException;	
}
