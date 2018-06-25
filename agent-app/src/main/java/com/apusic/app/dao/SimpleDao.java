package com.apusic.app.dao;

/**
 * @author ChengBing Han
 * @date 14:20  2018/6/22
 * @description
 */
public class SimpleDao {

    public void dao(String str) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println(this.getClass().getName() + " dao invoke");
    }
}
