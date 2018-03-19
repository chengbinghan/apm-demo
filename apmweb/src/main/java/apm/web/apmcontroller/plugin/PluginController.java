package apm.web.apmcontroller.plugin;

import apm.web.apmservice.plugin.PluginService;
import apm.web.beans.module.ApmPlugin;
import apm.web.util.apmutil.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author ChengBing Han
 * @date 14:36  2018/3/9
 * @description
 */
@Controller
@CrossOrigin
public class PluginController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private PluginService pluginService;

    /**
     * 判断能改applicationName 是否已经使用。
     *
     * @param applicationName
     * @return
     */
    @RequestMapping(value = "/validateApplicationName", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<Boolean> isApplicationNameExist(String applicationName) {

        JsonObject<Boolean> json = null;

        try {

            Boolean exist = pluginService.isApplicationNameExits(applicationName);
            json = new JsonObject<>();
            json.setResponeCode(200);
            json.setSuccess(true);
            json.setObj(exist);
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
     * 判断 SingletonServiceCode 是否已经存在，或是否满足某个范围。
     *
     * @param serviceCode
     * @return
     */
    @RequestMapping(value = "/validateServiceCode", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<Boolean> validateServiceCode(Integer serviceCode) {

        return pluginService.validateServiceCode(serviceCode);

    }

    @RequestMapping(value = "/createNewPlugin", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<Boolean> createNewPlugin(ApmPlugin apmPlugin) {

        // TODO: 2018/3/9 jstack

        return pluginService.createNewPlugin(apmPlugin);


    }


}
