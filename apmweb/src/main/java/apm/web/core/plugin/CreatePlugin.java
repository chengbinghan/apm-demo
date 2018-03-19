package apm.web.core.plugin;

import apm.web.beans.module.ApmPlugin;

import java.io.FileNotFoundException;
import java.util.Properties;

/**
 * @author ChengBing Han
 * @date 18:51  2018/3/9
 * @description
 */
public interface CreatePlugin {


    /**
     * 开始创建plugin
     * @return 是否遇到问题
     */
    public Boolean start() throws Exception;

    /**
     * 放弃创建
     */
    public void giveUp() throws Exception;

    /**
     * 创建结束
     */
    public boolean end();

    /**
     * 清空
     */
    public boolean clear();



    PluginInfo createPlugin(ApmPlugin apmPlugin);

    public boolean init(ApmPlugin apmPlugin) throws FileNotFoundException;

    Boolean validateApmPlugin(ApmPlugin apmPlugin);

}
