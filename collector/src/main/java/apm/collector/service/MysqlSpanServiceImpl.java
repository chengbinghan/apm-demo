package apm.collector.service;

import apm.collector.dao.MysqlSpan;
import apm.collector.dao.MysqlSpanDao;
import apm.collector.dao.MysqlSpanEvent;
import apm.collector.dao.MysqlTransaction;
import apm.collector.util.ApmStringUtil;
import com.navercorp.pinpoint.common.server.bo.AnnotationBo;
import com.navercorp.pinpoint.common.server.bo.SpanBo;
import com.navercorp.pinpoint.common.server.bo.SpanEventBo;
import com.navercorp.pinpoint.common.server.bo.serializer.trace.v2.SpanEncoder;
import com.navercorp.pinpoint.common.trace.AnnotationKey;
import com.navercorp.pinpoint.common.util.TransactionId;


import com.navercorp.pinpoint.thrift.dto.TIntStringStringValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ChengBing Han
 * @date 13:57  2018/3/6
 * @description
 */
@Service
@Transactional //span,spanenvent 插入属于一个事务
public class MysqlSpanServiceImpl implements MysqlSpanService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private MysqlSpanDao mysqlSpanDao;


    @Override
    public void addSpanAndSpanEventAndTransaction(SpanBo spanBo) {

        try {

            if (spanBo == null) {
                throw new Exception("param spanBo is null");
            }
            MysqlSpan mysqlSpan = new MysqlSpan();

            //封装数据
            TransactionId transaction = spanBo.getTransactionId();
            String transactionId = "";
            long transactionSequence = 0;
            if (transaction != null) {
                transactionSequence = transaction.getTransactionSequence();
                transactionId = transaction.getAgentId() + transaction.getAgentStartTime() + transactionSequence;
            }

            mysqlSpan.setTransactionId(transactionId);
            mysqlSpan.setAgentId(spanBo.getAgentId());
            mysqlSpan.setAgentStartTime(spanBo.getAgentStartTime());
            mysqlSpan.setTransactionSequence(transactionSequence);
            mysqlSpan.setAcceptedTime(spanBo.getCollectorAcceptTime());
            mysqlSpan.setType((int) SpanEncoder.TYPE_SPAN);
            mysqlSpan.setApplicationId(spanBo.getApplicationId());
            mysqlSpan.setVersion(spanBo.getVersion());
            mysqlSpan.setServiceType((int) spanBo.getServiceType());
            mysqlSpan.setParentSpanId(spanBo.getParentSpanId());
            Long spanStartTime = spanBo.getStartTime();
            mysqlSpan.setStartTime(spanStartTime);
            mysqlSpan.setElapsed(spanBo.getElapsed());
            mysqlSpan.setRpc(spanBo.getRpc());
            mysqlSpan.setEndPoint(spanBo.getEndPoint());
            mysqlSpan.setRemoteAddr(spanBo.getRemoteAddr());
            mysqlSpan.setApiId(spanBo.getApiId());
            mysqlSpan.setErrCode(spanBo.getErrCode());
            mysqlSpan.setExceptionId(spanBo.getExceptionId());
            mysqlSpan.setExceptionMessage(spanBo.getExceptionMessage());
            mysqlSpan.setFlag((int) spanBo.getFlag());
            mysqlSpan.setLoggingTransactionInfo((int) spanBo.getLoggingTransactionInfo());
            mysqlSpan.setAcceptorHost(spanBo.getAcceptorHost());

            Long spanId = mysqlSpanDao.insertSpanAndGetId(mysqlSpan);
            List<SpanEventBo> spanEventList = spanBo.getSpanEventBoList();

            //插入spanevent
            insertSpanEventBo(spanEventList, spanId, transactionId, spanStartTime);


            //抽取部分数据插入transaction 表
            MysqlTransaction mysqlTransaction = new MysqlTransaction();
            mysqlTransaction.setRpc(spanBo.getRpc());
            mysqlTransaction.setAppllicationId(spanBo.getApplicationId());
            mysqlTransaction.setElapsed(spanBo.getElapsed());
            mysqlTransaction.setStartTime(spanBo.getStartTime());
            mysqlTransaction.setEndTime(spanBo.getStartTime() + spanBo.getElapsed());
            mysqlTransaction.setId(transactionId);

            mysqlSpanDao.insertTransaction(mysqlTransaction);

        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();

        }
    }

    /**
     * 插入spanevent
     *
     * @param spanEventList
     * @param spanId
     * @param transactionId
     */
    private void insertSpanEventBo(List<SpanEventBo> spanEventList, Long spanId, String transactionId, Long spanStartTime) {


        if (spanEventList == null || spanEventList.size() == 0) {
            return;
        }

        if (spanId == null || ApmStringUtil.isEmpty(transactionId)) {
            throw new IllegalArgumentException("spanId or transactionId is null or empty!");
        }

        for (SpanEventBo spanEventBo : spanEventList) {
            MysqlSpanEvent mysqlSpanEvent = new MysqlSpanEvent();

            //封装数据
            mysqlSpanEvent.setTransactionId(transactionId);
            mysqlSpanEvent.setSpanId(spanId);
            Long spanEventStartTime = spanStartTime + spanEventBo.getStartElapsed();
            mysqlSpanEvent.setStartElapsed(spanEventStartTime);
            mysqlSpanEvent.setSequence((int) spanEventBo.getSequence());
            mysqlSpanEvent.setDepth(spanEventBo.getDepth());
            mysqlSpanEvent.setServiceType((int) spanEventBo.getServiceType());
            mysqlSpanEvent.setRpc(spanEventBo.getRpc());
            mysqlSpanEvent.setEndPoint(spanEventBo.getEndPoint());
            mysqlSpanEvent.setDestinationId(spanEventBo.getDestinationId());
            mysqlSpanEvent.setApiId(spanEventBo.getApiId());
            mysqlSpanEvent.setNextSpanId(spanEventBo.getNextSpanId());
            mysqlSpanEvent.setExceptionId(spanEventBo.getExceptionId());
            mysqlSpanEvent.setExceptionMessage(spanEventBo.getExceptionMessage());
            mysqlSpanEvent.setNextAsyncId(spanEventBo.getNextAsyncId());
            int sqlId = getSqlIdFromSpanEvent(spanEventBo);
            mysqlSpanEvent.setSqlId(sqlId);
            mysqlSpanEvent.setEndElapsed((long) spanEventBo.getEndElapsed());


            //todo 待有数据了解决
            mysqlSpanEvent.setParentMysqlSpanEventId(1L);
            mysqlSpanEvent.setBeforeMysqlSpanEventId(2L);
            mysqlSpanEvent.setAfterMysqlSpanEventId(3L);


            //插入数据
            mysqlSpanDao.insertSpanEvent(mysqlSpanEvent);

        }


    }


    /*
    从sqpnevent 获取sql id, 代码来源 廖
     */
    public int getSqlIdFromSpanEvent(SpanEventBo spanEventBo) {
        int sqlId = 0;
        List<AnnotationBo> annotationBos = spanEventBo.getAnnotationBoList();
        if (annotationBos != null) {
            int len = annotationBos.size();
            for (int i = 0; i < len; i++) {
                AnnotationBo annotationBo = annotationBos.get(i);
                if (annotationBo != null) {
                    int key = annotationBo.getKey();
                    if (key == AnnotationKey.SQL_ID.getCode()) {
                        Object value = annotationBo.getValue();
                        if (value != null && (value instanceof TIntStringStringValue)) {
                            sqlId = ((TIntStringStringValue) value).getIntValue();
                            break;
                        }
                    }
                }
            }
        }
        return sqlId;
    }
}
