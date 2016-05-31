package pl.com.pollub.task;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
	
	@Scheduled(cron = "${cronExpression}")
	private void doNothing() {
		System.out.println("Hello you " + new Date().toString());
	}

	/**
	 * Task do odśmiecania pamięci raz dziennie.
	 */
	@Scheduled(cron = "${gc.cron.expresssion}")
	private void gc() {
		System.gc();
	}
}
