package apm.web.apmservice;

import apm.web.beans.module.Span;
import apm.web.beans.module.SpanEvent;
import apm.web.beans.vo.*;

import java.util.List;
import java.util.Map;

/**
 * @author ChengBing Han
 * @date 14:45  2018/1/24
 * @description
 */
public interface ApmSpanService {

    /**
     * @param spanId
     * @description 查询某次事务在某个application中的span
     */
    Span getSpanBySpanId(Long spanId) throws Exception;

    /**
     * @param appId         applicationId
     * @param transactionId 事务Id
     * @description List<SpanEvent></SpanEvent>  按照某次调用顺序，将spanevent 放到list
     */
    /*
     *                                    eg： span{
     *                                            f1{
     *
     *                                            }
     *                                            f2{
     *                                                f3{
     *                                                    f4{}
     *                                                    f5{]}
     *                                                }
     *
     *                                                f6{}
     *                                                f7{}
     *                                            }
     *                                            f8{}
     *                                            f9{}
     *
     *                                    }
     *
     *                                    上述的调用在list中的顺序是 方法的执行顺序，即f1,f2,f3,f4,f5...........
     */
    public Span getSpanInvokeTrace(Long spanId) throws Exception;


    /**
     * @param appId         applicationId
     * @param transactionId 事务Id
     * @description 按照时间轴顺序找到某个transaction在某个application中所有的spanEvent
     */
    List<SlowTransactionTraceVO> getSpanEventListByTime(Long spanId) throws Exception;


    /**
     * @param appId         applicationId
     * @param transactionId 事务Id
     * @description 某个事务中的sql.
     */
    List<SqlVO> getSqlDetailBySpanId(Long spanId) throws Exception;

    /**
     * @param apiId         application id
     * @param transactionId transaction
     * @return 最慢组件VO 实体类
     */
    List<SlowestComponentVO> getSlowestComponentList(Long spanId) throws Exception;

    /**
     * @param rpc
     * @return 查询某一类事务的各个span时间耗时
     */
    List<SimpleSpanVO> getEachSpanInTransaction( String rpc,String appId, Long timeSection,String orderType) throws Exception;

    /**
     * @param appId       某个应用的Id
     * @param timeSection 查询的时间区间
     * @return 慢事务
     */
    List<SlowTransactionVO> getSlowTransaction(String appId, Long timeSection) throws Exception;

    /**
     * @param rpc 根据rpc 确定某类事务
     */
    List<TheTransactionanalysisVO> getTheTransactionanalysis(String rpc,Integer appId,Long timeSection) throws Exception;


    List<SpanEvent> getEachDbOperationBySqlId(Map<String, String> hashMap);

    /**
     * 事务追踪，一般信息查询
     *
     * @param spanId
     * @return
     */
    TraceTitleInfoVO getGeneralTransactionTraceInfo(Long spanId);
}
