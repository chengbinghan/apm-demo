package apm.collector.dao;

import com.navercorp.pinpoint.profiler.context.SpanEvent;

import java.util.List;

/**
 * @author ChengBing Han
 * @date 14:48  2018/3/6
 * @description
 */
public class MysqlSpanEvent {
    private Long spanEventId;
    private String transactionId;
    private Long spanId;
    private Long startElapsed;
    private Long endElapsed;
    private Integer sequence;
    private Integer depth;
    private Integer serviceType;
    private String rpc;
    private String endPoint;
    private String destinationId;
    private Integer apiId;
    private Long nextSpanId;
    private Integer exceptionId;
    private String exceptionMessage;
    private Integer nextAsyncId;
    private Integer sqlId;

    //父spanevent
    private Long parentMysqlSpanEventId;


    //上一级MysqlSpanEvent,兄弟节点，先执行
    private Long beforeMysqlSpanEventId;

    //下一级MysqlSpanEvent，兄弟节点，在本MysqlSpanEvent 之后
    private Long afterMysqlSpanEventId;




    public Long getSpanEventId() {
        return spanEventId;
    }

    public void setSpanEventId(Long spanEventId) {
        this.spanEventId = spanEventId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Long getSpanId() {
        return spanId;
    }

    public void setSpanId(Long spanId) {
        this.spanId = spanId;
    }

    public Long getStartElapsed() {
        return startElapsed;
    }

    public void setStartElapsed(Long startElapsed) {
        this.startElapsed = startElapsed;
    }

    public Long getEndElapsed() {
        return endElapsed;
    }

    public void setEndElapsed(Long endElapsed) {
        this.endElapsed = endElapsed;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public String getRpc() {
        return rpc;
    }

    public void setRpc(String rpc) {
        this.rpc = rpc;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }

    public Integer getApiId() {
        return apiId;
    }

    public void setApiId(Integer apiId) {
        this.apiId = apiId;
    }

    public Long getNextSpanId() {
        return nextSpanId;
    }

    public void setNextSpanId(Long nextSpanId) {
        this.nextSpanId = nextSpanId;
    }

    public Integer getExceptionId() {
        return exceptionId;
    }

    public void setExceptionId(Integer exceptionId) {
        this.exceptionId = exceptionId;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public Integer getNextAsyncId() {
        return nextAsyncId;
    }

    public void setNextAsyncId(Integer nextAsyncId) {
        this.nextAsyncId = nextAsyncId;
    }

    public Integer getSqlId() {
        return sqlId;
    }

    public void setSqlId(Integer sqlId) {
        this.sqlId = sqlId;
    }

    public Long getParentMysqlSpanEventId() {
        return parentMysqlSpanEventId;
    }

    public void setParentMysqlSpanEventId(Long parentMysqlSpanEventId) {
        this.parentMysqlSpanEventId = parentMysqlSpanEventId;
    }

    public Long getBeforeMysqlSpanEventId() {
        return beforeMysqlSpanEventId;
    }

    public void setBeforeMysqlSpanEventId(Long beforeMysqlSpanEventId) {
        this.beforeMysqlSpanEventId = beforeMysqlSpanEventId;
    }

    public Long getAfterMysqlSpanEventId() {
        return afterMysqlSpanEventId;
    }

    public void setAfterMysqlSpanEventId(Long afterMysqlSpanEventId) {
        this.afterMysqlSpanEventId = afterMysqlSpanEventId;
    }
}
