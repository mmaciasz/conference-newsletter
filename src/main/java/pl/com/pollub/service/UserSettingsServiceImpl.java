package pl.com.pollub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.pollub.db.UserSettingsRepository;
import pl.com.pollub.db.entity.UserSettings;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
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
    public List<UserSettings> getAllUserSettingsWithNewsletterLevel(int level) {
        return repository.getAllUserSettingsWithNewsletterLevel(level);
    }

    @Override
    public UserSettings getUserSettingsByUserId(int userId) {
        return repository.getUserSettingsByUserId(userId);
    }

}
