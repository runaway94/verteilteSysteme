package uniprojects.wise2122.verteilteSysteme.rmi.server.entity;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface BerichtIF extends Remote {
	String getDiagnose() throws RemoteException;

	String getWeiteresVorgehen() throws RemoteException;

	Date getDatum() throws RemoteException;
}
