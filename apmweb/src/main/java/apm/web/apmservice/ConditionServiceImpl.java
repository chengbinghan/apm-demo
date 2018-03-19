package apm.web.apmservice;

import org.springframework.stereotype.Service;

/**
 * @author ChengBing Han
 * @date 13:14  2018/1/30
 * @description
 */
@Service
public class ConditionServiceImpl implements ConditionService {

    //要给个默认值,s时间现在，要查询的开始时间是：当前时间-timeLimit
    private static   Long  timeLimit = 86400000000L;
     //慢sql 设定的阈值
    private static Long slowSqlThreshold = 100000L;

    /**
     * 外部服务类型。
     *
     */
    //HTTP service CODE 类型
    public final static String HTTP_SERVICE_CODE = "8080";
    //查询所有服务类型
    public final static String ALL_SERVICE = "ALL";

    //默认查询耗时外部事务个数
    public final static Integer DEFAULT_EXPENSIVE_OUTSERVICE_NUMBER = 5;



    public static Long getSlowSqlThreshold() {
        return slowSqlThreshold;
    }

    public static void setSlowSqlThreshold(Long slowSqlThreshold) {
        ConditionServiceImpl.slowSqlThreshold = slowSqlThreshold;
    }

    public static Long getTimeLimit() {
        return timeLimit;
    }

    public static void setTimeLimit(Long timeLimit) {
        ConditionServiceImpl.timeLimit = timeLimit;
    }
}
