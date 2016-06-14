package pl.com.pollub.webmail;

import javafx.util.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.com.ContextConfiguration;
import pl.com.pollub.db.entity.Conference;
import pl.com.pollub.db.entity.UserSettings;
import pl.com.pollub.dto.ConferenceWithChanges;
import pl.com.pollub.service.ConferenceService;
import pl.com.pollub.webmail.auxiliary.ConferenceContentCreator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by mmaciasz on 2016-06-13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ContextConfiguration.class)
public class MailContentTest {

    private static final Logger log = LoggerFactory.getLogger(MailContentTest.class);

    @Autowired
    ConferenceService conferenceService;

    @Autowired
    MailContent mailContent;

    @Test
    public void createMailContent() throws Exception {
        List<Pair<ConferenceContentCreator, List<ConferenceWithChanges>>> dataToSend = new ArrayList<>();
        List<Conference> allConferences = conferenceService.findAllConference();
        List<ConferenceWithChanges> conferences = new ArrayList<>();
        allConferences.forEach(conf -> conferences.add(new ConferenceWithChanges(conf, null)));
        dataToSend.add(new Pair<>(ConferenceContentCreator.NEW, conferences));
        UserSettings user = new UserSettings();
        user.setNewsletterLevel(3);
        mailContent.createMailContent(user, dataToSend);
        String content = this.mailContent.getMailContent();
        log.info(System.lineSeparator() + content);
        assertTrue(!content.isEmpty());
        String emptyContent = mailContent.getMailContent();
        assertTrue(emptyContent.isEmpty());
    }

}