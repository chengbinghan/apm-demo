package apm.web.dao.apmmysql;

import apm.web.beans.module.Transaction;
import apm.web.beans.vo.TransactionVO;

import java.util.List;
import java.util.Map;

/**
 * @author ChengBing Han
 * @date 10:27  2018/1/26
 * @description 事务操作数据库类
 */
public interface
ApmTransactionDao {


    /**
     * @param  Map hashMap
     *             key1= appId, value1 = 用户查询某个application 的id
     *             key2= findTime， value2 = "查询的时间范围"
     * @description 查询某个application 的应用中各个事务时间占比，要降序排列。
     *
     */
    List<TransactionVO> getTransactionPer(Map<String, String> hashMap);


   // List<Map<String,Long>> getAppsResponseAddex(Map<String, Double> hashMap);



    Double getAppsRpm(Map<String, String> hashMap);

    /**
     *
     * @param hashMap
     *              key1=appId,value1=查询的某个application的id
     *              key2=findTime,value2= 查询当前时间毫秒值-findTime ==> 当前 时间这个范围内的值
     *              key3=topNum, value=要查询的n条数据
     *
     * @return  TransactionVO
     */
    List<TransactionVO> getTopNTransaction(Map<String, String> hashMap);

    Double getAppsResponseAddexByAppId(Map<String, String> hashMap);

    Double getApdexByAppIdAndRpc(Map<String, String> hashMap);

    Double getErrPerByAppIdAndRpc(Map<String, String> hashMap);

    List<String> getAllTransactionUrlByAppId(String appId);

    Double getApdexByCondition(Map<String, String> hashMap);
}
