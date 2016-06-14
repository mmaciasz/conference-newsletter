package pl.com.pollub.db;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.com.pollub.db.entity.User;
import pl.com.pollub.db.entity.UserSettings;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

}
