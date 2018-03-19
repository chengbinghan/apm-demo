package apm.web.beans.vo;

/**
 * @author ChengBing Han
 * @date 10:34  2018/1/29
 * @description 确定某类事务，得出事务的分解表格,该表格的VO对象
 */
public class TheTransactionanalysisVO {

    private String rpc;
    private String applicationName;
    private Integer invokeCount;
    private String avgTime;
    private String timePer;

    public String getTimePer() {
        return timePer;
    }

    public void setTimePer(String timePer) {
        this.timePer = timePer;
    }

    public String getRpc() {
        return rpc;
    }

    public void setRpc(String rpc) {
        this.rpc = rpc;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public Integer getInvokeCount() {
        return invokeCount;
    }

    public void setInvokeCount(Integer invokeCount) {
        this.invokeCount = invokeCount;
    }

    public String getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(String avgTime) {
        this.avgTime = avgTime;
    }
}


