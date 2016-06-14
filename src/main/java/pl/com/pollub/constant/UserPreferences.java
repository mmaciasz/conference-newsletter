package pl.com.pollub.constant;

import pl.com.pollub.webmail.auxiliary.ConferenceContentCreator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mmaciasz on 2016-06-14.
 */
public enum UserPreferences {

    MESSAGES_ONLY(1, new ArrayList<>()),
    CONFERENCES_ONLY(2,
            Arrays.asList(
                    ConferenceContentCreator.EXPIRE_DEADLINES,
                    ConferenceContentCreator.NEARLY_START,
                    ConferenceContentCreator.FULL_TEXT_DEADLINES,
                    ConferenceContentCreator.PAYMENT_DEADLINES,
                    ConferenceContentCreator.LAST_YEAR,
                    ConferenceContentCreator.LAST_YEAR_STARTS
            )),
    CONFERENCES_WITH_PUBLICATIONS(3, Arrays.asList(ConferenceContentCreator.values())),
    ALL(4, Arrays.asList(ConferenceContentCreator.values()));

    private int newsletterLevel;
    private List<ConferenceContentCreator> allowedContent;

    UserPreferences(int newsletterLevel, List<ConferenceContentCreator> allowedContent) {
        this.newsletterLevel = newsletterLevel;
        this.allowedContent = allowedContent;
    }

    public int getNewsletterLevel() {
        return newsletterLevel;
    }

    public List<ConferenceContentCreator> getAllowedContent() {
        return allowedContent;
    }

    public static UserPreferences getByNewsletterLevel(int level) {
        for (UserPreferences userPreferences : UserPreferences.values()) {
            if (userPreferences.getNewsletterLevel() == level) {
                return userPreferences;
            }
        }
        throw new IllegalArgumentException("There is no user preferences matching level - " + level);
    }
}
