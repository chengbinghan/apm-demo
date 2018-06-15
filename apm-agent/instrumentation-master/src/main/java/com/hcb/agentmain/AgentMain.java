package com.hcb.agentmain;

import com.hcb.commons.utils.TimeUtils;
import com.hcb.instrumentation.Agent;
import com.hcb.instrumentation.Callback;
import com.hcb.instrumentation.Interceptor;
import com.hcb.instrumentation.transform.ParamTransForm;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;



/**
 * @author ChengBing Han
 * @date 10:21  2018/6/7
 * @description
 */
public class AgentMain {

    static boolean flag = true;
    private static int counter;


    public static void agentmain(String agentArgs, Instrumentation instrumentation) throws InstantiationException, UnmodifiableClassException {
        System.out.println("agentmain===invoke=============================>" + TimeUtils.getTimeStr());


        Class[] classes = instrumentation.getAllLoadedClasses();
        for (Class clazz : classes)
            if (clazz.getName().contains("SimpleClss")) {
                counter++;
                final String callbackId = String.valueOf(counter);
                try {
                    if (agentArgs == null) {
                        throw new IllegalArgumentException("Agent argument is required of the form 'interceptor-class-name[;interceptor-custom-args]'");
                    }
                    String[] tokens = agentArgs.split(";", 2);
                    Class<?> interceptorClazz = Agent.class.getClassLoader().loadClass(tokens[0]);
                    final Interceptor interceptor = (Interceptor) interceptorClazz.newInstance();
                    if (tokens.length == 2) {
                        interceptor.init(tokens[1]);
                    } else {
                        interceptor.init(null);
                    }
                    Callback.registerCallback(callbackId, interceptor);

                    final ParamTransForm paramTransForm = new ParamTransForm(interceptor, callbackId);
                    instrumentation.addTransformer(paramTransForm);

                } catch (Throwable th) {
                    th.printStackTrace(System.err);
                    System.exit(0);
                }

            }


    }
}
