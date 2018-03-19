package apm.web.apmservice.reportform;

import apm.web.beans.module.Sql;
import apm.web.beans.vo.DatabaseReportFormInfoVO;
import apm.web.beans.vo.DatabaseReportFormVO;
import apm.web.beans.vo.TransactionReportFormInfoVO;
import apm.web.beans.vo.TransactionReportFormVO;
import apm.web.dao.apmmysql.ApmTransactionDao;
import apm.web.dao.apmmysql.ReportFormDao;
import apm.web.util.apmconstant.APMConstant;
import apm.web.util.apmconstant.ReportFormConstant;
import apm.web.util.apmutil.ApmStringUtil;
import apm.web.util.apmutil.ReportFormTimeUtil;
import apm.web.util.apmutil.TimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ChengBing Han
 * @date 10:51  2018/2/23
 * @description 报表相关
 */
@Service
public class ReportFormServiceImpl implements ReportFormService {


    @Resource
    private ReportFormDao reportFormDao;

    @Resource
    ApmTransactionDao apmTransactionDao;


    /**
     * @param timeStr 应用名称
     * @param appId   应用名称
     * @return 数据库报表 对象
     */
    @Override
    public List<DatabaseReportFormVO> getDatabaseFormByTime(String timeStr, String appId) throws Exception {


        // TODO: 2018/2/23 参数写死 注意没有大于1周的数据。===============================>
        timeStr = "ALLDAY,WEEKBEFORE,WEEK,YESTERDAY,TODAY";
        timeStr = "TODAY,YESTERDAY,WEEK";
        appId = "11";

        if (ApmStringUtil.isEmpty(timeStr)) {

            throw new Exception("报表的日期参数是null或空字符串");
        }

        List<TransactionReportFormVO> transactionReportFormVOList = new ArrayList<>();

        // TODO: 2018/2/23 验证日期规范, 并按照日期时间顺序排列， 时间范围有大到小，周，昨日，。。。===》
        String[] orderTimeStrArr = handleDateStr(timeStr);
        if (orderTimeStrArr == null || orderTimeStrArr.length == 0) {
            return null;
        }


        List<Sql> sqlList = reportFormDao.getSqlListByAppId(appId);

        List<DatabaseReportFormVO> databaseReportFormVOList = new ArrayList<>();
        for (Sql sql : sqlList) {
            DatabaseReportFormVO databaseReportFormVO = new DatabaseReportFormVO();

            /*
            获取simpleSqlInfo
            比如sql 表中的某条记录的sql 是"select * from user,insert xx into xxxx"， 要获取select,insert
            由于，insert,update,delete,select 都是长度为6，所有截取6
             */
            String sqlInfo = sql.getSqlInfo();
            String simpleSqlStr = null;
            if (sqlInfo != null) {
                String[] sqlStrArr = sqlInfo.split(",");
                StringBuilder sb = new StringBuilder();
                for (String sqlStr : sqlStrArr) {

                    // 由于，insert,update,delete,select 都是长度为6，所有截取6
                    if (sqlStr != null && sqlStr.length() >= 6) {
                        String sqlStrPart = sqlStr.substring(0, 6);
                        sb.append(sqlStrPart).append(",");
                    } else {
                        sb.append("--------");
                    }

                }
                simpleSqlStr = sb.substring(0, sb.length() - 1);

            }

            databaseReportFormVO.setSimpleSqlStr(simpleSqlStr);
            databaseReportFormVO.setSqlInfo(sqlInfo);


            List<DatabaseReportFormInfoVO> databaseReportFormInfoVOList = getDatabaseReportFormVO(orderTimeStrArr, appId, sql.getSqlMetaDataId());
            databaseReportFormVO.setList(databaseReportFormInfoVOList);
            databaseReportFormVOList.add(databaseReportFormVO);
        }


        return databaseReportFormVOList;
    }


    public List<DatabaseReportFormInfoVO> getDatabaseReportFormVO(String[] orderTimeArr, String appId, Integer sqlMetaDataId) throws Exception {

        List<DatabaseReportFormInfoVO> databaseReportFormInfoVOList = new ArrayList<>();


        for (String timeType : orderTimeArr) {

            DatabaseReportFormInfoVO eachDatabaseReportFormInfoVO = getEachDatabaseReportFormInfoVO(timeType, appId, sqlMetaDataId.toString());
            if (eachDatabaseReportFormInfoVO != null) {
                databaseReportFormInfoVOList.add(eachDatabaseReportFormInfoVO);
            }
        }

        return databaseReportFormInfoVOList;


    }

