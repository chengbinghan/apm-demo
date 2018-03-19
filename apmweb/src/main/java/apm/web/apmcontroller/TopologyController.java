package apm.web.apmcontroller;


import apm.web.beans.vo.CallerMapVO;
import apm.web.apmservice.ApplicationService;
import apm.web.apmservice.CallerMapService;
import apm.web.util.apmutil.JsonObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author ChengBing Han
 * @description 处理应用拓扑相关接口
 */
@CrossOrigin
@Controller
public class TopologyController {



    @Resource
    private CallerMapService callerMapService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * @param appId 应用Id
     * @param timeSection 查询某个时间段内的。
     * @return ApplicationTopology 对象
     * @description 用于查询拓扑图表格
     */
    @RequestMapping(value = "/topologyInfo", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<CallerMapVO> getTopologyInfo( String appId, Long timeSection) {


        JsonObject<CallerMapVO> json = null;
        try {
            List<CallerMapVO> callerMapVOList = callerMapService.getAppCallerTopologyInfo(appId,timeSection);
            json = new JsonObject<>();
            json.setObjectList(callerMapVOList);
            json.setSuccess(true);
            json.setResponeCode(200);
            return json;
        } catch (Exception e) {
            logger.error(e.getMessage());
            json = new JsonObject<>();
            json.setSuccess(false);
            json.setResponeCode(1000);
            json.setErrMsg("error:" + e.getMessage());
        }
        return json;

    }
}
