package apm.web.beans.module;

/**
 * @author ChengBing Han
 * @date 15:54  2018/1/25
 * @description 对应map_statistics_caller
 */
public class CallerMap {


    private Long callerId;
    private Long timestamp;
    private Integer callerCode;
    private String callerDesc;
    private Integer calleeCode;
    private String calleeDesc;
    private Integer isError;
    private Integer callerAppId;
    private Integer calleeAppId;






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

    public Long getCallerId() {
        return callerId;
    }

    public void setCallerId(Long callerId) {
        this.callerId = callerId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
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
}
