package apm.web.dao.apmmysql;

import apm.web.beans.vo.AppErrByCount;
import apm.web.beans.vo.ErrMsgVO;
import apm.web.beans.vo.TransactionErrorVO;


import java.util.List;
import java.util.Map;

/**
 * @author ChengBing Han
 * @date 13:57  2018/2/1
 * @description
 */

public interface ApmErrorDao {
    List<TransactionErrorVO> getTransactionErrPerList(Map<String, String> hashMap);

    List<AppErrByCount> getErrPerErrCount(String appId, String queryErrorCount, String startTime);

    List<ErrMsgVO> getLastErrorList(Map<String, String> hashMap);
}
