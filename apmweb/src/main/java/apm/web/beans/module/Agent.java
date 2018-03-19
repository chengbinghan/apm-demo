package apm.web.beans.module;

/**
 * @author ChengBing Han
 * @date 14:04  2018/2/3
 * @description api_meta_data 表对应实体类
 */
public class Agent {

    private Integer apiMetaId;
    private String agentId;
    private Long startTimestamp;
    private Long eventIdentifier;
    private Long eventTimestamp;
    private Integer version;
    private Integer stateCode;
    private String stateDesc;
    private String stateName;


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

    public Long getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(Long startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public Long getEventIdentifier() {
        return eventIdentifier;
    }

    public void setEventIdentifier(Long eventIdentifier) {
        this.eventIdentifier = eventIdentifier;
    }

    public Long getEventTimestamp() {
        return eventTimestamp;
    }

    public void setEventTimestamp(Long eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getStateCode() {
        return stateCode;
    }

    public void setStateCode(Integer stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateDesc() {
        return stateDesc;
    }

    public void setStateDesc(String stateDesc) {
        this.stateDesc = stateDesc;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
}
