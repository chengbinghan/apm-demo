package apm.web.beans.vo;

/**
 * @author ChengBing Han
 * @date 21:10  2018/3/17
 * @description
 */
public class SimpleSpanVO {
    private Integer elapsed;
    private String applicationName;
    private Long startTime;
    private String remoteAddr;

    private Integer avgElapsed;


    public Integer getElapsed() {
        return elapsed;
    }

    public void setElapsed(Integer elapsed) {
        this.elapsed = elapsed;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    public Integer getAvgElapsed() {
        return avgElapsed;
    }

    public void setAvgElapsed(Integer avgElapsed) {
        this.avgElapsed = avgElapsed;
    }
}
