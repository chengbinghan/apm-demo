package com.apusic.agent.instrument;

import com.apusic.agent.filter.ClazzFilter;
import com.apusic.agent.transform.ParamTransform;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author ChengBing Han
 * @date 10:38  2018/6/22
 * @description
 */
public class AgentMain {


    static boolean flag = true;

    public static void agentmain(String agentArgs, Instrumentation inst) {
        System.out.println("agentmain invoke<====||=====>" + new Date());


        Class[] classes = inst.getAllLoadedClasses();
        try {
            for (Class clazz : classes) {
                final boolean transform = ClazzFilter.filterClazz(clazz.getName());
                if (transform) {
                    final ParamTransform paramTransform = new ParamTransform();
                    inst.addTransformer(paramTransform, true);
                    inst.retransformClasses(clazz);
                    inst.removeTransformer(paramTransform);
                }
            }
        } catch (Exception e) {
            System.out.println("Something error in agent");
            e.printStackTrace();
        }
    }
}

