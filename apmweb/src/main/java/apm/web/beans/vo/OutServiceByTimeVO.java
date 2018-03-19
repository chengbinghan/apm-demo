package apm.web.beans.vo;

/**
 * @author ChengBing Han
 * @date 11:20  2018/2/7
 * @description 外部服务随时间变化VO
 */
public class OutServiceByTimeVO {

    //开始时间
    private Long beginTime;
    //结束时间
    private Long endTime;

    //调用次数
    private long invokeCount;
    //平均响应时间
    private Integer avgTime;
    private Integer maxTime;
    private Integer minTime;

    public Long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Long beginTime) {
        this.beginTime = beginTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public long getInvokeCount() {
        return invokeCount;
    }

    public void setInvokeCount(long invokeCount) {
        this.invokeCount = invokeCount;
    }

    public Integer getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(Integer avgTime) {
        this.avgTime = avgTime;
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
}
