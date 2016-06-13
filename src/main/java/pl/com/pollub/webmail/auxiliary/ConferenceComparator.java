package pl.com.pollub.webmail.auxiliary;

import pl.com.pollub.db.entity.Conference;
import pl.com.pollub.dto.ConferenceWithChanges;

import java.util.Comparator;

/**
 * Created by mmaciasz on 2016-06-13.
 */
public class ConferenceComparator implements Comparator<ConferenceWithChanges> {

    private static final ConferenceComparator instance = new ConferenceComparator();

    private ConferenceComparator() {
    }

    public static ConferenceComparator getInstance() {
        return instance;
    }

    @Override
    public int compare(ConferenceWithChanges first, ConferenceWithChanges second) {
        return first != null && second != null ? first.getConference().getName().compareTo(second.getConference().getName()) : 0;
    }
}
