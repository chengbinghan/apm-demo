package apm.collector.dao;

/**
 * @author ChengBing Han
 * @date 22:25  2018/3/5
 * @description
 */
public interface MysqlApplicationDao {
    MysqlApplication getMysqlApplicationByApplicationName(String applicationName);

    void addAppliationToMysql(MysqlApplication mysqlApplication);
}
