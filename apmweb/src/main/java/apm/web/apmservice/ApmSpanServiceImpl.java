package apm.web.apmservice;

import apm.web.beans.module.Span;
import apm.web.beans.module.SpanEvent;
import apm.web.beans.vo.*;
import apm.web.dao.apmmysql.ApmSpanDao;
import apm.web.util.apmconstant.APMConstant;
import apm.web.util.apmutil.ApmStringUtil;
import apm.web.util.apmutil.TimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author ChengBing Han
 * @date 14:48  2018/1/24
 * @description
 */
@Service
public class ApmSpanServiceImpl implements ApmSpanService {

    private static final Long START_TIME_CONDITION = 86400000222L; //24 * 60 *60*1000;

    public static final String TIME_ORDER_TYPE = "TIME_ORDER_TYPE";
    public static final String ELAPAED_ORDER_TYPE = "ELAPAED_ORDER_TYPE";


    @Resource
    ApmSpanDao apmSpanDao;

    //某个一级spanevent, 后期可以做缓存
    private List<SpanEvent> firstLevelSpanEventsList = new ArrayList<>();


    /**
     * @param spanId
     * @description 查询某次事务在某个application中的span的所有spanevenet=====>后期该部分可以做缓存放到redis.
     */
    @Override
    public Span getSpanBySpanId(Long spanId) throws Exception {

        if (spanId == null || spanId < 0) {
            throw new IllegalArgumentException("param is null or empty");
        }


        /*
        根据apm 思路，查询第一个spanevent
         */
        //1、查询某个application 的某个transaction 的第一个spanevent.
        SpanEvent firstSpanEvent = apmSpanDao.getTheFirstSpanEvent(spanId);

        //操作前清空集合,改步及其重要。
        firstLevelSpanEventsList.clear();
        //递归找出所有的一级spanevnet.放到成员遍历firstLevelSpanEventsList
        recursionSpanEvent(firstSpanEvent);

        ArrayList<SpanEvent> resultSpanEventList = new ArrayList<>();

        //找到所有一级spanevent的所有子spanevent.
        for (SpanEvent spanEvent : firstLevelSpanEventsList) {

            SpanEvent childSpanEventList = getChildSpanEventList(spanEvent);
            resultSpanEventList.add(childSpanEventList);
        }


        Span span = new Span();
        span.setSpanEventList(resultSpanEventList);

        return span;
    }


    /**
     * @param appId         applicationId
     * @param transactionId 事务Id
     * @return 参考如下注释
     */
    /*
     * @description List<SpanEvent></SpanEvent>  按照某次调用顺序，将spanevent 放到list
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
    @Override
    public Span getSpanInvokeTrace(Long spanId) throws Exception {


        //1、按照数据结构，拿到某个span的所有spanevent
        Span span = getSpanBySpanId(spanId);


        //2、查询时间占比

        // TODO: 2018/3/17 检查一个span的elapsed是否等于其所有spanevent的endElpased 和
        Integer totalTime = apmSpanDao.getElapsedByAppIdAndTransactionId(spanId);
        span.setElapsed(totalTime);

        return span;
    }


    /**
     * @param appId         applicationId
     * @param transactionId 事务Id
     * @description 按照时间轴顺序找到某个transaction在某个application中所有的spanEvent
     */
    @Override
    public List<SlowTransactionTraceVO> getSpanEventListByTime(Long spanId) throws Exception {


        if (spanId == null || spanId < 0) {
            throw new Exception("param is null or not correct");
        }

/*        List<SlowTransactionTraceVO> spanEventList = apmSpanDao.getSpanEventListByTime();*/
        List<SlowTransactionTraceVO> spanEventList = apmSpanDao.getSpanEventBySpanIdOrderByStartTime(spanId);

        return spanEventList;
    }


    /**
     * @param parentSpanEvent
     * @return parentSpanEvent
     * @description 查询某个节点的第一个字节点, 按照顺序，放到ChildSpanEvent 的list 中。
     */

    public SpanEvent getChildSpanEventList(SpanEvent parentSpanEvent) throws Exception {

        if (parentSpanEvent == null || parentSpanEvent.getSpanEventId() == null) {
            throw new Exception("spanevent is null or it's id is null");
        }

        //查询出来的是要有顺序的,按照执行的时间顺序
        List<SpanEvent> spanEventList = apmSpanDao.getChildSpanEventList(parentSpanEvent.getSpanEventId());
        parentSpanEvent.setChildSpanEventList(spanEventList);


        if (spanEventList != null && spanEventList.size() >= 1) {
            for (SpanEvent spanEvent : spanEventList) {
                getChildSpanEventList(spanEvent);
            }
        }

        return parentSpanEvent;
    }


    /**
     * @param SpanEvnet parentSpanEvent 查询本节点的所有兄弟节点，返回为一个list.
     * @description 递归调用，查找某个节点的所有子节点。
     */

    public void recursionSpanEvent(SpanEvent spanEvent) throws Exception {

        firstLevelSpanEventsList.add(spanEvent);

        SpanEvent afterSpanEvent = spanEvent.getAfterSpanEvent();

        if (afterSpanEvent != null && afterSpanEvent.getSpanEventId() != null && !"".equals(afterSpanEvent.getSpanEventId())) {
            SpanEvent brotherSpanEvent = apmSpanDao.getNextSpaneventById(afterSpanEvent.getSpanEventId());
            recursionSpanEvent(brotherSpanEvent);
        }


    }


