package apm.web.beans.module;

/**
 * @author ChengBing Han
 * @date 19:32  2018/1/24
 * @description 对于api_meta_data  这张表
 */
public class ApiMethod {
    private Integer apiMetaId;
    private String agentId;
    private Long agentStartTime;
    private Integer apiId;
    //方法信息com.xx.f（String, int）
    private String apiInfo;
    //方法行号
    private Integer line;
    private Integer type;

    public Integer getApiMetaId() {
        return apiMetaId;
    }

    public void setApiMetaId(Integer apiMetaId) {
        this.apiMetaId = apiMetaId;
    }

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
