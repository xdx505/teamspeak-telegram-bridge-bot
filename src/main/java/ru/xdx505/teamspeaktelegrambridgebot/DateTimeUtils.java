package ru.xdx505.teamspeaktelegrambridgebot;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateTimeUtils {
    public static boolean isIntervalMoreThanMinutes(Date beforeDate, Date afterDate, int value) {
        return TimeUnit.MILLISECONDS.toMinutes(afterDate.getTime())
                - TimeUnit.MILLISECONDS.toMinutes(beforeDate.getTime())
                >= value;
    }
}
