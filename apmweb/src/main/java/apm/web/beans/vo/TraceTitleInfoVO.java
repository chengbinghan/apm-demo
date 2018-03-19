package apm.web.beans.vo;

/**
 * @author ChengBing Han
 * @date 19:13  2018/3/17
 * @description 慢事务追踪，顶部信息
 */
public class TraceTitleInfoVO {
    private String applicationName;
    private String rpc;
    private String traceTime;
    private Long responseTime;


    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getRpc() {
        return rpc;
    }

    public void setRpc(String rpc) {
        this.rpc = rpc;
    }

    public String getTraceTime() {
        return traceTime;
    }

    public void setTraceTime(String traceTime) {
        this.traceTime = traceTime;
    }

    public Long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Long responseTime) {
        this.responseTime = responseTime;
    }
}
