package apm.web.beans.vo;

/**
 * @author ChengBing Han
 * @date 14:57  2018/2/8
 * @description 耗时的外部服务
 */
public class ExpensiveOutserviceVO {
    //调用次数
    private Integer invokeCount;
    //平均耗时时间
    private Integer avgTime;
    //应用名
    private String applicationName;
    //serviceCode
    private String serviceCode;
    //时间占比，该时间占比为该外部服务在所有服务（自身，第三方）的比例
    private String timePer;
    //最大，最小时间
    private Integer maxTime;
    private Integer minTime;

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

    public String getTimePer() {
        return timePer;
    }

    public void setTimePer(String timePer) {
        this.timePer = timePer;
    }

    public Integer getInvokeCount() {
        return invokeCount;
    }

    public void setInvokeCount(Integer invokeCount) {
        this.invokeCount = invokeCount;
    }

    public Integer getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(Integer avgTime) {
        this.avgTime = avgTime;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }
}
