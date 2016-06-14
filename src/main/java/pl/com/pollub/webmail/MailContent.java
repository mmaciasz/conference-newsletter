package pl.com.pollub.webmail;

import javafx.util.Pair;
import org.springframework.stereotype.Component;
import pl.com.pollub.constant.UserPreferences;
import pl.com.pollub.db.entity.UserSettings;
import pl.com.pollub.dto.ConferenceWithChanges;
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
     */
    public void createMailContent(final UserSettings user, final List<Pair<ConferenceContentCreator, List<ConferenceWithChanges>>> dataToSend) {
        UserPreferences preferences = UserPreferences.getByNewsletterLevel(user.getNewsletterLevel());
        List<ConferenceContentCreator> allowedContent = preferences.getAllowedContent();
        dataToSend.forEach(data -> {
            ConferenceContentCreator contentCreator = data.getKey();
            if (allowedContent.contains(contentCreator)) {
                data.getValue().stream().map(contentCreator::getContent).forEach(stringBuilderMC::append);
            }
        });
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
