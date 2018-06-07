package com.hcb.asm;

/**
 * @author ChengBing Han
 * @date 21:43  2018/5/29
 * @description
 */
public class HelloASM {
    private static final int VALUE = 1123;
    String b = "hello world";

    void f() {
        System.out.println(b);
    }

    public static void main(String[] args) {
        System.out.println(HelloASM.VALUE);
    }
}
