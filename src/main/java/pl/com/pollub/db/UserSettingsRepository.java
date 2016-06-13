package pl.com.pollub.db;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.com.pollub.db.entity.ConferenceChanges;
import pl.com.pollub.db.entity.UserSettings;

@Repository
public interface UserSettingsRepository extends CrudRepository<UserSettings, Integer>{
	
	@Query("select us from usersettings us where us.id = ?1")
	UserSettings getById(int id);
	
	@Query("select us from usersettings where us.active = 1 and us.newsletterlevel = ?1")
    List<UserSettings> getAllUserSettingsWithNewsletterLevel(int level);
	
	@Query("select us from usersettings where us.active = 1 and us.userid = ?1")
	UserSettings getUserSettingsByUserId(int userId);
}