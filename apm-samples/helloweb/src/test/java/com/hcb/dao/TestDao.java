package com.hcb.dao;

import java.util.Random;

/**
 * @author ChengBing Han
 * @date 11:22  2018/3/2
 * @description
 */
public class TestDao {


    public void testDao(String thisNode) {

        try {
            Random random = new Random();
            int sleepTime = random.nextInt(300);
            Thread.sleep(sleepTime);

            System.out.println("数据库操作============》start");
            JDBCTransaction.jdbcTransaction(1);
            System.out.println("数据库操作=============》end");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
