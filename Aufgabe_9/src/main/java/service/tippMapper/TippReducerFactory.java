package service.tippMapper;

import com.hazelcast.mapreduce.Reducer;
import com.hazelcast.mapreduce.ReducerFactory;
import de.othr.vs.xml.Veranstaltung;

import java.util.ArrayList;
import java.util.List;

//                                                context<Suchwort, Veranstaltung>, was wollen wir als Ergebnis?
//                                                       <Suchwort, Veranstaltung, Return>
public class TippReducerFactory implements ReducerFactory<String, Veranstaltung, List<Veranstaltung>> {

    @Override
    public Reducer<Veranstaltung, List<Veranstaltung>> newReducer(String suchwort) {
        return new TippReducer();
    }

}

class TippReducer extends Reducer<Veranstaltung, List<Veranstaltung>>{

    private final List<Veranstaltung> sammler = new ArrayList<>();

    @Override
    //sammelt die emittierten werte und gibt uns den gewünschten returntyp zurück, und das für jedes Suchwort einzeln
    public void reduce(Veranstaltung veranstaltung) {
        if(!sammler.contains(veranstaltung)) {
            sammler.add(veranstaltung);
        }
    }

    @Override
    public List<Veranstaltung> finalizeReduce() {
        return sammler;
    }
}
