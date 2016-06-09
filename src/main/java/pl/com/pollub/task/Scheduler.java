package pl.com.pollub.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import pl.com.pollub.db.entity.Conference;
import pl.com.pollub.db.entity.ConferenceChanges;
import pl.com.pollub.service.ConferenceChangesService;
import pl.com.pollub.service.ConferenceService;
import pl.com.pollub.utils.DateUtilities;
import pl.com.pollub.utils.DateUtilities.DateRange;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Component
public class Scheduler {

    private static final Logger log = LoggerFactory.getLogger(Scheduler.class);

    private ConferenceService conferenceService;

    private ConferenceChangesService changedService;

    @Autowired
    public Scheduler(ConferenceService conferenceService, ConferenceChangesService confChangeService) {
        this.conferenceService = conferenceService;
        this.changedService = confChangeService;
    }

    /**
     * Daily task
     */
    @Scheduled(cron = "${cron.daily.expression}")
    private void dailyTask() {
        log.info("Executiong daily task...");
    }

    /**
     * Weekly task
     */
    @Scheduled(cron = "${cron.weekly.expression}")
    private void weeklyTask() {
        log.info("Executiong weekly task...");
    }

    /**
     * Monthly task
     */
    @Scheduled(cron = "${cron.monthly.expression}")
    private void monthlyTask() {
        log.info("Executing monthly task...");
    }

    /**
     * Quarter task
     */
    @Scheduled(cron = "${cron.quarter.expression}")
    private void quarterTask() {
        log.info("Executing quarter task...");
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
    	LocalDateTime startDateMonth = DateUtilities.getDayStart(today, DateRange.MONTH,true);
    	LocalDateTime startDate3Month = DateUtilities.getDayStart(today, DateRange.TREE_MONTH, true);
    	
    	LocalDateTime endDate = DateUtilities.getDayEnd(today, DateRange.DAY, true);
    	LocalDateTime endDateWeek = DateUtilities.getDayEnd(today, DateRange.WEEK, true);
    	LocalDateTime endDateMonth = DateUtilities.getDayEnd(today, DateRange.MONTH, false);
    	LocalDateTime endDate3Month = DateUtilities.getDayEnd(today, DateRange.TREE_MONTH, false);
    	LocalDateTime endDate2 = DateUtilities.getEndOfDay(today);

    	List<ConferenceChanges> changesMagazine = changedService.getConferenceWereMagazineChange(startDateWeek, endDateMonth);
    	List<ConferenceChanges> changesFile = changedService.getConferenceWereFileChange(startDateWeek, endDateMonth);
    	List<ConferenceChanges> changesComments = changedService.getConferenceWereCommentChange(startDateWeek, endDateMonth);
    	List<ConferenceChanges> changesField = changedService.getChangeConferenceAndPropertyFileds(startDateWeek, endDateMonth);
    	List<ConferenceChanges> changeswithout = changedService.getChangeConferenceWithoutFileds(startDateWeek, endDateMonth);

    	List<Conference> allConferences  = conferenceService.getConferenceWhereCreationDateBetween(startDateWeek, endDateMonth);
    	List<Conference> allConferences1 = conferenceService.getConferenceWereApplicationDateIsEnding(startDateWeek, endDateMonth);
    	List<Conference> allConferences2 = conferenceService.getConferenceWhereFullPaperDateIsEnding(startDateWeek, endDateMonth);
    	List<Conference> allConferences3 = conferenceService.getConferenceWherePaymentDateIsEnding(startDateWeek, endDateMonth);
    	List<Conference> allConferences4 = conferenceService.getConferenceWhereStartBetween(startDateWeek, endDateMonth);
    	
    	List<Conference> allConferences5 = conferenceService.getConferenceWereApplicationDateIsEnding(startDate.minusYears(1), endDateMonth.minusYears(1));
    	
    	//    	List<Conference> allConferences = conferenceService.getConferenceByDate(startDate, endDate);
//    	changesComments.stream().map(ConferenceChanges::getType).forEach(System.out::println);
    }
    
}
