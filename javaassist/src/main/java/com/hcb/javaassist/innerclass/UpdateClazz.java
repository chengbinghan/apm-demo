package com.hcb.javaassist.innerclass;

import com.hcb.javaassist.updateparam.SimplePrincipal;
import javassist.*;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author ChengBing Han
 * @date 9:56  2018/6/5
 * @description
 */
public class UpdateClazz {
    public static void main(String[] args) throws NotFoundException, CannotCompileException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, IOException {


        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("com.hcb.javaassist.innerclass.OutClazz$InnerClazz");


        CtConstructor ctc = cc.getConstructors()[0];


        ctc.setBody("{if(\"errorParam\".equals($2)){$2=\"correctParam\";} " +
                "this.innerField = $2;" +
                "}");


        OutClazz.InnerClazz sp = (OutClazz.InnerClazz) cc.toClass().getConstructor(OutClazz.class, String.class).newInstance(new OutClazz(), "errorParam");

        System.out.println(sp.getInnerField());


        final OutClazz outClazz = new OutClazz();
        final OutClazz.InnerClazz old = outClazz.new InnerClazz("old");

        System.out.println(old.getInnerField());
    }
}
