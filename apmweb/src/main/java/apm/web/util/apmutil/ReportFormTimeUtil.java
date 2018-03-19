package apm.web.util.apmutil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ChengBing Han
 * @date 10:14  2018/2/23
 * @description 报表相关时间工具类
 */
public class ReportFormTimeUtil {

    private ReportFormTimeUtil() {
    }

    /**
     *
     * @return Long 返回今日的凌晨到现在过了多少毫秒
     */
    public static Long getTodayMillisecond(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(date);
        String[] dateArr = dateStr.split(" ");
        String hmsStr = dateArr[1];

        String[] timeArr = hmsStr.split(":");
        String hourStr  = timeArr[0];
        String minuteStr = timeArr[1];
        String secondsStr= timeArr[2];

        Long todayTime = Long.parseLong(hourStr) * 60 * 60 * 1000 + Long.parseLong(minuteStr) * 60 *1000 + Long.parseLong(secondsStr) * 1000;

       return todayTime;
    }


    /**
     * @return Long 昨天凌晨到现在过了多少毫秒
     */
    public static Long getYesterdayMillisecond(){

        Long todayMillisecond = getTodayMillisecond();

        Long oneDayMillisecond = 24*60*60*1000L;
        return todayMillisecond = oneDayMillisecond;
    }


    /**
     *
     * @return Long 获取7日内的时间毫秒值
     */
    public static Long getWeekMillisecond(){

        Long todayMillisecond = getTodayMillisecond();

        Long oneDayMillisecond = 24*60*60*1000L;

        return todayMillisecond + oneDayMillisecond * 6;

    }


    public static Long getCurrentMillisecond(){

        Date date = new Date();
        return date.getTime();
    }


}
