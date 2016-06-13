package pl.com.pollub.webmail.auxiliary;

import pl.com.pollub.db.entity.Conference;

import java.util.Comparator;

/**
 * Created by mmaciasz on 2016-06-13.
 */
public class ConferenceComparator implements Comparator<Conference> {

    private static final ConferenceComparator instance = new ConferenceComparator();

    private ConferenceComparator() {
    }

    @Override
    public int compare(Conference first, Conference second) {
        return first.getName().compareTo(second.getName());
    }

    public static ConferenceComparator getInstance() {
        return instance;
    }
}
