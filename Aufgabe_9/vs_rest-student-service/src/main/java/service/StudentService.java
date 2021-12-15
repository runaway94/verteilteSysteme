package service;

import app.OTHRestException;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;
import de.othr.vs.xml.Adresse;
import de.othr.vs.xml.Student;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.*;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Path("studentaffairs")  // alternativer Pfad: @Path("studentaffairs/students") --> ersetzt dann alle @Path-Annotationen unten
public class StudentService {

    private static AtomicInteger nextStudentId = new AtomicInteger(1);
    private static ConcurrentMap<Integer, Student> studentDb = new ConcurrentHashMap<>();

    @POST
    @Path("students")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Student matriculate(Student s) {


        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
        Map<Integer, Student> students = hazelcastInstance.getMap( "students" );
        int lastNr = students.keySet().stream().sorted().findFirst().orElse(0);
        System.out.println(lastNr);
        int neaNumber = lastNr +1;
        s.setMatrikelNr(neaNumber);
        students.put(s.getMatrikelNr(), s);
        return s;

    }

    // @Produces, @Consumes: siehe beispielhaft oben!
    // kann für alle weiteren Methoden entsprechend angefügt werden.

    // Erlaubt @Produces mehrere Media-Types, so kann im Response-Header der Wert
    //    Accept: application/json    bzw.
    //    Accept: application/xml
    // angegeben werden.
    // Fehlt das "Accept", so verwendet der Server seinen konfigurierten Default-Media-Type.


    @DELETE
    @Path("students/{id}")
    public Student exmatriculate(@PathParam("id") int studentId) {

        if(studentDb.containsKey(studentId)) {
            Student geloescht = studentDb.remove(studentId);
            return geloescht;
        } else {
            throw new OTHRestException(404, "Student mit ID " + studentId + " ist nicht immatrikuliert");
        }

    }

    @GET
    @Path("students/{id}")
    public Student getStudentById(@PathParam("id") int studentId) throws SQLException {


        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
        Map<Integer, Student> students = hazelcastInstance.getMap( "students" );
        Student s = students.get(studentId);
        return s;

//        Connection c = DriverManager.getConnection(
//                "jdbc:mysql://im-vm-011:3306/vs-08?useSSL=false", "vs-08", "vs-08-pw");
//        Statement stmt = c.createStatement();
//        String query = "SELECT *  FROM Student WHERE matrikelNr = " + studentId;
//        ResultSet rs = stmt.executeQuery(query);
//
//        rs.first(); // Moves the cursor to the first row in this ResultSet object
//        String name = rs.getString("vorname");
//        Adresse a = new Adresse();
//        a.setStrasse(rs.getString("strasse"));
//        a.setOrt(rs.getString("ort"));
//
//        Student s = new Student();
//        s.setMatrikelNr(studentId);
//        s.setEcts(rs.getInt("ects"));
//        s.setVorname(rs.getString("vorname"));
//        s.setNachname(rs.getString("nachname"));
//        s.setAnschrift(a);
//
//        return s;

    }

    @PUT
    @Path("students/{id}")
    public Student updateStudentAccount(@PathParam("id") int studentId, Student newData) {

        newData.setMatrikelNr(studentId);

        if(studentDb.containsKey(studentId)) {
            studentDb.put(studentId, newData);
            return newData;
        } else {
            throw new OTHRestException(404, "Student mit ID " + studentId + " ist nicht immatrikuliert");
        }

    }

    // Diese Methode hätte eigentlich folgende Annotationen:
    // @GET
    // @Path("students")
    // Diese sind jedoch identisch mit den Annotationen von getStudentsByRange (s. u.)
    public Collection<Student> getAllStudents() {

        return studentDb.values();

    }

    @GET
    @Path("students")
    public Collection<Student> getStudentsByRange(@QueryParam("from") int fromStudentId, @QueryParam("to") int toStudentId) {

        /* Beispiele für mögliche Resource-Pfade zum Aufruf dieser Methode:

              /restapi/studentaffairs/students?from=100&to=108
              /restapi/studentaffairs/students?from=100
              /restapi/studentaffairs/students?to=108
              /restapi/studentaffairs/students

              Die Angabe der Query-Parameter "from" und "to" ist also nicht zwingend erforderlich.
              Werden Sie weggelassen wird entsprechend der Wert 0 übergeben
         */
        if(fromStudentId == 0 && toStudentId == 0)
            return getAllStudents();
        else if(toStudentId == 0 && fromStudentId > 0)
            return studentDb.values()
                    .stream()
                    .filter( student ->
                            student.getMatrikelNr() >= fromStudentId)
                    .collect(Collectors.toSet());
        else
            return studentDb.values()
                   .stream()
                   .filter( student ->
                           student.getMatrikelNr() >= fromStudentId
                                   && student.getMatrikelNr() <= toStudentId)
                   .collect(Collectors.toSet());

    }
}
