package apm.collector.service;

import apm.collector.dao.MysqlApplication;
import apm.collector.dao.MysqlApplicationDao;
import apm.collector.dao.MysqlMapStatisticsCaller;
import apm.collector.dao.MysqlMapStatisticsCallerDao;
import apm.collector.util.ApmStringUtil;
import apm.collector.util.ApplicationCodeNameMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;

/**
 * @author ChengBing Han
 * @date 23:37  2018/3/5
 * @description
 */
@Service
public class MysqlMapStatisticsCallerServiceImpl implements MysqlMapStatisticsCallerService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    MysqlApplicationDao mysqlApplicationDao;


    @Resource
    MysqlMapStatisticsCallerDao mysqlMapStatisticsCallerDao;

    @Override
    public void addMysqlMapStatisticsCaller(MysqlMapStatisticsCaller mysqlMapStatisticsCaller) {

        try {
            if (mysqlMapStatisticsCaller == null) {
                throw new IllegalArgumentException("param mysqlMapStatisticsCaller is null");
            }

            //设置时间撮，调用时间是取监控时的时间
            mysqlMapStatisticsCaller.setTimeStamp(System.currentTimeMillis());


            /*
            设置调用的code,根据caller,clalleeServiceType 获取其code, 如TOMCAT ===>1010
             */
            //1、调用者
            //type值如:TOMCAT
            String callerType = mysqlMapStatisticsCaller.getCallerDesc();

            if (ApmStringUtil.isEmpty(callerType)) {
                throw new Exception("callerType is null , please check it");
            }


            Map<String, String> codeNameMap = ApplicationCodeNameMap.codeNameMap;
            String callerCode = getKeyByValueInMap(codeNameMap, callerType);
            if (callerCode == null) {
                throw new Exception("in ApplcationCodeNameMap there is no callerCode  match " + callerType);
            }
            mysqlMapStatisticsCaller.setCallerCode(Integer.parseInt(callerCode));

            //2、被调用者
            String calleeType = mysqlMapStatisticsCaller.getCalleeDesc();
            if (ApmStringUtil.isEmpty(calleeType)) {
                throw new Exception("calleeType is null , please check it");
            }

            String calleeCode = getKeyByValueInMap(codeNameMap, calleeType);
            mysqlMapStatisticsCaller.setCalleeCode(Integer.parseInt(calleeCode));

            /*
            设置applicationId, 根据applicationName 查询
             */
            String callerAppName = mysqlMapStatisticsCaller.getCallerAppName();
            String calleeAppName = mysqlMapStatisticsCaller.getCalleeAppName();
            if (ApmStringUtil.isEmpty(callerAppName) || ApmStringUtil.isEmpty(calleeAppName)) {
                throw new Exception("callerAppName or calleeAppName is null or empty,please check it");
            }
            MysqlApplication callerApplication = mysqlApplicationDao.getMysqlApplicationByApplicationName(callerAppName);
            MysqlApplication calleeApplication = mysqlApplicationDao.getMysqlApplicationByApplicationName(calleeAppName);


            if (callerApplication == null || calleeApplication == null) {
                throw new Exception("根据该applicationName表中无该applicationName" + callerAppName + " 或 " + calleeAppName);
            }
            Integer callerAppId = callerApplication.getId();
            Integer calleeAppId = calleeApplication.getId();
            mysqlMapStatisticsCaller.setCallerAppId(callerAppId);
            mysqlMapStatisticsCaller.setCalleeAppId(calleeAppId);


            /*
            插入数据
             */
            mysqlMapStatisticsCallerDao.addMysqlMapStatisticsCaller(mysqlMapStatisticsCaller);


        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }


    }


    /**
     * 根据map的value ，获取其key
     *
     * @param map
     * @param value
     * @return
     */
    public String getKeyByValueInMap(Map<String, String> map, String value) {

        if (map == null) {
            throw new IllegalArgumentException("param map is null!");
        }
        if (ApmStringUtil.isEmpty(value)) {
            throw new IllegalArgumentException("param value is empyt");
        }
        Set<String> keySet = map.keySet();

        for (String key : keySet) {
            if (value.equals(map.get(key))) {
                return key;
            }

        }
        return null;


    }
}
