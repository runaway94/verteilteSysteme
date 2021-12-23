package uniprojects.wise2122.verteilteSysteme.rmi.server.entity;

import java.rmi.RemoteException;
import java.util.Date;

public class Bericht implements BerichtIF {

	private Date datum;
	private String diagnose;
	private String weiteresVorgehen;
	
	public Bericht() {
		
	}
	
	public Bericht(Date datum, String diagnose, String weiteresVorgehen) {
		this.datum = datum;
		this.diagnose = diagnose;
		this.weiteresVorgehen = weiteresVorgehen;
	}

	@Override
	public Date getDatum() throws RemoteException {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	@Override
	public String getDiagnose() throws RemoteException {
		return diagnose;
	}

	public void setDiagnose(String diagnose) {
		this.diagnose = diagnose;
	}

	@Override
	public String getWeiteresVorgehen() throws RemoteException {
		return weiteresVorgehen;
	}

	public void setWeiteresVorgehen(String weiteresVorgehen) {
		this.weiteresVorgehen = weiteresVorgehen;
	}

	@Override
	public String toString() {
		return "Datum: " + this.datum + ", Diagnose: " + this.diagnose + ", weiteres Vorgehen: " + this.weiteresVorgehen;
	}

	

}
