package net.begincode.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
            return (date.getMonth() + 1) + "月" + date.getDate() + "日" + "前";
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
        if (diff > minute) {
            r = (diff / minute);
            return r + " 分钟前";
        }
        return "刚刚";
    }

    public static List<String> getTimeFormatTextList(List<Date> dates) {
            List<String> timeList = new ArrayList<>();
        for (Date date: dates) {
            timeList.add(getTimeFormatText(date));
        }
        return timeList;
    }

    public static int getCurrentMonthDay() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    public static Date parseDateFromTime(Date dateTime) throws ParseException {
        Date reDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        reDate = dateFormat.parse(dateFormat.format(dateTime));
        return reDate;
    }
}

