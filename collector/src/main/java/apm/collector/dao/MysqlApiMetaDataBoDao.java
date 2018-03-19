package apm.collector.dao;

import java.util.List;
import java.util.Map;

/**
 * @author ChengBing Han
 * @date 17:16  2018/3/5
 * @description
 */
public interface MysqlApiMetaDataBoDao {
    MysqlApiMetaDataBo getMysqlMetaDataBoByApiId(Integer apiId);

    void addMysqlApiMetaDataBo(MysqlApiMetaDataBo mysqlApiMetaDataBo);

    List<MysqlApiMetaDataBo> getMysqlMetaDataBoByCondition(Map<String, String> hashMap);
}
