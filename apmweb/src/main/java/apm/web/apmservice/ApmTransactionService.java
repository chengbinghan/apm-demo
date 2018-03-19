package apm.web.apmservice;

import apm.web.beans.module.Transaction;
import apm.web.beans.vo.TransactionVO;

import java.util.List;

/**
 * @author ChengBing Han
 *
 * @description  事务相关业务处理
 */
public interface ApmTransactionService {


    /**
     * @param applicationId 应用的applicatiionId
     * @return Transaction 列表
     * @description 用于Transaction 列表
     */
   List<TransactionVO> getTransactionPercent(String applicationId,Long timeSection);

    /**
     * @param topNum 查询topN 个耗时事务
     * @description 用于查询拓扑图表格
     */
    List<TransactionVO> getExpensiveTranList(Integer topNum, String appId,Long timeSection) throws Exception;

    /**
     * @param appId applicationId
     * @description 用于慢事务查询，eg： 可以查询出最近的3次访问中， 有2次事务A, 和1次事务B比较耗时。
     */
    List<Transaction> slowTransactionList(String appId);
}
