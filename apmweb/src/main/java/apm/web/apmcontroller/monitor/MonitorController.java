package apm.web.apmcontroller.monitor;

import apm.web.apmservice.monitor.MonitorProService;
import apm.web.apmservice.monitor.MonitorService;
import apm.web.beans.module.MonitorPro;
import apm.web.beans.module.MonitorStrategy;

import apm.web.core.monitor.MonitorVO;
import apm.web.util.apmutil.JsonObject;
import org.apache.hadoop.hbase.shaded.org.apache.avro.data.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ChengBing Han
 * @date 20:43  2018/2/12
 * @description
 */

@CrossOrigin
@Controller
@RequestMapping(value = "/monitor")
public class MonitorController {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    MonitorService monitorService;

    @Resource
    MonitorProService monitorProService;

    /**
     * @param monitorPro 监控项目类型
     * @param     monitorVOStrs 是  monitorType1,monitorRpm1,monitorTime1,monitorThrehold1_ monitorType2,monitorRpm2,monitorTime2,monitorThrehold2
     * @deprecated 查询某个应用的agent 信息
     */
    @RequestMapping(value = "/addMonitorStrategy", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<MonitorStrategy> addMonitorStrategy(MonitorStrategy monitorPro, String monitorVOStrs) {

        JsonObject<MonitorStrategy> json = null;
        List<MonitorVO> monitorVO = null;

        try {
            monitorProService.addMonitorStrategy(monitorPro, monitorVOStrs);

            List<MonitorStrategy> monitorStrategyList = monitorProService.findMonitorStrategy();

            json = new JsonObject<>();
            json.setResponeCode(200);
            json.setSuccess(true);
            json.setObjectList(monitorStrategyList);
        } catch (Exception e) {
            logger.error(e.getMessage());
            json = new JsonObject<>();
            json.setSuccess(false);
            json.setResponeCode(1002);
        }

        return json;

    }


    /**
     * 查询警报策略
     */
    @RequestMapping(value = "/findMonitorStrategy", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<MonitorStrategy> findMonitorStrategy() {

        JsonObject<MonitorStrategy> json = null;

        try {

            List<MonitorStrategy> monitorStrategyList = monitorProService.findMonitorStrategy();
            json = new JsonObject<>();
            json.setSuccess(true);
            json.setObjectList(monitorStrategyList);
            json.setResponeCode(200);

        } catch (Exception e) {

            logger.error(e.getMessage());
            json = new JsonObject<>();
            json.setResponeCode(1000);
            json.setSuccess(false);
            json.setErrMsg(e.getMessage());
        }


        return json;


    }


    /**
     * @description 添加监控项目
     */
    @RequestMapping(value = "/addMonitorPro", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<MonitorPro> addMonitorPro(MonitorPro monitorPro, String appId, String strategyId) {
        JsonObject<MonitorPro> json = null;

        try {

            monitorProService.addMonitorPro(monitorPro, appId, strategyId);
            List<MonitorPro> monitorProList = monitorProService.findMonitorProList();
            json = new JsonObject<>();
            json.setSuccess(true);
            json.setObjectList(monitorProList);

        } catch (Exception e) {
            json = new JsonObject<>();
            json.setResponeCode(1002);
            json.setSuccess(false);

            json.setErrMsg(e.getMessage());
            logger.error(e.getMessage());

        }

        return json;

    }


    @RequestMapping(value = "/monitorProList", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<MonitorPro> findMonitorProList() {
        JsonObject<MonitorPro> json = null;

        try {

            List<MonitorPro> monitorProList = monitorProService.findMonitorProList();

        } catch (Exception e) {
            json = new JsonObject<>();
            json.setSuccess(false);
            json.setResponeCode(1000);
            json.setErrMsg(e.getMessage());
            logger.error(e.getMessage());
        }


        return json;

    }


    @RequestMapping(value = "/executeMonitor", method = RequestMethod.GET)
    @ResponseBody
    public void executeMonitor() throws Exception {
         monitorService.initMonitorThreadPool();
    }
}
