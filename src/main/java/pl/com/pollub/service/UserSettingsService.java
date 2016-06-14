package pl.com.pollub.service;

import pl.com.pollub.db.entity.UserSettings;

import java.util.List;

public interface UserSettingsService {

    UserSettings getById(int id);

    List<UserSettings> getAllUserSettings();

    List<UserSettings> getAllUserSettingsWithNewsletterLevel(int level);

    UserSettings getUserSettingsByUserId(int userId);
}
