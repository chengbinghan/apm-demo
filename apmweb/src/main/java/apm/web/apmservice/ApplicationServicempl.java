package apm.web.apmservice;

import apm.web.beans.vo.ApdexVO;
import apm.web.beans.vo.ApplicationVO;
import apm.web.dao.apmmysql.ApmSpanDao;
import apm.web.dao.apmmysql.ApmTransactionDao;
import apm.web.dao.apmmysql.ApplicationDao;
import apm.web.beans.module.ApmApplication;
import apm.web.util.apmconstant.APMConstant;
import apm.web.util.apmconstant.DBConstant;
import apm.web.util.apmutil.ApmStringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author ChengBing Han
 * @date 10:30  2018/1/22
 * @description 应用处理类
 */
@Service
public class ApplicationServicempl implements ApplicationService {


    //apdex 计算说明


    @Resource
    private ApplicationDao applicationDao;

    @Resource
    ApmTransactionDao apmTransactionDao;

    @Resource
    ApmSpanDao apmSpanDao;

    /**
     * @param null
     * @return Application 列表
     * @description 用于Mysql
     */
    @Override
    public List<ApmApplication> selectAllApplicationNames() {


        List<ApmApplication> applicationList = applicationDao.getApplicationList();

        return applicationList;
    }

    /**
     * @return List<ApplicationVO>
     * @description 查询应用 列表相关信息
     */
    @Override
    public List<ApplicationVO> getApmApplicationInfo(Long timeSection) {


        List<ApplicationVO> applicationVOList = new ArrayList<>();


        /**
         * 要计算的比较复杂，所以分开单独计算。
         */
        //1、查询出所有application
        List<ApmApplication> apmApplicationList = selectAllApplicationNames();
        for (ApmApplication apmApplication : apmApplicationList) {

            ApplicationVO applicationVO = new ApplicationVO();
            String appId = apmApplication.getId().toString();
            applicationVO.setAppId(appId);
            applicationVO.setApplicationName(apmApplication.getApplicationName());

            Map<String, String> hashMap = new HashMap<>();
            hashMap.put(APMConstant.APP_ID, appId);

            //时间查询区间，比如默认查询当前一个小时，用户不传参数就给默认值
            if (timeSection == null || timeSection <= 0) {
                hashMap.put(APMConstant.TIME_SECTION, APMConstant.DEFAULT_APP_INFO_TIMESECTION.toString());
            }else {
                hashMap.put(APMConstant.TIME_SECTION, timeSection.toString());
            }

            //当前时间
            hashMap.put(APMConstant.CURRENT_TIME_MILLISECOND, String.valueOf(System.currentTimeMillis()));

            Integer appResponseTime = applicationDao.getAppsResponseTimeByAppId(hashMap);

            hashMap.put(APMConstant.APDEX_SATISFACTION_TIME, APMConstant.SATISFACTION_TIME.toString());
            hashMap.put(APMConstant.APDEX_QUALIFIED_TIME, APMConstant.QUALIFIED_TIME.toString());
            //应用ID ，apdex 得出addex 值。

                /*
                  Apdex值，是一个APM领域的计算公式，内容如下：
                  首先确保	求的响应时间，制定三个响应时间区间：0~3秒、3~12秒、大于12秒，分别代表“满意”、“容忍”、“失望”，统计一段时间内某个请求发生的次数（通过在span表中按照endTimeDelta字段统计），在span表中查出请求响应时间，然后将请求次数分散到三个响应时间区间里面，通过如下公司计算apdex值：
                  Apdex指数 = (1*满意数+0.5*容忍数)/请求总数
                  例如：如果30分钟内某个请求发生了10次：6次满意，2次容忍，2次失望，则apdex值为
                  apdex = (1*6 + 0.5*2)/10 = 0.7   (apdex范围为0~1，0代表最失望，1代表最满意)
                  最后按照从小到大排序，显示apdex最低的几个事务。
                 */


            hashMap.put(APMConstant.DATABASE_SPAN_ERRORCODE, DBConstant.SPAN_CORRECT_CODE.toString());
            Double apdex = apmTransactionDao.getAppsResponseAddexByAppId(hashMap);
            Double errPer = apmSpanDao.getAppsErrPer(hashMap);
            Double rpm = apmTransactionDao.getAppsRpm(hashMap);

            applicationVO.setResponseTime(appResponseTime);
            if (apdex != null) {
                applicationVO.setApdex(apdex.toString());
            }
            if (errPer != null) {
                applicationVO.setErrPercentage(errPer.toString());
            }
            if (rpm != null) {
                applicationVO.setRpm(rpm.toString());
            }

            applicationVOList.add(applicationVO);
        }

        return applicationVOList;
    }

    /**
     * @param appId
     * @return
     * @description 计算apdex 值
     */
    @Override
    public ApdexVO getApdexVOByTimeAndAppId(String appId) throws Exception {
        // TODO: 2018/2/3
        // TODO: 2018/2/3
        // TODO: 2018/2/3
        appId = "11";
        if (ApmStringUtil.isEmpty(appId)) {
            throw new Exception("param is null or is empty");
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

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("appId", appId);
        ApdexVO apdexVO = new ApdexVO();
        List<Long> xList = new ArrayList<>();
        List<Double> yList = new ArrayList<>();

        for (int i = 0; i < arr.length - 1; i++) {
            hashMap.put("startTime", arr[i].toString());
            hashMap.put("endTime", arr[i + 1].toString());
            Double apdexValue = applicationDao.getApdexVOByTimeAndAppId(hashMap);
            if (apdexValue == null || apdexValue.isNaN()) {
                apdexValue = 0.0;
            }
            xList.add((arr[i] + section / 2));
            yList.add(apdexValue);
        }
        apdexVO.setXaxisList(xList);
        apdexVO.setYaxisList(yList);

        return apdexVO;
    }
}









