package apm.web.dao.apmmysql;

import apm.web.beans.module.ApiMethod;

/**
 * @author ChengBing Han
 * @date 13:48  2018/3/16
 * @description
 */
public interface ApiMetaDataDao {
    ApiMethod getApiIdByApiInFo(String apiInfo);
}
