package pl.com.pollub.service;

import java.util.List;

import pl.com.pollub.db.entity.UserSettings;

public interface UserSettingsService {

	public UserSettings getById(int id);
	
	public List<UserSettings> getAllUserSettings();
	
	public List<UserSettings> getAllUserSettingsWithNewsletterLevel(int level);
	
	public UserSettings getUserSettingsByUserId(int userId);
}
