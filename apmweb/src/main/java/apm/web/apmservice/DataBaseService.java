package apm.web.apmservice;

import apm.web.beans.module.SpanEvent;
import apm.web.beans.vo.DataBaseSqlViewVO;
import apm.web.beans.vo.DatabaseResponseVO;
import apm.web.beans.vo.TopNSqlVO;

import java.util.List;

/**
 * @author ChengBing Han
 * @date 13:10  2018/1/30
 * @description
 */
public interface DataBaseService {

    /**
     * @description 数据库一览
     * @param appId 要查询的应用Id,
     * @return
     */
    List<DataBaseSqlViewVO> getDbOverView(String appId) throws Exception;

    /**
     * @param appId 应用Id
     * @description  topN 耗时Sql
     * @return
     */
    List<TopNSqlVO> getTopNExpensiveSql(String appId) throws Exception;

    /**
     * @description 慢sql 追踪列表， 在一个应用中的某个时间范围内的
     * @param appId 应用id
     * @param slowSqlThreshold 慢sql 时间阈值， 大于该时间限定的为慢sql。
     */
    List<TopNSqlVO> traceExpensiveSql(String slowSqlThreshold, String appId) throws Exception;

    /**
     * @description 根据sql id 查询某个应用(appId)的 调用某个sql 的情况， 对应sql 分析中的慢sql 追踪。
     * @param appId 应用id
     * @param sqlId 查询某条sql
     */
    TopNSqlVO getTheSqlInfo(String sqlId, String appId) throws Exception;

    /**
     * @description 根据sql id 查询某个应用的 调用某个sql 的情况， 对应sql 分析中的慢sql 追踪。
     * @param appId 应用id
     * @param  sqlId sqlID
     */
    List<SpanEvent> getEachDbOperationBySqlId(String appId, String sqlId, Integer count) throws Exception;

    /**
     * @param appId
     * @param sqlCount 一次查询多少条sql的情况
     * @description 查询某个application 的数据库操作时间变化，单位sql 看时间及耗时
     */
    List<DatabaseResponseVO> getDabaseResponse(String appId, String sqlCount);

    /**
     * @param appId applicationId
     * @param sqlId 某sqlId
     * @param sqlCount 一次查询多少条sql的情况
     * @description 查询某个application 的sqliD 是xx 的数据库操作时间变化，执行单位单位sql 看时间及耗时
     */
    List<DatabaseResponseVO> getDBSqlListBySqlId(String appId, String sqlId, String sqlCount);
}
