package apm.web.dao.apmmysql;


import apm.web.beans.module.SpanEvent;
import apm.web.beans.vo.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ChengBing Han
 * @date 16:36  2018/1/26
 * @description span 实体 类
 */
public interface ApmSpanDao {

    /**
     * @description 统计某个application 中的错误率。查询span 表
     * @return
     */
    Double getAppsErrPer(Map<String,String> hashMap);

    SpanEvent getTheFirstSpanEvent(Long spanId);

    List<SpanEvent> getChildSpanEventList(Long spanEventId);

    SpanEvent getNextSpaneventById(Long spanEventId);

    List<SlowTransactionTraceVO> getSpanEventListByTime(Map<String, String> hashMap);

    List<SlowestComponentVO> getSlowestComponentList(Long spanId);

    List<SqlVO> getSqlDetailBySpanId(Long spanId);

    List<SimpleSpanVO> getEachSpanByTime(HashMap<String, String> hashMap);

    List<SlowTransactionVO> getSlowTransaction(Map<String,String> hashMap);

    List<TheTransactionanalysisVO> getTheTransactionAnlysisVOByRpc(Map<String, String> hashMap);

     Integer getElapsedByAppIdAndTransactionId(Long spanId);

    List<SlowTransactionTraceVO> getSpanEventBySpanIdOrderByStartTime(Long spanId);

    TraceTitleInfoVO getGeneralTransactionTraceInfo(Long spanId);

    List<SimpleSpanVO> getEachSpanByElapsed(HashMap<String, String> hashMap);
}
