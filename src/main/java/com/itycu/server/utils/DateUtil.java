package com.itycu.server.utils;

import org.joda.time.DateTime;
import org.joda.time.Months;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 * @author lch
 * @create 2019-12-13 19:01
 */
public class DateUtil {

    public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_FORMAT1 = "yyyy/MM/dd HH:mm:ss";
    public static final String DEFAULT_FORMATS = "yyyy-MM-dd";
    public static final String DATE_FOMATE_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String Message_TIME = "yyyy年MM月dd日HH点mm分";

    /**
     * 获取当前系统的年份
     * @return
     */
    public static String getSysYear() {
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        return year;
    }

    /**
     * 获取年份
     * @return
     */
    public static String getCurrentYear(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        return sdf.format(date);
    }

    /**
     * 格式化时间(Date 转换成String)
     * @param date   时间
     * @return 字符串
     */
    public static String format(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
        return sdf.format(date);
    }

    /**
     * 获取字符串时间 yyyyMMddHHmmss
     * @return
     */
    public static String getStringDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FOMATE_YYYYMMDDHHMMSS);
        return sdf.format(date);
    }

    /**
     * 计算两个时间的月份差
     * @param before
     * @param after
     * @return
     */
    public static int getBetweenMonths(Date before, Date after) {
        // 格式化时间
        String beforeDate = DateFormat.getDateInstance().format(before);
        String afterDate = DateFormat.getDateInstance().format(after);
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime be = formatter.parseDateTime(beforeDate);
        DateTime af = formatter.parseDateTime(afterDate);
        int months = Months.monthsBetween(be, af).getMonths();
        return months;
    }

    /**
     * 获取本年度月份差值
     * @param date
     * @return
     */
    public static int getThisYearBetweenMonths(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        String thisYear = String.valueOf(year) + "-01-01";
        // 格式化时间
        String afterDate = DateFormat.getDateInstance().format(date);
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime be = formatter.parseDateTime(thisYear);
        DateTime af = formatter.parseDateTime(afterDate);
        int months = Months.monthsBetween(be, af).getMonths();
        return months;
    }

    public static Date addMonthDate(Date olddate, String recordDate) {
        Date date = olddate;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String data = format.format(date);
        String dataStr[] = data.split("-");
        //年份
        int  year = (Integer.parseInt(dataStr[1]) + Integer.parseInt(recordDate))/12;
        //月份
        int yue = (Integer.parseInt(dataStr[1]) + Integer.parseInt(recordDate))%12;
        String a = "";
        if(yue<10){
            if(yue<1){
                a = "12";
            }else{
                a = "0"+yue;
            }
        }else {
            a = yue+"";
        }
        dataStr[0]=String.valueOf(Integer.parseInt(dataStr[0]) + year);
        dataStr[1]=a;
        String newdata = dataStr[0]+"-"+dataStr[1]+"-"+dataStr[2];
        Date newDate = null;
        try {
            newDate = format.parse(newdata);
        } catch (ParseException e) {
            return newDate;
        }
        return newDate;
    }

//    public static void main(String[] args) throws ParseException {
//
//    }

}