    public DatabaseReportFormInfoVO getEachDatabaseReportFormInfoVO(String timeType, String appId, String sqlMetaDataId) throws Exception {

        if (ApmStringUtil.isEmpty(sqlMetaDataId)) {
            throw new Exception("param is null or param is empty!!");
        }


        //当前毫秒值
        Long endTime = ReportFormTimeUtil.getCurrentMillisecond();
        //计算的区间有多少毫秒
        Long timeSection = null;


        if (ReportFormConstant.TIME_TYPE_TODAY.equals(timeType)) {
            timeSection = ReportFormTimeUtil.getTodayMillisecond();
        } else if (ReportFormConstant.TIME_TYPE_YESTERDAY.equals(timeType)) {
            timeSection = ReportFormTimeUtil.getYesterdayMillisecond();
        } else if (ReportFormConstant.TIME_TYPE_WEEK.equals(timeType)) {
            timeSection = ReportFormTimeUtil.getWeekMillisecond();
        }

        // 时间区间错误，目前仅仅支持今天，昨天，一周
        if (timeSection == null) {
            throw new Exception("报表的时间参数错误，请确认参数是否是限定值!!!");
        }

        Long startTime = endTime - timeSection;

        // TODO: 2018/2/28 假数据=================
        endTime = 1911111111112L;
        startTime = 0L;
        appId = "11";
        sqlMetaDataId = "1";

        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("endTime", endTime.toString());
        hashMap.put("startTime", startTime.toString());
        hashMap.put("appId", appId);
        hashMap.put("sqlMetaDataId", sqlMetaDataId);


        DatabaseReportFormInfoVO databaseReportFormInfoVO = reportFormDao.getEachDatabaseReportFormInfoVO(hashMap);

        //设置报表时间类型
        databaseReportFormInfoVO.setTimeType(timeType);
        //统计区间长度
        databaseReportFormInfoVO.setStatisticsSectionTime(timeSection);
        //统计数据时的毫秒值
        databaseReportFormInfoVO.setStatisticsTime(endTime);
        setThroughput(databaseReportFormInfoVO);

        return databaseReportFormInfoVO;
    }

    public void setThroughput(DatabaseReportFormInfoVO databaseReportFormInfoVO) throws Exception {

        if (databaseReportFormInfoVO == null || databaseReportFormInfoVO.getStatisticsSectionTime() == null || databaseReportFormInfoVO.getInvokeCount() == null) {
            throw new Exception("param is null or empty, please check your param!!!");
        }


        double rpm = databaseReportFormInfoVO.getInvokeCount() / databaseReportFormInfoVO.getStatisticsSectionTime();

        databaseReportFormInfoVO.setThroughput(rpm);

    }

    //O===||==============================================================================>>下面是事务报表涉及的方法。上面是数据库报表涉及的方法


    /**
     * @param appId   应用名称
     * @param timeStr 应用名称
     *                "ALLDAY,WEEKBEFORE,WEEK,YESTERDAY,TODAY" 分别表示查询的是今天，昨天，一周，一周前，所有
     * @return 报表 对象
     */
    @Override
    public List<TransactionReportFormVO> getTransactionFormByTime(String timeStr, String appId) throws Exception {


        // TODO: 2018/2/23 参数写死 注意没有大于1周的数据。===============================>
        timeStr = "ALLDAY,WEEKBEFORE,WEEK,YESTERDAY,TODAY";
        timeStr = "TODAY,YESTERDAY,WEEK";
        appId = "11";


        if (ApmStringUtil.isEmpty(timeStr)) {

            throw new Exception("报表的日期参数是null或空字符串");
        }

        List<TransactionReportFormVO> transactionReportFormVOList = new ArrayList<>();

        // TODO: 2018/2/23 验证日期规范, 并按照日期时间顺序排列， 时间范围有大到小，周，昨日，。。。===》
        String[] orderTimeStrArr = handleDateStr(timeStr);

        if (orderTimeStrArr == null || orderTimeStrArr.length == 0) {
            return null;
        }


        //虽然查询的是所有的rpc, 后面
        List<String> rpcList = apmTransactionDao.getAllTransactionUrlByAppId(appId);

        if (rpcList == null) {
            return null;
        }
        for (String rpc : rpcList) {

            TransactionReportFormVO transactionReportFormVO = getTransactionReportFormVO(appId, rpc, orderTimeStrArr);

            //url是某个应用的所有url, 某个url 可能在要查询的时间段内没有一次调用，也展示，前端数据用-表示没有调用
            if (transactionReportFormVO != null) {
                transactionReportFormVOList.add(transactionReportFormVO);
            }

        }
        return transactionReportFormVOList;
    }

