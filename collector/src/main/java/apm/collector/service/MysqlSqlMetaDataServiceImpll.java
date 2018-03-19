package apm.collector.service;

import apm.collector.dao.MysqlSqlMetaDataVer;
import apm.collector.dao.MysqlSqlMetaDataVerDao;
import com.navercorp.pinpoint.thrift.dto.TSqlMetaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ChengBing Han
 * @date 17:00  2018/3/6
 * @description
 */
@Service
public class MysqlSqlMetaDataServiceImpll implements MysqlSqlMetaDataService {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    MysqlSqlMetaDataVerDao mysqlSqlMetaDataVerDao;

    @Override
    public void addMysqlSqlMetaDataVer(TSqlMetaData sqlMetaData) {
        try {

            if (sqlMetaData == null) {
                throw new IllegalArgumentException("sqlmetaData is null");
            }

            MysqlSqlMetaDataVer mysqlSqlMetaDataVer = new MysqlSqlMetaDataVer();
            mysqlSqlMetaDataVer.setAgentId(sqlMetaData.getAgentId());
            mysqlSqlMetaDataVer.setAgentStartTime(sqlMetaData.getAgentStartTime());
            mysqlSqlMetaDataVer.setSqlId(sqlMetaData.getSqlId());
            mysqlSqlMetaDataVer.setSqlInfo(sqlMetaData.getSql());

            mysqlSqlMetaDataVerDao.insert(mysqlSqlMetaDataVer);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }


    }
}
