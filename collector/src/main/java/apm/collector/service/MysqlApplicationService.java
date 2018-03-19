package apm.collector.service;

/**
 * @author ChengBing Han
 * @date 22:08  2018/3/5
 * @description
 */
public interface MysqlApplicationService {

    void addApplicationToMysql(String applicationName, String serviceType);
}
