package com.hcb.javaassist.updateparam;

import javassist.*;

import java.lang.reflect.InvocationTargetException;

/**
 * @author ChengBing Han
 * @date 11:43  2018/6/20
 * @description
 */
public class UpdateUser {

    public static void main(String[] args) throws NotFoundException, CannotCompileException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("com.hcb.javaassist.updateparam.UserService");
        CtConstructor ctc = cc.getConstructors()[0];

       CtMethod method = cc.getDeclaredMethod("f");

        method.insertAt(18, "System.out.println($1);\n" +
                "        $1.setName(\"new Name\");\n" +
                "        System.out.println($1);" );
        final User user = new User();

        user.setAge(11);
        user.setName("oldName");
        UserService sp=(UserService)cc.toClass().getConstructor(User.class).newInstance(user);


        sp.f(user);


    }
}
