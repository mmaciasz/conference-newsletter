package pl.com.pollub.service;

import pl.com.pollub.db.entity.Conference;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by mmaciasz on 2016-06-02.
 */
public interface ConferenceService {

    List<Conference> findAllConference();

    Conference getByName(String conferenceName);

    List<Conference> getConferenceByDate(LocalDateTime start, LocalDateTime end);
}
