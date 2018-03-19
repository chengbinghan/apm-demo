package apm.web.apmservice;

import apm.web.beans.vo.*;
import apm.web.dao.apmmysql.OutServiceDao;
import apm.web.util.apmutil.ApmStringUtil;
import apm.web.util.apmutil.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ChengBing Han
 * @date 15:29  2018/2/5
 * @description
 */

@Service
public class OutServiceImpl implements OutService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Resource
    private OutServiceDao outServiceDao;

    /**
     * @param appId 应用id
     * @return
     * @description 外部服务，查询。
     */
    @Override
    public List<OutServiceVO> getOutServiceList(String appId, String findType) throws Exception {

        // TODO: 2018/2/5 参数固定========================》
        // TODO: 2018/2/5 参数固定========================》
        // TODO: 2018/2/5 参数固定========================》
        // TODO: 2018/2/5 参数固定========================》
        appId = "11";
        if (findType == null) {
            findType = "ALL";
        }

        if (ApmStringUtil.isEmpty(appId) || ApmStringUtil.isEmpty(findType)) {
            throw new Exception("param is empty or param is null");
        }

        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("appId", appId);
        hashMap.put("startTime", ConditionServiceImpl.getTimeLimit().toString());

        List<OutServiceVO> outServiceVOList = outServiceDao.getOutServiceList(hashMap);


        /**
         * 根据findType 查找，返回所有ALL, 还是返回， 某种类型
         */
        List<OutServiceVO> resultOutServiceVOList = new ArrayList<>();

        if (!ConditionServiceImpl.ALL_SERVICE.equals(findType)) {


            for (OutServiceVO outServiceVO : outServiceVOList) {

                //查询http的外部服务。
                if (ConditionServiceImpl.HTTP_SERVICE_CODE.equals(outServiceVO.getServiceType())) {
                    resultOutServiceVOList.add(outServiceVO);
                }

                // TODO: 2018/2/5 查询其它的 
            }

            return resultOutServiceVOList;
        }

        return outServiceVOList;
    }

    /**
     * @param appId
     * @return
     */
    @Override
    public OutServiceResponseVO getTheOutServiceByTime(String appId, String serviceCode) throws Exception {

        // TODO: 2018/2/7 appId
        appId = "11";
        serviceCode = "8080";
        if (ApmStringUtil.isEmpty(appId) || ApmStringUtil.isEmpty(serviceCode)) {
            throw new Exception("The param is null or the param is empty!!!");
        }

        /**
         * 要计算apdex值随时间变化，将时间划分为n个片段，计算每个片段的。
         * eg:
         * 开始时间是2018-01-01 00:00:00，
         * 结束时间是2018-01-02 00:00:00，
         * 如果划分12个片段，则每个片段是2小时，
         * 01:00:00， 03:00:00,5,7,9,11,13,15,17,19,21,23
         *
         */
        //把查询时间分为n等分,n 为12
        int sectionCount = 12;


        OutServiceResponseVO outServiceResponseVO = new OutServiceResponseVO();

        Long timeLimit = ConditionServiceImpl.getTimeLimit();
        Long section = timeLimit / sectionCount;
        Long startTime = System.currentTimeMillis() - timeLimit;
        Long[] arr = new Long[sectionCount + 1];
        for (int i = 0; i < sectionCount + 1; i++) {
            if (i == 0) {
                arr[i] = startTime;
            } else {
                arr[i] = arr[i - 1] + section;
            }
        }


        List<Long> xList = new ArrayList<>();
        List<OutServiceByTimeVO> yList = new ArrayList<>();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("appId", appId);
        hashMap.put("serviceCode", serviceCode);

        for (int i = 0; i < arr.length - 1; i++) {
            hashMap.put("startTime", arr[i].toString());
            hashMap.put("endTime", arr[i + 1].toString());
            OutServiceByTimeVO outServiceByTimeVO = outServiceDao.getTheOutServiceByTime(hashMap);
/*            if (apdexValue == null || apdexValue.isNaN()) {
                apdexValue = 0.0;
            }*/
            xList.add((arr[i] + section / 2));
            if (outServiceByTimeVO.getInvokeCount() != 0) {


                outServiceByTimeVO.setBeginTime(arr[i]);
                outServiceByTimeVO.setEndTime(arr[i + 1]);

                yList.add(outServiceByTimeVO);
            }
        }
        outServiceResponseVO.setXaxisList(xList);
        outServiceResponseVO.setYaxisList(yList);

        return outServiceResponseVO;

    }

    /**
     * @param appId
     * @param topN
     * @return json ,topN耗时外部服务
     */
    @Override
    public JsonObject<ExpensiveOutserviceVO> getTopNExpensiveOutService(String appId, Integer topN) {


        JsonObject<ExpensiveOutserviceVO> json = null;
        // TODO: 2018/2/8 参数写死
        appId = "11";
        topN = 5;

        if (topN == null) {
            topN = ConditionServiceImpl.DEFAULT_EXPENSIVE_OUTSERVICE_NUMBER;
        }

        try {
            if (ApmStringUtil.isEmpty(appId)) {
                throw new Exception("param is null or param is empty!!!");
            }


            Long startTime = ConditionServiceImpl.getTimeLimit();

            Map<String, String> hashMap = new HashMap<>();
            hashMap.put("appId", appId);
            hashMap.put("topN", topN.toString());
            hashMap.put("startTime", startTime.toString());
            List<ExpensiveOutserviceVO> expensiveOutserviceVOList = outServiceDao.getTopNExpensiveOutService(hashMap);
            json = new JsonObject<>();
            json.setObjectList(expensiveOutserviceVOList);
            json.setResponeCode(200);
            json.setSuccess(true);

        } catch (Exception e) {
            logger.error(e.getMessage());
            json = new JsonObject<>();
            json.setSuccess(false);
            json.setResponeCode(1000);
            json.setErrMsg(e.getMessage());


        }


        return json;
    }
}
