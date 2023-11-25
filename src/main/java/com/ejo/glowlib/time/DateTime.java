package com.ejo.glowlib.time;

import java.time.LocalDateTime;
import java.util.Calendar;

/**
 * The DateTime class is an easy-to-use way to store a date and time. The date may be created and formatted.
 * It uses the java Calendar class to store its data. To modify data within this DateTime, it must be done by using
 * the Calendar.
 */
public class DateTime {

    public static final DateTime NULL_TIME = new DateTime(0, 0, 0);

    private final Calendar calendar;

    public DateTime(int year, int month, int day, int hour, int min, int sec) {
        calendar = (Calendar) Calendar.getInstance().clone();
        calendar.set(year, month - 1, day, hour, min, sec);
    }

    public DateTime(int year, int month, int day, int hour, int min) {
        this(year, month, day, hour, min, 0);
    }

    public DateTime(int year, int month, int day, int hour) {
        this(year, month, day, hour, 0, 0);
    }

    public DateTime(int year, int month, int day) {
        this(year, month, day, 0, 0, 0);
    }

    public DateTime(String formattedDateTime) {
        this(Integer.parseInt(formattedDateTime.split(" ")[0].split("-")[0]),
                Integer.parseInt(formattedDateTime.split(" ")[0].split("-")[1]),
                Integer.parseInt(formattedDateTime.split(" ")[0].split("-")[2]),
                Integer.parseInt(formattedDateTime.split(" ")[1].split(":")[0]),
                Integer.parseInt(formattedDateTime.split(" ")[1].split(":")[1]),
                Integer.parseInt(formattedDateTime.split(" ")[1].split(":")[2]));
    }

    public DateTime(long id) {
        this(Integer.parseInt((String.valueOf(id)).substring(0, 4)),
                Integer.parseInt((String.valueOf(id)).substring(4, 6)),
                Integer.parseInt((String.valueOf(id)).substring(6, 8)),
                Integer.parseInt((String.valueOf(id)).substring(8, 10)),
                Integer.parseInt((String.valueOf(id)).substring(10, 12)),
                Integer.parseInt((String.valueOf(id)).substring(12, 14))
        );
    }

    public DateTime getAdded(int seconds) {
        return new DateTime(getYear(), getMonth(), getDay(), getHour(), getMinute(), getSecond() + seconds);
    }

    public boolean isWeekend() {
        int dayOfWeek = getCalendar().get(Calendar.DAY_OF_WEEK);
        return dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY;
    }

    public static DateTime getCurrentDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return new DateTime(localDateTime.getYear(), localDateTime.getMonthValue(), localDateTime.getDayOfMonth(), localDateTime.getHour(), localDateTime.getMinute(), localDateTime.getSecond());
    }

    public long getDateTimeID() {
        try {
            return Long.parseLong(getYearString() + getMonthString() + getDayString() + getHourString() + getMinuteString() + getSecondString());
        } catch (NumberFormatException e) {
            return -1L;
        }
    }

    public int getYear() {
        return Integer.parseInt(getYearString());
    }

    public int getMonth() {
        return Integer.parseInt(getMonthString());
    }

    public int getDay() {
        return Integer.parseInt(getDayString());
    }

    public int getHour() {
        return Integer.parseInt(getHourString());
    }

    public int getMinute() {
        return Integer.parseInt(getMinuteString());
    }

    public int getSecond() {
        return Integer.parseInt(getSecondString());
    }


    public String getYearString() {
        return String.valueOf(getCalendar().getWeekYear());
    }

    public String getMonthString() {
        String month = getCalendar().getTime().toString().substring(4, 19).substring(0, 3);
        return switch (month) {
            case ("Jan") -> "01";
            case ("Feb") -> "02";
            case ("Mar") -> "03";
            case ("Apr") -> "04";
            case ("May") -> "05";
            case ("Jun") -> "06";
            case ("Jul") -> "07";
            case ("Aug") -> "08";
            case ("Sep") -> "09";
            case ("Oct") -> "10";
            case ("Nov") -> "11";
            case ("Dec") -> "12";
            default -> null;
        };
    }

    private String getDayString() {
        return getCalendar().getTime().toString().substring(4, 19).substring(4, 6);
    }

    private String getHourString() {
        return getCalendar().getTime().toString().substring(4, 19).substring(7, 9);
    }

    private String getMinuteString() {
        return getCalendar().getTime().toString().substring(4, 19).substring(10, 12);
    }

    private String getSecondString() {
        return getCalendar().getTime().toString().substring(4, 19).substring(13, 15);
    }


    public Calendar getCalendar() {
        return calendar;
    }


    @Override
    public String toString() {
        return getYearString() + "-" + getMonthString() + "-" + getDayString() + " " + getHourString() + ":" + getMinuteString() + ":" + getSecondString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DateTime dateTime)) return false;
        return dateTime.getYearString().equals(getYearString())
                && dateTime.getMonthString().equals(getMonthString())
                && dateTime.getDayString().equals(getDayString())
                && dateTime.getHourString().equals(getHourString())
                && dateTime.getMinuteString().equals(getMinuteString())
                && dateTime.getSecondString().equals(getSecondString());
    }
}