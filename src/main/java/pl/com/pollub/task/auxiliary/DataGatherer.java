package pl.com.pollub.task.auxiliary;

import javafx.util.Pair;
import pl.com.pollub.application.AppContext;
import pl.com.pollub.constant.ChangeType;
import pl.com.pollub.db.entity.Conference;
import pl.com.pollub.db.entity.ConferenceChanges;
import pl.com.pollub.dto.ConferenceWithChanges;
import pl.com.pollub.service.ConferenceChangesService;
import pl.com.pollub.service.ConferenceService;
import pl.com.pollub.webmail.auxiliary.ConferenceContentCreator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by mmaciasz on 2016-06-14.
 */
public enum DataGatherer {

    NEW {
        @Override
        public Pair<ConferenceContentCreator, List<ConferenceWithChanges>> getData(LocalDateTime startDt, LocalDateTime stopDt) {
            final List<Conference> conferences = confService.getConferenceWhereCreationDateBetween(startDt, stopDt);
            return DataRepacker.repackConferences(conferences, ConferenceContentCreator.NEW);
        }
    },
    CHANGES_COMMENT {
        @Override
        public Pair<ConferenceContentCreator, List<ConferenceWithChanges>> getData(LocalDateTime startDt, LocalDateTime stopDt) {
            final List<ConferenceChanges> conferenceChanges = confChangesService.getConferenceWereCommentChange(startDt, stopDt);
            return DataRepacker.repackConferenceChanges(conferenceChanges, ConferenceContentCreator.CHANGES_COMMENT);
        }
    },
    CHANGES_FILE {
        @Override
        public Pair<ConferenceContentCreator, List<ConferenceWithChanges>> getData(LocalDateTime startDt, LocalDateTime stopDt) {
            final List<ConferenceChanges> conferenceChanges = confChangesService.getConferenceWereFileChange(startDt, stopDt);
            return DataRepacker.repackConferenceChanges(conferenceChanges, ConferenceContentCreator.CHANGES_FILE);
        }
    },
    CHANGES_MAGAZINE {
        @Override
        public Pair<ConferenceContentCreator, List<ConferenceWithChanges>> getData(LocalDateTime startDt, LocalDateTime stopDt) {
            final List<ConferenceChanges> conferenceChanges = confChangesService.getConferenceWereMagazineChange(startDt, stopDt);
            return DataRepacker.repackConferenceChanges(conferenceChanges, ConferenceContentCreator.CHANGES_MAGAZINE);
        }
    },
    CHANGES_FIELDS {
        @Override
        public Pair<ConferenceContentCreator, List<ConferenceWithChanges>> getData(LocalDateTime startDt, LocalDateTime stopDt) {
            final List<ConferenceChanges> conferenceChanges = confChangesService.getChangeConferenceAndPropertyFields(startDt, stopDt);
            final List<ConferenceWithChanges> confWithChanges = new ArrayList<>();
            conferenceChanges.forEach(conf -> {
                String changedFields = conf.getChangedFields();
                if (changedFields != null && !changedFields.isEmpty()) {
                    String[] byName = changedFields.split(",");
                    Stream<String> stringStream = Stream.of(byName).map(el -> {
                        String[] byType = el.split(";");
                        if (byType.length == 3) {
                            ChangeType changeType = ChangeType.getByName(byType[0]);
                            return String.format(changeType.getChangeName(), byType[1], byType[2]);
                        }
                        return null;
                    });
                    stringStream.forEach(el -> confWithChanges.add(new ConferenceWithChanges(conf.getConference(), el)));
                }
            });
            return new Pair<>(ConferenceContentCreator.CHANGES_FIELDS, confWithChanges);
        }
    },
    CHANGES_OTHERS {
        @Override
        public Pair<ConferenceContentCreator, List<ConferenceWithChanges>> getData(LocalDateTime startDt, LocalDateTime stopDt) {
            final List<ConferenceChanges> conferenceChanges = confChangesService.getChangeConferenceWithoutFileds(startDt, stopDt);
            return DataRepacker.repackConferenceChanges(conferenceChanges, ConferenceContentCreator.CHANGES_OTHERS);
        }
    },
    EXPIRE_DEADLINES {
        @Override
        public Pair<ConferenceContentCreator, List<ConferenceWithChanges>> getData(LocalDateTime startDt, LocalDateTime stopDt) {
            final List<Conference> conferences = confService.getConferenceWereApplicationDateIsEnding(startDt, stopDt);
            return DataRepacker.repackConferences(conferences, ConferenceContentCreator.EXPIRE_DEADLINES);
        }
    },
    NEARLY_START {
        @Override
        public Pair<ConferenceContentCreator, List<ConferenceWithChanges>> getData(LocalDateTime startDt, LocalDateTime stopDt) {
            final List<Conference> conferences = confService.getConferenceWhereStartBetween(startDt, stopDt);
            return DataRepacker.repackConferences(conferences, ConferenceContentCreator.NEARLY_START);
        }
    },
    FULL_TEXT_DEADLINES {
        @Override
        public Pair<ConferenceContentCreator, List<ConferenceWithChanges>> getData(LocalDateTime startDt, LocalDateTime stopDt) {
            final List<Conference> conferences = confService.getConferenceWhereFullPaperDateIsEnding(startDt, stopDt);
            return DataRepacker.repackConferences(conferences, ConferenceContentCreator.FULL_TEXT_DEADLINES);
        }
    },
    PAYMENT_DEADLINES {
        @Override
        public Pair<ConferenceContentCreator, List<ConferenceWithChanges>> getData(LocalDateTime startDt, LocalDateTime stopDt) {
            final List<Conference> conferences = confService.getConferenceWherePaymentDateIsEnding(startDt, stopDt);
            return DataRepacker.repackConferences(conferences, ConferenceContentCreator.PAYMENT_DEADLINES);
        }
    },
    LAST_YEAR {
        @Override
        public Pair<ConferenceContentCreator, List<ConferenceWithChanges>> getData(LocalDateTime startDt, LocalDateTime stopDt) {
            final List<Conference> conferences = confService.getConferenceWereApplicationDateIsEnding(startDt.minusYears(1), stopDt.minusYears(1));
            return DataRepacker.repackConferences(conferences, ConferenceContentCreator.LAST_YEAR);
        }
    },
    LAST_YEAR_STARTS {
        @Override
        public Pair<ConferenceContentCreator, List<ConferenceWithChanges>> getData(LocalDateTime startDt, LocalDateTime stopDt) {
            final List<Conference> conferences = confService.getConferenceWhereStartBetween(startDt.minusYears(1), stopDt.minusYears(1));
            return DataRepacker.repackConferences(conferences, ConferenceContentCreator.LAST_YEAR_STARTS);
        }
    };

    private static final ConferenceService confService = AppContext.getContext().getBean(ConferenceService.class);
    private static final ConferenceChangesService confChangesService = AppContext.getContext().getBean(ConferenceChangesService.class);

    public abstract Pair<ConferenceContentCreator, List<ConferenceWithChanges>> getData(LocalDateTime startDt, LocalDateTime stopDt);

    public static DataGatherer getByName(String name) {
        for (DataGatherer dataGatherer : DataGatherer.values()) {
            if (dataGatherer.name().equals(name)) {
                return dataGatherer;
            }
        }
        throw new IllegalArgumentException("There is no enum for name - " + name);
    }
}
