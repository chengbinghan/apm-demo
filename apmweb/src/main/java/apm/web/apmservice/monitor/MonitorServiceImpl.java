package apm.web.apmservice.monitor;

import apm.web.apmservice.monitor.task.MonitorTaskQueue;
import apm.web.apmservice.monitor.thread.MonitorThread;
import apm.web.apmservice.monitor.thread.MonitorThreadPool;
import apm.web.beans.module.*;

import apm.web.core.monitor.MonitorWarnInfo;
import apm.web.dao.apmmysql.ApmTransactionDao;
import apm.web.dao.apmmysql.MonitorDao;
import apm.web.dao.apmmysql.MonitorProDao;
import apm.web.dao.apmmysql.UserDao;
import apm.web.util.apmconstant.APMConstant;
import apm.web.util.monitor.MonitorFlagObj;
import apm.web.util.apmconstant.MonitorConstant;
import apm.web.util.apmutil.ApmStringUtil;
import apm.web.util.apmutil.MonitorDaoObjContainer;
import apm.web.util.apmutil.TimeUtil;
import apm.web.util.email.MonitorEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ChengBing Han
 * @date 21:32  2018/2/12
 * @description
 */
@Service
public class MonitorServiceImpl implements MonitorService {


    MonitorTaskQueue<MonitorPro> monitorTaskQueue = MonitorTaskQueue.getInstance();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 将对象放入容器，Spring多线程@Resource无法注入，所以在多线程之前先注入，然后放到一个对象中，供后期使用
     */
    static MonitorDaoObjContainer monitorDaoObjContainer = new MonitorDaoObjContainer();

    @Resource
    MonitorProDao monitorProDao;
    @Resource
    MonitorDao monitorDao;
    @Resource
    UserDao userDao;

    @Resource
    ApmTransactionDao apmTransactionDao;


    /**
     * @deprecated 查询所有的监控项目，放入缓冲，web项目启动时执行。
     */
/*    @Override
    public List<MonitorPro> getAllMonitorPro() {
        return null;
    }*/

    /**
     * 查询所有要监控的监控项目
     */
    @Override
    public void findMonitor() {


        //如果，标志位为true,则要查数据库。
        if (MonitorFlagObj.getOperateMonitorTable()) {
            //清空监控任务
            monitorTaskQueue.empty();

            List<MonitorPro> monitorProList = monitorProDao.getAllMonitorPro();

            //测试使用,请勿删除。
           /* MonitorPro monitorPro1 = new MonitorPro();
            monitorPro1.setId(1);
            monitorPro1.setUserIds("1,2");
            ApmApplication apmApplication = new ApmApplication();
            apmApplication.setId(11);


            monitorPro1.setApp(apmApplication);

            MonitorStrategy monitorStrategy = new MonitorStrategy();
            monitorStrategy.setName("mstr1");
            monitorStrategy.setId(1);
            monitorStrategy.setType("app");


            Monitor mo = new Monitor();
            mo.setId(1);
            mo.setType("MONITOR_ERROR");
            mo.setTime(1111111111);
            mo.setThreshold("1");
            mo.setRpm(111);
            ArrayList<Monitor> monitors = new ArrayList<>();
            monitors.add(mo);
            monitorStrategy.setMonitorList(monitors);
            monitorPro1.setMonitorStrategy(monitorStrategy);

            monitorTaskQueue.offer(monitorPro1);

            if(true){
                return;
            }*/

            // TODO: 2018/2/21 此处操作可以优化
            for (MonitorPro monitorPro : monitorProList) {
                monitorTaskQueue.offer(monitorPro);

                String monitorsStr = monitorPro.getMonitorStrategy().getMonitors();
                String[] monitorsIdArr = monitorsStr.split(",");


                List<Monitor> monitorList = monitorDao.getMonitorsByIds(monitorsIdArr);
                // TODO: 2018/2/21 根据 monitor id 查询 每个monitor
                monitorPro.getMonitorStrategy().setMonitorList(monitorList);


            }


            //查询后将标志位设置为false. 下次不再查询数据库
            MonitorFlagObj.setOperateMonitorTable(false);
        }
    }


