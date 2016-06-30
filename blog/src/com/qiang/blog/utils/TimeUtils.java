package com.qiang.blog.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtils {

    public static String getWeekOfCal(Calendar cal) {
	String[] weekOfDays = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
	cal.setTimeZone(TimeZone.getTimeZone("GMT+8"));//
	int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
	if (w < 0) {
	    w = 0;
	}
	return weekOfDays[w];
    }

    public static String date2String(String pattern, Date date) {
	return new SimpleDateFormat(pattern).format(date.getTime());
    }

    public static Date String2Date(String strDate) {
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	ParsePosition pos = new ParsePosition(0);
	Date strtodate = formatter.parse(strDate, pos);
	return strtodate;
    }

    public static Date calendar2Date() {
	Calendar calendar = Calendar.getInstance();
	return calendar.getTime();
    }

    public static Calendar date2Calendar() {
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(new Date());
	return calendar;
    }

}
