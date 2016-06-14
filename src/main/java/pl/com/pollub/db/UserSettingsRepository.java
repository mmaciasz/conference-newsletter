package pl.com.pollub.db;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.com.pollub.db.entity.UserSettings;

import java.util.List;

@Repository
public interface UserSettingsRepository extends CrudRepository<UserSettings, Integer> {

    @Query("select us from UserSettings us where us.id = ?1")
    UserSettings getById(int id);

    @Query("select us from UserSettings us where us.active = 1 and us.newsletterLevel = ?1")
    List<UserSettings> getAllUserSettingsWithNewsletterLevel(int level);

    @Query("select us from UserSettings us where us.active = 1 and us.userId = ?1")
    UserSettings getUserSettingsByUserId(int userId);
}