package pl.com.pollub.synchro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import pl.com.pollub.constant.Converter;
import pl.com.pollub.db.UserRepository;
import pl.com.pollub.db.UserSettingsRepository;
import pl.com.pollub.db.entity.User;
import pl.com.pollub.db.entity.UserSettings;

@Component
public class UserSettingsSynchronize {

	private UserSettingsRepository userSettingRepository;
	private UserRepository userRepository;
	
	private final Converter<User,UserSettings> converter = from -> {
		UserSettings us = new UserSettings();
		us.setActive(from.getIsActive());
		us.setEmail(from.getEmail());
		us.setUserId(from.getUserId());
		return us;
	};
	
	@Autowired
	public UserSettingsSynchronize(UserSettingsRepository userSettingRepository, UserRepository userRepository ){
		this.userSettingRepository = userSettingRepository;
		this.userRepository =  userRepository;
	}
	
	
	public void synchronizeUserSettings(){
		
		List<User> usersList = new ArrayList<>(); 
		List<UserSettings> usersSettingsList = new ArrayList<>();
		
		Map <Integer,User> mapUser = new HashMap<>();
		Map <Integer,UserSettings> mapUserSettings = new HashMap<>();
		
		List<UserSettings> usersToSaveOrUpdate = new ArrayList<>(); 
		List<UserSettings> usersToDelete = new ArrayList<>(); 
		
		
		userRepository.findAll().forEach(usersList::add);
		userSettingRepository.findAll().forEach(usersSettingsList::add);	
		
		usersList.stream().forEach(u -> mapUser.put(u.getUserId(), u));
		usersSettingsList.stream().forEach(us -> mapUserSettings.put(us.getUserId(), us));
		
		mapUser.forEach((id, user) ->  {
			
				if(!mapUserSettings.containsKey(id)){
					usersToSaveOrUpdate.add(converter.convert(user));
				}
				else if(user.getIsActive() == 0 || StringUtils.isEmpty(user.getEmail())){
					usersToDelete.add(converter.convert(user));
				}
				else{
					UserSettings us = mapUserSettings.get(id);
					if(!us.compare(user)){
						usersToSaveOrUpdate.add(converter.convert(user));
				}
			}
			
		});	
		
		usersToDelete.forEach(us -> us.setActive(0));
		
		usersToSaveOrUpdate.forEach(userSettingRepository::save);
		usersToDelete.forEach(userSettingRepository::save);
	}
	
	
}
