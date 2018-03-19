package apm.collector.dao;

/**
 * @author ChengBing Han
 * @date 14:14  2018/3/5
 * @description
 */
public interface MysqlAgentLifeCycleDao {
    void insert(MysqlAgentLifeCycleBo mysqlAgentLifeCycleBo);

    MysqlAgentLifeCycleBo getAgentLifeCycleBoByAgentId(String agentId);
}
