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

    List<Conference> getConferenceWhereCreationDateBetween(LocalDateTime start, LocalDateTime end);
    
    List<Conference> getConferenceWereApplicationDateIsEnding(LocalDateTime start, LocalDateTime end);
    
    List<Conference> getConferenceWhereStartBetween(LocalDateTime start, LocalDateTime end);
    
    List<Conference> getConferenceWhereFullPaperDateIsEnding(LocalDateTime start, LocalDateTime end);

    List<Conference> getConferenceWherePaymentDateIsEnding(LocalDateTime start, LocalDateTime end);
}
