package com.example.dell.classschedule;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class getweek {
    static public int getWeeks(String termBegin) {
        try {
            Date currentTime = new Date();

            SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dFormat.parse(termBegin);

            Calendar calendar = new GregorianCalendar();
            calendar.setFirstDayOfWeek(Calendar.SUNDAY);        //将星期天作为一个星期的开始

            calendar.setTime(date);
            int weeks2 = calendar.get(Calendar.WEEK_OF_YEAR);    // 开学星期数

            calendar.setTime(currentTime);
            int weeks1 = calendar.get(Calendar.WEEK_OF_YEAR);    // 当前星期数

            if (date.after(currentTime)) {
                return 0;
            } else {
                int n = (weeks1 - weeks2 > 0) ? (weeks1 - weeks2 + 1) : (weeks1 - weeks2 + 53);
                return n;
            }
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 计算当前时间是周几
     *
     * @param
     * @return
     */
    static public int getWeekDay() {
        try {
            Date today = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(today);
            int weekday = c.get(Calendar.DAY_OF_WEEK);

            if (weekday == 1) {
                return 7;
            } else {
                return weekday - 1;
            }
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 计算当前时间是周几
     *
     * @param
     * @return
     */
    static public int getWeekDay1() {
        Calendar cal = Calendar.getInstance();
        int i = cal.get(Calendar.DAY_OF_WEEK);
        if (i == 1) {
            return 7;
        } else {
            return i - 1;
        }

    }

    static public String getDayStr(int day) {//由数字星期得出文字星期
        String dayStr;
        switch (day) {
            case 1:
                dayStr = "一";
                break;
            case 2:
                dayStr = "二";
                break;
            case 3:
                dayStr = "三";
                break;
            case 4:
                dayStr = "四";
                break;
            case 5:
                dayStr = "五";
                break;
            case 6:
                dayStr = "六";
                break;
            case 7:
                dayStr = "日";
                break;
            default:
                dayStr = "";
                break;
        }
        return dayStr;
    }

    static public String getDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());//填入当前时间
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        String curDate = s.format(calendar.getTime());  //当前日期
        return curDate;
    }
}