    /**
     * @param map
     */
    @Override
    public void execute(Map<String, MonitorPro> map) {
        /*
         获取线程数据, 如过用户添加监控任务，则会再次查询数据库。
        */
        // findMonitor();

        MonitorPro monitorPro = map.get(MonitorThread.THREAD_LOCAL_MAP_KEY);

        // TODO: 2018/2/24 在此处参数校验，后面的调用方法中不在校验。 包括验证如果是响应时间，那么其阈值是整数。*
        if (monitorPro == null) {
            return;
        }

        if (monitorPro.getApp() == null || ApmStringUtil.isEmpty(monitorPro.getId().toString())) {
            return;
        }
        if (ApmStringUtil.isEmpty(monitorPro.getUserIds())) {

            try {
                String userIds = monitorPro.getUserIds();
                String[] userIdArr = userIds.split(",");
                for (String s : userIdArr) {
                    Integer.parseInt(s);
                }
            } catch (Exception e) {
                logger.error(e.getMessage() + "\r\n报警用户id解析异常，请判断该监控项目接警用户id!!");
                return;

            }
        }
        if (monitorPro.getMonitorStrategy().getMonitorList() == null || monitorPro.getMonitorStrategy().getMonitorList().size() == 0) {
            logger.warn("该警报项目(id:" + monitorPro.getId() + ")没有具体监控项目");
            return;
        }
        //多线程run 方法，spring 无法注入，mybatis 的是接口，无法new。所以在线程开始之前注入，放到自己的一个容器类中。
        MonitorProDao monitorProDao = monitorDaoObjContainer.getMonitorProDao();

        //每次执行后台监控，日志 记录
        String monitorProInfo = "O===||=====================================>> 开始执行一次后台警报监控\r\n" +
                "监控日期：" + TimeUtil.getCurrentTime() + "\r\n" +
                "应用ID" + "\t" + monitorPro.getApp().getId() + "\r\n" +
                "接警用户" + monitorPro.getUserIds() + "\r\n" +
                "警报策略：\r\n" +
                "策略ID:" + "\t" + monitorPro.getMonitorStrategy().getId() +
                "策略名称：" + "\t" + monitorPro.getMonitorStrategy().getName() +
                "策略类型：" + "\t" + monitorPro.getMonitorStrategy().getType() +
                "监控条件：" + "\t" + monitorPro.getMonitorStrategy().getMonitors();

        logger.info(monitorProInfo);

        //要监控的id
        String appId = monitorPro.getApp().getId().toString();
        String userIds = monitorPro.getUserIds();


        MonitorStrategy monitorStrategy = monitorPro.getMonitorStrategy();
        List<Monitor> monitorList = monitorStrategy.getMonitorList();

        for (Monitor monitor : monitorList) {
            MonitorWarnInfo monitorWarnInfo = null;
            // TODO: 2018/2/24 监控类型判断
            //响应时间监控
            if (MonitorConstant.MONITOR_TYPE_RESPNOSE_TIME.equals(monitor.getType())) {
                monitorWarnInfo = monitorResponseTime(monitor, appId, userIds, monitorProDao);
            } else if (MonitorConstant.MONITOR_TYPE_APDEX.equals(monitor.getType())) {
                // TODO: 2018/2/26 监控apdex

                monitorWarnInfo = monitorApdex(monitor, appId, userIds, monitorProDao);

            } else if (MonitorConstant.MONITOR_TYPE_ERROR.equals(monitor.getType())) {
                // TODO: 2018/2/26 监控error
                monitorWarnInfo = monitorErrorPercent(monitor, appId, userIds, monitorProDao);

            }
            //处理警报
            sendMonitorWarnInfo(monitorWarnInfo);
        }

    }


