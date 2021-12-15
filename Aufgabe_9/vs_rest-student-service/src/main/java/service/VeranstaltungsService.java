package service;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import de.othr.vs.xml.Student;
import de.othr.vs.xml.Veranstaltung;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

public class VeranstaltungsService {



    @GET
    @Path("students/{id}")
    public Veranstaltung getStudentById(@PathParam("id") int studentId) {


        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
        Map<Integer, Veranstaltung> events = hazelcastInstance.getMap("veranstaltungen");
        return null;
    }


    @POST
    @Path("students")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Student matriculate(Veranstaltung v) {


        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
        Map<Integer, Veranstaltung> veranstaltungen = hazelcastInstance.getMap( "veranstaltungen" );
        String id =  java.util.UUID.randomUUID().toString();

    }
}
