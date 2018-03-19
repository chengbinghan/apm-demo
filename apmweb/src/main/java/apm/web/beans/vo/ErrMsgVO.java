package apm.web.beans.vo;

/**
 * @author ChengBing Han
 * @date 13:38  2018/2/2
 * @description 异常相关实体类
 */
public class ErrMsgVO {
    private String rpc;
    private String appId;
    private String exceptionMsg;
    private Long startElapsed;

    public String getRpc() {
        return rpc;
    }

    public void setRpc(String rpc) {
        this.rpc = rpc;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getExceptionMsg() {
        return exceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }

    public Long getStartElapsed() {
        return startElapsed;
    }

    public void setStartElapsed(Long startElapsed) {
        this.startElapsed = startElapsed;
    }
}
