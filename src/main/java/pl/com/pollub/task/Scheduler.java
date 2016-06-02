package pl.com.pollub.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.com.pollub.db.entity.Conference;
import pl.com.pollub.service.ConferenceService;

import java.util.List;

@Component
public class Scheduler {

    private static final Logger log = LoggerFactory.getLogger(Scheduler.class);

    private ConferenceService conferenceService;

    @Autowired
    public Scheduler(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
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
        List<Conference> allConferences = conferenceService.findAllConference();
        allConferences.stream().map(Conference::getName).forEach(System.out::println);
        Conference byName = conferenceService.getByName("10th International Conference on Interactive Mobile Communication, Technologies and Learning");
        System.out.println(byName.getName());
    }
}
