package com.hcb.javaassist.updateparam;

import javassist.ClassPool;
import javassist.CtClass;

public class ReClassPool extends ClassPool {
    @Override
    public CtClass removeCached(String classname) {
        return (CtClass)classes.remove(classname);
    }
}