package uniprojects.wise2122.verteilteSysteme.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import uniprojects.wise2122.verteilteSysteme.rmi.server.entity.BerichtIF;
import uniprojects.wise2122.verteilteSysteme.rmi.server.entity.RöntgenbildIF;

public interface FrueherkennungsIF extends Remote{
	BerichtIF analysieren(RöntgenbildIF bild) throws RemoteException;	
}
