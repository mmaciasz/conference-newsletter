package pl.com.pollub.db;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.com.pollub.db.entity.Conference;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by mmaciasz on 2016-06-02.
 */
@Repository
public interface ConferenceRepository extends CrudRepository<Conference, Integer> {

    @Query("Select cs from Conference cs where name = ?1")
    Conference getByName(String name);

    @Query("Select cs from Conference cs where cs.CreationDate between ?1 and ?2")
    List<Conference> getConferenceByDate(LocalDateTime start, LocalDateTime end);

}
