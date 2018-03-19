package apm.web.dao.apmmysql;

import apm.web.beans.module.Monitor;
import apm.web.beans.module.MonitorStrategy;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author ChengBing Han
 * @date 16:24  2018/2/21
 * @description
 */
public interface MonitorDao {
    List<Monitor> getMonitorsByIds(@Param("monitorsIdArr") String[] monitorsIdArr);

    Integer getMaxMonitorId();

    void addMonitor(Monitor monitor);

    void addMonitorStrategy(MonitorStrategy monitorStrategy);

    List<MonitorStrategy> findMonitorStrategy();

    void addMonitorPro(Map<String, String> hashMap);
}
