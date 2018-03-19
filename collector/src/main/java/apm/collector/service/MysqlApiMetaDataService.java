package apm.collector.service;

import apm.collector.dao.MysqlApiMetaDataBo;
import com.navercorp.pinpoint.common.server.bo.ApiMetaDataBo;
import com.navercorp.pinpoint.thrift.dto.TApiMetaData;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ChengBing Han
 * @date 15:05  2018/3/5
 * @description
 */

public interface MysqlApiMetaDataService {


    void addMysqlApiMetaDataBo(TApiMetaData apiMetaDataBo) throws Exception;

    MysqlApiMetaDataBo getMysqlMetaDataBoByApiId(Integer apiId);

    List<MysqlApiMetaDataBo> getMysqlMetaDataBoByCondition(Map<String, String> hashMap);
}
