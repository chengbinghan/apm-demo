package apm.web.core.monitor;

/**
 * @author ChengBing Han
 * @date 16:04  2018/2/26
 * @description
 */
public class MonitorVO {
    private String monitorType;
    private Integer monitorTime;
    private String monitorThreshold;
    private Integer monitorRpm;

    public Integer getMonitorTime() {
        return monitorTime;
    }

    public void setMonitorTime(Integer monitorTime) {
        this.monitorTime = monitorTime;
    }

    public Integer getMonitorRpm() {
        return monitorRpm;
    }

    public void setMonitorRpm(Integer monitorRpm) {
        this.monitorRpm = monitorRpm;
    }

    public String getMonitorType() {
        return monitorType;
    }

    public void setMonitorType(String monitorType) {
        this.monitorType = monitorType;
    }


    public String getMonitorThreshold() {
        return monitorThreshold;
    }

    public void setMonitorThreshold(String monitorThreshold) {
        this.monitorThreshold = monitorThreshold;
    }


}
