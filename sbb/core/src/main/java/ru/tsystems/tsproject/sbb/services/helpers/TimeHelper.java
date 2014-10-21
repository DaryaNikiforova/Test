package ru.tsystems.tsproject.sbb.services.helpers;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by apple on 20.10.14.
 */
public class TimeHelper {
    private TimeHelper() {}

    public static Date getHourBack(Date date, int hour, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, - hour);
        cal.add(Calendar.MINUTE, - minute);
        return cal.getTime();
    }

    public static Date addHours (Date date, int hour, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, hour);
        cal.add(Calendar.MINUTE, minute);
        return cal.getTime();
    }
}
