package apm.web.beans.vo;


/**
 * @author ChengBing Han
 * @description  应用信息
 */
public class ApplicationVO {



    //实例数
    private int instanceCount;

    //Apdex
    private String apdex;

    //错误率
    private String errPercentage;

    //吞吐率
    private String rpm;

    //响应时间
    private Integer responseTime;

    private String applicationName;

    private String appId;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getApplicationName() {

        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public int getInstanceCount() {
        return instanceCount;
    }

    public void setInstanceCount(int instanceCount) {
        this.instanceCount = instanceCount;
    }

    public String getApdex() {
        return apdex;
    }

    public void setApdex(String apdex) {
        this.apdex = apdex;
    }

    public String getErrPercentage() {
        return errPercentage;
    }

    public void setErrPercentage(String errPercentage) {
        this.errPercentage = errPercentage;
    }

    public String getRpm() {
        return rpm;
    }

    public void setRpm(String rpm) {
        this.rpm = rpm;
    }

    public Integer getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Integer responseTime) {
        this.responseTime = responseTime;
    }
}
