package pl.com.pollub.task;

import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.com.pollub.constant.ChangeType;
import pl.com.pollub.db.entity.Conference;
import pl.com.pollub.db.entity.ConferenceChanges;
import pl.com.pollub.dto.ConferenceWithChanges;
import pl.com.pollub.service.ConferenceChangesService;
import pl.com.pollub.service.ConferenceService;
import pl.com.pollub.utils.DateUtilities;
import pl.com.pollub.utils.DateUtilities.DateRange;
import pl.com.pollub.webmail.MailContent;
import pl.com.pollub.webmail.auxiliary.ConferenceContentCreator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Component
public class Scheduler {

    private static final Logger log = LoggerFactory.getLogger(Scheduler.class);

    private ConferenceService conferenceService;
    private ConferenceChangesService changedService;
    private MailContent mailContent;

    private static final Object synchroObj = new Object();

    @Autowired
    public Scheduler(ConferenceService conferenceService, ConferenceChangesService confChangeService, MailContent mailContent) {
        this.conferenceService = conferenceService;
        this.changedService = confChangeService;
        this.mailContent = mailContent;
    }

    /**
     * Daily task
     */
    @Scheduled(cron = "${cron.daily.expression}")
    private void dailyTask() {
        log.info("Executiong daily task...");
        synchronized (synchroObj) {

        }
    }

    /**
     * Weekly task
     */
    @Scheduled(cron = "${cron.weekly.expression}")
    private void weeklyTask() {
        log.info("Executiong weekly task...");
        synchronized (synchroObj) {

        }
    }

    /**
     * Monthly task
     */
    @Scheduled(cron = "${cron.monthly.expression}")
    private void monthlyTask() {
        log.info("Executing monthly task...");
        synchronized (synchroObj) {

        }
    }

    /**
     * Quarter task
     */
    @Scheduled(cron = "${cron.quarter.expression}")
    private void quarterTask() {
        log.info("Executing quarter task...");
        synchronized (synchroObj) {

        }
    }

    /**
     * Daily garbage collection task.
     */
    @Scheduled(cron = "${cron.gc.expresssion}")
    private void gc() {
        log.info("Garbage collection started.");
        System.gc();
        log.info("Garbage collection ended.");
    }

    @Scheduled(cron = "${cron.test.expresssion}")
    private void testRepo() {

        System.out.println("testRepo");
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime startDate = DateUtilities.getDayStart(today, DateRange.DAY, true);
        LocalDateTime startDateWeek = DateUtilities.getDayStart(today, DateRange.WEEK, true);
        LocalDateTime startDateMonth = DateUtilities.getDayStart(today, DateRange.MONTH, true);
        LocalDateTime startDate3Month = DateUtilities.getDayStart(today, DateRange.TREE_MONTH, true);

        LocalDateTime endDate = DateUtilities.getDayEnd(today, DateRange.DAY, true);
        LocalDateTime endDateWeek = DateUtilities.getDayEnd(today, DateRange.WEEK, true);
        LocalDateTime endDateMonth = DateUtilities.getDayEnd(today, DateRange.MONTH, false);
        LocalDateTime endDate3Month = DateUtilities.getDayEnd(today, DateRange.TREE_MONTH, false);
        LocalDateTime endDate2 = DateUtilities.getEndOfDay(today);

        List<Pair<ConferenceContentCreator, List<ConferenceWithChanges>>> dane = getData(startDate, endDate);
        List<Pair<ConferenceContentCreator, List<ConferenceWithChanges>>> dane1 = getData(startDateMonth, endDateMonth);

        System.out.println("end");

    }

    private List<Pair<ConferenceContentCreator, List<ConferenceWithChanges>>> getData(LocalDateTime startDt, LocalDateTime stopDt) {

        List<Pair<ConferenceContentCreator, List<ConferenceWithChanges>>> ret = new ArrayList<>();

        List<Conference> conferenceWhereCreationDateBetween = conferenceService.getConferenceWhereCreationDateBetween(startDt, stopDt);
        final List<ConferenceWithChanges> confCreationList = new ArrayList<>(conferenceWhereCreationDateBetween.size());
        conferenceWhereCreationDateBetween.forEach(conf -> confCreationList.add(new ConferenceWithChanges(conf, null)));
        Pair<ConferenceContentCreator, List<ConferenceWithChanges>> pair_conferenceWhereCreationDateBetween = new Pair<>(ConferenceContentCreator.NEW, confCreationList);
        ret.add(pair_conferenceWhereCreationDateBetween);

        List<ConferenceChanges> changesFile = changedService.getConferenceWereFileChange(startDt, stopDt);
        final List<ConferenceWithChanges> confFile = new ArrayList<>(changesFile.size());
        changesFile.forEach(conf -> confFile.add(new ConferenceWithChanges(conf.getConference(), null)));
        Pair<ConferenceContentCreator, List<ConferenceWithChanges>> pair_changesFile = new Pair<>(ConferenceContentCreator.CHANGES_FILE, confFile);
        ret.add(pair_changesFile);

        List<ConferenceChanges> changesMagazine = changedService.getConferenceWereMagazineChange(startDt, stopDt);
        final List<ConferenceWithChanges> confMagazine = new ArrayList<>(changesMagazine.size());
        changesMagazine.forEach(conf -> confMagazine.add(new ConferenceWithChanges(conf.getConference(), null)));
        Pair<ConferenceContentCreator, List<ConferenceWithChanges>> pair_changesMagazine = new Pair<>(ConferenceContentCreator.CHANGES_MAGAZINE, confMagazine);
        ret.add(pair_changesMagazine);

        List<ConferenceChanges> conferenceChangesWithFileds = changedService.getChangeConferenceAndPropertyFileds(startDt, stopDt);
        List<ConferenceWithChanges> confWithFields = new ArrayList<>();

        conferenceChangesWithFileds.forEach(conf -> {

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
                stringStream.forEach(el ->
                        confWithFields.add(new ConferenceWithChanges(conf.getConference(), el))
                );
            }
        });
        Pair<ConferenceContentCreator, List<ConferenceWithChanges>> pair_conferenceChangesWithFileds = new Pair<>(ConferenceContentCreator.CHANGES_FIELDS, confWithFields);
        ret.add(pair_conferenceChangesWithFileds);

