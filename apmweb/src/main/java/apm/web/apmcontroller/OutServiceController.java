package apm.web.apmcontroller;

import apm.web.apmservice.OutService;
import apm.web.beans.vo.ExpensiveOutserviceVO;
import apm.web.beans.vo.OutServiceByTimeVO;
import apm.web.beans.vo.OutServiceResponseVO;
import apm.web.beans.vo.OutServiceVO;
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
 * @date 10:10  2018/2/4
 * @description 外部服务相关类
 */

@Controller
@CrossOrigin
public class OutServiceController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Resource
    private OutService outService;

    /**
     * @param appId    应用名称
     * @param findType 应用名称
     * @return ApplicationTopology 对象
     * @description 用于查询拓扑图表格
     */
    @RequestMapping(value = "/outService", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<OutServiceVO> getOutServiceList(String appId, String findType) {

            JsonObject<OutServiceVO> json = null;

            try {
                List<OutServiceVO> outServiceVOList = outService.getOutServiceList(appId, findType);
                json = new JsonObject<>();
                json.setResponeCode(200);
                json.setSuccess(true);
                json.setObjectList(outServiceVOList);
            } catch (Exception e) {
                logger.error(e.getMessage());
                json = new JsonObject<>();
                json.setSuccess(false);
                json.setResponeCode(1000);
                json.setErrMsg(e.getMessage());
            }
            return json;
    }


    /**
     * @param appId       应用Id
     * @param serviceCode 服务code
     * @description 外部服务平均响应时间。
     */

    @RequestMapping(value = "/theOutServiceByTime", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<OutServiceResponseVO> getTheOutServiceByTime(String appId, String serviceCode) {
        JsonObject<OutServiceResponseVO> json = null;

        try {

            OutServiceResponseVO outServiceResponseVO = outService.getTheOutServiceByTime(appId, serviceCode);
            json = new JsonObject<>();
            json.setResponeCode(200);
            json.setObj(outServiceResponseVO);
            json.setSuccess(true);
        } catch (Exception e) {
            logger.error(e.getMessage());
            json = new JsonObject<>();
            json.setErrMsg(e.getMessage());
            json.setResponeCode(1000);
            json.setSuccess(false);
        }
        return json;
    }

    /**
     * @param appId 应用Id
     * @description topN耗时外部服务
     */

    @RequestMapping(value = "/topNExpensiveOutService", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<ExpensiveOutserviceVO> getTopNExpensiveOutService(String appId, Integer topN) {


        return outService.getTopNExpensiveOutService(appId, topN);

    }


}
