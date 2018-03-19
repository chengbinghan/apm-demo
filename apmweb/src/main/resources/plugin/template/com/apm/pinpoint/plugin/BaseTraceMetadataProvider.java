package com.apm.pinpoint.plugin;

import com.navercorp.pinpoint.common.trace.TraceMetadataProvider;
import com.navercorp.pinpoint.common.trace.TraceMetadataSetupContext;





public class BaseTraceMetadataProvider implements TraceMetadataProvider {
    /**
     * @see TraceMetadataProvider#setup(TraceMetadataSetupContext)
     */
    @Override
    public void setup(TraceMetadataSetupContext context) {

        System.out.println("O====||=============================================================>start");
        System.out.println("BaseTraceMetadataProvider.setup invoke...........");
        System.out.println("O====||=============================================================>end");
        //设定当前插件的ServiceType，既插件的唯一身份
        context.addServiceType(BasePlugin.BIZLOG_SERVICE_TYPE);
        //设定当前插件要展示的参数
        context.addAnnotationKey(BasePlugin.BIZLOG_ANNOTATION_KEY_INFO);



    }
}