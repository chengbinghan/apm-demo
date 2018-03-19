package com.hcb.service;

import com.hcb.redis.RedisUtil;
import org.junit.Test;


import java.nio.charset.Charset;
import java.util.Random;
import java.util.jar.Pack200;

/**
 * @author ChengBing Han
 * @date 20:58  2018/3/13
 * @description
 */
public class RedisService {

    private static final Charset UTF_8 = Charset.forName("utf-8");


    @Test
    public void testRedis() throws Exception {

        StringBuffer sb = new StringBuffer();
        String v1 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        for (int i = 0; i < 10000; i++) {
             sb.append(v1);
        }

        try {
            for (int i = 0; i < 1; i++) {
                Random random = new Random();
                int intValue = random.nextInt(100000);
                String key = "key_" + i;
                String value = "value_" + i;
                if (i == 0) {
                    value = sb.toString() + "_" + i;
                }
                RedisUtil.setBytes(key.getBytes(UTF_8), value.getBytes(UTF_8));
                String val = RedisUtil.getString(key);
                System.out.println("输出:===>" + val);

            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }


    }


}
