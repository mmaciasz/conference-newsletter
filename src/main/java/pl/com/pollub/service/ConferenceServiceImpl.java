package pl.com.pollub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.pollub.db.ConferenceRepository;
import pl.com.pollub.db.entity.Conference;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mmaciasz on 2016-06-02.
 */
@Service
@Transactional(readOnly = true)
public class ConferenceServiceImpl implements ConferenceService {

    private ConferenceRepository repository;

    @Autowired
    public ConferenceServiceImpl(ConferenceRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Conference> findAllConference() {
        List<Conference> conferences = new ArrayList<>();
        repository.findAll().forEach(conferences::add);
        return conferences;
    }

    @Override
    public Conference getByName(String conferenceName) {
        return repository.getByName(conferenceName);
    }

	@Override
	public List<Conference> getConferenceWhereCreationDateBetween(LocalDateTime start, LocalDateTime end) {
		return repository.getConferenceWhereCreationDateBetween(start, end);
	}

	@Override
	public List<Conference> getConferenceWereApplicationDateIsEnding(LocalDateTime start, LocalDateTime end) {
		return repository.getConferenceWhereApplicationDateIsEnding(start, end);
	}

	@Override
	public List<Conference> getConferenceWhereStartBetween(LocalDateTime start, LocalDateTime end) {
		return repository.getConferenceWhereStartBetween(start, end);
	}

	@Override
	public List<Conference> getConferenceWhereFullPaperDateIsEnding(LocalDateTime start, LocalDateTime end) {
		return repository.getConferenceWhereFullPaperDateIsEnding(start, end);
	}

	@Override
	public List<Conference> getConferenceWherePaymentDateIsEnding(LocalDateTime start, LocalDateTime end) {
		return repository.getConferenceWherePaymentDateIsEnding(start, end);
	}
}
