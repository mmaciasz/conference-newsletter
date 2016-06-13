package pl.com.pollub.webmail;

import org.springframework.stereotype.Component;
import pl.com.pollub.db.entity.Conference;
import pl.com.pollub.webmail.auxiliary.ConferenceContentCreator;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by mmaciasz on 2016-06-10.
 */
@Component
public class MailContent {

    private StringBuilder stringBuilderMC;

    @PostConstruct
    private void initBuilders() {
        stringBuilderMC = new StringBuilder();
    }

    /**
     * Creating content from exact conference type.
     * @param conferences    Conferences to send
     * @param contentCreator Conference type
     */
    public void createMailContent(List<Conference> conferences, ConferenceContentCreator contentCreator) {
        conferences.stream().map(contentCreator::getContent).forEach(stringBuilderMC::append);
    }

    /**
     * After return content is clearing.
     *
     * @return Mail content
     */
    public String getMailContent() {
        String mailContent = stringBuilderMC.toString();
        stringBuilderMC = new StringBuilder();
        return mailContent;
    }
}
