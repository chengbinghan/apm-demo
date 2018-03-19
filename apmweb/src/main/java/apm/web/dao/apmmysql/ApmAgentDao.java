package apm.web.dao.apmmysql;

import apm.web.beans.module.Agent;

import java.util.Map;

/**
 * @author ChengBing Han
 * @date 14:16  2018/2/3
 * @description
 */
public interface ApmAgentDao {


    Agent getAgetnInfoByAppId(Map<String, String> hashMap);
}
