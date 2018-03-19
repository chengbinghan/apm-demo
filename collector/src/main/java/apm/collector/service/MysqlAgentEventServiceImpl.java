package apm.collector.service;


import apm.collector.dao.MysqlAgentEventBoDao;
import apm.collector.dao.MysqlAgentEventBo;
import com.navercorp.pinpoint.common.server.bo.event.AgentEventBo;
import com.navercorp.pinpoint.common.server.util.AgentEventTypeCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @author ChengBing Han
 * @date 17:55  2018/3/4
 * @description
 */
@Service
public class MysqlAgentEventServiceImpl implements MysqlAgentEventService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Resource
    MysqlAgentEventBoDao mysqlAgentEventBoDao;

    /**
     * 获取AgentEventBo
     *
     * @param agentEventBo
     */
    @Override
    public void getAgentEventBo(AgentEventBo agentEventBo) {

        // TODO: 2018/3/5  该表数据暂时未用到,所以屏蔽
        if (true) {
            return;
        }

        try {

            // TODO: 2018/3/5 插入前要先判断是否已经插入，不然每分钟都插入很多条，每个agent有多种状体。
            /*
            封装数据
             */
            MysqlAgentEventBo mysqlAgentEventBo = new MysqlAgentEventBo();
            mysqlAgentEventBo.setAgentId(agentEventBo.getAgentId());
            mysqlAgentEventBo.setEventTimestamp(agentEventBo.getEventTimestamp());
            mysqlAgentEventBo.setEventTypeCode(agentEventBo.getEventType().getCode());
            mysqlAgentEventBo.setEventTypeDesc(agentEventBo.getEventType().getDesc());
            //name（） 方法参见Enum 类。
            mysqlAgentEventBo.setEventTypeName(agentEventBo.getEventType().name());
            //getMessageType 方法返回的是Class<?> 类型
            mysqlAgentEventBo.setEventTypeMessage(agentEventBo.getEventType().getMessageType().toString());
            Set<AgentEventTypeCategory> categorySet = agentEventBo.getEventType().getCategory();

            StringBuilder sb = new StringBuilder("");
            for (AgentEventTypeCategory agentEventTypeCategory : categorySet) {
                String category = agentEventTypeCategory.name().toString();
                sb.append(category).append(",");
            }
            //去掉最后一个逗号，
            if (!"".equals(sb.toString())) {
                String categorys = sb.substring(0, sb.length() - 1);
                mysqlAgentEventBo.setEventTypeCategory(categorys);
            }
            mysqlAgentEventBo.setVersion(agentEventBo.getVersion());
            mysqlAgentEventBo.setStartTimestamp(agentEventBo.getStartTimestamp());

            //byte 数组
            byte[] eventBody = agentEventBo.getEventBody();
            StringBuilder eventBodySB = new StringBuilder("");
            for (byte b : eventBody) {
                eventBodySB.append(b).append(",");
            }
            //去掉最后的，上述的eventBody 可能为空，而数据库的该字段不为null，此时存空字符串
            String eventBodySBStr = "";
            if (!"".equals(eventBodySB.toString())) {
                eventBodySBStr = eventBodySB.substring(0, sb.length() - 1);

            }
            mysqlAgentEventBo.setEventBody(eventBodySBStr);
            /*
            插入数据到mysql
             */
            mysqlAgentEventBoDao.insert(mysqlAgentEventBo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();

        }

    }
}