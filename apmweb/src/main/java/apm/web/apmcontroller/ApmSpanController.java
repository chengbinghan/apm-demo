package apm.web.apmcontroller;

import apm.web.beans.module.Span;
import apm.web.beans.vo.*;
import apm.web.apmservice.ApmSpanService;
import apm.web.util.apmutil.JsonObject;
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
 * @date 14:23  2018/1/24
 * @description 将span 表中的数据展示到页面，
 */
@Controller
@CrossOrigin
public class ApmSpanController {


    @Resource
    ApmSpanService apmSpanService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @RequestMapping(value = "/traceTitleInfo", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<TraceTitleInfoVO> getTraceTitleInfo(Long spanId) {

        JsonObject<TraceTitleInfoVO> json = null;


        try {
            TraceTitleInfoVO traceTitleInfoVO = apmSpanService.getGeneralTransactionTraceInfo(spanId);

            json = new JsonObject<>();
            json.setResponeCode(200);
            json.setSuccess(true);
            json.setObj(traceTitleInfoVO);

        } catch (Exception e) {
            json = new JsonObject<>();
            json.setErrMsg(e.getMessage());
            json.setResponeCode(1000);
            json.setSuccess(false);
            logger.error(e.getMessage());


        }

        return json;

    }


    /**
     * @param spanId span的id
     * @return Span
     * @description 按照时间轴顺序列出一个span的spanevent.
     */
    @RequestMapping(value = "/tranInvokeTrace", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<SlowTransactionTraceVO> tranInvokeTrace(Long spanId) {


        JsonObject<SlowTransactionTraceVO> json = null;

        try {
            List<SlowTransactionTraceVO> spanEventList = apmSpanService.getSpanEventListByTime(spanId);

            json = new JsonObject<>();
            json.setObjectList(spanEventList);
            json.setSuccess(true);
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


/*   *//* *//**//**
     * @param appId         应用applicationId
     * @param transactionId 事务Id
     * @return Span
     * @description 用于查询某个事务transaction中耗时的spanevent
     *//*

    @Deprecated
    @RequestMapping(value = "/expensiveSpanevent", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<SpanEvent> expensiveSpanEvent(String appId, String transactionId) {

        JsonObject<SpanEvent> json = null;

        try {

            List<SpanEvent> spanList = apmSpanService.expensiveSpanEvent(appId, transactionId);

            json = new JsonObject<>();
            json.setObjectList(spanList);
            json.setSuccess(true);
            json.setResponeCode(200);

        } catch (Exception e) {
            json = new JsonObject<>();
            json.setSuccess(false);
            json.setResponeCode(1000);
            json.setErrMsg(e.getMessage());
            logger.error(e.getMessage());
        }

        return json;
    }*/


    /**
     * @param spanId spanId
     * @return Span
     * @description 用于查询某个事务的spanevent中有哪些sql
     */
    @RequestMapping(value = "/sqlDetailByTranIdAndAppId", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<SqlVO> getSqlDetailByTranIdAndAppId(Long spanId) {
        JsonObject<SqlVO> json = null;


        try {

            List<SqlVO> sqlList = apmSpanService.getSqlDetailBySpanId(spanId);
            json = new JsonObject<>();
            json.setResponeCode(200);
            json.setSuccess(true);
            json.setObjectList(sqlList);
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
     * 慢组件列表，按照有个app 中一个span 的所有spanevent 按耗时时间排序
     *
     * @param spanId
     * @return SlowestComponentVO
     * @description
     */
    @RequestMapping(value = "/slowestComponentList", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<SlowestComponentVO> getSlowestComponentList(Long spanId) {


        JsonObject<SlowestComponentVO> json = null;

        try {
            List<SlowestComponentVO> slowestComponentVOs = apmSpanService.getSlowestComponentList(spanId);
            json = new JsonObject<>();
            json.setObjectList(slowestComponentVOs);
            json.setResponeCode(200);
            json.setSuccess(true);
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
     *
     * @return Span
     * @description 用于查询某类事务的 每个span 情况
     */
    @RequestMapping(value = "/eachSpanInTransaction", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<SimpleSpanVO> getEachSpanInTransaction( String rpc,String appId, Long timeSection,String orderType) {

        JsonObject<SimpleSpanVO> json = null;


        try {
            List<SimpleSpanVO> spanVOList = apmSpanService.getEachSpanInTransaction(rpc, appId, timeSection, orderType);
            json = new JsonObject<>();
            json.setSuccess(true);
            json.setObjectList(spanVOList);
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

    /**
     * @param appId 应用applicationId
     * @return
     * @description
     */
    @RequestMapping(value = "/slowTransaction", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<SlowTransactionVO> getSlowTranSaction(String appId, Long timeSection) {

        JsonObject<SlowTransactionVO> json = null;

        try {
            List<SlowTransactionVO> slowTransactionVOList = apmSpanService.getSlowTransaction(appId, timeSection);
            json = new JsonObject<>();
            json.setResponeCode(200);
            json.setSuccess(true);
            json.setObjectList(slowTransactionVOList);
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
     * @param rpc 事务的url
     * @param appId 应用Id
     * @description 根据url 确定某类事务，得出事务的分解表格
     */
    @RequestMapping(value = "/theTransactionanalysis", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<TheTransactionanalysisVO> getTheTransactionanalysis(String rpc, Integer appId,Long timeSection) {

        JsonObject<TheTransactionanalysisVO> json = null;


        try {

            List<TheTransactionanalysisVO> transactionanalysisVOList = apmSpanService.getTheTransactionanalysis(rpc,appId,timeSection);
            json = new JsonObject<>();
            json.setSuccess(true);
            json.setResponeCode(200);
            json.setObjectList(transactionanalysisVOList);

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
     * @param spanId spanId
     * @description
     */
    @RequestMapping(value = "/tracedetail", method = RequestMethod.GET)

    @ResponseBody
    public JsonObject<Span> getTracedetail(Long spanId) {
        JsonObject<Span> json = null;

        try {

            Span span = apmSpanService.getSpanInvokeTrace(spanId);
            json = new JsonObject<>();
            json.setObj(span);
            json.setSuccess(true);
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


}
