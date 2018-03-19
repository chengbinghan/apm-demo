package apm.collector.dao;

import com.navercorp.pinpoint.common.server.util.AgentEventType;

/**
 * @author ChengBing Han
 * @date 17:58  2018/3/4
 * @description
 */
public class MysqlAgentEventBo {


    private  String agentId;
    private  long eventTimestamp;
    private  Integer eventTypeCode;
    private String eventTypeDesc;
    private String eventTypeName;
    private String eventTypeMessage;
    private String eventTypeCategory;

    private  Integer version;
    private  long startTimestamp;
    private  AgentEventType eventType;
    private String eventBody;


    public Integer getEventTypeCode() {
        return eventTypeCode;
    }

    public void setEventTypeCode(Integer eventTypeCode) {
        this.eventTypeCode = eventTypeCode;
    }

    public String getEventTypeDesc() {
        return eventTypeDesc;
    }

    public void setEventTypeDesc(String eventTypeDesc) {
        this.eventTypeDesc = eventTypeDesc;
    }

    public String getEventTypeName() {
        return eventTypeName;
    }

    public void setEventTypeName(String eventTypeName) {
        this.eventTypeName = eventTypeName;
    }

    public String getEventTypeMessage() {
        return eventTypeMessage;
    }

    public void setEventTypeMessage(String eventTypeMessage) {
        this.eventTypeMessage = eventTypeMessage;
    }

    public String getEventTypeCategory() {
        return eventTypeCategory;
    }

    public void setEventTypeCategory(String eventTypeCategory) {
        this.eventTypeCategory = eventTypeCategory;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public long getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(long startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public long getEventTimestamp() {
        return eventTimestamp;
    }

    public void setEventTimestamp(long eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }

    public AgentEventType getEventType() {
        return eventType;
    }

    public void setEventType(AgentEventType eventType) {
        this.eventType = eventType;
    }

    public String getEventBody() {
        return eventBody;
    }

    public void setEventBody(String eventBody) {
        this.eventBody = eventBody;
    }
}
