package apm.web.apmcontroller;


import apm.web.beans.module.ApmApplication;
import apm.web.beans.vo.ApdexVO;
import apm.web.beans.vo.ApplicationVO;
import apm.web.apmservice.ApplicationService;
import apm.web.util.apmutil.JsonObject;
import org.apache.hadoop.hbase.shaded.org.apache.avro.data.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ChengBing Han
 */
@CrossOrigin
@Controller
public class ApplicationsController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    ApplicationService applicationService;


    /**
     * @param null
     * @return List<ApmApplication></ApmApplication>
     * @description ==>查询所有的应用，应用名，id 等
     */
    @RequestMapping(value = "/apmApplications", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<ApmApplication> getApplicationGroup() {
        JsonObject<ApmApplication> json = new JsonObject<>();

        try {
            List<ApmApplication> applicationList = applicationService.selectAllApplicationNames();
            json.setSuccess(true);
            json.setResponeCode(200);
            json.setObjectList(applicationList);
        } catch (Exception e) {
            logger.error(e.getMessage());
            json.setSuccess(false);
            json.setResponeCode(1000);
            json.setErrMsg(e.getMessage());
        }

        return json;

    }

    /**
     * @param null
     * @return List<ApmApplication></ApmApplication>
     * @description ==>查询所有的应用的 平均响应时间，apdex,错误率等
     */
    @RequestMapping(value = "/apmApplicationInfo")
    @ResponseBody
    public JsonObject<ApplicationVO> getApplicationInfo(Long timeSection) {


        JsonObject<ApplicationVO> json = new JsonObject<>();
        try {

            List<ApplicationVO> applicationVOList = applicationService.getApmApplicationInfo(timeSection);

            json.setObjectList(applicationVOList);
            json.setSuccess(true);
            json.setResponeCode(200);
        } catch (Exception e) {
            logger.error(e.getMessage());
            json.setResponeCode(1000);
            json.setErrMsg(e.getMessage());
            json.setSuccess(false);
        }

        return json;
    }

    /**
     * @param appId 某个应用Id
     * @description 获取应用apdex 值
     */

    @RequestMapping(value = "/getApdexVOByTimeAndAppId", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<ApdexVO> getApdexVOByTimeAndAppId(String appId) {

        JsonObject<ApdexVO> json = null;

        try {
            ApdexVO apdexVO = applicationService.getApdexVOByTimeAndAppId(appId);
            json = new JsonObject<>();
            json.setSuccess(true);
            json.setObj(apdexVO);
            json.setResponeCode(200);
        } catch (Exception e) {
            logger.error(e.getMessage());
            json = new JsonObject<>();
            json.setSuccess(false);
            json.setResponeCode(1000);
            json.setErrMsg(e.getMessage());


        }

        return json;
    }


}
