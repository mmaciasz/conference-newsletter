package pl.com.pollub.utils;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

public class DateUtilities {

	public static Date getEndOfDay(Date date) {
	    return DateUtils.addMilliseconds(DateUtils.ceiling(date, Calendar.DATE), -1);
	}

	public static Date getStartOfDay(Date date) {
	    return DateUtils.truncate(date, Calendar.DATE);
	}
	
	public static Date getDayEnd(Date date, DateRange range, boolean inPast) {

		switch (range) {
		case DAY:
			date = getEndOfDay(date);
			break;
		case WEEK:
			date = getEndOfDay(DateUtils.addWeeks(new Date(), inPast?-1:1));
			break;
		case MONTH:
			date = getEndOfDay(DateUtils.addMonths(new Date(), inPast?-1:1));
			break;
		case TREE_MONTH:
			date = getEndOfDay(DateUtils.addMonths(new Date(), inPast?-3:3));
			break;
		}
		return date;
	}
	
	public static Date getDayStart(Date date, DateRange range, boolean inPast) {
		

		switch (range) {
		case DAY:
			date = getStartOfDay(date);
			break;
		case WEEK:
			date = getStartOfDay(DateUtils.addWeeks(new Date(), inPast?-1:1));
			break;
		case MONTH:
			date = getStartOfDay(DateUtils.addMonths(new Date(), inPast?-1:1));
			break;
		case TREE_MONTH:
			date = getStartOfDay(DateUtils.addMonths(new Date(), inPast?-3:3));
			break;
		}
		return date;
	}
	
	public static enum DateRange{
		DAY,
		WEEK,
		MONTH,
		TREE_MONTH
	};
	
}
