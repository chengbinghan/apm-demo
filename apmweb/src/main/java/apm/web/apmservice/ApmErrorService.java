package apm.web.apmservice;

import apm.web.beans.vo.AppErrByCount;
import apm.web.beans.vo.ErrMsgVO;
import apm.web.beans.vo.TransactionErrorVO;
import apm.web.util.apmutil.JsonObject;

import java.util.List;

/**
 * @author ChengBing Han
 * @date 13:48  2018/2/1
 * @description 错误率相关业务处理类
 */
public interface ApmErrorService {


    /**
     * @param appId 应用Id
     * @return
     * @description 根据应用Id 查询某个应用各个事务出错的
     */
    List<TransactionErrorVO> getTransactionErrPerList(String appId) throws Exception;

    /**
     * @param appId 应用applicationId
     * @param errorCount 错误条数
     * @description 用于根据rpc 划分 查询某个app(appId) 的 n条sql错误时间，
     */
    JsonObject<AppErrByCount> getErrPerByErrCount(String appId, String errorCount);

    /**
     * @param appId.
     * @description 最近异常列表查询
     */
    List<ErrMsgVO> getLastErrorList(String appId) throws Exception;
}
