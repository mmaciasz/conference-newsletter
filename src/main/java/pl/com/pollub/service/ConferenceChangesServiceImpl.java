package pl.com.pollub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.com.pollub.db.ConferenceChangesRepository;
import pl.com.pollub.db.entity.ConferenceChanges;

import java.util.ArrayList;
import java.util.Date;
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

	@Override
	public List<ConferenceChanges> getConferenceWereCommentChange(Date start, Date end) {
		return repository.getConferenceWereCommentChange(start, end);
	}

	@Override
	public List<ConferenceChanges> getConferenceWereFileChange(Date start, Date end) {
		return repository.getConferenceWereCommentChange(start, end);
	}

	@Override
	public List<ConferenceChanges> getConferenceWereMagazineChange(Date start,
			Date end) {
		return repository.getConferenceWereMagazineChange(start, end);
	}

	@Override
	public List<ConferenceChanges> getChangeConferenceAndPropertyFileds(
			Date start, Date end) {
		return repository.getChangeConferenceAndPropertyFileds(start, end);
	}

	@Override
	public List<ConferenceChanges> getChangeConferenceWithoutFileds(Date start,
			Date end) {
		return repository.getChangeConferenceWithoutFileds(start, end);
	}

}
