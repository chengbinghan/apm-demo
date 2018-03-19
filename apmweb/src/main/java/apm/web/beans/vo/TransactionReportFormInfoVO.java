package apm.web.beans.vo;

/**
 * @author ChengBing Han
 * @date 10:03  2018/2/23
 * @description 事务 报表
 */
public class TransactionReportFormInfoVO {


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
    private Integer minResponseTime;

    //吞吐率
    private Double throughput;

    //错误率
    private Double errorPer;

    //apdex
    private Double apdex;

    //调用次数
    private Long invokeCount;



    //事务url
    private String transactionUrl;

    //事务耗时百分比
    private String transactionPer;

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

    public Integer getMinResponseTime() {
        return minResponseTime;
    }

    public void setMinResponseTime(Integer minResponseTime) {
        this.minResponseTime = minResponseTime;
    }

    public Double getThroughput() {
        return throughput;
    }

    public void setThroughput(Double throughput) {
        this.throughput = throughput;
    }

    public Double getErrorPer() {
        return errorPer;
    }

    public void setErrorPer(Double errorPer) {
        this.errorPer = errorPer;
    }

    public Double getApdex() {
        return apdex;
    }

    public void setApdex(Double apdex) {
        this.apdex = apdex;
    }

    public Long getInvokeCount() {
        return invokeCount;
    }

    public void setInvokeCount(Long invokeCount) {
        this.invokeCount = invokeCount;
    }

    public String getTransactionUrl() {
        return transactionUrl;
    }

    public void setTransactionUrl(String transactionUrl) {
        this.transactionUrl = transactionUrl;
    }

    public String getTransactionPer() {
        return transactionPer;
    }

    public void setTransactionPer(String transactionPer) {
        this.transactionPer = transactionPer;
    }
}
