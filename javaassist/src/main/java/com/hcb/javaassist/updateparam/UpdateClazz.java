package com.hcb.javaassist.updateparam;

import javassist.*;

import java.lang.reflect.InvocationTargetException;

/**
 * @author ChengBing Han
 * @date 9:56  2018/6/5
 * @description
 */
public class UpdateClazz {
    public static void main(String[] args) throws NotFoundException, CannotCompileException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("com.hcb.javaassist.updateparam.SimplePrincipal");
        CtConstructor ctc = cc.getConstructors()[0];
        ctc.setBody("{name=\"modify name\";this.name = name;}");





        SimplePrincipal sp=(SimplePrincipal)cc.toClass().getConstructor(String.class).newInstance("name");

        System.out.println(sp.getName());

        SimplePrincipal pp=new SimplePrincipal("ww");
        System.out.println(pp.getName());
    }
}
