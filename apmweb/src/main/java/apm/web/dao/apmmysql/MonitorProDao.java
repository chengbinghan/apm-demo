package apm.web.dao.apmmysql;

import apm.web.beans.module.MonitorPro;

import java.util.List;
import java.util.Map;

/**
 * @author ChengBing Han
 * @date 13:52  2018/2/21
 * @description
 */
public interface    MonitorProDao {

    List<MonitorPro> getAllMonitorPro();

    Double monitorResponseTime(Map<String, String> hashMap);

    Long monitorRpm(Map<String, String> hashMap);

    Double getMonitorErrorPercentByCondition(Map<String, String> hashMap);
}
