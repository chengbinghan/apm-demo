package apm.collector.dao;

/**
 * @author ChengBing Han
 * @date 23:32  2018/3/5
 * @description
 */
public class MysqlMapStatisticsCaller {
    private Long callerId;
    private Long timeStamp;
    private Integer callerCode;
    private String callerDesc;
    private Integer calleeCode;
    private String calleeDesc;
    //0表示没有异常
    private Integer isError;
    private Integer callerAppId;
    private Integer calleeAppId;


    private String callerAppName;
    private String calleeAppName;

    public String getCallerAppName() {
        return callerAppName;
    }

    public void setCallerAppName(String callerAppName) {
        this.callerAppName = callerAppName;
    }

    public String getCalleeAppName() {
        return calleeAppName;
    }

    public void setCalleeAppName(String calleeAppName) {
        this.calleeAppName = calleeAppName;
    }

    public Long getCallerId() {
        return callerId;
    }

    public void setCallerId(Long callerId) {
        this.callerId = callerId;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Integer getCallerCode() {
        return callerCode;
    }

    public void setCallerCode(Integer callerCode) {
        this.callerCode = callerCode;
    }

    public String getCallerDesc() {
        return callerDesc;
    }

    public void setCallerDesc(String callerDesc) {
        this.callerDesc = callerDesc;
    }

    public Integer getCalleeCode() {
        return calleeCode;
    }

    public void setCalleeCode(Integer calleeCode) {
        this.calleeCode = calleeCode;
    }

    public String getCalleeDesc() {
        return calleeDesc;
    }

    public void setCalleeDesc(String calleeDesc) {
        this.calleeDesc = calleeDesc;
    }

    public Integer getIsError() {
        return isError;
    }

    public void setIsError(Integer isError) {
        this.isError = isError;
    }

    public Integer getCallerAppId() {
        return callerAppId;
    }

    public void setCallerAppId(Integer callerAppId) {
        this.callerAppId = callerAppId;
    }

    public Integer getCalleeAppId() {
        return calleeAppId;
    }

    public void setCalleeAppId(Integer calleeAppId) {
        this.calleeAppId = calleeAppId;
    }
}
