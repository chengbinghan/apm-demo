package apm.web.beans.vo;

/**
 * @author ChengBing Han
 * @date 15:08  2018/2/1
 * @description 某个应用app, 查询出现 n 条错误，每条错误发生时间曲线。
 */
public class AppErrorVO {

    //查询app 的n条错误，其中发生错误最早的时间
    private Long startTime;
    //查询app 的n条错误，其中发生错误最晚的时间
    private Long endTime;
    private String rpc;
    private Long executeTime;
    //错误率
    private String errPer;
    //调用次数
    private Integer invokeCount;


    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getRpc() {
        return rpc;
    }

    public void setRpc(String rpc) {
        this.rpc = rpc;
    }

    public Long getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Long executeTime) {
        this.executeTime = executeTime;
    }

    public String getErrPer() {
        return errPer;
    }

    public void setErrPer(String errPer) {
        this.errPer = errPer;
    }

    public Integer getInvokeCount() {
        return invokeCount;
    }

    public void setInvokeCount(Integer invokeCount) {
        this.invokeCount = invokeCount;
    }
}
