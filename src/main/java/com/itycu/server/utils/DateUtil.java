package com.itycu.server.utils;

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

}
