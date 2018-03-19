package apm.web.core.properties;

import apm.web.core.plugin.SingletonServiceCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author ChengBing Han
 * @date 15:36  2018/3/9
 * @description apm-web.properties对应的单例对象
 */
public class ApmProperties extends Properties {


    //使用volatile关键字保其可见性
    private static volatile ApmProperties apmProperties = null;

    //properties 数据封装到hashMap
    public static Map<String, String> propertiesMap = new HashMap<String, String>();


    static Logger logger = LoggerFactory.getLogger(ApmProperties.class);

    /**
     * apm-web.properties 路径
     */
    private static final String path = "/properties/apm-web.properties";


    private ApmProperties() {


    }

    public static ApmProperties getApmProperties() {

        if (apmProperties == null) {

            synchronized (ApmProperties.class) {
                if (apmProperties == null) {
                    apmProperties = new ApmProperties();

                    InputStream in = null;
                    try {

                        in = ApmProperties.class.getResourceAsStream(path);

                        apmProperties.load(in);


                         /*
                         Properties 继承HashTable,将properties 数据保存到hashMap.
                          */
                        Set<Object> propertyKeySet = apmProperties.keySet();
                        for (Object objKey : propertyKeySet) {
                            String key = (String) objKey;
                            String value = (String) apmProperties.get(key);
                            propertiesMap.put(key, value);
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
        }
        return apmProperties;
    }

    public static void main(String[] args) {
        getApmProperties();
    }

}
