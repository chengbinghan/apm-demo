package com.hcb.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ChengBing Han
 * @date 14:15  2018/2/24
 * @description 时间工具类
 */
public class TimeUtil {


    private TimeUtil() {
    }

    /**
     * @return String 获取当前时间格式为：yyyy-MM-dd HH:mm:ss
     */
    public static String getCurrentTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String dateStr = sdf.format(date);
        return dateStr;
    }

    public static Long getCurrentMillisecond() {
        Date date = new Date();
        long time = date.getTime();
        return time;

    }


}
