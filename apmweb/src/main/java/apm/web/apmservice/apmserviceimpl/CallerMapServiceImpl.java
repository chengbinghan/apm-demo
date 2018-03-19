package apm.web.apmservice.apmserviceimpl;

import apm.web.util.apmconstant.APMConstant;
import com.navercorp.pinpoint.common.util.StringUtils;
import apm.web.beans.vo.CallerMapVO;
import apm.web.dao.apmmysql.CallerMapDao;
import apm.web.apmservice.CallerMapService;
import apm.web.util.apmutil.ApmStringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author ChengBing Han
 * @date 16:09  2018/1/25
 * @description
 */
@Service
public class CallerMapServiceImpl implements CallerMapService {


    @Resource
    private CallerMapDao callerMapDao;

    /**
     * @param appId@description 根据某个应用Id 查询调用关系
     */
    @Override
    public List<CallerMapVO> getAppCallerTopologyInfo(String appId, Long timeSectioin) throws Exception {

        if (ApmStringUtil.isEmpty(appId)) {
            throw new Exception("param is null or is empty !!!!!");
        }

        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put(APMConstant.APP_ID, appId);
        hashMap.put(APMConstant.CURRENT_TIME_MILLISECOND, String.valueOf(System.currentTimeMillis()));
        //默认的时间查询区间
        if (timeSectioin == null || timeSectioin <= 0) {
            hashMap.put(APMConstant.TIME_SECTION, APMConstant.DEFAULT_APP_INVOKE_TIMESECTION.toString());
        } else {
            hashMap.put(APMConstant.TIME_SECTION, timeSectioin.toString());
        }

        List<CallerMapVO> callerMapVOList = callerMapVOList = callerMapDao.getAppCalleeCountByAppId(hashMap);


        return callerMapVOList;
    }
}
