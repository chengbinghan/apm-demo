package apm.collector.dao;

/**
 * @author ChengBing Han
 * @date 22:17  2018/3/5
 * @description
 */
public class MysqlApplication {
    private Integer id;
    private String applicationName;
    private String applicationType;
    private Integer code;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
