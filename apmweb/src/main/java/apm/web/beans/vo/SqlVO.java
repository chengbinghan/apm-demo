package apm.web.beans.vo;

import apm.web.beans.module.Sql;

/**
 * @author ChengBing Han
 * @date 22:39  2018/1/28
 * @description
 */
public class SqlVO {
    private Sql sql;
    private String spanId;
    private Integer sqlCount;
    private Integer totalSqlTime;

    public Integer getTotalSqlTime() {
        return totalSqlTime;
    }

    public void setTotalSqlTime(Integer totalSqlTime) {
        this.totalSqlTime = totalSqlTime;
    }

    public Integer getSqlCount() {
        return sqlCount;
    }

    public void setSqlCount(Integer sqlCount) {
        this.sqlCount = sqlCount;
    }

    public Sql getSql() {
        return sql;
    }

    public void setSql(Sql sql) {
        this.sql = sql;
    }

    public String getSpanId() {
        return spanId;
    }

    public void setSpanId(String spanId) {
        this.spanId = spanId;
    }
}
