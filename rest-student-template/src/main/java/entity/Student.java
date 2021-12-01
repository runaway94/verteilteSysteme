package entity;

//Im Package entity befindet sich die Entitäts-Klasse Student. Diese ist mit der Annotation @XmlRootElement versehen und
// hat einen Konstruktor ohne Parameter. Erklären Sie, wofür beide notwendig sind und erklären Sie auch die Wirkungsweise
// der weiteren Annotationen @XmlAccessorType, @XmlAttribute oder @XmlTransient für ein XML-Binding.

import jakarta.xml.bind.annotation.*;

import java.util.Objects;

@XmlRootElement // = <student>; top lvl class
@XmlAccessorType(XmlAccessType.FIELD)   //weather field should be serialized
                                        //FIELD = Every non static, non transient field in a JAXB-bound class will be automatically bound to XML, unless annotated by XmlTransient.
                                        //NONE = None of the fields or properties is bound to XML unless they are specifically annotated with some of the JAXB annotations
                                        //PROPERTY = Every getter/setter pair in a JAXB-bound class will be automatically bound to XML, unless annotated by XmlTransient.
                                        //PUBLIC_MEMBER = Every public getter/setter pair and every public field will be automatically bound to XML, unless annotated by XmlTransient.

public class Student {


    // @XmlTransient Prevents the mapping of a JavaBean property/type to XML representation.
    @XmlAttribute // = < .. => attributes
    private int matrikelNr;
    private String vorname;
    private String nachname;
    private Adresse adresse;

    // Default-Konstruktor zwingend notwendig
    public Student() {}

    public Student(String vorname, String nachname, Adresse adresse) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.adresse = adresse;
    }

    public Student(int matrikelNr, String vorname, String nachname, Adresse adresse) {
        this(vorname, nachname, adresse);
        this.matrikelNr = matrikelNr;
    }


    public int getMatrikelNr() {
        return matrikelNr;
    }

    public void setMatrikelNr(int matrikelNr) {
        this.matrikelNr = matrikelNr;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return matrikelNr == student.matrikelNr;
    }

    @Override
    public int hashCode() {
        return Objects.hash(matrikelNr);
    }
}
