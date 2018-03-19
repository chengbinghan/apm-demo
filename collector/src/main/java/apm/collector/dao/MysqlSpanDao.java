package apm.collector.dao;

/**
 * @author ChengBing Han
 * @date 14:34  2018/3/6
 * @description
 */
public interface MysqlSpanDao {
    Long insertSpanAndGetId(MysqlSpan mysqlSpan);

    void insertSpanEvent(MysqlSpanEvent mysqlSpanEvent);

    void insertTransaction(MysqlTransaction mysqlTransaction);
}
