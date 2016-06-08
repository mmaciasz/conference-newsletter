package pl.com.pollub.db;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.com.pollub.db.entity.Conference;
import pl.com.pollub.db.entity.ConferenceChanges;


@Repository
public interface ConferenceChangesRepository extends CrudRepository<ConferenceChanges, Integer> {

    @Query("Select cs from ConferenceChanges cs where cs.type = ?1")
    List<ConferenceChanges> getByType(String type);
    
    @Query("Select cs from ConferenceChanges cs where cs.type = ?1 and cs.changeType=?2")
    List<ConferenceChanges> getByTypeAndChangeType(String type, String changeType);
    
    @Query("Select cs from ConferenceChanges cs where cs.type ='COMMENT' and cs.changedate between ?1 and ?2 order by cs.changedate")
    List<ConferenceChanges> getConferenceWereCommentChange(Date start, Date end);
    
    @Query("Select cs from ConferenceChanges cs where cs.type ='FILE' and cs.changedate between ?1 and ?2 order by cs.changedate")
    List<ConferenceChanges> getConferenceWereFileChange(Date start, Date end);
    
    @Query("Select cs from ConferenceChanges cs where (cs.type ='MAGAZINE' or cs.type ='SCORE') "
    		+ "and cs.changedate between ?1 and ?2 order by cs.type, cs.changedate")
    List<ConferenceChanges> getConferenceWereMagazineChange(Date start, Date end);
}
