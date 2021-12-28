package service.tippMapper;

import com.hazelcast.mapreduce.Collator;
import de.othr.vs.xml.Veranstaltung;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class TippCollator implements Collator<Map.Entry<String, List<Veranstaltung>>, List<Veranstaltung>> {

    @Override
    //macht aus suchwort, veranstaltungen pärchen eine gemeinstame liste mit allen veranstaltungen
    public List<Veranstaltung> collate(Iterable<Map.Entry<String, List<Veranstaltung>>> iterable) {
        return StreamSupport
                .stream(iterable.spliterator(), false)
                .map(Map.Entry::getValue)
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList());
    }
}
