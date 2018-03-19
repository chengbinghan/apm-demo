package apm.web.apmservice.informationservice;

import com.navercorp.pinpoint.web.vo.ApplicationAgentList;

/**
 * @author ChengBing Han
 * @date 10:45  2018/2/11
 * @description agent 所在的环境信息
 */
public interface ApmAgentInfoService {



    public ApplicationAgentList getApplicationAgentList(ApplicationAgentList.Key applicationAgentListKey, String applicationName, long timestamp);

}


