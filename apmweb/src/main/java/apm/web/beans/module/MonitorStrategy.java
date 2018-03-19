package apm.web.beans.module;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChengBing Han
 * @date 21:03  2018/2/12
 * @description 监控项目类型
 */
public class MonitorStrategy {
    //监控项目id
    private Integer id;
    //监控项目名称
    private String name;
    //监控项目类型,应用，关键事务，外部服务==常量
    private String type;
    //对应monitros, 逗号分割每个id
    private String monitors;

    private List<Monitor> monitorList = new ArrayList<>();

    public List<Monitor> getMonitorList() {
        return monitorList;
    }

    public void setMonitorList(List<Monitor> monitorList) {
        this.monitorList = monitorList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMonitors() {
        return monitors;
    }

    public void setMonitors(String monitors) {
        this.monitors = monitors;
    }

    public Integer getId() {
        return id;

    }

    public void setId(Integer id) {
        this.id = id;
    }
}
