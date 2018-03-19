package apm.web.apmservice;

import apm.web.beans.module.SpanEvent;
import apm.web.beans.vo.DataBaseSqlViewVO;
import apm.web.beans.vo.DatabaseResponseVO;
import apm.web.beans.vo.TopNSqlVO;
import apm.web.dao.apmmysql.DataBaseDao;
import apm.web.util.apmutil.ApmStringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ChengBing Han
 * @date 13:11  2018/1/30
 * @description 数据库相关业务实现
 */
@Service
public class DataBaseServiceImpl implements DataBaseService {


    @Resource
    private DataBaseDao dataBaseDao;

    @Resource
    private ApmSpanService apmSpanService;

    /**
     * @param appId 要查询的应用Id,
     * @return
     * @description 数据库一览
     */
    @Override
    public List<DataBaseSqlViewVO> getDbOverView(String appId) throws Exception {

        // TODO: 2018/1/30 数据写死=================================》
        // TODO: 2018/1/30 数据写死=================================》
        // TODO: 2018/1/30 数据写死=================================》
        appId = "11";
        if (ApmStringUtil.isEmpty(appId)) {
            throw new Exception("para is empty or is null!");
        }
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("appId", appId);
        hashMap.put("beginTime", ConditionServiceImpl.getTimeLimit().toString());
        List<DataBaseSqlViewVO> dataBaseSqlViewVOList = dataBaseDao.getDbOverView(hashMap);


        return dataBaseSqlViewVOList;
    }

    /**
     * @param appId 应用Id
     * @description topN 耗时Sql
     */
    @Override
    public List<TopNSqlVO> getTopNExpensiveSql(String appId) throws Exception {
        // TODO: 2018/1/30  数据写死==================================》
        // TODO: 2018/1/30  数据写死==================================》
        // TODO: 2018/1/30  数据写死==================================》
        // TODO: 2018/1/30  数据写死==================================》
        appId = "11";
        if (ApmStringUtil.isEmpty(appId)) {

            throw new Exception("param is empty or is null");
        }


        // TODO: 2018/2/28 topNsql 参数写死4 
        List<TopNSqlVO> topNSqlVOList = dataBaseDao.getTopNExpensiveSql(appId, ConditionServiceImpl.getTimeLimit(), 5);

        return topNSqlVOList;
    }

    /**
     * @param slowSqlThreshold 慢sql 时间阈值， 大于该时间限定的为慢sql。
     * @param appId            应用id
     * @description 慢sql 追踪列表， 在一个应用中的某个时间范围内的
     */
    @Override
    public List<TopNSqlVO> traceExpensiveSql(String slowSqlThreshold, String appId) throws Exception {

        // TODO: 2018/1/30  参数写死============================》
        // TODO: 2018/1/30  参数写死============================》
        // TODO: 2018/1/30  参数写死============================》
        // TODO: 2018/1/30  参数写死============================》
        appId = "11";
        if (ApmStringUtil.isEmpty(appId)) {
            throw new Exception("param is empty or null !!!");
        }

        //阈值参数为空则传默认值
        if (ApmStringUtil.isEmpty(slowSqlThreshold)) {
            // TODO: 2018/1/30 测试时为了有数据，设置时间阈值为0；============》
            // TODO: 2018/1/30 测试时为了有数据，设置时间阈值为0；============》
            // TODO: 2018/1/30 测试时为了有数据，设置时间阈值为0；============》
            // TODO: 2018/1/30 测试时为了有数据，设置时间阈值为0；============》
            slowSqlThreshold = "1000";
        }

        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("appId", appId);
        hashMap.put("slowSqlThreshold", slowSqlThreshold);
        hashMap.put("beginTime", ConditionServiceImpl.getTimeLimit().toString());

        List<TopNSqlVO> topNSqlVOList = dataBaseDao.getExpensiveSqlByThreshold(hashMap);
        return topNSqlVOList;
    }

