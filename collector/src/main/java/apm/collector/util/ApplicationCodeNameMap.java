package apm.collector.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author ChengBing Han
 * @date 22:12  2018/3/5
 * @description 收集应用的code 和name 的map, 比如， TOMCAT 对应1010
 */
public class ApplicationCodeNameMap {

    public static Map<String, String> codeNameMap = new HashMap<String, String>();

    static Logger logger = LoggerFactory.getLogger(ApplicationCodeNameMap.class);

    /**
     * serviceTypeCode.properties 路径
     */
    private static final String serviceTypeCodePath = "/collector/serviceTypeCode.properties";

    static {
        /*
        从serviceTypeCode.properties中读取数据
         */
        Properties properties = new Properties();
        InputStream in = null;
        try {

            in = ApplicationCodeNameMap.class.getResourceAsStream(serviceTypeCodePath);
            properties.load(in);

            /*
            Properties 继承HashTable,将properties 数据保存到hashMap.
             */
            Set<Object> propertyKeySet = properties.keySet();
            for (Object objKey : propertyKeySet) {
                String key = (String) objKey;
                String value = (String) properties.get(key);
                codeNameMap.put(key, value);

            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.error(e.getMessage());
                }

            }
        }


    }

}
