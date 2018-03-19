package apm.web.apmservice;

import apm.web.beans.vo.AppErrByCount;
import apm.web.beans.vo.AppErrorVO;
import apm.web.beans.vo.ErrMsgVO;
import apm.web.beans.vo.TransactionErrorVO;
import apm.web.dao.apmmysql.ApmErrorDao;
import apm.web.util.apmconstant.APMConstant;
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
 * @date 13:49  2018/2/1
 * @description
 */
@Service
public class ApmErrorServiceImpl implements ApmErrorService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    ApmErrorDao apmErrorDao;

    /**
     * @param appId 应用Id
     * @return
     * @description 根据应用Id 查询某个应用各个事务出错的
     */
    @Override
    public List<TransactionErrorVO> getTransactionErrPerList(String appId) throws Exception {

        // TODO: 2018/2/1 写死数据 =========================================》
        // TODO: 2018/2/1 写死数据 =========================================》
        // TODO: 2018/2/1 写死数据 =========================================》
        appId = "11";
        if (ApmStringUtil.isEmpty(appId)) {
            throw new Exception("param is null or param is empty!!!!!");
        }

        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("appId", appId);
        hashMap.put("startTime", ConditionServiceImpl.getTimeLimit().toString());

        //按照错误率降序排列
        List<TransactionErrorVO> transactionErrorVOList = apmErrorDao.getTransactionErrPerList(hashMap);


        return transactionErrorVOList;
    }

    /**
     * @param appId      应用applicationId
     * @param errorCount 错误条数
     * @description 用于根据rpc 划分 查询某个app(appId) 的 n条sql错误时间，
     */
    @Override
    public JsonObject<AppErrByCount> getErrPerByErrCount(String appId, String queryErrorCount) {

        List<List<AppErrorVO>> lists = new ArrayList<>();

        // TODO: 2018/2/1 写死参数
        appId = "11";
        if (ApmStringUtil.isEmpty(appId)) {
            try {
                throw new Exception("param is null or param is empty!!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //默认查询错误条数,用户未指明一次一次查询多少条错误。默认值
        if (ApmStringUtil.isEmpty(queryErrorCount)) {
            queryErrorCount = APMConstant.DEFAULT_ERROR_COUNT.toString();
        }


        //每个rpc 相同的放到一个list 中，再把这些list 放到外部的list.
        JsonObject<AppErrByCount> json = null;
        try {

     /*       Map<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("appId", appId);
            hashMap.put("queryErrorCount", queryErrorCount);
            hashMap.put("startTime", ConditionServiceImpl.getTimeLimit().toString());*/


            //查询错误率。升序排列
            List<AppErrByCount> appErrorVOList = apmErrorDao.getErrPerErrCount(appId, queryErrorCount, ConditionServiceImpl.getTimeLimit().toString());

            StringBuilder sb = new StringBuilder();
            if (appErrorVOList.size() > 0) {
                Long endTime = System.currentTimeMillis();
                Long startTime = System.currentTimeMillis() - ConditionServiceImpl.getTimeLimit();

                Long totalSection = endTime - startTime;

                Long section = totalSection / 8;
                sb.append(startTime).append(",");
                Long time = startTime;
                for (int i = 0; i < 14; i++) {
                    sb.append(time += section).append(",");
                }
                System.out.println(sb.toString());
            }

            Map<Object, Object> map = new HashMap<>();
            map.put("timeSection", sb.toString());


            json = new JsonObject<>();
            json.setObjectMaps(map);
            json.setSuccess(true);
            json.setResponeCode(200);
            json.setObjectList(appErrorVOList);
        } catch (Exception e) {
            logger.error(e.getMessage());
            json = new JsonObject<>();
            json.setResponeCode(1000);
            json.setSuccess(false);
            json.setErrMsg(e.getMessage());

        }


        return json;
    }

    /**
     * @param appId@description 最近异常列表查询
     */
    @Override
    public List<ErrMsgVO> getLastErrorList(String appId) throws Exception {

        // TODO: 2018/2/2 写死参数==============================》
        // TODO: 2018/2/2 写死参数==============================》
        // TODO: 2018/2/2 写死参数==============================》
        // TODO: 2018/2/2 写死参数==============================》

        appId = "11";

        if(ApmStringUtil.isEmpty(appId)){
            throw  new Exception("param is null or param is empty!!");
        }

        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("appId",appId);
        hashMap.put("startTime", ConditionServiceImpl.getTimeLimit().toString());

        List<ErrMsgVO> errMsgVOList = apmErrorDao.getLastErrorList(hashMap);


        return errMsgVOList;
    }
}
