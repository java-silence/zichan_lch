package com.itycu.server.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 生产资产编码
 * @author lch
 * @create 2019-11-24 11:42
 */
public class ZiChanCodeUtil {

    public static final String PREFIX = "ZC_";
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    /**
     * 生产资产编码
     * @return
     */
    public static synchronized String getZiChanCode (){
        String date = simpleDateFormat.format(new Date());
        return PREFIX+date+random6();
    }

    /**
     * 生产当前时间+6位随机数CODE码
     * @return
     */
    public static synchronized String RandomCode (){
        String date = simpleDateFormat.format(new Date());
        return date+random6();
    }

    /**
     * 生产6位随机数
     * @return
     */
    public static synchronized String random6(){
        int intFlag = (int)((Math.random() * 9 + 1) * 100000);
        return String.valueOf(intFlag);
    }

    /**
     * 时间加1秒
     * @param date
     * @return
     */
    public static Date addOneSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, 1);
        return calendar.getTime();
    }

}
