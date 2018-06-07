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

    public static void agentmain(String agentArgs, Instrumentation inst) throws CannotCompileException, NotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, UnmodifiableClassException {
        System.out.println("com.hcb.agent.update.AgentMain invoke==========>");

/*        final HcbSimplePrincipal aaa = new HcbSimplePrincipal("aaa");
        System.out.println(aaa);*/

        Class[] classes = inst.getAllLoadedClasses();
        for (Class clazz : classes) {

            if (clazz.getName().contains("HcbSimplePrincipal")) {
                System.out.println("add transformer to TBRemotingRPCProtocolComponent.class");
                inst.addTransformer(new MyTransformer(), true);
                inst.retransformClasses(clazz);
            }
        }
/*
        final HcbSimplePrincipal aaa1 = new HcbSimplePrincipal("aaa");
        System.out.println(aaa1);*/

    }
}
