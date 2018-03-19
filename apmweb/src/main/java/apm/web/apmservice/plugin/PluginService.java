package apm.web.apmservice.plugin;

import apm.web.beans.module.ApmPlugin;
import apm.web.util.apmutil.JsonObject;

/**
 * @author ChengBing Han
 * @date 14:42  2018/3/9
 * @description
 */
public interface PluginService {

    /**
     * 判断该applictionName 是否已经存在。
     *
     * @param applicationName 监控的applicationName
     * @return
     */
    Boolean isApplicationNameExits(String applicationName);

    /**
     * 判断 SingletonServiceCode 是否已经存在，或是否满足某个范围。
     * @param serviceCode
     * @return
     */
    JsonObject<Boolean> validateServiceCode(Integer serviceCode);

    /**
     * 创建插件
     * @param className
     * @param methodName
     * @return
     */
    JsonObject<Boolean> createNewPlugin(ApmPlugin apmPlugin);
}
