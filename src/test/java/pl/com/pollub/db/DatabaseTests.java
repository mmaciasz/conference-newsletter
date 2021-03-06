package pl.com.pollub.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;
import pl.com.ContextConfiguration;
import pl.com.pollub.db.entity.Conference;
import pl.com.pollub.db.entity.ConferenceChanges;
import pl.com.pollub.service.ConferenceChangesService;
import pl.com.pollub.service.ConferenceService;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by mmaciasz on 2016-06-08.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ContextConfiguration.class)
public class DatabaseTests {

    private static final Logger log = LoggerFactory.getLogger(DatabaseTests.class);

    @Autowired
    ConferenceService conferenceService;

    @Autowired
    ConferenceChangesService changedService;

    @Test
    public void testConferences() {
        List<Conference> allConferences = conferenceService.findAllConference();
        assertEquals(6, allConferences.size());
        allConferences.stream().map(Conference::getName).forEach(log::info);
        Conference byName = conferenceService.getByName("Technologie przyszłości");
        assertTrue(byName != null);
    }

    @Test
    public void testConferenceChanges() {
        List<ConferenceChanges> allConferences = changedService.findAllChanges();
        assertEquals(6, allConferences.size());
        allConferences.stream().map(ConferenceChanges::getConference).map(Conference::getName).forEach(log::info);
        List<ConferenceChanges> changes = changedService.getByType("E");
        assertTrue(!CollectionUtils.isEmpty(changes));
    }
}
