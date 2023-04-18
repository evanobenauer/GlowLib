package org.util.glowlib.time;

import java.util.Calendar;

/**
 * The DateTime class is an easy-to-use way to store a date and time. The date may be created and formatted.
 * It uses the java Calendar class to store its data. To modify data within this DateTime, it must be done by using
 * the Calendar.
 */
public class DateTime {


    private final Calendar calendar;


    public DateTime(int year, int month, int day, int hour, int min, int sec) {
        calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day,hour,min,sec);
    }

    public DateTime(int year, int month, int day, int hour, int min) {
        this(year,month,day,hour,min,0);
    }

    public DateTime(int year, int month, int day, int hour) {
        this(year,month,day,hour,0,0);
    }

    public DateTime(int year, int month, int day) {
        this(year,month,day,0,0,0);
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
        this(Integer.parseInt((id + "").substring(0, 4)),
                Integer.parseInt((id + "").substring(4, 6)),
                Integer.parseInt((id + "").substring(6, 8)),
                Integer.parseInt((id + "").substring(8, 10)),
                Integer.parseInt((id + "").substring(10, 12)),
                Integer.parseInt((id + "").substring(12, 14))
        );
    }


    public String getYear() {
        return "" + getCalendar().getWeekYear();
    }

    public String getMonth() {
        String timeString = getCalendar().getTime().toString().substring(4,19);
        String month = timeString.substring(0,3);
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

    public String getDay() {
        String timeString = getCalendar().getTime().toString().substring(4,19);
        return timeString.substring(4,6);
    }

    public String getHour() {
        String timeString = getCalendar().getTime().toString().substring(4,19);
        return timeString.substring(7,9);
    }

    public String getMinute() {
        String timeString = getCalendar().getTime().toString().substring(4,19);
        return timeString.substring(10,12);
    }

    public String getSecond() {
        String timeString = getCalendar().getTime().toString().substring(4,19);
        return timeString.substring(13,15);
    }

    public int getYearInt() {
        return Integer.parseInt(getYear());
    }

    public int getMonthInt() {
        return Integer.parseInt(getMonth());
    }

    public int getDayInt() {
        return Integer.parseInt(getDay());
    }

    public int getHourInt() {
        return Integer.parseInt(getHour());
    }

    public int getMinuteInt() {
        return Integer.parseInt(getMinute());
    }

    public int getSecondInt() {
        return Integer.parseInt(getSecond());
    }

    public String getFormattedDateTime() {
        return getYear() + "-" + getMonth() + "-" + getDay() + " " + getHour() + ":" + getMinute() + ":" + getSecond();
    }

    public long getDateTimeID() {
        try {
            return Long.parseLong(getYear() + getMonth() + getDay() + getHour() + getMinute() + getSecond());
        } catch (Exception e) {
            return -1L;
        }
    }
    
    public boolean isWeekend() {
        int dayOfWeek = getCalendar().get(Calendar.DAY_OF_WEEK);
        return dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    @Override
    public String toString() {
        return getFormattedDateTime();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DateTime dateTime)) return false;
        return dateTime.getYear().equals(getYear())
                && dateTime.getMonth().equals(getMonth())
                && dateTime.getDay().equals(getDay())
                && dateTime.getHour().equals(getHour())
                && dateTime.getMinute().equals(getMinute())
                && dateTime.getSecond().equals(getSecond());
    }
}
