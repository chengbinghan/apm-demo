package com.hcb.agent.update;

import javassist.*;

import java.lang.instrument.ClassFileTransformer;

import java.security.ProtectionDomain;

/**
 * @author ChengBing Han
 * @date 13:48  2018/6/7
 * @description
 */
public class MyTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        byte[] bytes = null;
        ClassPool cp = ClassPool.getDefault();
        CtClass cc = null;
        try {
            cc = cp.get("com.hcb.agent.update.HcbSimplePrincipal");
            cc.defrost();

            CtConstructor ctc = cc.getConstructors()[0];

            ctc.setBody("{name=\"modify nameaaaaaaaaaaaaaaaaa\";this.name = name;}");
            System.out.println("modify nameaaaaaaaaaaaaaaaa=================>");

            final Class<? extends CtConstructor> aClass = ctc.getClass();
            bytes = cc.toBytecode();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }
}
