package apm.web.apmservice.informationservice;

import com.navercorp.pinpoint.web.dao.AgentInfoDao;
import com.navercorp.pinpoint.web.dao.AgentLifeCycleDao;
import com.navercorp.pinpoint.web.dao.ApplicationIndexDao;
import com.navercorp.pinpoint.web.vo.AgentInfo;
import com.navercorp.pinpoint.web.vo.ApplicationAgentList;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author ChengBing Han
 * @date 10:45  2018/2/11
 * @description
 */
@Service
public class ApmAgentInfoServiceImpl implements ApmAgentInfoService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private ApplicationIndexDao applicationIndexDao;
    @Autowired
    private AgentInfoDao agentInfoDao;

    @Autowired
    private AgentLifeCycleDao agentLifeCycleDao;

    @Override
    public ApplicationAgentList getApplicationAgentList(ApplicationAgentList.Key applicationAgentListKey, String applicationName, long timestamp) {
        if (applicationName == null) {
            throw new NullPointerException("applicationName must not be null");
        }
        if (applicationAgentListKey == null) {
            throw new NullPointerException("applicationAgentListKey must not be null");
        }
        final List<String> agentIdList = this.applicationIndexDao.selectAgentIds(applicationName);
        if (logger.isDebugEnabled()) {
            logger.debug("agentIdList={}", agentIdList);
        }

        if (CollectionUtils.isEmpty(agentIdList)) {
            logger.debug("agentIdList is empty. applicationName={}", applicationName);
            return new ApplicationAgentList(new TreeMap<String, List<AgentInfo>>());
        }

        // key = hostname
        // value= list fo agentinfo
        SortedMap<String, List<AgentInfo>> result = new TreeMap<>();

        List<AgentInfo> agentInfos = this.agentInfoDao.getAgentInfos(agentIdList, timestamp);
        this.agentLifeCycleDao.populateAgentStatuses(agentInfos, timestamp);
        for (AgentInfo agentInfo : agentInfos) {
            if (agentInfo != null) {
                String hostname = applicationAgentListKey.getKey(agentInfo);

                if (result.containsKey(hostname)) {
                    result.get(hostname).add(agentInfo);
                } else {
                    List<AgentInfo> list = new ArrayList<>();
                    list.add(agentInfo);
                    result.put(hostname, list);
                }
            }
        }

        for (List<AgentInfo> agentInfoList : result.values()) {
            Collections.sort(agentInfoList, AgentInfo.AGENT_NAME_ASC_COMPARATOR);
        }

        logger.info("getApplicationAgentList={}", result);

        return new ApplicationAgentList(result);
    }
}
