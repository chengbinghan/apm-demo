package com.apusic.agent.transform;

import com.apusic.agent.context.DetectorContext;
import com.apusic.agent.core.ParamHandler;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author ChengBing Han
 * @date 13:45  2018/6/22
 * @description
 */
public class ParamTransform implements ClassFileTransformer {
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        byte[] bytes = null;
        ClassPool cp = ClassPool.getDefault();
        CtClass cc = null;
        try {
            final CopyOnWriteArrayList<String> list = DetectorContext.list;
            final String s = list.get(list.size() - 1);
            final String[] arr = s.split(DetectorContext.SEPARTOR);
            String clazz = arr[0];
            String method = arr[1];


            cc = cp.get(clazz);
            if (cc == null) {
                // TODO: 2018/6/22
            }
            final CtMethod declaredMethod = cc.getDeclaredMethod(method);
            declaredMethod.insertBefore(
                    "final ParamHandler paramHandler = new ParamHandler();" +
                            "paramHandler.handlerParam(s, $0);"
            );


            bytes = cc.toBytecode();
            cc.defrost();

        } catch (Exception e) {
            e.printStackTrace();
        }


        return bytes;
    }
}
