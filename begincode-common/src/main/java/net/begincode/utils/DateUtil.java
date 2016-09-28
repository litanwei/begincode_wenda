package net.begincode.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by saber on 2016/9/15.
 */
public class DateUtil {
    private final static long minute = 60 * 1000;// 1分钟
    private final static long hour = 60 * minute;// 1小时
    private final static long day = 24 * hour;// 1天
    private final static long month = getCurrentMonthDay() * day;// 月
    private final static long year = 12 * month;// 年


    public static String getTimeFormatText(Date date) {
        if (date == null) {
            return null;
        }
        long diff = new Date().getTime() - date.getTime();
        long r = 0;
        if (diff > year) {
            r = (diff / year);
            return "很久以前";
        }
        if (diff > month) {
            r = (diff / month);
            return r + " 月前";
        }
        if (diff > day) {
            r = (diff / day);
            return r + " 天前";
        }
        if (diff > hour) {
            r = (diff / hour);
            return r + " 小时前";
        }
        return "刚刚";
    }
    public static int getCurrentMonthDay(){
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }
}
