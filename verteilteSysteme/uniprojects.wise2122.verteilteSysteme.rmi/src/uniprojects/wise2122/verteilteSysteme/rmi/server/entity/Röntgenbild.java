package uniprojects.wise2122.verteilteSysteme.rmi.server.entity;

import java.io.Serializable;
import java.util.Date;

public class Röntgenbild implements RöntgenbildIF {

	private Date aufnahmenVom;
	private transient String patientenName;
	private byte[] rawData;

	public Röntgenbild(Date aufnahmenVom, String patientenName, byte[] rawData) {
		super();
		this.aufnahmenVom = aufnahmenVom;
		this.patientenName = patientenName;
		this.rawData = rawData;
	}

	public Röntgenbild() {
		
	}

	@Override
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

	@Override
	public byte[] getRawData() {
		return rawData;
	}

	public void setRawData(byte[] rawData) {
		this.rawData = rawData;
	}

}
