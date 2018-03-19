package apm.web.beans.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import apm.web.beans.module.SpanEvent;

import java.util.List;

/**
 * @author ChengBing Han
 * @date 16:43  2018/1/31
 * @description
 */
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class SpanEventVO {

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
    private SpanEvent parentSpanEvent;

    //上一级spanevent,兄弟节点，先执行
    private SpanEvent beforeSpanEvent;

    //下一级spanevent，兄弟节点，在本spanevent 之后
    private SpanEvent afterSpanEvent;

    //子spanEven,多个子spanEvent 按照顺序放到List中
    private List<SpanEvent> childSpanEventList;


    // 本方法在某次事务调用次数
    private Integer invokeCount;
    //本方法在某次事务中多次调用持续时间
    private long totalTime;
    private float timePercent;

    private String apiName;

    public SpanEvent getBeforeSpanEvent() {
        return beforeSpanEvent;
    }

    public void setBeforeSpanEvent(SpanEvent beforeSpanEvent) {
        this.beforeSpanEvent = beforeSpanEvent;
    }

    public SpanEvent getAfterSpanEvent() {
        return afterSpanEvent;
    }

    public void setAfterSpanEvent(SpanEvent afterSpanEvent) {
        this.afterSpanEvent = afterSpanEvent;
    }






    public List<SpanEvent> getChildSpanEventList() {
        return childSpanEventList;
    }

    public void setChildSpanEventList(List<SpanEvent> childSpanEventList) {
        this.childSpanEventList = childSpanEventList;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    public float getTimePercent() {
        return timePercent;
    }

    public void setTimePercent(float timePercent) {
        this.timePercent = timePercent;
    }

    public Integer getInvokeCount() {

        return invokeCount;
    }

    public void setInvokeCount(Integer invokeCount) {
        this.invokeCount = invokeCount;
    }


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
    public SpanEvent getParentSpanEvent() {
        return parentSpanEvent;
    }

    public void setParentSpanEvent(SpanEvent parentSpanEvent) {
        this.parentSpanEvent = parentSpanEvent;
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
}
