package uniprojects.wise2122.verteilteSysteme.rmi.server.entity;

import java.io.Serializable;
import java.util.Date;

public class R�ntgenbild implements Serializable {

	private Date aufnahmenVom;
	private transient String patientenName;
	private byte[] rawData;

	public R�ntgenbild(Date aufnahmenVom, String patientenName, byte[] rawData) {
		super();
		this.aufnahmenVom = aufnahmenVom;
		this.patientenName = patientenName;
		this.rawData = rawData;
	}

	public R�ntgenbild() {
		
	}

	public Date getAufnahmenVom() {
		return aufnahmenVom;
	}

	public void setAufnahmenVom(Date aufnahmenVom) {
		this.aufnahmenVom = aufnahmenVom;
	}

	public String getPatientenName() {
		return patientenName;
	}

	public void setPatientenName(String patientenName) {
		this.patientenName = patientenName;
	}

	public byte[] getRawData() {
		return rawData;
	}

	public void setRawData(byte[] rawData) {
		this.rawData = rawData;
	}

}
