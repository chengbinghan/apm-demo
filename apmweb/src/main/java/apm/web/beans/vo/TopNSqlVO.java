package apm.web.beans.vo;

/**
 * @author ChengBing Han
 * @date 16:37  2018/1/30
 * @description  topN 最耗时Sql
 */
public class TopNSqlVO {

    private DataBaseSqlViewVO dataBaseSqlViewVO;
    //最大的一次时间
    private Integer maxTime;
    //最小的一次时间
    private String minTime;
    //本sql 调用时间
    private Long invokeTime;
    //平均时间
   private Long avgTime;




    public Integer getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(Integer maxTime) {
        this.maxTime = maxTime;
    }

    public String getMinTime() {
        return minTime;
    }

    public void setMinTime(String minTime) {
        this.minTime = minTime;
    }

    public Long getInvokeTime() {
        return invokeTime;
    }

    public void setInvokeTime(Long invokeTime) {
        this.invokeTime = invokeTime;
    }


    public DataBaseSqlViewVO getDataBaseSqlViewVO() {
        return dataBaseSqlViewVO;
    }

    public void setDataBaseSqlViewVO(DataBaseSqlViewVO dataBaseSqlViewVO) {
        this.dataBaseSqlViewVO = dataBaseSqlViewVO;
    }

    public Long getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(Long avgTime) {
        this.avgTime = avgTime;
    }



}
