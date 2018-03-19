package apm.collector.service;

import apm.collector.dao.MysqlAgentLifeCycleBo;
import com.navercorp.pinpoint.common.server.bo.AgentLifeCycleBo;

/**
 * @author ChengBing Han
 * @date 10:13  2018/3/5
 * @description
 */
public interface MysqlAgentLifeCycleService {

    /**
     * 添加数据到agent_life_cycle 表
     * @param agentLifeCycleBo
     */
    public void addAgentLifeCycle(AgentLifeCycleBo agentLifeCycleBo);

    /**
     * 根据agentId 查询agentLIfeCycle
     */
    MysqlAgentLifeCycleBo getAgentLifeCycleBoByAgentId(String agentId);
}
