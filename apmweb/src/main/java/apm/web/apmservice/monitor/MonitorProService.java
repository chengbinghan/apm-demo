package apm.web.apmservice.monitor;

import apm.web.beans.module.MonitorPro;
import apm.web.beans.module.MonitorStrategy;
import apm.web.core.monitor.MonitorVO;

import java.util.List;

/**
 * @author ChengBing Han
 * @date 16:08  2018/2/26
 * @description
 */
public interface MonitorProService {
    void addMonitorStrategy(MonitorStrategy monitorPro, String monitorVOStrs) throws Exception;

    List<MonitorStrategy> findMonitorStrategy();

    void addMonitorPro(MonitorPro monitorPro, String id, String appId) throws Exception;



    List<MonitorPro> findMonitorProList();
}
