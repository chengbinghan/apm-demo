package apm.web.beans.vo;

/**
 * @author ChengBing Han
 * @date 14:51  2018/2/28
 * @description
 */
public class DatabaseReportFormInfoVO {



    //查询日期类范围，可以是今天、昨天、7日内，七日前，所有
    private String timeType;

    //统计时间区间长度，比如今天13时0分0秒开始统计，那么统计时间的毫秒值为13个小时的毫秒值。单位毫秒
    private Long statisticsSectionTime;

    //统计时刻，比如是13时0分0秒开始计算，那么该值为其毫秒值。
    private Long statisticsTime;



    //平均响应时间
    private Double avgResponseTime;

    //最大响应时间
    private Integer maxResponseTime;

    //最小响应时间
    private Double minResponseTime;
    //总时间
    private Integer totalTime;

    //吞吐率
    private Double throughput;

    //调用次数
    private Long invokeCount;

    //该sql对应的 spanevent 即方法信息。 包名加方法名
    private String apiInfo;


    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    public Long getStatisticsSectionTime() {
        return statisticsSectionTime;
    }

    public void setStatisticsSectionTime(Long statisticsSectionTime) {
        this.statisticsSectionTime = statisticsSectionTime;
    }

    public Long getStatisticsTime() {
        return statisticsTime;
    }

    public void setStatisticsTime(Long statisticsTime) {
        this.statisticsTime = statisticsTime;
    }

    public Double getAvgResponseTime() {
        return avgResponseTime;
    }

    public void setAvgResponseTime(Double avgResponseTime) {
        this.avgResponseTime = avgResponseTime;
    }

    public Integer getMaxResponseTime() {
        return maxResponseTime;
    }

    public void setMaxResponseTime(Integer maxResponseTime) {
        this.maxResponseTime = maxResponseTime;
    }

    public Double getMinResponseTime() {
        return minResponseTime;
    }

    public void setMinResponseTime(Double minResponseTime) {
        this.minResponseTime = minResponseTime;
    }

    public Integer getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

    public Double getThroughput() {
        return throughput;
    }

    public void setThroughput(Double throughput) {
        this.throughput = throughput;
    }

    public Long getInvokeCount() {
        return invokeCount;
    }

    public void setInvokeCount(Long invokeCount) {
        this.invokeCount = invokeCount;
    }

    public String getApiInfo() {
        return apiInfo;
    }

    public void setApiInfo(String apiInfo) {
        this.apiInfo = apiInfo;
    }
}
