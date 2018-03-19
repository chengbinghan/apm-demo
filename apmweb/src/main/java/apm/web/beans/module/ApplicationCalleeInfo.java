package apm.web.beans.module;

/**
 *
 */
public class ApplicationCalleeInfo {

    //应用被调用者
    private ApmApplication applicationCallee;

    public ApmApplication getApplicationCallee() {
        return applicationCallee;
    }

    public void setApplicationCallee(ApmApplication applicationCallee) {
        this.applicationCallee = applicationCallee;
    }

    //调用次数
    private String callTimes;

    //相应时间
    private String responseTime;

    public String getCallTimes() {
        return callTimes;
    }

    public void setCallTimes(String callTimes) {
        this.callTimes = callTimes;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }


}
