package apm.web.apmcontroller;

import apm.web.beans.module.SpanEvent;
import apm.web.beans.vo.DataBaseSqlViewVO;
import apm.web.beans.vo.DatabaseResponseVO;
import apm.web.beans.vo.TopNSqlVO;
import apm.web.apmservice.DataBaseService;
import apm.web.util.apmconstant.APMConstant;
import apm.web.util.apmutil.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author ChengBing Han
 * @date 11:55  2018/1/30
 * @description 数据库监控相关controller
 */
@CrossOrigin
@Controller
public class DatabaseController {

    @Resource
    private DataBaseService dataBaseService;


    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * @param appId 应用Id
     * @return
     * @description 数据库一览
     */
    @RequestMapping(value = "/dbOverview", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<DataBaseSqlViewVO> getDbOverView(String appId) {


        JsonObject<DataBaseSqlViewVO> json = null;

        try {
            List<DataBaseSqlViewVO> dataBaseSqlViewVOList = dataBaseService.getDbOverView(appId);
            json = new JsonObject<>();
            json.setResponeCode(200);
            json.setSuccess(true);
            json.setObjectList(dataBaseSqlViewVOList);


        } catch (Exception e) {
            logger.error(e.getMessage());
            json = new JsonObject<>();
            json.setErrMsg(e.getMessage());
            json.setSuccess(false);
            json.setResponeCode(1000);


        }

        return json;
    }


    /**
     * @param appId 应用Id
     * @return
     * @description topN 耗时sql
     */
    @RequestMapping(value = "/topNExpensiveSql", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<TopNSqlVO> getTopNExpensiveSql(String appId) {

        JsonObject<TopNSqlVO> json = null;


        try {

            List<TopNSqlVO> topNSqlVOList = dataBaseService.getTopNExpensiveSql(appId);
            json = new JsonObject<>();
            json.setSuccess(true);
            json.setObjectList(topNSqlVOList);
            json.setResponeCode(200);
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
     * @param appId            应用id
     * @param slowSqlThreshold 慢sql 时间阈值， 大于该时间限定的为慢sql。
     * @description 慢sql 追踪列表， 在一个应用中的sql时间在某个时间范围内的
     */
    @RequestMapping(value = "/traceExpensiveSql", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<TopNSqlVO> traceExpensiveSql(String slowSqlThreshold, String appId) {
        JsonObject<TopNSqlVO> json;
        try {
            List<TopNSqlVO> topNSqlVoList = dataBaseService.traceExpensiveSql(slowSqlThreshold, appId);

            json = new JsonObject<>();
            json.setResponeCode(200);
            json.setObjectList(topNSqlVoList);
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


    /**
     * @param appId 应用id
     * @param sqlId 查询某条sql
     * @description 根据sql id 查询某个应用(appId)的 调用某个sql 的情况， 对应sql 分析中的慢sql 追踪。
     */
    @RequestMapping(value = "/theSqlInfo", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<TopNSqlVO> getTheSqlInfo(String sqlId, String appId) {
        JsonObject<TopNSqlVO> json = null;
        try {

            TopNSqlVO topNSqlVO = dataBaseService.getTheSqlInfo(sqlId, appId);
            json = new JsonObject<>();
            json.setResponeCode(200);
            json.setSuccess(true);
            json.setObj(topNSqlVO);

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
     * @param appId 应用id
     * @param sqlId sqlID
     * @param count 查询多少条，后期分页
     * @description 根据sql id 查询某个应用的 调用某个sql 的情况， 对应sql 分析中的慢sql 追踪。
     */
    @RequestMapping(value = "/eachDbOperation", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<SpanEvent> getEachDbOperationBySqlId(String appId, String sqlId, Integer count) {

        JsonObject<SpanEvent> json = null;


        try {

            List<SpanEvent> spanEventList = dataBaseService.getEachDbOperationBySqlId(appId, sqlId, count);

            json = new JsonObject<>();
            json.setResponeCode(200);
            json.setSuccess(true);
            json.setObjectList(spanEventList);
        } catch (Exception e) {
            logger.error(e.getMessage());
            json = new JsonObject<>();
            json.setErrMsg(e.getMessage());
            json.setSuccess(false);
            json.setResponeCode(1000);
        }

        return json;


    }


    /**
     * @param appId
     * @param sqlCount 一次查询多少条sql的情况
     * @description 查询某个application 的数据库操作时间变化，执行单位单位sql 看时间及耗时
     */
    @RequestMapping(value = "/databaseResponse", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<DatabaseResponseVO> dabaseResponse(String appId, String sqlCount) {

        JsonObject<DatabaseResponseVO> json;

        try {

            List<DatabaseResponseVO> dataBaseSqlViewVOList = dataBaseService.getDabaseResponse(appId, sqlCount);
            json = new JsonObject<>();
            json.setResponeCode(200);
            json.setObjectList(dataBaseSqlViewVOList);
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


    /**
     * @param appId    applicationId
     * @param sqlId    某sqlId
     * @param sqlCount 一次查询多少条sql的情况
     * @description 查询某个application 的sqliD 是xx 的数据库操作时间变化，执行单位单位sql 看时间及耗时
     */
    @RequestMapping(value = "/traceSqlBySqlId", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<DatabaseResponseVO> getDBSqlListBySqlId(String appId, String sqlId, String sqlCount) {

        JsonObject<DatabaseResponseVO> json;

        try {
            List<DatabaseResponseVO> databaseResponseVOList = dataBaseService.getDBSqlListBySqlId(appId, sqlId, sqlCount);

            /*
                为前台提供一个时间区间，用于画图
                查询执行单位条数的sql其时间不同，这样画图的时间轴单位长度不好确定。在此提供
             */

            json = new JsonObject<>();
            //list 中元素是按照每次执行sql时间增序的
            if (databaseResponseVOList != null && databaseResponseVOList.size() >= 2) {
                Long startTime = databaseResponseVOList.get(0).getExecuteTime();
                Long endTime = databaseResponseVOList.get(databaseResponseVOList.size() - 1).getExecuteTime();

                //时间跨度
                Long timeSpan = (endTime - startTime) / APMConstant.TIME_FENCE_COUNT;
                Long[] timeArr = new Long[APMConstant.TIME_FENCE_COUNT];
                for (int i = 0; i < APMConstant.TIME_FENCE_COUNT; i++) {
                    timeArr[i] = startTime + timeSpan;
                }

                HashMap<Object, Object> timeMap = new HashMap<>();

                json.setObjectMaps(timeMap);

            }



            json.setSuccess(true);
            json.setResponeCode(200);
            json.setObjectList(databaseResponseVOList);
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
