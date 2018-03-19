package apm.collector.service;

import com.navercorp.pinpoint.common.server.bo.event.AgentEventBo;

/**
 * @author ChengBing Han
 * @date 17:54  2018/3/4
 * @description agentEventService 实体类
 */
public interface MysqlAgentEventService {

    /**
     * 获取AgentEventBo
     *
     */
    public void getAgentEventBo(AgentEventBo agentEventBo);
}
