package apm.web.apmcontroller.ppcontroller;

import apm.web.apmservice.informationservice.ApmAgentInfoService;
import com.navercorp.pinpoint.web.vo.ApplicationAgentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author ChengBing Han
 * @date 10:40  2018/2/11
 * @description 应用环境信息
 */
@Controller
@CrossOrigin
public class ApmAgentInfoController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Resource
    ApmAgentInfoService apmAgentInfoService;

    /**
     * @param
     * eg:
     *   http://localhost:8082/apm/getAgentList.pinpoint?application=MyTestPP&from=1518316035000&to=1518316335000
     * @description 查询某个应用的agent 信息
     */

    @RequestMapping(value = "/getAgentInfoList", method = RequestMethod.GET, params = {"application", "from", "to"})
    @ResponseBody
    public ApplicationAgentList getAgentList(
            @RequestParam("application") String applicationName,
            @RequestParam("from") long from,
            @RequestParam("to") long to) {
        return this.apmAgentInfoService.getApplicationAgentList(ApplicationAgentList.Key.HOST_NAME, applicationName, to);
    }



}
