package pl.com.pollub.task;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.com.pollub.ContextConfiguration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mmaciasz on 2016-05-31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ContextConfiguration.class)
public class SchedulerTest extends TestCase {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void testCron() {
        CronSequenceGenerator cronGenerator = new CronSequenceGenerator("0 0 1 1 * *");
        final Date next;
        try {
            next = cronGenerator.next(DATE_FORMAT.parse("2016-06-14"));
            System.out.println(DATE_FORMAT.format(next));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}