package service;

import entity.Pruefung;
import entity.Pruefungsleistung;
import entity.Student;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.sql.*;
import java.util.concurrent.atomic.AtomicInteger;

@Path("pruefungsaffairs")
public class PruefungsleistungService {

    private static AtomicInteger nextLeistungId = new AtomicInteger(1);

    @POST
    @Path("leistung")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Pruefungsleistung leistungEintragen(Pruefungsleistung pl) throws SQLException {

        pl.setId(nextLeistungId.getAndIncrement());
        //Verbindung zur Datenbank
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://im-vm-011:3306/vs-08?useSSL=false", "vs-08", "vs-08-pw");
        Statement stmt = c.createStatement();

        //update Student
        if(pl.getNote() < 5) {

            //ects von Prüfung rausfinden
            String query = "SELECT ects  FROM Pruefung WHERE pruefungId = " + pl.getPruefungId();
            ResultSet rs = stmt.executeQuery(query);
            rs.first(); // Moves the cursor to the first row in this ResultSet object
            int ects = rs.getInt("ects");

            String matrikelNr = pl.getMatrikelNr();
            query = "UPDATE Students SET ects = ects + " + ects + " WHERE matrikelNr = " + matrikelNr;
            stmt.executeUpdate(query);
        }

        //prüfungsleistung speichern

        String q2="INSERT INTO Pruefungsleistung VALUES ('" + pl.getPruefungId() + "', '" + pl.getMatrikelNr()+"', '" + pl.getVersuch() +"', " +pl.getNote() +")";
        stmt.executeUpdate(q2);
        c.commit();    // nur wenn beide erfolgreich waren, kommt es zum Commit...

        return pl;

    }
}