    /**
     * @param sqlId 查询某条sql
     * @param appId 应用id
     * @description 根据sql id 查询某个应用(appId)的 调用某个sql 的情况， 对应sql 分析中的慢sql 追踪。
     */
    @Override
    public TopNSqlVO getTheSqlInfo(String sqlId, String appId) throws Exception {
        // TODO: 2018/1/31 假数据==========================》
        // TODO: 2018/1/31 假数据==========================》
        // TODO: 2018/1/31 假数据==========================》

        sqlId = "1";
        appId = "11";
        if (ApmStringUtil.isEmpty(sqlId) || ApmStringUtil.isEmpty(appId)) {
            throw new Exception("param is null or is empty!!!");
        }


        Map<String, String> hashMap = new HashMap<>();

        hashMap.put("appId", appId);
        hashMap.put("sqlId", sqlId);
        hashMap.put("startTime", ConditionServiceImpl.getTimeLimit().toString());

        TopNSqlVO topNsqlVO = dataBaseDao.getTheSqlInfo(hashMap);

        return topNsqlVO;
    }

    /**
     * @param appId 应用id
     * @param sqlId sqlID
     * @description 根据sql id 查询某个应用的 调用某个sql 的情况， 对应sql 分析中的慢sql 追踪。
     */
    @Override
    public List<SpanEvent> getEachDbOperationBySqlId(String appId, String sqlId, Integer count) throws Exception {

        //默认100条
        if (count == null) {
            count = 100;
        }

        // TODO: 2018/1/31 假数据===========================================》
        // TODO: 2018/1/31 假数据===========================================》
        // TODO: 2018/1/31 假数据===========================================》
        // TODO: 2018/1/31 假数据===========================================》
        appId = "11";
        sqlId = "1";

        if (ApmStringUtil.isEmpty(appId) || ApmStringUtil.isEmpty(sqlId)) {
            throw  new Exception("param is empty or param is null !!!");
        }

        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("appId", appId);
        hashMap.put("sqlId", sqlId);
        hashMap.put("beginTime", ConditionServiceImpl.getTimeLimit().toString());
        hashMap.put("count", count.toString());

        List<SpanEvent> spanEventList = apmSpanService.getEachDbOperationBySqlId(hashMap);

        return null;
    }

    /**
     * @param appId
     * @param sqlCount 一次查询多少条sql的情况
     * @description 查询某个application 的数据库操作时间变化，单位sql 看时间及耗时
     */
    @Override
    public List<DatabaseResponseVO> getDabaseResponse(String appId, String sqlCount) {

        if(ApmStringUtil.isEmpty(sqlCount)){
            sqlCount = "30";
        }
        // TODO: 2018/1/31  暂时参数================================》
        // TODO: 2018/1/31  暂时参数================================》
        // TODO: 2018/1/31  暂时参数================================》
        // TODO: 2018/1/31  暂时参数================================》
        appId = "11";

        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("sqlCount", sqlCount);
        hashMap.put("appId", appId);
        hashMap.put("startTime", ConditionServiceImpl.getTimeLimit().toString());
        List<DatabaseResponseVO> databaseResponseVOList = dataBaseDao.getDabaseResponse(hashMap);


        return databaseResponseVOList;
    }

    /**
     * @param appId    applicationId
     * @param sqlId    某sqlId
     * @param sqlCount 一次查询多少条sql的情况
     * @description 查询某个application 的sqliD 是xx 的数据库操作时间变化，执行单位单位sql 看时间及耗时
     */
    @Override
    public List<DatabaseResponseVO> getDBSqlListBySqlId(String appId, String sqlId, String sqlCount) {

        //默认查询sqlCount 是30条。
        if(ApmStringUtil.isEmpty(sqlCount)){
            sqlCount = "30";
        }

        // TODO: 2018/2/1  假数据 ==================================================================》
        // TODO: 2018/2/1  假数据 ==================================================================》
        // TODO: 2018/2/1  假数据 ==================================================================》
        appId = "11";
        sqlId = "1";
        Map<String,String> hashMap = new HashMap<String,String>();
        hashMap.put("appId", appId);
        hashMap.put("sqlId", sqlId);
        hashMap.put("sqlCount",sqlCount);
        hashMap.put("startTime", ConditionServiceImpl.getTimeLimit().toString());
        List<DatabaseResponseVO> databaseResponseVOList = dataBaseDao.getDBSqlListBySqlId(hashMap);




        return databaseResponseVOList;
    }


}
