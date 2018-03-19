package apm.collector.service;

import apm.collector.dao.MysqlSpan;
import com.navercorp.pinpoint.common.server.bo.SpanBo;

/**
 * @author ChengBing Han
 * @date 13:57  2018/3/6
 * @description
 */
public interface MysqlSpanService {


    void addSpanAndSpanEventAndTransaction(SpanBo spanBo);


}