    /**
     * @param spanId spanId
     * @description 某个事务中的sql.
     */
    @Override
    public List<SqlVO> getSqlDetailBySpanId(Long spanId) throws Exception {


        if (spanId == null || spanId < 0) {
            throw new Exception("spanId is empty or null");
        }


        List<SqlVO> sqlList = apmSpanDao.getSqlDetailBySpanId(spanId);

        return sqlList;
    }

    /**
     * @param apiId         application id
     * @param transactionId transaction
     * @return 最慢组件VO 实体类
     */
    @Override
    public List<SlowestComponentVO> getSlowestComponentList(Long spanId) throws Exception {


        if (spanId == null || spanId < 0) {
            throw new Exception("param is null or param is empty");
        }

        List<SlowestComponentVO> slowestComponentVOList = apmSpanDao.getSlowestComponentList(spanId);


        return slowestComponentVOList;
    }


    /**
     * @param rpc
     * @return 查询某一类事务的各个span时间耗时
     */
    @Override
    public List<SimpleSpanVO> getEachSpanInTransaction(String rpc, String appId, Long timeSection, String orderType) throws Exception {


        if (ApmStringUtil.isEmpty(rpc) || ApmStringUtil.isEmpty(appId)) {
            throw new Exception("param is empty or param is null!!!!");
        }

        //是否使用默认时间区间
        if (timeSection == null || timeSection < 0) {
            timeSection = APMConstant.DEFAULT_TRANSACTION_TIMESECTION;
        }


        HashMap<String, String> hashMap = new HashMap<>();

        hashMap.put(APMConstant.CURRENT_TIME_MILLISECOND, TimeUtil.getCurrentMillisecond().toString());
        hashMap.put(APMConstant.TIME_SECTION, timeSection.toString());
        hashMap.put(APMConstant.APP_ID, appId);
        hashMap.put(APMConstant.RPC, rpc);

        if (ApmStringUtil.isEmpty(orderType)) {
            orderType = ELAPAED_ORDER_TYPE;
        }
        List<SimpleSpanVO> theSpanVOList = null;

        if (ELAPAED_ORDER_TYPE.equals(orderType)) {

            theSpanVOList = apmSpanDao.getEachSpanByElapsed(hashMap);

        } else if (TIME_ORDER_TYPE.equals(orderType)) {

            theSpanVOList = apmSpanDao.getEachSpanByTime(hashMap);

        }


        return theSpanVOList;
    }

    /**
     * @param appId 某个应用的Id
     * @return 慢事务
     */
    @Override
    public List<SlowTransactionVO> getSlowTransaction(String appId, Long timeSection) throws Exception {

        if (ApmStringUtil.isEmpty(appId)) {
            throw new Exception("param is empty or param is null");
        }

        //使用默认
        if (timeSection == null || timeSection < 0) {
            timeSection = APMConstant.DEFAULT_TRANSACTION_TIMESECTION;

        }

        Map<String, String> hashMap = new HashMap<>();
        hashMap.put(APMConstant.APP_ID, appId);
        hashMap.put(APMConstant.CURRENT_TIME_MILLISECOND, TimeUtil.getCurrentMillisecond().toString());
        hashMap.put(APMConstant.TIME_SECTION, timeSection.toString());
        List<SlowTransactionVO> slowTransactionVOList = apmSpanDao.getSlowTransaction(hashMap);

        return slowTransactionVOList;
    }

    /**
     * @param rpc 根据rpc 确定某类事务
     */
    @Override
    public List<TheTransactionanalysisVO> getTheTransactionanalysis(String rpc, Integer appId, Long timeSection) throws Exception {


        if (ApmStringUtil.isEmpty(rpc)) {
            throw new Exception("param is null or is empty !!!!!");
        }

        if (appId == null) {

            throw new Exception("param is null or empty!!!");
        }


        Map<String, String> hashMap = new HashMap<>();
        hashMap.put(APMConstant.CURRENT_TIME_MILLISECOND, TimeUtil.getCurrentMillisecond().toString());
        hashMap.put(APMConstant.RPC, rpc);
        hashMap.put(APMConstant.TIME_SECTION, timeSection.toString());
        hashMap.put(APMConstant.APP_ID, appId.toString());

        List<TheTransactionanalysisVO> theTransactionanalysisVOList = apmSpanDao.getTheTransactionAnlysisVOByRpc(hashMap);


        return theTransactionanalysisVOList;
    }

    @Override
    public List<SpanEvent> getEachDbOperationBySqlId(Map<String, String> hashMap) {
        // List<SpanEvent> spanEventList =  apmSpanDao.getEachDbOperationBySqlId(hashMap);
        return null;
    }

    /**
     * 事务追踪，一般信息查询
     *
     * @param spanId
     * @return
     */
    @Override
    public TraceTitleInfoVO getGeneralTransactionTraceInfo(Long spanId) {

        TraceTitleInfoVO traceTitleInfoVO = apmSpanDao.getGeneralTransactionTraceInfo(spanId);
        String currentTime = TimeUtil.getCurrentTime();
        traceTitleInfoVO.setTraceTime(currentTime);


        return traceTitleInfoVO;
    }


}
