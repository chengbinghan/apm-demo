package apm.web.apmservice.plugin;

import apm.web.beans.module.ApmApplication;
import apm.web.beans.module.ApmPlugin;
import apm.web.core.plugin.*;
import apm.web.dao.apmmysql.ApplicationDao;
import apm.web.util.apmutil.ApmStringUtil;
import apm.web.util.apmutil.JsonObject;
import apm.web.util.apmutil.TimeUtil;
import org.apache.hadoop.hbase.shaded.org.apache.avro.data.Json;
import org.apache.taglibs.standard.tag.el.sql.SetDataSourceTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author ChengBing Han
 * @date 14:42  2018/3/9
 * @description
 */
@Service
public class PluginServiceImpl implements PluginService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Resource
    private CreatePlugin createPlugin;


    @Resource
    ApplicationDao applicationDao;

    /**
     * 判断该applictionName 是否已经存在。
     *
     * @param applicationName 监控的applicationName
     * @return
     */
    @Override
    public Boolean isApplicationNameExits(String applicationName) {

        if (ApmStringUtil.isEmpty(applicationName)) {

            ApmApplication apmApplication = applicationDao.selectApplicationByName(applicationName);
            if (apmApplication != null) {
                return true;
            }
        }


        return false;
    }

    /**
     * 判断 SingletonServiceCode 或是否满足某个范围。是否已经存在，
     * server=1900,1999
     * db=2900,2999
     * cache=8900,8999
     * rpc=9900,9999
     * others=7500,7999
     *
     * @param serviceCode
     * @return
     */
    @Override
    public JsonObject<Boolean> validateServiceCode(Integer code) {

        JsonObject<Boolean> json = null;

        try {
            if (code == null) {
                json = new JsonObject<>();
                json.setResponeCode(200);
                json.setSuccess(true);
                json.setObj(true);
                return json;
            }

            /**
             判断范围
             */
            SingletonServiceCode serviceCode = SingletonServiceCode.getInstance();

            Map<String, Scope> serviceCodeScopeMap = serviceCode.serviceCodeScopeMap;

            Set<String> keySet = serviceCodeScopeMap.keySet();

            // TODO: 2018/3/9 后期可以更加严格的限制，比如用户是db client ,其service Code就要符合db 的那个官方限制。
            //code是否符合范围限制标志位， 默认不符合范围限制
            boolean scopeFlag = false;
            for (String key : keySet) {
                Scope scope = serviceCodeScopeMap.get(key);
                if (code <= scope.getEnd() && code >= scope.getStart()) {
                    scopeFlag = true;
                    break;
                }
            }

            //用户输入的socpe范围不满足要求
            if (!scopeFlag) {
                json = new JsonObject<>();
                json.setObj(false);
                json.setSuccess(true);
                json.setResponeCode(1003);//输入的范围不满足要求，请确认

                return json;
            }

            /*
            判断是否已经存在
             */
            ApmApplication apmApplication = applicationDao.selectApplicationByCode(code);

            json = new JsonObject<>();
            if (apmApplication == null) {
                json.setSuccess(true);
                json.setObj(true);
                json.setResponeCode(200);

                return json;
            } else {
                json.setObj(false);
                json.setResponeCode(1004);//该数据已经存在
                json.setSuccess(true);
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            json = new JsonObject<>();
            json.setSuccess(false);
            json.setErrMsg(e.getMessage());
            json.setResponeCode(1000);

        }

        return json;
    }

    /**
     * 创建插件
     *
     * @param apmPlugin
     * @return
     */
    @Override
    public JsonObject<Boolean> createNewPlugin(ApmPlugin apmPlugin) {


        // TODO: 2018/3/13 测试数据
        apmPlugin = new ApmPlugin();
        apmPlugin.setAnnotationKeyName("annoKeyName");
        apmPlugin.setAnnotationKeyCode(123);
        apmPlugin.setServiceCode(456);
        apmPlugin.setServiceName("serviceName");
        List<String> list = new ArrayList<>();
        list.add("com.aa.bb.CLASS1,f1");
        list.add("com.aa.bb.CLASS2,f2");
        apmPlugin.setDetectorList(list);

        PluginInfo plugin = createPlugin.createPlugin(apmPlugin);
        System.out.println("111");
        System.out.println("111");
        System.out.println("111");
        return null;
    }
}
