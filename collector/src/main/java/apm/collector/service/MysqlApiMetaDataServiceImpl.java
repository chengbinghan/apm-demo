package apm.collector.service;

import apm.collector.dao.MysqlApiMetaDataBo;
import apm.collector.dao.MysqlApiMetaDataBoDao;
import com.navercorp.pinpoint.collector.dao.ApiMetaDataDao;
import com.navercorp.pinpoint.common.server.bo.ApiMetaDataBo;
import com.navercorp.pinpoint.thrift.dto.TApiMetaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ChengBing Han
 * @date 15:06  2018/3/5
 * @description
 */
@Service
public class MysqlApiMetaDataServiceImpl implements MysqlApiMetaDataService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    private final static String API_ID_KEY = "apiId";
    private final static String API_INFO_KEY = "apiInfo";
    private final static String API_LINE_KEY = "line";


    @Resource
    MysqlApiMetaDataBoDao mysqlApiMetaDataBoDao;

    /**
     * 插入数据
     *
     * @param apiMetaDataBo
     */
    StringBuffer sb = new StringBuffer();

    @Override
    public void addMysqlApiMetaDataBo(TApiMetaData apiMetaDataBo) throws Exception {


        try {

            /*
            判断数据库是否有。
             */
            if (apiMetaDataBo == null || apiMetaDataBo.getApiId() == 0) {
                throw new Exception("apiMetaDataBo is null or apiMetaDataBo.getApiId is null");
            }


            //要比较的不仅仅是 apiId,还有apiInfo，line
            Integer apiId = apiMetaDataBo.getApiId();
            String apiInfo = apiMetaDataBo.getApiInfo();
            Integer line = apiMetaDataBo.getLine();

            Map<String, String> hashMap = new HashMap<>();

            hashMap.put(API_ID_KEY, apiId.toString());
            hashMap.put(API_INFO_KEY, apiInfo);
            hashMap.put(API_LINE_KEY, line.toString());
            List<MysqlApiMetaDataBo> mysqlApiMetaDataBoList = getMysqlMetaDataBoByCondition(hashMap);
            if (mysqlApiMetaDataBoList != null && mysqlApiMetaDataBoList.size() > 0) {
                return;
            }

            //参数封装
            MysqlApiMetaDataBo mysqlApiMetaDataBo = new MysqlApiMetaDataBo();
            mysqlApiMetaDataBo.setAgentId(apiMetaDataBo.getAgentId());
            mysqlApiMetaDataBo.setAgentStartTime(apiMetaDataBo.getAgentStartTime());
            mysqlApiMetaDataBo.setApiId(apiMetaDataBo.getApiId());
            mysqlApiMetaDataBo.setApiInfo(apiMetaDataBo.getApiInfo());
            mysqlApiMetaDataBo.setLine(apiMetaDataBo.getLine());
            Integer type = apiMetaDataBo.getType();
            mysqlApiMetaDataBo.setType(type);

            mysqlApiMetaDataBoDao.addMysqlApiMetaDataBo(mysqlApiMetaDataBo);


        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }


    }

    /*
    根据apiId 查询是否已经有了mysqlApiMetaDataBO
     */
    @Override
    public MysqlApiMetaDataBo getMysqlMetaDataBoByApiId(Integer apiId) {
        return mysqlApiMetaDataBoDao.getMysqlMetaDataBoByApiId(apiId);

    }

    @Override
    public List<MysqlApiMetaDataBo> getMysqlMetaDataBoByCondition(Map<String, String> hashMap) {

        if (hashMap == null) {
            throw new IllegalArgumentException("patam hashMap is null");
        }
        List<MysqlApiMetaDataBo> mysqlApiMetaDataBoList = mysqlApiMetaDataBoDao.getMysqlMetaDataBoByCondition(hashMap);
        return mysqlApiMetaDataBoList;
    }


}
