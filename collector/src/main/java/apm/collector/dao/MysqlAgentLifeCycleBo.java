package apm.collector.dao;

import com.navercorp.pinpoint.common.server.util.AgentLifeCycleState;

/**
 * @author ChengBing Han
 * @date 10:18  2018/3/5
 * @description
 */
public class MysqlAgentLifeCycleBo {

    private  String agentId;
    private  Long startTimestamp;
    private  Long eventTimestamp;
    private  Long eventIdentifier;
    private  Integer version;
    private  Integer stateCode;
    private String stateDesc;
    private String stateName;


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

    public Long getEventTimestamp() {
        return eventTimestamp;
    }

    public void setEventTimestamp(Long eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }

    public Long getEventIdentifier() {
        return eventIdentifier;
    }

    public void setEventIdentifier(Long eventIdentifier) {
        this.eventIdentifier = eventIdentifier;
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
