package com.alarm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by pavlo.shtefanesku on 8/29/2016.
 */
public final class TimeUtils {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

    public static String getCurrentTime() {
        return dateFormat.format(Calendar.getInstance().getTime());
    }

    public static String getDifferentTime(int min) {
        String myTime = getCurrentTime();
        Date d = null;
        try {
            d = dateFormat.parse(myTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.MINUTE, min);
        return dateFormat.format(cal.getTime());
    }
}
