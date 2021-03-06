package entity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Adresse {


    private String strasse;
    private String ort;
    private String plz;

    public Adresse(){}

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adresse adresse = (Adresse) o;
        return plz == adresse.plz && Objects.equals(strasse, adresse.strasse) && Objects.equals(ort, adresse.ort);
    }

    @Override
    public int hashCode() {
        return Objects.hash(strasse, ort, plz);
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getStrasse() {
        return strasse;
    }

    public String getOrt() {
        return ort;
    }

    public String getPlz() {
        return plz;
    }

    public Adresse(String strasse, String ort, String plz) {
        this.strasse = strasse;
        this.ort = ort;
        this.plz = plz;
    }


}
