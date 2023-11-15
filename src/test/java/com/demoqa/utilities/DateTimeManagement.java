package com.demoqa.utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeManagement {

    // Date format used for formatting date/time strings
    private static final String DEFAULT_DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";

    // Calendar instance used for date/time calculations
    private static final Calendar CALENDAR = Calendar.getInstance();

    private DateTimeManagement() {
        // Private constructor to prevent instantiation
    }


    //region    Get the current timestamp as a formatted string.
    public static String getCurrentTimestamp() {
        return formatTimestamp(new Date());
    }
    //endregion


    //region    Format a given date as a string using the default date format.
    public static String formatTimestamp(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        return dateFormat.format(date);
    }
    //endregion


    //region    Add a specified number of days to a given date.
    public static Date addDaysToDate(Date date, int daysToAdd) {
        CALENDAR.setTime(date);
        CALENDAR.add(Calendar.DAY_OF_MONTH, daysToAdd);
        return CALENDAR.getTime();
    }
    //endregion


    //region    Get the hour of the day from a given date.
    public static int getHourOfDay(Date date) {
        CALENDAR.setTime(date);
        return CALENDAR.get(Calendar.HOUR_OF_DAY);
    }
    //endregion


    //region    Get the minute of the hour from a given date.
    public static int getMinuteOfHour(Date date) {
        CALENDAR.setTime(date);
        return CALENDAR.get(Calendar.MINUTE);
    }
    //endregion
}

