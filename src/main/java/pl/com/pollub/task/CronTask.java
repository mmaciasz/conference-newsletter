package pl.com.pollub.task;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CronTask {
	
	@Scheduled(cron = "${cronExpression}")
	private void doNothing() {
		System.out.println("Hello you " + new Date().toString());
	}
}
