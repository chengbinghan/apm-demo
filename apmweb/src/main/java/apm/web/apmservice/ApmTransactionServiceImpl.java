package apm.web.apmservice;

import apm.web.beans.module.Transaction;
import apm.web.beans.vo.TransactionVO;
import apm.web.dao.apmmysql.ApmTransactionDao;
import apm.web.util.apmconstant.APMConstant;
import apm.web.util.apmutil.ApmStringUtil;
import com.sun.deploy.association.utility.AppConstants;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ChengBing Han
 * @description 事务相关业务处理
 */
@Service
public class ApmTransactionServiceImpl implements ApmTransactionService {


    public static final Byte DEFAULT_TOP_NUMBER_EXPENSIVE_TRANSACTION = 5;
    public static final String TOP_NUMBER = "topN";
    /**
     * 查询某个时间范围的值，默认为1天的值。
     */
    private static Long FIND_TIME = 8640000011111L;

    @Resource
    private ApmTransactionDao apmTranSactionDao;

    /**
     * @param applicationId 应用的applicatiionId
     * @return Transaction 列表
     * @description 用于Transaction 列表
     */
    @Override
    public List<TransactionVO> getTransactionPercent(String applicationId, Long timeSection) {

        if (ApmStringUtil.isEmpty(applicationId)) {
            throw new IllegalArgumentException("param applicationId is null or empty!!");
        }


        Map<String, String> hashMap = new HashMap<>();
        hashMap.put(APMConstant.APP_ID, applicationId);
        hashMap.put(APMConstant.CURRENT_TIME_MILLISECOND, String.valueOf(System.currentTimeMillis()));

        //默认的时间限制
        if (timeSection == null || timeSection <= 0) {
            hashMap.put(APMConstant.TIME_SECTION, APMConstant.DEFAULT_TRANSACTION_TIMESECTION.toString());
        } else {
            hashMap.put(APMConstant.TIME_SECTION, timeSection.toString());
        }


        List<TransactionVO> transactionVOList = apmTranSactionDao.getTransactionPer(hashMap);


        return transactionVOList;
    }

    /**
     * @param topNum 查询topN 个耗时事务
     * @param appId  事务所属appId
     * @description 用于查询拓扑图表格
     */
    @Override
    public List<TransactionVO> getExpensiveTranList(Integer topNum, String appId, Long timeSection) throws Exception {


        //topNum 字段无用，待后期解决, limit中无法用#{topNum}, int ===>String
        // hashMap.put("topNum", "");
        //后期会分页，暂时是先查询出30 个，从中取topNum 个。
        // TODO: 2018/2/23  目前最多查询30,再优化
        if (topNum > 30) {
            topNum = 30;
        }

        if (ApmStringUtil.isEmpty(appId)) {
            throw new Exception("param appId is null or empty!!!");
        }


        //根据application id 查询其中 耗时的事务，每个事务有多次调用，是平均值。

        Map<String, String> hashMap = new HashMap<>();
        hashMap.put(APMConstant.APP_ID, appId);
        hashMap.put(APMConstant.CURRENT_TIME_MILLISECOND, String.valueOf(System.currentTimeMillis()));
        if (topNum == null || topNum <= 0) {

            hashMap.put(TOP_NUMBER, DEFAULT_TOP_NUMBER_EXPENSIVE_TRANSACTION.toString());

        } else {
            hashMap.put(TOP_NUMBER, topNum.toString());
        }

        if (timeSection == null || timeSection <= 0) {
            hashMap.put(APMConstant.TIME_SECTION, APMConstant.DEFAULT_TRANSACTION_TIMESECTION.toString());
        }else {
            hashMap.put(APMConstant.TIME_SECTION,timeSection.toString());
        }





        List<TransactionVO> transactionVOList = apmTranSactionDao.getTopNTransaction(hashMap);


        if (transactionVOList.size() >= topNum) {
            return transactionVOList.subList(0, topNum + 1);
        } else {
            return transactionVOList;
        }

    }

    /**
     * @param appId applicationId
     * @description 用于慢事务查询，eg： 可以查询出最近的3次访问中， 有2次事务A, 和1次事务B比较耗时。
     */
    @Override
    public List<Transaction> slowTransactionList(String appId) {


        // TODO: 2018/1/24   注意分页，第二次迭代时
        List<Transaction> transactionList = new ArrayList<>();

        Transaction transaction1 = new Transaction();
        transaction1.setAppllicationId("appId1");
        transaction1.setElapsed(30000);
        transaction1.setEndTime(1212222L);
        transaction1.setStartTime(222222111L);
        transaction1.setRpc("/bb/bburl.pinpoint");
        //事务耗时百分比
        //  transaction1.setTransactionPer(29.9F);


        Transaction transaction2 = new Transaction();
        transaction2.setAppllicationId("appId1");
        transaction2.setElapsed(10000);
        transaction2.setEndTime(1111111L);
        transaction2.setStartTime(22222L);
        transaction2.setRpc("/bb/bburl.pinpoint");
        //事务耗时百分比
        //    transaction2.setTransactionPer(30F);
        Transaction transaction3 = new Transaction();
        transaction3.setAppllicationId("appId1");
        transaction3.setElapsed(10000);
        transaction3.setEndTime(1212222L);
        transaction3.setStartTime(222222111L);
        transaction3.setRpc("/cc/ccurl.pinpoint");
        //    transaction3.setTransactionPer(40.0F);
        transactionList.add(transaction1);
        transactionList.add(transaction2);
        transactionList.add(transaction3);

        return transactionList;
    }


}
