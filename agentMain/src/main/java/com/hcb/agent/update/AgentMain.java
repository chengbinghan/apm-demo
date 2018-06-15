package com.hcb.agent.update;

import javassist.*;


import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.lang.reflect.InvocationTargetException;


/**
 * @author ChengBing Han
 * @date 10:21  2018/6/7
 * @description
 */
public class AgentMain {

    static boolean flag = true;

    public static void agentmain(String agentArgs, Instrumentation inst) throws CannotCompileException, NotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, UnmodifiableClassException {
        System.out.println("agentmain===invoke=============================>");


        Class[] classes = inst.getAllLoadedClasses();
        for (Class clazz : classes) {

            if (clazz.getName().contains("HcbSimplePrincipal")) {
                System.out.println("add transformer to TBRemotingRPCProtocolComponent.class");

                if (flag) {
                    final MyTransformer myTransformer = new MyTransformer();
                    inst.addTransformer(myTransformer, true);
                    inst.retransformClasses(clazz);
                    inst.removeTransformer(myTransformer);
                    flag = !flag;
                } else {
                    final MyTransformer2 myTransformer2 = new MyTransformer2();
                    inst.addTransformer(myTransformer2, true);
                    inst.retransformClasses(clazz);
                    inst.removeTransformer(myTransformer2);
                    flag = !flag;
                }

            }
        }


    }
}
