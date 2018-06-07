package com.hcb.javaassist.nonameinnerclass;



/**
 * @author ChengBing Han
 * @date 15:25  2018/6/5
 * @description
 */
public class OuterClazz {


    public static void method(String[] args) {
         new MyInterface(){
             public void f(String param) {
                 System.out.println("源代码第16行输出。。。。。。。。。。。。。。。。");
                 System.out.println("源代码第17行输出。。。。。。。。。。。。。。。。");
                 System.out.println("源代码第18行输出。。。。。。。。。。。。。。。。");
                 System.out.println("函数的参数是：" + param);


             }
         }.f("default-param");



    }



}
