package apm.web.apmservice;

import apm.web.beans.module.Agent;

/**
 * @author ChengBing Han
 * @date 14:10  2018/2/3
 * @description
 */
public interface ApmAgentService {

    /**
     *
     * @param appId 应用id
     * @return  根据应用id 查询agent
     */
    Agent getAgentInfoByAppId(String appId) throws Exception;
}
