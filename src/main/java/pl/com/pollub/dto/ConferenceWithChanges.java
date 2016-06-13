package pl.com.pollub.dto;

import pl.com.pollub.db.entity.Conference;

/**
 * Created by mmaciasz on 2016-06-13.
 */
public class ConferenceWithChanges {

    private Conference conference;
    private String changes;

    public ConferenceWithChanges(Conference conference, String changes) {
        this.conference = conference;
        this.changes = changes;
    }

    public Conference getConference() {
        return conference;
    }

    public String getChanges() {
        return changes;
    }
}
