package apm.web.dao.apmmysql;

import apm.web.beans.module.Sql;
import apm.web.beans.vo.DatabaseReportFormInfoVO;
import apm.web.beans.vo.TransactionReportFormInfoVO;

import java.util.List;
import java.util.Map;

/**
 * @author ChengBing Han
 * @date 11:12  2018/2/23
 * @description 报表相关
 */
public interface ReportFormDao {

    List<TransactionReportFormInfoVO> getTransactionTime(Map<String, String> hashMap);

    TransactionReportFormInfoVO getReportFormByRpcAndTime(Map<String, String> hashMap);

    Double getErrorPer(Map<String, String> hashMap);

    Double getApdex(Map<String, String> hashMap);

    DatabaseReportFormInfoVO getEachDatabaseReportFormInfoVO(Map<String, String> hashMap);

    List<Sql> getSqlListByAppId(String appId);
}
