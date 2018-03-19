package apm.web.dao.apmmysql;

import apm.web.beans.module.ApmApplication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ChengBing Han
 * @date 18:02  2018/1/22
 * @description  Application 数据库操作
 */

public interface ApplicationDao {

    List<ApmApplication> getApplicationList();




    Integer getAppsResponseTimeByAppId(Map<String, String> hashMap);

    Double getApdexVOByTimeAndAppId(HashMap<String, String> hashMap);

    ApmApplication selectApplicationByName(String applicationName);

    ApmApplication selectApplicationByCode(Integer serviceCode);
}
