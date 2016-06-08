package pl.com.pollub.task;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.com.ContextConfiguration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mmaciasz on 2016-05-31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ContextConfiguration.class)
public class SchedulerTest extends TestCase {

    private static final Logger log = LoggerFactory.getLogger(SchedulerTest.class);
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Value("${cron.daily.expression}")
    private String dailyExpression;

    @Value("${cron.weekly.expression}")
    private String weeklyExpression;

    @Value("${cron.monthly.expression}")
    private String monthlyExpression;

    @Value("${cron.quarter.expression}")
    private String quarterExpression;

    @Test
    public void testDailyCron() {
        CronSequenceGenerator cronGenerator = new CronSequenceGenerator(dailyExpression);
        testCronExpression(cronGenerator, "2016-05-14 02:00:00", "2016-05-14 01:00:00");
    }

    @Test
    public void testWeeklyCron() {
        CronSequenceGenerator cronGenerator = new CronSequenceGenerator(weeklyExpression);
        testCronExpression(cronGenerator, "2016-06-13 02:30:00", "2016-06-09 01:00:00");
    }

    @Test
    public void testMonthlyCron() {
        CronSequenceGenerator cronGenerator = new CronSequenceGenerator(monthlyExpression);
        testCronExpression(cronGenerator, "2015-01-01 01:00:00", "2014-12-09 01:00:00");
    }

    @Test
    public void testQuarterCron() {
        CronSequenceGenerator cronGenerator = new CronSequenceGenerator(quarterExpression);
        testCronExpression(cronGenerator, "2015-03-01 00:00:00", "2015-02-09 01:00:00");
    }

    private void testCronExpression(CronSequenceGenerator cronGenerator, String expectedDateString, String actualDate) {
        try {
            final Date expectedDate = DATE_FORMAT.parse(expectedDateString);
            final Date next = cronGenerator.next(DATE_FORMAT.parse(actualDate));
            assertEquals(expectedDate, next);
        } catch (ParseException e) {
            log.error("Unparsable date exception. Wrong date: {} or {}.", expectedDateString, actualDate);
        }
    }
}