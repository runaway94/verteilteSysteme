package uniprojects.wise2122.verteilteSysteme.rmi.server.entity;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface RöntgenbildIF extends Remote {
	Date getAufnahmenVom()  throws RemoteException;
	byte[] getRawData()  throws RemoteException;

}
