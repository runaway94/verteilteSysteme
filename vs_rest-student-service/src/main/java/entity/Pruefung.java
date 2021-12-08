package entity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Pruefung {
    private String pruefungId;

    public Pruefung() { }

    public Pruefung(String pruefungId, String bezeichnung, int ects) {
        this.pruefungId = pruefungId;
        this.bezeichnung = bezeichnung;
        this.ects = ects;
    }

    public void setPruefungId(String pruefungId) {
        this.pruefungId = pruefungId;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public void setEcts(int ects) {
        this.ects = ects;
    }

    @XmlAttribute
    public String getPruefungId() {
        return pruefungId;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public int getEcts() {
        return ects;
    }

    private String bezeichnung;
    private int ects;
}
