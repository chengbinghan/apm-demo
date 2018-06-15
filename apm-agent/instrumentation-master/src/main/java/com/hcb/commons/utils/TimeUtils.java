package com.hcb.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ChengBing Han
 * @date 14:23  2018/6/15
 * @description
 */
public class TimeUtils {

    private TimeUtils() {
    }


    public static String getTimeStr() {
        final Date date = new Date();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final String format = sdf.format(date);

        return format;
    }
}
