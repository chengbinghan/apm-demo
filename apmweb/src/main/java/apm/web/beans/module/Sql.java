package apm.web.beans.module;

/**
 * @author ChengBing Han
 * @date 17:28  2018/1/24
 * @description  对应数据库 sql_metadata_ver2
 */
public class Sql {

    private Integer sqlMetaDataId;
    private String agentId;
    private Long agentStartTime;
    private Integer sqlId;
    private String sqlInfo;

/*    //sql 执行时间
    private Float time;*/



/*
    public Float getTime() {
        return time;
    }

    public void setTime(Float time) {
        this.time = time;
    }*/



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
