package com.apm.pinpoint.plugin;

import com.navercorp.pinpoint.bootstrap.context.MethodDescriptor;
import com.navercorp.pinpoint.bootstrap.context.SpanEventRecorder;
import com.navercorp.pinpoint.bootstrap.context.Trace;
import com.navercorp.pinpoint.bootstrap.context.TraceContext;
import com.navercorp.pinpoint.bootstrap.interceptor.AroundInterceptor;
import com.navercorp.pinpoint.bootstrap.logging.PLogger;
import com.navercorp.pinpoint.bootstrap.logging.PLoggerFactory;


public class BasePluginInterceptor implements AroundInterceptor {
    private final TraceContext traceContext;
    private final MethodDescriptor descriptor;
    private final PLogger logger = PLoggerFactory.getLogger(getClass());

    public BasePluginInterceptor(TraceContext traceContext, MethodDescriptor descriptor) {
        this.traceContext = traceContext;
        this.descriptor = descriptor;
    }

    private static boolean shouldTrace(Object[] args){
        System.out.println("O====||=============================================================>start");
        System.out.println("BasePluginInterceptor.shouldTrace返回值如下：");
        System.out.println(null!=args
                && args.length>0
                && (args[0] instanceof String));
        System.out.println("O====||=============================================================>end");

        return null!=args
                && args.length>0
                && (args[0] instanceof String)
               /* && ((String)args[0]).indexOf("pinpoint_bizlog_name")>-1*/; //update by hcb , reduce the limit
    }

    @Override
    public void before(Object target, Object[] args) {
        if (logger.isDebugEnabled()) {
            logger.beforeInterceptor(target, args);
        }

        final Trace trace = traceContext.currentTraceObject();
        if (trace == null) {
            return;
        }

        if(!shouldTrace(args)){
            return;
        }

        trace.traceBlockBegin();

    }

    @Override
    public void after(Object target, Object[] args, Object result, Throwable throwable) {
        if (logger.isDebugEnabled()) {
            logger.afterInterceptor(target, args);
        }

        Trace trace = traceContext.currentTraceObject();
        if (trace == null) {
            return;
        }

        if(!shouldTrace(args)){
            return;
        }

        try {
            SpanEventRecorder recorder = trace.currentSpanEventRecorder();
            recorder.recordServiceType(BasePlugin.BIZLOG_SERVICE_TYPE);
            recorder.recordApi(descriptor);
            recorder.recordException(throwable);
            recorder.recordAttribute(BasePlugin.BIZLOG_ANNOTATION_KEY_INFO, args[0]);
        } finally {
            trace.traceBlockEnd();
        }
    }
}