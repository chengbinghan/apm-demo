package apm.web.apmcontroller;

import apm.web.apmservice.ApmAgentService;
import apm.web.beans.module.Agent;
import apm.web.util.apmutil.JsonObject;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author ChengBing Han
 * @date 23:45  2018/2/2
 * @description
 */

@Controller
@CrossOrigin
public class AgentController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    ApmAgentService apmAgentService;

    /**
     * @param appId 应用applicationId
     * @description 查询某个应用的agent 信息
     */
    @RequestMapping(value = "/agentInfoByAppId", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<Agent> getAgentInfoByAppId(String appId) {

        JsonObject<Agent> json = null;
        try {


            Agent agent = apmAgentService.getAgentInfoByAppId(appId);
            json = new JsonObject<>();
            json.setObj(agent);
            json.setResponeCode(200);
            json.setSuccess(true);
        } catch (Exception e) {
            logger.error(e.getMessage());
            json = new JsonObject<>();
            json.setErrMsg(e.getMessage());
            json.setSuccess(false);
            json.setResponeCode(1000);
        }

        return json;
    }


}
