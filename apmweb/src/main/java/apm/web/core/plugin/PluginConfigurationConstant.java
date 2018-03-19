package apm.web.core.plugin;

/**
 * @author ChengBing Han
 * @date 16:14  2018/3/10
 * @description
 */
public class PluginConfigurationConstant {

    /*
    插件信息相关常量
     */
    //key 的名字作为插件中的配置文件的key, 插件代码中读取该key,名字要和插件代码中读取的保持一致
    public final static String SERVICENAME_KEY = "serviceName";
    public final static String SERVICECODE_KEY = "serviceCode";
    public final static String ANNOTATION_KEY_CODE_KEY = "annotationKeyCode";
    public final static String ANNOTATION_KEY_NAME_KEY = "annotationKeyName";

    /*
    一个插件可以有众多的埋点，每个埋点包括类名和方法名，用逗号隔开
    eg:
    detector_1=com.aa.Service,f2,interceptor1
    detector_2=com.aa.Service,f1,inteerceptor2
     */
    public final static String DETECTOR_PREFIX = "detector_";
    public final static String PLUGIN_PROPERTIES_FILE_NAME = "plugin.properties";



}