    public void sendMonitorWarnInfo(MonitorWarnInfo monitorWarnInfo) {

        if (monitorWarnInfo != null) {
            //发送邮件
            try {
                logger.info(
                        "\r\nO===||===================>" +
                                "\r\n发生警报，即将发送邮件,info:" +
                                "\r\n" + monitorWarnInfo.toString() +
                                "\nO===||===================>"
                );
                MonitorEmail.seedEamil(monitorWarnInfo);
            } catch (Exception e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }


    }


    public MonitorWarnInfo monitorErrorPercent(Monitor monitor, String appId, String userIds, MonitorProDao monitorProDao) {

        String type = monitor.getType();
        if (!MonitorConstant.MONITOR_TYPE_ERROR.equals(type) || ApmStringUtil.isEmpty(appId)) {
            return null;
        }


        Map<String, String> hashMap = new HashMap<String, String>();

        hashMap.put(MonitorConstant.APP_ID, appId);
        hashMap.put(MonitorConstant.TIME_SECTION, monitor.getTime().toString());
        hashMap.put(MonitorConstant.CURRENT_TIME, TimeUtil.getCurrentMillisecond().toString());
        //监控吞吐率，如果吞吐率，
        Double rpm = monitorRpm(monitor, hashMap, monitorProDao);
        // TODO: 2018/2/24 rpm 值过小，暂时注释到下面的if,实际环境中解开该注释
/*        if (rpm < monitor.getRpm()) {
            return null;
        }*/

        Double errorPercent = monitorProDao.getMonitorErrorPercentByCondition(hashMap);
        MonitorWarnInfo monitorInfo = null;
        if (errorPercent <= Double.parseDouble(monitor.getThreshold())) {

            System.out.println("不用发警报");
            // TODO: 2018/2/24 测试使用， 删除下述的alarm方法调用
            monitorInfo = createMonitorInfo(monitor, appId, userIds);

        } else { // 发送警报
            System.out.println("发出警报");
            monitorInfo = createMonitorInfo(monitor, appId, userIds);


        }

        return monitorInfo;

    }


    /**
     * 监控apdex
     *
     * @param monitor
     * @param appId
     * @param userIds
     * @param monitorProDao
     * @return
     */
    public MonitorWarnInfo monitorApdex(Monitor monitor, String appId, String userIds, MonitorProDao monitorProDao) {

        String type = monitor.getType();
        if (!MonitorConstant.MONITOR_TYPE_APDEX.equals(type) || ApmStringUtil.isEmpty(appId)) {
            return null;
        }


        Map<String, String> hashMap = new HashMap<String, String>();

        hashMap.put(MonitorConstant.APP_ID, appId);
        hashMap.put(MonitorConstant.TIME_SECTION, monitor.getTime().toString());
        hashMap.put(MonitorConstant.CURRENT_TIME, TimeUtil.getCurrentMillisecond().toString());

        hashMap.put(APMConstant.APDEX_SATISFACTION_TIME, APMConstant.SATISFACTION_TIME.toString());
        hashMap.put(APMConstant.APDEX_QUALIFIED_TIME, APMConstant.QUALIFIED_TIME.toString());

        //监控吞吐率，如果吞吐率，
        Double rpm = monitorRpm(monitor, hashMap, monitorProDao);
        // TODO: 2018/2/24 rpm 值过小，暂时注释到下面的if,实际环境中解开该注释
/*        if (rpm < monitor.getRpm()) {
            return null;
        }*/
        Double apdex = getApdexByCondition(hashMap);


        MonitorWarnInfo monitorInfo = null;
        if (apdex <= Double.parseDouble(monitor.getThreshold())) {

            System.out.println("不用发警报");
            // TODO: 2018/2/24 测试使用， 删除下述的alarm方法调用
            monitorInfo = createMonitorInfo(monitor, appId, userIds);

        } else { // 发送警报
            System.out.println("发出警报");
            monitorInfo = createMonitorInfo(monitor, appId, userIds);


        }

        return monitorInfo;


    }


    /*
   Apdex值，是一个APM领域的计算公式，内容如下：
   首先确保	求的响应时间，制定三个响应时间区间：0~3秒、3~12秒、大于12秒，分别代表“满意”、“容忍”、“失望”，统计一段时间内某个请求发生的次数（通过在span表中按照endTimeDelta字段统计），在span表中查出请求响应时间，然后将请求次数分散到三个响应时间区间里面，通过如下公司计算apdex值：
   Apdex指数 = (1*满意数+0.5*容忍数)/请求总数
   例如：如果30分钟内某个请求发生了10次：6次满意，2次容忍，2次失望，则apdex值为
   apdex = (1*6 + 0.5*2)/10 = 0.7   (apdex范围为0~1，0代表最失望，1代表最满意)
   最后按照从小到大排序，显示apdex最低的几个事务。
  */
    public Double getApdexByCondition(Map<String, String> hashMap) {
        if (hashMap == null || ApmStringUtil.isEmpty(hashMap.get(MonitorConstant.APP_ID)) ||
                ApmStringUtil.isEmpty(hashMap.get(MonitorConstant.CURRENT_TIME)) ||
                ApmStringUtil.isEmpty(hashMap.get(MonitorConstant.TIME_SECTION))) {

            throw new IllegalArgumentException("param in method getApdexByCondition is empty or null!!");
        }

        ApmTransactionDao apmTransactionDao = monitorDaoObjContainer.getApmTransactionDao();
        Double apdex = apmTransactionDao.getApdexByCondition(hashMap);

        return apdex;


    }


    /*
      监控响应时间
     */
    public MonitorWarnInfo monitorResponseTime(Monitor monitor, String appId, String userIds, MonitorProDao monitorProDao) {

        String type = monitor.getType();


        if (!MonitorConstant.MONITOR_TYPE_RESPNOSE_TIME.equals(type) || ApmStringUtil.isEmpty(appId)) {
            return null;
        }


        Map<String, String> hashMap = new HashMap<String, String>();

        hashMap.put("appId", appId);
        hashMap.put("timeSection", monitor.getTime().toString());
        hashMap.put("currentTime", TimeUtil.getCurrentMillisecond().toString());

        //监控吞吐率，如果吞吐率，
        Double rpm = monitorRpm(monitor, hashMap, monitorProDao);


        // TODO: 2018/2/24 rpm 值过小，暂时注释到下面的if,实际环境中解开该注释
/*        if (rpm < monitor.getRpm()) {
            return null;
        }*/

        Double avgResponse = monitorProDao.monitorResponseTime(hashMap);


        MonitorWarnInfo monitorInfo = null;
        if (avgResponse <= Double.parseDouble(monitor.getThreshold())) {

            System.out.println("不用发警报");
            // TODO: 2018/2/24 测试使用， 删除下述的alarm方法调用
            monitorInfo = createMonitorInfo(monitor, appId, userIds);

        } else { // 发送警报
            System.out.println("发出警报");
            monitorInfo = createMonitorInfo(monitor, appId, userIds);


        }

        return monitorInfo;
    }


    public MonitorWarnInfo createMonitorInfo(Monitor monitor, String appId, String userIds) {
        UserDao userDao = monitorDaoObjContainer.getUserDao();

        //查询要发邮件的用户
        String[] userIdArr = userIds.split(",");
        List<User> userList = userDao.getUserListByIds(userIdArr);


        String warnInfo = "警报：\r\n" + "\r\n" +
                "发生时间：\t" + TimeUtil.getCurrentTime() +
                "应用id:\t" + appId + "\r\n" +
                "监控类型:\t" + monitor.getType() + "\r\n" +
                "rpm为:\t" + monitor.getRpm() + "\r\n" +
                "已经超过阈值:\t" + monitor.getThreshold();

        MonitorWarnInfo monitorWarnInfo = new MonitorWarnInfo();
        monitorWarnInfo.setAppId(appId);
        monitorWarnInfo.setWarnInfo(warnInfo);
        monitorWarnInfo.setUserList(userList);
        return monitorWarnInfo;


    }

    /**
     * @param monitor
     * @param appId
     * @param monitorProDaoByContainer
     * @return 监控RPM
     */
    public Double monitorRpm(Monitor monitor, Map<String, String> hashMap, MonitorProDao monitorProDao) {


        Long invokeCount = monitorProDao.monitorRpm(hashMap);

        double rpm = invokeCount / monitor.getTime();

        return rpm;


    }


    /**
     * 初始化线程池，并执行监控
     */
    @Override
    public void initMonitorThreadPool() {

        /*
         获取线程数据。初始化
        */
        findMonitor();

        //将对象放入容器，Spring多线程@Resource无法注入，所以在多线程之前先注入，然后放到一个对象中，供后期使用
        monitorDaoObjContainer.setMonitorProDao(monitorProDao);
        monitorDaoObjContainer.setUserDao(userDao);
        monitorDaoObjContainer.setApmTransactionDao(apmTransactionDao);

        MonitorThreadPool monitorThreadPool = new MonitorThreadPool();
        monitorThreadPool.buildThreadPool(MonitorConstant.DEFAULT_MONITORTHREADPOOL);
        monitorThreadPool.executeThread();
    }


}
