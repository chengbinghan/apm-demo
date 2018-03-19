package apm.web.beans.module;

/**
 * @author ChengBing Han
 * @date 21:07  2018/2/12
 * @description 监控
 */
public class Monitor {
    private Integer id;

/*    private String name;*/
    //type可以是apdex,响应时间
    private String type;

    //阈值
    private String threshold;

    //持续时间
    private Integer time;

    //rpm 值
    private Integer rpm ;

    public String getThreshold() {
        return threshold;
    }

    public void setThreshold(String threshold) {
        this.threshold = threshold;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getRpm() {
        return rpm;
    }

    public void setRpm(Integer rpm) {
        this.rpm = rpm;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

/*    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }*/

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
