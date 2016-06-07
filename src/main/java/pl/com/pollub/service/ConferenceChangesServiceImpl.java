package pl.com.pollub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.com.pollub.db.ConferenceChangesRepository;
import pl.com.pollub.db.entity.ConferenceChanges;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional(readOnly = true)
public class ConferenceChangesServiceImpl implements ConferenceChangesService {

    private ConferenceChangesRepository repository;

    @Autowired
    public ConferenceChangesServiceImpl(ConferenceChangesRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ConferenceChanges> findAllChanges() {
        List<ConferenceChanges> conferences = new ArrayList<>();
        repository.findAll().forEach(conferences::add);
        return conferences;
    }

    @Override
    public List<ConferenceChanges> getByType(String conferenceName) {
        return repository.getByType(conferenceName);
    }

	@Override
	public List<ConferenceChanges> getByTypeAndOperation(String type, String operation) {
		return repository.getByTypeAndChangeType(type, operation);
		
	}

}
