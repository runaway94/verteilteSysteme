package uniprojects.wise2122.verteilteSysteme.rmi.server.entity;

import java.io.Serializable;
import java.util.Date;

public class Bericht implements Serializable {

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
	
	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getDiagnose() {
		return diagnose;
	}

	public void setDiagnose(String diagnose) {
		this.diagnose = diagnose;
	}

	public String getWeiteresVorgehen() {
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
