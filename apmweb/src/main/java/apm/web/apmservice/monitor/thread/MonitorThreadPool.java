package apm.web.apmservice.monitor.thread;

import apm.web.apmservice.monitor.task.MonitorTaskQueue;
import apm.web.beans.module.MonitorPro;
import apm.web.beans.module.MonitorStrategy;
import apm.web.util.apmconstant.MonitorConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ChengBing Han
 * @date 17:43  2018/2/13
 * @description
 */
public class MonitorThreadPool {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ScheduledThreadPoolExecutor exec = null;

    public void buildThreadPool(Integer poolSize) {
        if (exec == null) {
            exec = new ScheduledThreadPoolExecutor(poolSize);
        }
    }

    public void executeThread() {
        if (exec == null) {
            buildThreadPool(MonitorConstant.DEFAULT_MONITORTHREADPOOL);
            logger.warn("you didn't set the size of ScheduledThreadPoolExecutor, it will use default pool size ,the  value is :" + MonitorConstant.DEFAULT_MONITORTHREADPOOL);
        }


        // TODO: 2018/2/13 查询监控队列大小，执行线程
        MonitorTaskQueue<MonitorPro> monitorTaskQueue = MonitorTaskQueue.getInstance();


        Queue<MonitorPro> storage = monitorTaskQueue.getStorage();

        for (MonitorPro monitorPro : storage) {

            exec.scheduleAtFixedRate(new MonitorThread(monitorPro), MonitorConstant.MONITOR_INITIALDELAY, MonitorConstant.MONITOR_PERIOD, TimeUnit.MILLISECONDS);
        }

        // TODO: 2018/2/24  tomcat 关闭后台线程没关闭。。。。。。。。。。。。。。。。。。。。。。问题待解决

    }


}
