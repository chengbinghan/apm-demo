package com.navercorp.pinpoint.collector.dao.hbase;

import apm.collector.service.MysqlSpanService;
import apm.collector.util.CollectorConstant;
import com.navercorp.pinpoint.collector.dao.TraceDao;
import com.navercorp.pinpoint.common.hbase.HbaseOperations2;
import com.navercorp.pinpoint.common.server.bo.SpanBo;
import com.navercorp.pinpoint.common.server.bo.SpanChunkBo;
import com.navercorp.pinpoint.common.server.bo.SpanEventBo;
import com.navercorp.pinpoint.common.server.bo.serializer.RowKeyEncoder;
import com.navercorp.pinpoint.common.server.bo.serializer.trace.v2.SpanChunkSerializerV2;
import com.navercorp.pinpoint.common.server.bo.serializer.trace.v2.SpanSerializerV2;
import com.navercorp.pinpoint.common.util.TransactionId;
import org.apache.commons.collections.CollectionUtils;
import org.apache.hadoop.hbase.client.Put;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.File;
import java.io.PrintStream;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.navercorp.pinpoint.common.hbase.HBaseTables.TRACE_V2;

/**
 * @author Woonduk Kang(emeroad)
 */
@Repository
public class HbaseTraceDaoV2 implements TraceDao {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HbaseOperations2 hbaseTemplate;


    @Autowired
    private SpanSerializerV2 spanSerializer;

    @Autowired
    private SpanChunkSerializerV2 spanChunkSerializer;

    @Autowired
    @Qualifier("traceRowKeyEncoderV2")
    private RowKeyEncoder<TransactionId> rowKeyEncoder;


    @Resource
    private MysqlSpanService mysqlSpanService;


    StringBuffer sb = new StringBuffer();

    AtomicInteger atomicInteger = new AtomicInteger();

    @Override
    public void insert(final SpanBo spanBo) {
        if (spanBo == null) {
            throw new NullPointerException("spanBo must not be null");
        }


        //O===||=================>add by hcb start
        /*
         Insert record into mysql DB
         */
        if (true) {

            mysqlSpanService.addSpanAndSpanEventAndTransaction(spanBo);

        }

        // Insert record into mysql DB.
        //   DBTraceDaoV2 dbTraceDao = new DBTraceDaoV2();
        // dbTraceDao.insertSpanToDB(spanBo);
        //O===||=================>add by hcb end


        long acceptedTime = spanBo.getCollectorAcceptTime();

        TransactionId transactionId = spanBo.getTransactionId();
        final byte[] rowKey = this.rowKeyEncoder.encodeRowKey(transactionId);
        final Put put = new Put(rowKey, acceptedTime);

        this.spanSerializer.serialize(spanBo, put, null);


        boolean success = hbaseTemplate.asyncPut(TRACE_V2, put);
        if (!success) {
            hbaseTemplate.put(TRACE_V2, put);
        }

    }


    @Override
    public void insertSpanChunk(SpanChunkBo spanChunkBo) {

        TransactionId transactionId = spanChunkBo.getTransactionId();
        final byte[] rowKey = this.rowKeyEncoder.encodeRowKey(transactionId);

        final long acceptedTime = spanChunkBo.getCollectorAcceptTime();
        final Put put = new Put(rowKey, acceptedTime);

        final List<SpanEventBo> spanEventBoList = spanChunkBo.getSpanEventBoList();
        if (CollectionUtils.isEmpty(spanEventBoList)) {
            return;
        }

        this.spanChunkSerializer.serialize(spanChunkBo, put, null);

        if (!put.isEmpty()) {
            boolean success = hbaseTemplate.asyncPut(TRACE_V2, put);
            if (!success) {
                hbaseTemplate.put(TRACE_V2, put);
            }
        }
    }


}
