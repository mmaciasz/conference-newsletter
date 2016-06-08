package pl.com.pollub.service;

import pl.com.pollub.db.entity.ConferenceChanges;

import java.util.Date;
import java.util.List;


public interface ConferenceChangesService {

    List<ConferenceChanges> findAllChanges();

    List<ConferenceChanges> getByType(String type);
    
    List<ConferenceChanges> getByTypeAndOperation(String type, String operation);

    List<ConferenceChanges> getConferenceWereCommentChange(Date start, Date end);
    
    List<ConferenceChanges> getConferenceWereFileChange(Date start, Date end);
    
    List<ConferenceChanges> getConferenceWereMagazineChange (Date start, Date end);
    
    List<ConferenceChanges> getChangeConferenceAndPropertyFileds(Date start, Date end);
    
    List<ConferenceChanges> getChangeConferenceWithoutFileds(Date start, Date end);
}
