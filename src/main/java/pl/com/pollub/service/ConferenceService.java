package pl.com.pollub.service;

import pl.com.pollub.db.entity.Conference;

import java.util.List;

/**
 * Created by Mateusz on 2016-06-02.
 */
public interface ConferenceService {

    List<Conference> findAllConference();

    Conference getByName(String conferenceName);
}
