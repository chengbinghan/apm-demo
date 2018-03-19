package apm.web.beans.module;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

/**
 * @author ChengBing Han
 * @date 14:40  2018/1/22
 * @description 对应Span 表
 */
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class Span {

    //spanId
    private Long spanId;

    //transactionId
    private String transactionId;

    //agentId
    private String agentId;

    //探针开始时间
    private Long agentStartTime;

    //递增
    private Long transactionSequence;

    //acceptedTime
    private Long acceptedTime;

    //span type
    private Integer type;

    //span 所属的 app id
    private String applicationId;

    //version
    private Integer version;

    // ServiceType  app 的 service Type
    private Integer serviceType;
    //父span id
    private Long parentSpanId;

    private long startTime;

    //该span耗时
    private Integer elapsed;

    //url
    private String rpc;

    //agent所处应用服务器地址和端口号
    private String endPoint;

   //客户端地址
    private String remoteAddr;

    //方法标识
    private Integer apiId;

    //异常Id
    private Integer exceptionId;

    //错误码
    private Integer errCode;

    //异常信息
    private String exceptionMessage;


    private Integer flag;

    private Integer loggingTransactionInfo;

    //监控数据接收主机地址
    private String acceptorHost;


    //百分比，该span本事务的所有span中的百分比
    private Float timePercentInTran;

    private List<SpanEvent> spanEventList;


    //方法名
    private String methodName;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public List<SpanEvent> getSpanEventList() {
        return spanEventList;
    }

    public void setSpanEventList(List<SpanEvent> spanEventList) {
        this.spanEventList = spanEventList;
    }

    public Float getTimePercentInTran() {
        return timePercentInTran;
    }

    public void setTimePercentInTran(Float timePercentInTran) {
        this.timePercentInTran = timePercentInTran;
    }

    public Long getSpanId() {
        return spanId;
    }

    public void setSpanId(Long spanId) {
        this.spanId = spanId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public Long getAgentStartTime() {
        return agentStartTime;
    }

    public void setAgentStartTime(Long agentStartTime) {
        this.agentStartTime = agentStartTime;
    }

    public Long getTransactionSequence() {
        return transactionSequence;
    }

    public void setTransactionSequence(Long transactionSequence) {
        this.transactionSequence = transactionSequence;
    }

    public Long getAcceptedTime() {
        return acceptedTime;
    }

    public void setAcceptedTime(Long acceptedTime) {
        this.acceptedTime = acceptedTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public Long getParentSpanId() {
        return parentSpanId;
    }

    public void setParentSpanId(Long parentSpanId) {
        this.parentSpanId = parentSpanId;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public Integer getElapsed() {
        return elapsed;
    }

    public void setElapsed(Integer elapsed) {
        this.elapsed = elapsed;
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

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    public Integer getApiId() {
        return apiId;
    }

    public void setApiId(Integer apiId) {
        this.apiId = apiId;
    }

    public Integer getExceptionId() {
        return exceptionId;
    }

    public void setExceptionId(Integer exceptionId) {
        this.exceptionId = exceptionId;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getLoggingTransactionInfo() {
        return loggingTransactionInfo;
    }

    public void setLoggingTransactionInfo(Integer loggingTransactionInfo) {
        this.loggingTransactionInfo = loggingTransactionInfo;
    }

    public String getAcceptorHost() {
        return acceptorHost;
    }

    public void setAcceptorHost(String acceptorHost) {
        this.acceptorHost = acceptorHost;
    }
}


