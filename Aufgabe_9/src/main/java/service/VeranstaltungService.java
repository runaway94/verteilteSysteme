package service;

import app.Server;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.mapreduce.Job;
import com.hazelcast.mapreduce.JobCompletableFuture;
import com.hazelcast.mapreduce.JobTracker;
import com.hazelcast.mapreduce.KeyValueSource;
import de.othr.vs.xml.Student;
import de.othr.vs.xml.Veranstaltung;
import service.tippMapper.TippCollator;
import service.tippMapper.TippMapper;
import service.tippMapper.TippReducerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Path("events")
public class VeranstaltungService {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    //Beispielpfad: restapi/events
    public String addEvent(Veranstaltung v) {
        String id = UUID.randomUUID().toString();
        v.setId(id);
        IMap<String, Veranstaltung> veranstaltungen = Server.hazelcast.getMap("veranstaltungen");
        veranstaltungen.put(id, v);
        return id;
    }


    @GET
    @Path("{id}")
    //Beispielpfad: /restapi/events/{id}
    public Veranstaltung getEvent(@PathParam("id") String id) {
        IMap<String, Veranstaltung> veranstaltungen = Server.hazelcast.getMap("veranstaltungen");
        return veranstaltungen.get(id);
    }


    @GET
    //Beispielpfad: restapi/events?search=Regensburg+Party+Studierendenheim
    public List<Veranstaltung> getEventsByQuery(@QueryParam("tipps") String tippSuchwoerter) {

        IMap<String, Veranstaltung> veranstaltungen = Server.hazelcast.getMap("veranstaltungen");
        String[] suchwoerter = tippSuchwoerter.split(" ");

        //gib alle Veranstaltungen zurück, wenn keine filter eingegeben werden
        if(suchwoerter.length == 0){
            Collection<Veranstaltung> values = veranstaltungen.values();
            return new ArrayList<>(values);
        }

        JobTracker jobTracker = Server.hazelcast.getJobTracker("event-map-reducer-123");

        //würde eine mag erstellen, wenn wir nicht schon eine hätten
        KeyValueSource<String, Veranstaltung> source = KeyValueSource.fromMap(veranstaltungen);

        //generieren der jobs
        Job<String, Veranstaltung> job = jobTracker.newJob(source);

        //dem job mapper, reducer und collator mitgeben
        JobCompletableFuture<List<Veranstaltung>> future = job.mapper(new TippMapper(suchwoerter))
                .reducer(new TippReducerFactory())
                .submit(new TippCollator());

        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();

    }
}
