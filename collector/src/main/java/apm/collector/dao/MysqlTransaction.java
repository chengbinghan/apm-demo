package apm.collector.dao;

/**
 * @author ChengBing Han
 * @date 16:15  2018/3/6
 * @description
 */
public class MysqlTransaction {
    //id
    private String id;

    //调用字符串
    private String rpc;
    //开始时间
    private Long startTime;
    //结束时间
    private Long endTime;

    //持续时间
    private Integer elapsed ;

    //应用ID
    private String appllicationId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRpc() {
        return rpc;
    }

    public void setRpc(String rpc) {
        this.rpc = rpc;
    }

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

    public Integer getElapsed() {
        return elapsed;
    }

    public void setElapsed(Integer elapsed) {
        this.elapsed = elapsed;
    }

    public String getAppllicationId() {
        return appllicationId;
    }

    public void setAppllicationId(String appllicationId) {
        this.appllicationId = appllicationId;
    }
}
