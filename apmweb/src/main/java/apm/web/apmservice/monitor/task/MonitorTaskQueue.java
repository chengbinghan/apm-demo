package apm.web.apmservice.monitor.task;

/**
 * @author ChengBing Han
 * @date 19:48  2018/2/13
 * @description 监控任务队列,单例
 */
public class MonitorTaskQueue<T> extends BaseQueue<T> {

    //使用volatile关键字保其可见性
    volatile private static MonitorTaskQueue monitorTaskQueue = null;

    private MonitorTaskQueue(){}

    public static MonitorTaskQueue getInstance() {
        try {
            //懒汉式
            if(monitorTaskQueue != null){

            }else{
                //创建实例之前可能会有一些准备性的耗时工作
                //Thread.sleep(300);
                synchronized (MonitorTaskQueue.class) {
                    //二次检查
                    if(monitorTaskQueue == null){
                        monitorTaskQueue = new MonitorTaskQueue();
                    }
                }
            }
        } catch (/*Interrupted*/Exception e) {
            e.printStackTrace();
        }
        return monitorTaskQueue;
    }

}
