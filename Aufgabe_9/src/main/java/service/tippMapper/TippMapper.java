package service.tippMapper;

import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;
import de.othr.vs.xml.Veranstaltung;

import java.util.Arrays;

//                                      <InputKey, InputValue, OutputKey, OutputValue>
public class TippMapper implements Mapper<String, Veranstaltung, String, Veranstaltung> {

    private String[] suchwoerter;

    //                suchwörter wie bei runnables beim erzeugen des mappers mitgeben
    public TippMapper(String[] suchwoerter){
        this.suchwoerter = suchwoerter;
    }


    @Override
    //              <InputKey, InputValue, die neue map, die emittiert wird>
    public void map(String veranstaltungsId, Veranstaltung veranstaltung, Context<String, Veranstaltung> context) {
        Arrays.stream(suchwoerter)
                .filter(w -> contains(veranstaltung, w))
                .forEach(w -> context.emit(w, veranstaltung)); //jede veranstaltung emittieren, die das Suchwort enthält
    }

    private boolean contains(Veranstaltung veranstaltung, String wort){
        wort = wort.toLowerCase();
        return veranstaltung.getBeschreibung().toLowerCase().contains(wort)
                || veranstaltung.getTitel().toLowerCase().contains(wort)
                || veranstaltung.getOwner().toLowerCase().contains(wort);
    }
}
