package apm.web.apmservice.reportform;

import apm.web.beans.vo.BaseReportFormVO;
import apm.web.beans.vo.DatabaseReportFormVO;
import apm.web.beans.vo.TransactionReportFormInfoVO;
import apm.web.beans.vo.TransactionReportFormVO;

import java.util.List;

/**
 * @author ChengBing Han
 * @date 10:50  2018/2/23
 * @description 报表相关
 */
public interface ReportFormService {
    /**
     * @param appId    应用名称
     * @param timeStr 应用名称
     *
     * @return 事务报表 对象
     */
    List<TransactionReportFormVO> getTransactionFormByTime(String timeStr, String appId) throws Exception;


    /**
     * @param appId    应用名称
     * @param timeStr 应用名称
     *
     * @return 数据库报表 对象
     */
    List<DatabaseReportFormVO> getDatabaseFormByTime(String timeStr, String appId) throws Exception;
}
