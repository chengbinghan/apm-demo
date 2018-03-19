package apm.web.beans.vo;

/**
 * @author ChengBing Han
 * @date 23:30  2018/1/28
 * @description  某个事务的性能分解
 */
public class TheSpanVO {

    private Integer averageTime ;
    private Integer maxTime;
    private Integer minTime;
    private String rpc;
    private Integer invokeCount;
    private String  theSpanPer;
    private String applicationId;
    private String applicationName;

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getTheSpanPer() {
        return theSpanPer;
    }

    public void setTheSpanPer(String theSpanPer) {
        this.theSpanPer = theSpanPer;
    }

    public Integer getAverageTime() {
        return averageTime;
    }

    public void setAverageTime(Integer averageTime) {
        this.averageTime = averageTime;
    }

    public Integer getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(Integer maxTime) {
        this.maxTime = maxTime;
    }

    public Integer getMinTime() {
        return minTime;
    }

    public void setMinTime(Integer minTime) {
        this.minTime = minTime;
    }

    public String getRpc() {
        return rpc;
    }

    public void setRpc(String rpc) {
        this.rpc = rpc;
    }

    public Integer getInvokeCount() {
        return invokeCount;
    }

    public void setInvokeCount(Integer invokeCount) {
        this.invokeCount = invokeCount;
    }
}
