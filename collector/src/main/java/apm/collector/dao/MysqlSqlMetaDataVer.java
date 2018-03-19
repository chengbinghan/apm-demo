package apm.collector.dao;

/**
 * @author ChengBing Han
 * @date 16:52  2018/3/6
 * @description
 */
public class MysqlSqlMetaDataVer {
    private Integer sqlMetaDataId;
    private String agentId;
    private Long agentStartTime;
    private Integer sqlId;
    private String sqlInfo;


    public Integer getSqlMetaDataId() {
        return sqlMetaDataId;
    }

    public void setSqlMetaDataId(Integer sqlMetaDataId) {
        this.sqlMetaDataId = sqlMetaDataId;
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

    public Integer getSqlId() {
        return sqlId;
    }

    public void setSqlId(Integer sqlId) {
        this.sqlId = sqlId;
    }

    public String getSqlInfo() {
        return sqlInfo;
    }

    public void setSqlInfo(String sqlInfo) {
        this.sqlInfo = sqlInfo;
    }
}
