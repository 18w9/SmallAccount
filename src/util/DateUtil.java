package util;

import java.util.Calendar;
import java.util.Date;

/**
 * 工具类DateUtil时间
 */
public class DateUtil {
    //定义一天的时间
    private static final long millisecondsOfOneDay =1000 * 60 * 60 * 24;
    //获取日历
    private static Calendar c = Calendar.getInstance();

    //util转sql
    public static java.sql.Date util2sql(java.util.Date d){
        return new java.sql.Date(d.getTime());
    }

    //获取当天0点时间
    public static Date today(){
        c.setTime(new Date());
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND,0);
        return c.getTime();

    }

    //获取当月月初时间
    public static Date monthBegin(){
        c.setTime(new Date());
        c.set(Calendar.DATE, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    //获取当月月末时间
    public static Date monthEnd(){
        return monthEnd(monthBegin());
    }

    //获取当月月末时间
    public static Date monthEnd(Date start){
        c.setTime(start);
        c.set(Calendar.DATE, 1);
        c.add(Calendar.MONTH, 1);
        c.add(Calendar.DATE, -1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    //获取当月剩余天数
    public static  int thisMonthLeftDay() {
        int TotalDay = thisMonthTotalDay();
        today();
        int today = c.get(Calendar.DATE);
        return TotalDay - today + 1;
    }

    // 获取当月天数
    public static int thisMonthTotalDay() {
        c.setTime(new Date());
        monthEnd();
        return c.get(Calendar.DATE);
    }

    // 获取本年年份
    public static int thisYear() {
        today();
        return c.get(Calendar.YEAR);
    }

    //获取当月月份
    public static int thisMonth() {
        today();
        return c.get(Calendar.MONTH);
    }
}
