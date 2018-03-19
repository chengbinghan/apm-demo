package apm.web.apmservice.monitor.thread;

import apm.web.apmservice.monitor.MonitorServiceImpl;
import apm.web.beans.module.MonitorPro;
import apm.web.beans.module.MonitorStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChengBing Han
 * @date 17:36  2018/2/13
 * @description 监控线程
 */
public class MonitorThread implements Runnable {


    private MonitorPro monitorPro;
    public static final String THREAD_LOCAL_MAP_KEY = "monitorPro";

    public MonitorThread(MonitorPro monitorPro) {



        this.monitorPro = monitorPro;

    }

    /**
     * @deprecated 从队列中取监控任务。
     */
    @Override
    public void run() {
        executeRun();
    }

    synchronized void executeRun() {

        // TODO: 2018/2/13 调用执行监控 ,传入要监控的MonitorPro
        Thread thread = Thread.currentThread();
        ThreadLocal threadLocal = new ThreadLocal<Map<String, MonitorStrategy>>();
        Map<String, MonitorPro> hashMap = new HashMap<>();
        hashMap.put(THREAD_LOCAL_MAP_KEY, monitorPro);
        threadLocal.set(hashMap);

/*
        Object o = threadLocal.get();
        Object o1 = threadLocal.get();
        ThreadLocal<Object> objectThreadLocal = new ThreadLocal<>();
        Object o2 = objectThreadLocal.get();
        System.out.println("Thread: \t" + Thread.currentThread().getId() + "\t" + Thread.currentThread().getName() + "\t:" + o2);
*/




        // TODO: 2018/2/13
        MonitorServiceImpl monitorService = new MonitorServiceImpl();
        monitorService.execute(hashMap);


    }
}
