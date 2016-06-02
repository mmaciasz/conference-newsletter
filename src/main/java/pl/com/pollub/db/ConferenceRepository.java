package pl.com.pollub.db;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.com.pollub.db.entity.Conference;

import java.util.List;

/**
 * Created by Mateusz on 2016-06-02.
 */
@Repository
public interface ConferenceRepository extends CrudRepository<Conference, Integer> {

    @Query("Select * from Conference where conferenceName = ?1")
    List<Conference> getByName(String name);
}
