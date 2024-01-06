package com.ejo.glowlib.util;

import com.ejo.glowlib.setting.Container;
import com.ejo.glowlib.time.DateTime;

public class TimeUtil {

    public static final Container<Integer> SECOND_ADJUST = new Container<>(0);

    public static DateTime getAdjustedCurrentTime() {
        return getAdjustedCurrentTime(SECOND_ADJUST.get());
    }

    public static DateTime getAdjustedCurrentTime(int secondAdjust) {
        return DateTime.getCurrentDateTime().getAdded(secondAdjust);
    }

    public static long getSecondDifference(DateTime timeFinal, DateTime timeInitial) {
        return (timeFinal.getCalendar().getTimeInMillis() / 1000) - (timeInitial.getCalendar().getTimeInMillis() / 1000);
    }

    public static double getDateTimePercent(DateTime start, DateTime current, DateTime end) {
        return (double) getSecondDifference(current, start) / (getSecondDifference(end,start));
    }

}
