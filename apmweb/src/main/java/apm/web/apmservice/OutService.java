package apm.web.apmservice;

import apm.web.beans.vo.ExpensiveOutserviceVO;
import apm.web.beans.vo.OutServiceByTimeVO;
import apm.web.beans.vo.OutServiceResponseVO;
import apm.web.beans.vo.OutServiceVO;
import apm.web.util.apmutil.JsonObject;

import java.util.List;

/**
 * @author ChengBing Han
 * @date 15:29  2018/2/5
 * @description 外部服务
 */
public interface OutService {


    /**
     *
     * @param appId 应用id
     * @return
     * @description 外部服务，查询。
     */
    List<OutServiceVO> getOutServiceList(String appId,String findType) throws Exception;

    /**
     *
     * @param appId
     *  @param serviceCode 服务code
     * @return
     */
    OutServiceResponseVO getTheOutServiceByTime(String appId, String serviceCode) throws Exception;

    /**
     *
     * @param appId
     * @param topN
     * @return json ,topN耗时外部服务
     */
    JsonObject<ExpensiveOutserviceVO> getTopNExpensiveOutService(String appId, Integer topN);
}
