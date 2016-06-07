package pl.com.pollub.service;

import pl.com.pollub.db.entity.ConferenceChanges;
import java.util.List;


public interface ConferenceChangesService {

    List<ConferenceChanges> findAllChanges();

    List<ConferenceChanges> getByType(String type);
    
    List<ConferenceChanges> getByTypeAndOperation(String type, String operation); 
}
