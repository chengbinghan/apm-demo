package apm.web.dao.apmmysql;

import apm.web.apmservice.nosql.NosqlDaoParam;
import apm.web.beans.vo.NosqlVO;

import java.util.Map;

/**
 * @author ChengBing Han
 * @date 10:48  2018/3/15
 * @description
 */
public interface ApmNoSqlDao {
    NosqlVO getNoSqlGeneralView(Map<String, String> hashMap);

    NosqlVO getNosqlSetGeneralInfo(NosqlDaoParam nosqlDaoParam);
}
