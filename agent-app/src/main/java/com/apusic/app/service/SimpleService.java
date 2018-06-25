package com.apusic.app.service;

import com.apusic.app.dao.SimpleDao;

import java.util.Date;

/**
 * @author ChengBing Han
 * @date 14:20  2018/6/22
 * @description
 */
public class SimpleService {
    public void service(String str) throws InterruptedException {
        Thread.sleep(1000);
        final SimpleDao simpleDao = new SimpleDao();
        simpleDao.dao("" + new Date());


    }
}
