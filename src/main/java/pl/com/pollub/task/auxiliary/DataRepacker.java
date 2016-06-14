package pl.com.pollub.task.auxiliary;

import javafx.util.Pair;
import pl.com.pollub.db.entity.Conference;
import pl.com.pollub.db.entity.ConferenceChanges;
import pl.com.pollub.dto.ConferenceWithChanges;
import pl.com.pollub.webmail.auxiliary.ConferenceContentCreator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mmaciasz on 2016-06-14.
 */
public class DataRepacker {

    public static Pair<ConferenceContentCreator, List<ConferenceWithChanges>> repackConferenceChanges(List<ConferenceChanges> conferenceChanges, ConferenceContentCreator contentCreator) {
        final List<ConferenceWithChanges> confWithChanges = new ArrayList<>(conferenceChanges.size());
        conferenceChanges.forEach(conf -> confWithChanges.add(new ConferenceWithChanges(conf.getConference(), null)));
        return new Pair<>(contentCreator, confWithChanges);
    }

    public static Pair<ConferenceContentCreator, List<ConferenceWithChanges>> repackConferences(List<Conference> conferences, ConferenceContentCreator contentCreator) {
        final List<ConferenceWithChanges> confWithChanges = new ArrayList<>(conferences.size());
        conferences.forEach(conf -> confWithChanges.add(new ConferenceWithChanges(conf, null)));
        return new Pair<>(contentCreator, confWithChanges);
    }
}