        List<ConferenceChanges> changesOthers = changedService.getChangeConferenceWithoutFileds(startDt, stopDt);
        final List<ConferenceWithChanges> confOtherChanges = new ArrayList<>(changesOthers.size());
        changesOthers.forEach(conf -> confOtherChanges.add(new ConferenceWithChanges(conf.getConference(), null)));
        Pair<ConferenceContentCreator, List<ConferenceWithChanges>> pair_confOtherChanges = new Pair<>(ConferenceContentCreator.CHANGES_OTHERS, confOtherChanges);
        ret.add(pair_confOtherChanges);

        List<Conference> conferenceApplicationDateEnding = this.conferenceService.getConferenceWereApplicationDateIsEnding(startDt, stopDt);
        final List<ConferenceWithChanges> confApplicationDateEnding = new ArrayList<>(conferenceApplicationDateEnding.size());
        conferenceApplicationDateEnding.forEach(conf -> confApplicationDateEnding.add(new ConferenceWithChanges(conf, null)));
        Pair<ConferenceContentCreator, List<ConferenceWithChanges>> pair_confApplicationDateEnding = new Pair<>(ConferenceContentCreator.EXPIRE_DEADLINES, confApplicationDateEnding);
        ret.add(pair_confApplicationDateEnding);

        List<Conference> conferenceStartBetween = this.conferenceService.getConferenceWhereStartBetween(startDt, stopDt);
        final List<ConferenceWithChanges> confStartBetween = new ArrayList<>(conferenceStartBetween.size());
        conferenceStartBetween.forEach(conf -> confStartBetween.add(new ConferenceWithChanges(conf, null)));
        Pair<ConferenceContentCreator, List<ConferenceWithChanges>> pair_confStartBetween = new Pair<>(ConferenceContentCreator.NEARLY_START, confStartBetween);
        ret.add(pair_confStartBetween);

        List<Conference> conferenceFullPaperDate = this.conferenceService.getConferenceWhereFullPaperDateIsEnding(startDt, stopDt);
        final List<ConferenceWithChanges> confFullPaperDate = new ArrayList<>(conferenceFullPaperDate.size());
        conferenceFullPaperDate.forEach(conf -> confFullPaperDate.add(new ConferenceWithChanges(conf, null)));
        Pair<ConferenceContentCreator, List<ConferenceWithChanges>> pair_confFullPaperDate = new Pair<>(ConferenceContentCreator.FULL_TEXT_DEADLINES, confFullPaperDate);
        ret.add(pair_confFullPaperDate);

        List<Conference> conferencePaymentDate = this.conferenceService.getConferenceWhereFullPaperDateIsEnding(startDt, stopDt);
        final List<ConferenceWithChanges> confPaymentDate = new ArrayList<>(conferencePaymentDate.size());
        conferencePaymentDate.forEach(conf -> confPaymentDate.add(new ConferenceWithChanges(conf, null)));
        Pair<ConferenceContentCreator, List<ConferenceWithChanges>> pair_confPaymentDate = new Pair<>(ConferenceContentCreator.PAYMENT_DEADLINES, confPaymentDate);
        ret.add(pair_confPaymentDate);

        List<Conference> conferenceApplicationDateLastYear = this.conferenceService.getConferenceWereApplicationDateIsEnding(startDt.minusYears(1), stopDt.minusYears(1));
        final List<ConferenceWithChanges> confApplicationDateLastYear = new ArrayList<>(conferenceApplicationDateLastYear.size());
        conferenceApplicationDateLastYear.forEach(conf -> confApplicationDateLastYear.add(new ConferenceWithChanges(conf, null)));
        Pair<ConferenceContentCreator, List<ConferenceWithChanges>> pair_confApplicationDateLastYear = new Pair<>(ConferenceContentCreator.LAST_YEAR, confApplicationDateLastYear);
        ret.add(pair_confApplicationDateLastYear);

        List<Conference> conferenceStartedLastYear = this.conferenceService.getConferenceWhereStartBetween(startDt.minusYears(1), stopDt.minusYears(1));
        final List<ConferenceWithChanges> confStartedLastYear = new ArrayList<>(conferenceStartedLastYear.size());
        conferenceStartedLastYear.forEach(conf -> confStartedLastYear.add(new ConferenceWithChanges(conf, null)));
        Pair<ConferenceContentCreator, List<ConferenceWithChanges>> pair_confStartedLastYear = new Pair<>(ConferenceContentCreator.LAST_YEAR_STARTS, confStartedLastYear);
        ret.add(pair_confStartedLastYear);

        return ret;

    }
}