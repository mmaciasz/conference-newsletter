package pl.com.pollub.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import pl.com.pollub.db.UserSettingsRepository;
import pl.com.pollub.db.entity.UserSettings;

public class UserSettingsServiceImpl implements UserSettingsService {

	private UserSettingsRepository repository;
	
	@Autowired
    public UserSettingsServiceImpl(UserSettingsRepository repository) {
        this.repository = repository;
    }
	
	@Override
	public UserSettings getById(int id) {
		return repository.getById(id);
	}

	@Override
	public List<UserSettings> getAllUserSettings() {
		List<UserSettings> userSettigns = new ArrayList<>();
        repository.findAll().forEach(userSettigns::add);
        return userSettigns;
	}
	
	@Override
	public List<UserSettings> getAllUserSettingsWithNewsletterLevel(int level)
	{
	    return repository.getAllUserSettingsWithNewsletterLevel(level);
	}
	
	@Override
	public UserSettings getUserSettingsByUserId(int userId)
	{
		return repository.getUserSettingsByUserId(userId);
	}

}
