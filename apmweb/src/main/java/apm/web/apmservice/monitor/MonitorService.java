package apm.web.apmservice.monitor;

import apm.web.beans.module.MonitorPro;
import apm.web.beans.module.MonitorStrategy;

import java.util.List;
import java.util.Map;

/**
 * @author ChengBing Han
 * @date 21:32  2018/2/12
 * @description
 */
public interface MonitorService {

    /**
     * @description  查询所有的监控项目，放入缓冲，web项目启动时执行。
     */
/*    List<MonitorStrategy> getAllMonitorPro();*/

    /**
     *  执行监控，
     */
    void findMonitor();

    //执行
    void execute(Map<String, MonitorPro> map);

    /**
     * @description 执行监控
     */
    void initMonitorThreadPool();


}
