package apm.web.apmservice;

import apm.web.beans.module.Agent;
import apm.web.dao.apmmysql.ApmAgentDao;
import apm.web.util.apmutil.ApmStringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ChengBing Han
 * @date 14:11  2018/2/3
 * @description
 */
@Service
public class ApmAgentServiceImpl implements ApmAgentService{

    @Resource
    ApmAgentDao apmAgentDao;

    /**
     * @param appId 应用id
     * @return 根据应用id 查询agent
     */
    @Override
    public Agent getAgentInfoByAppId(String appId) throws Exception {

        // TODO: 2018/2/3 参数写死=====================》
        // TODO: 2018/2/3 参数写死=====================》
        // TODO: 2018/2/3 参数写死=====================》
        appId = "11";

        if(ApmStringUtil.isEmpty(appId)){
            throw  new Exception("param is null or param is empty!!!");
        }


        Map<String,String> hashMap = new HashMap<String, String>();
        hashMap.put("appId", appId);
        Agent agent = apmAgentDao.getAgetnInfoByAppId(hashMap);



        return agent;
    }
}
