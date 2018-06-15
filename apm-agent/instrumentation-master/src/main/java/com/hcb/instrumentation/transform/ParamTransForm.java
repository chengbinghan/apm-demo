package com.hcb.instrumentation.transform;

import com.hcb.instrumentation.Agent;
import com.hcb.instrumentation.Interceptor;
import com.hcb.instrumentation.asm.AsmInstrumentator;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;

/**
 * @author ChengBing Han
 * @date 10:07  2018/6/15
 * @description
 */
public class ParamTransForm implements ClassFileTransformer {


    Interceptor interceptor;
    String callbackId;

    public ParamTransForm() {
    }

    public ParamTransForm(Interceptor interceptor, String callbackId) {
        this.interceptor = interceptor;
        this.callbackId = callbackId;
    }

    @Override
    public byte[] transform(ClassLoader loader, final String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, final byte[] classfileBuffer) throws IllegalClassFormatException {


        if (!isAncestor(Agent.class.getClassLoader(), loader)) {
            return classfileBuffer;
        }

        return AccessController.doPrivileged(new PrivilegedAction<byte[]>() {
            public byte[] run() {

                AsmInstrumentator instrumentator = new AsmInstrumentator(className, classfileBuffer, interceptor, callbackId);

                final byte[] bytes = instrumentator.modifyClass();

                return bytes;
            }
        });

    }


    private static boolean isAncestor(ClassLoader ancestor, ClassLoader cl) {
        if (ancestor == null || cl == null) {
            return false;
        }
        if (ancestor.equals(cl)) {
            return true;
        }
        return isAncestor(ancestor, cl.getParent());
    }
}
