package pl.com.pollub.db;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.com.pollub.db.entity.Conference;
import pl.com.pollub.db.entity.ConferenceChanges;


@Repository
public interface ConferenceChangesRepository extends CrudRepository<ConferenceChanges, Integer> {

    @Query("Select cs from ConferenceChanges cs where type = ?1")
    List<ConferenceChanges> getByType(String type);
    
    @Query("Select cs from ConferenceChanges cs where type = ?1 and changetype=?2")
    List<ConferenceChanges> getByTypeAndChangeType(String type, String changeType);
}
