package service;

import entity.Adresse;
import entity.Student;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

@Path("studentaffairs")
public class StudentService {

    private static AtomicInteger nextStudentId = new AtomicInteger(1);
    private static ConcurrentMap<Integer, Student> studentDb = new ConcurrentHashMap<>();  // kann als interne Datenbank verwendet werden

    @POST
    @Path("students")
    public Student matriculate(Student s) {
        studentDb.put(s.getMatrikelNr(), s);
        return s;
    }

    @DELETE
    @Path("students/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student exmatriculate(@PathParam("id") int studentId) {
        Student toEx = studentDb.get(studentId);
        studentDb.remove(studentId, toEx);
        return toEx;
    }

    @Path("students/{id}")
    @Produces(MediaType.APPLICATION_XML)
    @GET
    public Student getStudentById(@PathParam("id") int studentId) {
        Student s = studentDb.get(studentId);
        return s;
    }

//    @PUT
//    @Path("students")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Student updateStudentAccount(int studentId, Student newData) {
//        Student s = studentDb.get(studentId);
//        s.setMatrikelNr(newData.getMatrikelNr());
//        s.setVorname(newData.getVorname());
//        s.setNachname(newData.getNachname());
//       return s;
//    }

    @GET
    @Path("students")
    @Produces(MediaType.APPLICATION_XML)
    public Collection<Student> getAllStudents() {
        return studentDb.values();
    }

    @GET
    @Path("students/{von}/{bis}")
    public Collection<Student> getStudentsByRange( @PathParam("von") int fromStudentId, @PathParam("bis") int toStudentId) {
        // Methode annotieren und ausimplementieren und folgende "throw"-Anweisung löschen!
        throw new IllegalStateException("method 'getStudentsByRange' needs to be implemented first");

    }
}
