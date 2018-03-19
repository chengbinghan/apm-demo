package apm.collector.service;

import apm.collector.dao.MysqlAgentLifeCycleBo;
import apm.collector.dao.MysqlAgentLifeCycleDao;
import apm.collector.util.ApmStringUtil;
import com.navercorp.pinpoint.common.server.bo.AgentLifeCycleBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ChengBing Han
 * @date 10:13  2018/3/5
 * @description
 */
@Service
public class MysqlAgentLifeCycleServiceImpl implements MysqlAgentLifeCycleService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Resource
    MysqlAgentLifeCycleDao mysqlAgentLifeCycleDao;

    /**
     * 添加数据到agent_life_cycle 表
     *
     * @param agentLifeCycleBo
     */
    @Override
    public void addAgentLifeCycle(AgentLifeCycleBo agentLifeCycleBo) {

        try {
            //判断是否已经插入
            String agentId = agentLifeCycleBo.getAgentId();
            MysqlAgentLifeCycleBo agentLifeCycleBoByAgentId = getAgentLifeCycleBoByAgentId(agentId);

            //已经插入
            if (agentLifeCycleBoByAgentId != null) {
                return;
            }

            MysqlAgentLifeCycleBo mysqlAgentLifeCycleBo = new MysqlAgentLifeCycleBo();
            mysqlAgentLifeCycleBo.setAgentId(agentId);
            mysqlAgentLifeCycleBo.setStartTimestamp(agentLifeCycleBo.getStartTimestamp());
            mysqlAgentLifeCycleBo.setEventIdentifier(agentLifeCycleBo.getEventIdentifier());
            mysqlAgentLifeCycleBo.setEventTimestamp(agentLifeCycleBo.getEventTimestamp());
            mysqlAgentLifeCycleBo.setVersion(agentLifeCycleBo.getVersion());
            mysqlAgentLifeCycleBo.setStateCode((int) agentLifeCycleBo.getAgentLifeCycleState().getCode());
            mysqlAgentLifeCycleBo.setStateDesc(agentLifeCycleBo.getAgentLifeCycleState().getDesc());
            mysqlAgentLifeCycleBo.setStateName(agentLifeCycleBo.getAgentLifeCycleState().name());


            mysqlAgentLifeCycleDao.insert(mysqlAgentLifeCycleBo);

        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }


    }

    /**
     * 根据agentId 查询agentLIfeCycle
     *
     * @param agentId
     */
    @Override
    public MysqlAgentLifeCycleBo getAgentLifeCycleBoByAgentId(String agentId) {

        MysqlAgentLifeCycleBo mysqlAgentLifeCycleBo = null;
        try {
            if (ApmStringUtil.isEmpty(agentId)) {
                logger.error("param agentId is empyty or agentId is null");
                throw new IllegalArgumentException("param agentId is empyty or agentId is null");
            }
            mysqlAgentLifeCycleBo = mysqlAgentLifeCycleDao.getAgentLifeCycleBoByAgentId(agentId);

        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        return mysqlAgentLifeCycleBo;
    }


}
