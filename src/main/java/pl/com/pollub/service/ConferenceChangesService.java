package pl.com.pollub.service;

import pl.com.pollub.db.entity.ConferenceChanges;

import java.time.LocalDateTime;
import java.util.List;


public interface ConferenceChangesService {

    List<ConferenceChanges> findAllChanges();

    List<ConferenceChanges> getByType(String type);

    List<ConferenceChanges> getByTypeAndOperation(String type, String operation);

    List<ConferenceChanges> getConferenceWereCommentChange(LocalDateTime start, LocalDateTime end);

    List<ConferenceChanges> getConferenceWereFileChange(LocalDateTime start, LocalDateTime end);

    List<ConferenceChanges> getConferenceWereMagazineChange(LocalDateTime start, LocalDateTime end);

    List<ConferenceChanges> getChangeConferenceAndPropertyFileds(LocalDateTime start, LocalDateTime end);

    List<ConferenceChanges> getChangeConferenceWithoutFileds(LocalDateTime start, LocalDateTime end);
}
