package apm.web.beans.vo;

/**
 * @author ChengBing Han
 * @date 0:31  2018/1/29
 * @description 慢事务列表 VO 对象
 */
public class SlowTransactionVO {

    // 给一个用于表示列表顺序的号， 前端要用，后期分页优化
    private  Long number = 1L;   //注意其get 方法。

    private String startTime;
    private String rpc ;
    private String applicationId;
    private Integer elapsed;
    private String transactionId;
    private String spanId;


    public String getSpanId() {
        return spanId;
    }

    public void setSpanId(String spanId) {
        this.spanId = spanId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getRpc() {
        return rpc;
    }

    public void setRpc(String rpc) {
        this.rpc = rpc;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public Integer getElapsed() {
        return elapsed;
    }

    public void setElapsed(Integer elapsed) {
        this.elapsed = elapsed;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