    public TransactionReportFormVO getTransactionReportFormVO(String appId, String rpc, String[] orderTimeStrArr) throws Exception {

        TransactionReportFormVO transactionReportFormVO = new TransactionReportFormVO();
        transactionReportFormVO.setTransactionUrl(rpc);


        List<TransactionReportFormInfoVO> transactionReportFormInfoVOList = new ArrayList<>();
        //time，可以是WEEK，YESTERDAY，TODAY ，顺序也是时间长到短
        for (String time : orderTimeStrArr) {

            //查询平均，最大，最小，响应时间，该rpc(url), 调用次数
            TransactionReportFormInfoVO transactionReportFormInfoVO = getEachTransactionFormByTime(time, appId, rpc);

            //查询的值不为null；
            if (transactionReportFormInfoVO != null) {
                //设置时间类型，是查一天/周。。。
                transactionReportFormInfoVO.setTimeType(time);
                transactionReportFormInfoVO.setTransactionUrl(rpc);

                //设置吞吐率
                setThroughput(transactionReportFormInfoVO);
                //设置错误率
                setErrorPer(transactionReportFormInfoVO, appId);
                //设置apdex值
                setApdex(transactionReportFormInfoVO, appId);
                transactionReportFormInfoVOList.add(transactionReportFormInfoVO);
            }
        }

        //如果，今天，昨天，本周，没有都没有数据，那么其size 为0，返回null.
        if (transactionReportFormInfoVOList.size() == 0) {
            return null;
        }

        transactionReportFormVO.setList(transactionReportFormInfoVOList);

        return transactionReportFormVO;
    }


    private void setApdex(TransactionReportFormInfoVO transactionReportFormInfoVO, String appId) throws Exception {

        if (ApmStringUtil.isEmpty(transactionReportFormInfoVO.getTransactionUrl()) || appId == null
                || transactionReportFormInfoVO.getStatisticsSectionTime() == null || transactionReportFormInfoVO.getStatisticsTime() == null) {
            throw new Exception("param is empty or null!!");
        }


        Map<String, String> hashMap = new HashMap<String, String>();

        Long startTime = transactionReportFormInfoVO.getStatisticsTime() - transactionReportFormInfoVO.getStatisticsSectionTime();
        Long endTime = transactionReportFormInfoVO.getStatisticsTime();

        hashMap.put("appId", appId);
        hashMap.put("startTime", startTime.toString());
        hashMap.put("endTime", endTime.toString());
        hashMap.put("rpc", transactionReportFormInfoVO.getTransactionUrl());
        hashMap.put("satisfactionTime", APMConstant.SATISFACTION_TIME.toString());
        hashMap.put("qualifiedTime", APMConstant.QUALIFIED_TIME.toString());


        // TODO: 2018/2/27 参数写死
        hashMap.put("startTime", "0");
        hashMap.put("endTime", "2519719080184");


        Double apdex = reportFormDao.getApdex(hashMap);
        transactionReportFormInfoVO.setApdex(apdex);
    }

    /**
     * 设置错误率
     *
     * @param transactionReportFormInfoVO
     * @param appId
     */
    private void setErrorPer(TransactionReportFormInfoVO transactionReportFormInfoVO, String appId) {


        Map<String, String> hashMap = new HashMap<String, String>();

        //开始执行统计的时刻，统计某个时间段结束的时刻， 比如统计一天的报表，在今天，13:00:00 时统计，那么该值为其（今天 13：00：00）毫秒值
        Long statisticsTime = transactionReportFormInfoVO.getStatisticsTime();
        //统计开始时间端，比如一天 即今天凌晨时刻的毫秒值
        Long startTime = transactionReportFormInfoVO.getStatisticsTime() - transactionReportFormInfoVO.getStatisticsSectionTime();

        hashMap.put("endTime", statisticsTime.toString());
        hashMap.put("startTime", startTime.toString());
        hashMap.put("appId", appId);

        String rpc = transactionReportFormInfoVO.getTransactionUrl();
        // TODO: 2018/2/27  在解决span 表和transaction表rpc 不一致前，设置为transaction的rpc 和span的一致。
        rpc = "/hcbtra111";
        hashMap.put("endTime", 2519715444213L + "");
        hashMap.put("startTime", "0");
        hashMap.put("rpc", rpc);


        Double errorPer = reportFormDao.getErrorPer(hashMap);
        transactionReportFormInfoVO.setErrorPer(errorPer);


    }


