package com.apm.pinpoint.plugin;

import com.navercorp.pinpoint.bootstrap.instrument.InstrumentClass;
import com.navercorp.pinpoint.bootstrap.instrument.InstrumentException;
import com.navercorp.pinpoint.bootstrap.instrument.InstrumentMethod;
import com.navercorp.pinpoint.bootstrap.instrument.Instrumentor;
import com.navercorp.pinpoint.bootstrap.instrument.MethodFilters;
import com.navercorp.pinpoint.bootstrap.instrument.transformer.TransformCallback;
import com.navercorp.pinpoint.bootstrap.instrument.transformer.TransformTemplate;
import com.navercorp.pinpoint.bootstrap.instrument.transformer.TransformTemplateAware;
import com.navercorp.pinpoint.bootstrap.logging.PLogger;
import com.navercorp.pinpoint.bootstrap.logging.PLoggerFactory;
import com.navercorp.pinpoint.bootstrap.plugin.ProfilerPlugin;
import com.navercorp.pinpoint.bootstrap.plugin.ProfilerPluginSetupContext;
import com.navercorp.pinpoint.common.trace.AnnotationKey;
import com.navercorp.pinpoint.common.trace.AnnotationKeyFactory;
import com.navercorp.pinpoint.common.trace.ServiceType;
import com.navercorp.pinpoint.common.trace.ServiceTypeFactory;

import java.security.ProtectionDomain;
import java.util.Properties;
import java.util.Set;

/**
 * @author willzhao
 */
public class BasePlugin implements ProfilerPlugin, TransformTemplateAware {
    //BIZLOG_SERVICE_TYPE是bizlog插件的身份定义，用了1998这个id
    public static final ServiceType BIZLOG_SERVICE_TYPE;

    /**
     * BIZLOG_ANNOTATION_KEY_INFO是打算在pinpoint追踪信息中显示的属性的定义，用了9998这个id
     */
    public static final AnnotationKey BIZLOG_ANNOTATION_KEY_INFO;

    private static final String BIZLOG_SCOPE = "BIZLOG_SCOPE";

    private final PLogger logger = PLoggerFactory.getLogger(this.getClass());

    private TransformTemplate transformTemplate;


    //add by hcb
    private static final String DETECTOR_START = "detector";
    private static final Integer DETECTOR_CLAZZ_INDEX = 0;
    private static final Integer DETECTOR_METHOD_INDEX = 1;
    private static final Integer DETECTOR_INCEPTOR_INDEX = 2;
    private static final String PLUGIN_SERVICE_NAME_KEY = "serviceName";
    private static final String PLUGIN_SERVICE_CODE_KEY = "serviceCode";
    private static final String ANNNOTATION_KEY_NAME = "annotationKeyName";
    private static final String ANNOCATION_KEY_CODE = "annotationKeyCode";


    static {

        //配置serviceCode, ServiceName,AnnotationKey_Name,AnnotationKey_Code

        PluginPropeties pluginProperties = PluginPropeties.getPluginProperties();
        String serviceName = (String) pluginProperties.get(PLUGIN_SERVICE_NAME_KEY);
        Integer serviceCode = (Integer) pluginProperties.get(PLUGIN_SERVICE_CODE_KEY);
        String annotationKeyName = (String) pluginProperties.get(ANNNOTATION_KEY_NAME);
        Integer annotationKeyCode = (Integer) pluginProperties.get(ANNOCATION_KEY_CODE);

        // TODO: 2018/3/10 AnnocationKey 

        BIZLOG_SERVICE_TYPE = ServiceTypeFactory.of(serviceCode, serviceName);

        // TODO: 2018/3/10 Annocation key
        BIZLOG_ANNOTATION_KEY_INFO = AnnotationKeyFactory.of(annotationKeyCode, annotationKeyName, com.navercorp.pinpoint.common.trace.AnnotationKeyProperty.VIEW_IN_RECORD_SET);
    }

    /**
     * @param context
     */
    @Override
    public void setup(ProfilerPluginSetupContext context) {


        PluginPropeties pluginProperties = PluginPropeties.getPluginProperties();

        Set<Object> keySet = pluginProperties.keySet();

        for (Object obj : keySet) {
            String key = (String) obj;
            if (key.startsWith(DETECTOR_START)) {
                String detecctorValue = (String) pluginProperties.get(key);
                String[] detectorArr = detecctorValue.split(",");


                final String detectorClazz = detectorArr[DETECTOR_CLAZZ_INDEX];
                final String detectorMethod = detectorArr[DETECTOR_METHOD_INDEX];
                final String detectorInceptor = detectorArr[DETECTOR_INCEPTOR_INDEX];


                //Logger类被加载的时候，会注入这里new的TransformCallback，对这个类的实例在线程中的行为进行拦截
                transformTemplate.transform(detectorClazz, new TransformCallback() {

                    @Override
                    public byte[] doInTransform(Instrumentor instrumentor, ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws InstrumentException {
                        InstrumentClass target = instrumentor.getInstrumentClass(loader, className, classfileBuffer);

                        //找到所有名为info的方法
                        for (InstrumentMethod m : target.getDeclaredMethods(MethodFilters.name(detectorMethod))) {
                            ////注入Interceptor，在Logger类的实例执行info方法的时候会执行这个interceptor
                            // m.addScopedInterceptor("BasePluginInterceptor", BIZLOG_SCOPE);
                            m.addInterceptor(detectorInceptor);
                        }
                        return target.toBytecode();
                    }
                });


            }


        }


    }


    @Override
    public void setTransformTemplate(TransformTemplate transformTemplate) {

        this.transformTemplate = transformTemplate;
    }
}