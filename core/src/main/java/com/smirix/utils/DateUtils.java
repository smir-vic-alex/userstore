package com.smirix.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Class description
 *
 * @author Smirnov-VA
 * @created on 2019-04-14
 */
public class DateUtils {

    private static final DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    private static DateFormat getFormatter() {
        return (DateFormat)formatter.clone();
    }

    public static Calendar getDate(String dateString) {
        Date date = null;
        if (!"".equals(dateString)) {
            try {
                date = getFormatter().parse(dateString);
            } catch (ParseException e) {
                throw new RuntimeException("Ошибка при получении даты " + e);
            }
        }
        return dateToCalendar(date);
    }

    public static Calendar dateToCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar;
    }

    public static Integer getDiffDateAndCurrentDateInSeconds(String dateString) {
        return (int) ((getDate(dateString).getTimeInMillis() - Calendar.getInstance().getTimeInMillis()) / 1000L);
    }

    public static Long getDiffDateAndCurrentDateInMinutes(Calendar date) {
        return (date.getTimeInMillis() - Calendar.getInstance().getTimeInMillis()) / 60000L;
    }

    public static Long getDiffDateAndCurrentDateInMinutes(String date) {
        return getDiffDateAndCurrentDateInMinutes(getDate(date));
    }

    public static String dateToString(Calendar date) {
        if (date == null) {
            return "";
        }

        return formatter.format(date.getTime());
    }

}
