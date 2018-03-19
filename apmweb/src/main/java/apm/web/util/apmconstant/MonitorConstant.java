package apm.web.util.apmconstant;

import apm.web.apmservice.monitor.thread.MonitorThreadPool;

/**
 * @author ChengBing Han
 * @date 21:10  2018/2/12
 * @description 监控相关常量
 */
public class MonitorConstant {
    public MonitorConstant() {
    }


    //监控应用
    public static final String MONITOR_PRO_APPLICATION = "MONITOR_APPLICATION";
    //监控事务
    public static final String MONITOR_PRO_TRANSACTION = "MONITOR_TRANSACTION";
    //监控外部服务
    public static final String MONITOR_PRO_OUTSERVICE = "MONITOR_OUTSERVICE";


    /*
    策略类型
     */
    //应用类型
    public static final String MONITOR_STRATEGY_APP = "app";
    //关键事务
    public static final String MONITOR_STRATEGY_TRANSACTION = "transaction";
    //外部服务
    public static final String MONITOR_STRATEGY_OUTSERVICE = "outService";






    /*
    具体监控类型常量
     */
    //监控apdex
    public static final String MONITOR_TYPE_APDEX = "MONITOR_APDEX";
    //监控error
    public static final String MONITOR_TYPE_ERROR = "MONITOR_ERROR";
    //监控response time
    public static final String MONITOR_TYPE_RESPNOSE_TIME = "MONITOR_RESPNOSE_TIME";

    /*
     监控线程多久执行一次，单位秒值
     */




    //30秒执行一次,the period between successive executions
    public static final Integer MONITOR_PERIOD = 30000;
    //the time to delay first execution
    public static final Integer MONITOR_INITIALDELAY = 1000;

    //默认监控MonitorThreadPool大小
    public static final Integer DEFAULT_MONITORTHREADPOOL = 16;


    /*
    常用参数，作为map的key,定义为常量
     */
    public static final String APP_ID = "appId";
    public static  final String CURRENT_TIME="currentTime";
    public static final String TIME_SECTION = "timeSection";




}
