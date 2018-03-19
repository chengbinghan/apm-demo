package apm.collector.service;

import apm.collector.dao.MysqlSqlMetaDataVer;
import com.navercorp.pinpoint.thrift.dto.TSqlMetaData;

/**
 * @author ChengBing Han
 * @date 16:56  2018/3/6
 * @description
 */
public interface MysqlSqlMetaDataService {

    public void addMysqlSqlMetaDataVer(TSqlMetaData sqlMetaData);
}
