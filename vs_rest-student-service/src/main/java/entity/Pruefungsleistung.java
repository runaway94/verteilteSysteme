package entity;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Pruefungsleistung {

    private int id;
    private String pruefungId;
    private String matrikelNr;
    private int versuch;
    private float note;

    public Pruefungsleistung(int id, String pruefungId, String matrikelNr, int versuch, float note) {
        this.id = id;
        this.pruefungId = pruefungId;
        this.matrikelNr = matrikelNr;
        this.versuch = versuch;
        this.note = note;
    }

    public Pruefungsleistung() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPruefungId() {
        return pruefungId;
    }

    public void setPruefungId(String pruefungId) {
        this.pruefungId = pruefungId;
    }

    public String getMatrikelNr() {
        return matrikelNr;
    }

    public void setMatrikelNr(String matrikelNr) {
        this.matrikelNr = matrikelNr;
    }

    public int getVersuch() {
        return versuch;
    }

    public void setVersuch(int versuch) {
        this.versuch = versuch;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }
}
