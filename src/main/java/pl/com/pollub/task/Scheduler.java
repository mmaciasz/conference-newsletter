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
import pl.com.pollub.task.auxiliary.DataGatherer;
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

        DataGatherer.getByName("LAST_YEAR_STARTS");

        Pair<ConferenceContentCreator, List<ConferenceWithChanges>> dane = DataGatherer.NEW.getData(startDate, endDate);
        Pair<ConferenceContentCreator, List<ConferenceWithChanges>> dane1 = DataGatherer.NEW.getData(startDateMonth, endDateMonth);

        System.out.println("end");

    }
}