    public TransactionReportFormInfoVO getEachTransactionFormByTime(String timeType, String appId, String rpc) throws Exception {

        TransactionReportFormInfoVO transactionReportFormInfoVO = null;

        Long timeSection = null;

        List<TransactionReportFormInfoVO> list = null;


        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("appId", appId);
        hashMap.put("rpc", rpc);


        //查询今天的
        if (ReportFormConstant.TIME_TYPE_TODAY.equals(timeType)) {

            timeSection = ReportFormTimeUtil.getTodayMillisecond();
        }
        //查询昨天开始到今天的
        if (ReportFormConstant.TIME_TYPE_YESTERDAY.equals(timeType)) {
            timeSection = ReportFormTimeUtil.getYesterdayMillisecond();
        }
        //查询一周以来。
        if (ReportFormConstant.TIME_TYPE_WEEK.equals(timeType)) {
            timeSection = ReportFormTimeUtil.getWeekMillisecond();
        }

        //如果查询的不是上述
        if (timeSection == null) {
            return null;
        }


        Long currentMillisecond = ReportFormTimeUtil.getCurrentMillisecond();
        Long startTime = currentMillisecond - timeSection;
        hashMap.put("startTime", startTime.toString());
        hashMap.put("endTime", currentMillisecond.toString());
        hashMap.put("rpc", rpc);
        hashMap.put("appId", appId);


        // TODO: 2018/2/27 参数写死==================================================》
        hashMap.put("startTime", "0");
        hashMap.put("endTime", "11111111111111111");
        hashMap.put("appId", "11");
        hashMap.put("rpc", "/aa/bb");

        transactionReportFormInfoVO = reportFormDao.getReportFormByRpcAndTime(hashMap);
        //设置统计时间。供计算吞吐率时使用
        transactionReportFormInfoVO.setStatisticsSectionTime(timeSection);
        //设置统计时刻，供错误率计算使用。（拼接sql）
        transactionReportFormInfoVO.setStatisticsTime(currentMillisecond);


        return transactionReportFormInfoVO;

    }

    /**
     * @param transactionReportFormInfoVO
     * @return double
     * @description 根据某次查询的时间间隔，调用次数， 计算每秒执行事务数==》rpm
     */
    public void setThroughput(TransactionReportFormInfoVO transactionReportFormInfoVO) throws Exception {

        if (transactionReportFormInfoVO == null || transactionReportFormInfoVO.getInvokeCount() == null ||
                transactionReportFormInfoVO.getStatisticsSectionTime() == null) {
            throw new Exception("param is empty!!!");
        }

        //统计时间,单位秒
        Long statisticsTime = transactionReportFormInfoVO.getStatisticsSectionTime() / 1000;
        Long invokeCount = transactionReportFormInfoVO.getInvokeCount();

        double rpm = invokeCount / statisticsTime;
        transactionReportFormInfoVO.setThroughput(rpm);

    }

    /**
     * @param str
     * @return 验证时间参数并排序，str 可能是"WEEK,YESTERDAY,TODAY"， 也可能是任意个。 要验证其字符串是否符合定义的常量格式,并且按照时间长短排列，
     */
    public String[] handleDateStr(String str) throws Exception {
        if (ApmStringUtil.isEmpty(str)) {
            return null;
        }

        String[] dateStrArr = str.split(",");

        Map<String, String> hashMap = new HashMap<>();

        for (String dateStr : dateStrArr) {
            if (ReportFormConstant.TIME_TYPE_TODAY.equals(dateStr)) {
                hashMap.put(ReportFormConstant.TIME_TYPE_TODAY, dateStr);
            } else if (ReportFormConstant.TIME_TYPE_YESTERDAY.equals(dateStr)) {
                hashMap.put(ReportFormConstant.TIME_TYPE_YESTERDAY, dateStr);
            } else if (ReportFormConstant.TIME_TYPE_WEEK.equals(dateStr)) {
                hashMap.put(ReportFormConstant.TIME_TYPE_WEEK, dateStr);
            } else {
                throw new Exception("报表的日期参数错误，请确认后台接收的参数是否有拼写错误！！！");
            }
        }

        dateStrArr = null;

        List<String> dateList = new ArrayList<>();
        if (hashMap.containsKey(ReportFormConstant.TIME_TYPE_WEEK)) {
            dateList.add(hashMap.get(ReportFormConstant.TIME_TYPE_WEEK));
        }
        if (hashMap.containsKey(ReportFormConstant.TIME_TYPE_YESTERDAY)) {
            dateList.add(hashMap.get(ReportFormConstant.TIME_TYPE_YESTERDAY));
        }
        if (hashMap.containsKey(ReportFormConstant.TIME_TYPE_TODAY)) {
            dateList.add(hashMap.get(ReportFormConstant.TIME_TYPE_TODAY));
        }
        String[] orderDateArr = new String[dateList.size()];
        orderDateArr = dateList.toArray(orderDateArr);

        return orderDateArr;
    }


}