package com.hcb.javaassist.insert;

import javassist.*;

import java.io.IOException;

/**
 * @author ChengBing Han
 * @date 9:38  2018/6/5
 * @description
 */
public class JavaassistApi {

    public static void main(String[] args) throws CannotCompileException, IOException {
        // ClassPool包含所有动态生成的类,getDefault()返回默认的ClassPool,
        ClassPool cp = ClassPool.getDefault();
        // 该ClassPool默认从java lib,ext,classpath搜索class文件,并生成一个CtClass返回
        CtClass clazz = null;
        try {
            clazz = cp.get("com.hcb.javaassist.insert.TestClass");
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        // 修改compute方法
        CtMethod method = null;
        try {
            method = clazz.getDeclaredMethod("compute");
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        // $args,参数($1,第一个参数,$2,$3以此类推) ,$_:返回值
        try {
            method.insertAfter("System.out.println(\"add this output\");");
            method.insertAt(7,"System.out.println(\"insert at line6\");");


        } catch (CannotCompileException e) {
            e.printStackTrace();
        }
        // 转换成Class,这一步也载入了修改后的Class。注意:必须保证之前这个Class没有载入过,不然会报异常:java.lang.LinkageError,因为JVM不允许一个class多次加载
        try {
            clazz.toClass();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        }
        // 将该CtClass从ClassPool中移除,
        clazz.detach();
        // 这时载入的TestClass已经被修改
        TestClass test = new TestClass();
        // 调用方法
        test.compute(5);

        clazz.writeFile("aaaaaaaaaab.class");

    }
}
