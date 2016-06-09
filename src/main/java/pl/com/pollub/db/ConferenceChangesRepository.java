package pl.com.pollub.db;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.com.pollub.db.entity.ConferenceChanges;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface ConferenceChangesRepository extends CrudRepository<ConferenceChanges, Integer> {

    @Query("Select cs from ConferenceChanges cs where cs.type = ?1")
    List<ConferenceChanges> getByType(String type);

    @Query("Select cs from ConferenceChanges cs where cs.type = ?1 and cs.changeType=?2")
    List<ConferenceChanges> getByTypeAndChangeType(String type, String changeType);

    @Query("Select cs from ConferenceChanges cs where cs.type ='COMMENT' and cs.changedate between ?1 and ?2 order by cs.changedate")
    List<ConferenceChanges> getConferenceWhereCommentChange(LocalDateTime start, LocalDateTime end);

    @Query("Select cs from ConferenceChanges cs where cs.type ='FILE' and cs.changedate between ?1 and ?2 order by cs.changedate")
    List<ConferenceChanges> getConferenceWhereFileChange(LocalDateTime start, LocalDateTime end);

    @Query("Select cs from ConferenceChanges cs where (cs.type ='MAGAZINE' or cs.type ='SCORE') "
            + "and cs.changedate between ?1 and ?2 order by cs.type, cs.changedate")
    List<ConferenceChanges> getConferenceWhereMagazineChange(LocalDateTime start, LocalDateTime end);

    @Query("Select cs from ConferenceChanges cs where cs.type ='CONFERENCE' and cs.changeType = 'U' "
            + "and cs.changedate between ?1 and ?2 order by cs.changedate")
    List<ConferenceChanges> getChangeConferenceAndPropertyFileds(LocalDateTime start, LocalDateTime end);

    @Query("Select cs from ConferenceChanges cs where cs.type ='CONFERENCE' and cs.changeType = 'E' "
            + "and cs.changedate between ?1 and ?2 order by cs.changedate")
    List<ConferenceChanges> getChangeConferenceWithoutFileds(LocalDateTime start, LocalDateTime end);
    
    
}
