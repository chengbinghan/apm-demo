package com.hcb.service;

import com.hcb.dao.JDBCTransaction;


import java.util.Random;

/**
 * @author ChengBing Han
 * @date 11:19  2018/3/2
 * @description
 */
public class TestService {


    public void testServiceMethod(String thisNode) {

        try {

            Random random = new Random();
            int sleepTime = random.nextInt(500);
            System.out.println(sleepTime);

            Thread.sleep(sleepTime);
            JDBCTransaction.jdbcTransaction(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
