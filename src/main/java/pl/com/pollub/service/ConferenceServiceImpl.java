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
 * Created by Mateusz on 2016-06-02.
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
	public List<Conference> getConferenceByDate(LocalDateTime start, LocalDateTime end) {
		return repository.getConferenceByDate(start, end);
	}
}
