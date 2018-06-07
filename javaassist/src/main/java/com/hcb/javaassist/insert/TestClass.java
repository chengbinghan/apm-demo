package com.hcb.javaassist.insert;

class TestClass {
    public int compute(int param) {
        System.out.println("compute start");
        System.out.println("flag1");
        System.out.println("flag2");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("compute end");
        return param + 1000;
    }
}