package pl.com.pollub.utils;

import java.time.LocalDateTime;

public class DateUtilities {

    private static LocalDateTime getEndOfDay(LocalDateTime date) {
        return date.withHour(23).withMinute(59).withSecond(59).withNano(0);
    }

    private static LocalDateTime getStartOfDay(LocalDateTime date) {
        return date.withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

    public static LocalDateTime getDayEnd(LocalDateTime date, DateRange range, boolean inPast) {

        switch (range) {
            case DAY:
                date = getEndOfDay(inPast ? date.minusDays(1) : date);
                break;
            case WEEK:
                date = getEndOfDay(inPast ? date : date.plusWeeks(1));
                break;
            case MONTH:
                date = getEndOfDay(inPast ? date : date.plusMonths(1));
                break;
            case TREE_MONTH:
                date = getEndOfDay(inPast ? date : date.plusMonths(3));
                break;
        }
        return date;
    }

    public static LocalDateTime getDayStart(LocalDateTime date, DateRange range, boolean inPast) {


        switch (range) {
            case DAY:
                date = getStartOfDay(inPast ? date.minusDays(1) : date);
                break;
            case WEEK:
                date = getStartOfDay(inPast ? date.minusWeeks(1) : date);
                break;
            case MONTH:
                date = getStartOfDay(inPast ? date.minusMonths(1) : date);
                break;
            case TREE_MONTH:
                date = getStartOfDay(inPast ? date.minusMonths(3) : date);
                break;
        }
        return date;
    }

    public enum DateRange {
        DAY,
        WEEK,
        MONTH,
        TREE_MONTH
    }

}
