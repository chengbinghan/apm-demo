package apm.collector.service;

import apm.collector.dao.MysqlApplication;
import apm.collector.dao.MysqlApplicationDao;
import apm.collector.util.ApmStringUtil;
import apm.collector.util.ApplicationCodeNameMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ChengBing Han
 * @date 22:08  2018/3/5
 * @description
 */
@Service
public class MysqlApplicationServiceImpl implements MysqlApplicationService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private MysqlApplicationDao mysqlApplicationDao;
    /**
     * @param applicationName
     * @param serviceType     比如1010， 该code 映射的是TOMCAT
     * @See ApplicationCodeNameMap
     */
    @Override
    public void addApplicationToMysql(String applicationName, String serviceType) {

        try {

            if (ApmStringUtil.isEmpty(applicationName) || ApmStringUtil.isEmpty(serviceType)) {
                throw new IllegalArgumentException("param applicationName or param serviceType is null or empyt string.");
            }

            /*
            判断是否已经保存过
             */

            MysqlApplication  mysqlApplication = mysqlApplicationDao.getMysqlApplicationByApplicationName(applicationName);
            if(mysqlApplication != null){
                return;
            }


            mysqlApplication = new MysqlApplication();
            mysqlApplication.setCode(Integer.parseInt(serviceType));
            mysqlApplication.setApplicationName(applicationName);

            //根据serviceType 获取类型 比如serviceType 1010 对应类型TOMCAT

            String applicationType = ApplicationCodeNameMap.codeNameMap.get(serviceType);
            if(ApmStringUtil.isEmpty(applicationType)){
                throw new Exception("serviceType:" + serviceType + "has no applicationType match, such as 1010 match TOMCAT.please add it!!");
            }
            mysqlApplication.setApplicationType(applicationType);


            mysqlApplicationDao.addAppliationToMysql(mysqlApplication);


        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }


    }
}
