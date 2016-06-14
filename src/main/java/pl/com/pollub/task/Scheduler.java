package pl.com.pollub.task;

import javafx.util.Pair;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import pl.com.pollub.db.entity.UserSettings;
import pl.com.pollub.dto.ConferenceWithChanges;
import pl.com.pollub.service.UserSettingsService;
import pl.com.pollub.synchro.UserSettingsSynchronize;
import pl.com.pollub.task.auxiliary.DataGatherGroups;
import pl.com.pollub.utils.DateUtilities;
import pl.com.pollub.utils.DateUtilities.DateRange;
import pl.com.pollub.webmail.MailContent;
import pl.com.pollub.webmail.MailSender;
import pl.com.pollub.webmail.auxiliary.ConferenceContentCreator;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Component
@Scope("singleton")
public class Scheduler {

    private static final Logger log = LoggerFactory.getLogger(Scheduler.class);

    private UserSettingsService userSettings;
    private MailContent mailContent;
    private MailSender mailSender;
    private UserSettingsSynchronize userSettingsSynchro;

    private static final Object synchroObj = new Object();

    @Autowired
    public Scheduler(UserSettingsService userSettings, MailContent mailContent, MailSender mailSender, UserSettingsSynchronize userSettingsSynchro) {
        this.userSettings = userSettings;
        this.mailContent = mailContent;
        this.mailSender = mailSender;
        this.userSettingsSynchro = userSettingsSynchro;
    }

    /**
     * Daily task
     */
    @Scheduled(cron = "${cron.daily.expression}")
    private void dailyTask() {
        log.info("Executiong daily task...");
        userSettingsSynchro.synchronizeUserSettings();
        log.info("Synchronize userSettings...");
        log.info("Gathering data...");
        final List<Pair<ConferenceContentCreator, List<ConferenceWithChanges>>> dataToSend = new LinkedList<>();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime dayStart = DateUtilities.getDayStart(now, DateRange.DAY, true);
        LocalDateTime dayEnd = DateUtilities.getDayEnd(now, DateRange.DAY, true);
        DataGatherGroups.DAILY.getDataPacks().parallelStream().forEach(dg -> dataToSend.add(dg.getData(dayStart, dayEnd)));
        log.info("Fetching all users...");
        List<UserSettings> users = userSettings.getAllUserSettings();
        for (UserSettings user : users) {
            createAndSendEmail(user, "Dzienny raport konferencji", dataToSend);
        }
        log.info("Daily task - zakończono wysyłanie danych.");
    }

    /**
     * Weekly task
     */
    @Scheduled(cron = "${cron.weekly.expression}")
    private void weeklyTask() {
        log.info("Executiong weekly task...");
        final List<Pair<ConferenceContentCreator, List<ConferenceWithChanges>>> dataToSend = new LinkedList<>();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime weekBefore = DateUtilities.getDayStart(now, DateRange.WEEK, true);
        LocalDateTime weekAfter = DateUtilities.getDayEnd(now, DateRange.WEEK, false);
        DataGatherGroups.PAST.getDataPacks().parallelStream().forEach(dg -> dataToSend.add(dg.getData(weekBefore, now)));
        DataGatherGroups.FUTURE.getDataPacks().parallelStream().forEach(dg -> dataToSend.add(dg.getData(now, weekAfter)));
        log.info("Fetching all users...");
        List<UserSettings> users = userSettings.getAllUserSettings();
        for (UserSettings user : users) {
            synchronized (synchroObj) {
                createAndSendEmail(user, "Tygodniowy raport konferencji", dataToSend);
            }
        }
        log.info("Weekly task - zakończono wysyłanie danych.");
    }

    /**
     * Monthly task
     */
    @Scheduled(cron = "${cron.monthly.expression}")
    private void monthlyTask() {
        log.info("Executing monthly task...");
        final List<Pair<ConferenceContentCreator, List<ConferenceWithChanges>>> dataToSend = new LinkedList<>();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime monthBefore = DateUtilities.getDayStart(now, DateRange.MONTH, true);
        LocalDateTime monthAfter = DateUtilities.getDayEnd(now, DateRange.MONTH, false);
        DataGatherGroups.PAST.getDataPacks().parallelStream().forEach(dg -> dataToSend.add(dg.getData(monthBefore, now)));
        DataGatherGroups.FUTURE.getDataPacks().parallelStream().forEach(dg -> dataToSend.add(dg.getData(now, monthAfter)));
        log.info("Fetching all users...");
        List<UserSettings> users = userSettings.getAllUserSettings();
        for (UserSettings user : users) {
            synchronized (synchroObj) {
                createAndSendEmail(user, "Miesięczny raport konferencji", dataToSend);
            }
        }
        log.info("Monthly task - zakończono wysyłanie danych.");
    }

    /**
     * Quarter task
     */
    @Scheduled(cron = "${cron.quarter.expression}")
    private void quarterTask() {
        log.info("Executing quarter task...");
        final List<Pair<ConferenceContentCreator, List<ConferenceWithChanges>>> dataToSend = new LinkedList<>();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime quarterBefore = DateUtilities.getDayStart(now, DateRange.TREE_MONTH, true);
        LocalDateTime quarterAfter = DateUtilities.getDayEnd(now, DateRange.TREE_MONTH, false);
        DataGatherGroups.PAST.getDataPacks().parallelStream().forEach(dg -> dataToSend.add(dg.getData(quarterBefore, now)));
        DataGatherGroups.FUTURE.getDataPacks().parallelStream().forEach(dg -> dataToSend.add(dg.getData(now, quarterAfter)));
        log.info("Fetching all users...");
        List<UserSettings> users = userSettings.getAllUserSettings();
        for (UserSettings user : users) {
            synchronized (synchroObj) {
                createAndSendEmail(user, "Kwartalny raport konferencji", dataToSend);
            }
        }
        log.info("Quarter task - zakończono wysyłanie danych.");
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

    private void createAndSendEmail(final UserSettings user, String title, final List<Pair<ConferenceContentCreator, List<ConferenceWithChanges>>> dataToSend) {
        synchronized (synchroObj) {
            mailContent.createMailContent(user, dataToSend);
            String content = mailContent.getMailContent();
            mailSender.sendEmail(content, title, user.getEmail());
        }
    }
}