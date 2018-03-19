package apm.collector.dao;

/**
 * @author ChengBing Han
 * @date 15:50  2018/3/5
 * @description
 */
public class MysqlApiMetaDataBo {
    private String agentId;
    private Long agentStartTime;
    private Integer apiId;
    private String apiInfo;
    private Integer line;
    private Integer type;

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public Long getAgentStartTime() {
        return agentStartTime;
    }

    public void setAgentStartTime(Long agentStartTime) {
        this.agentStartTime = agentStartTime;
    }

    public Integer getApiId() {
        return apiId;
    }

    public void setApiId(Integer apiId) {
        this.apiId = apiId;
    }

    public String getApiInfo() {
        return apiInfo;
    }

    public void setApiInfo(String apiInfo) {
        this.apiInfo = apiInfo;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
