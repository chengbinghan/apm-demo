package apm.web.core.properties;

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
 * @date 13:23  2018/3/16
 * @description
 */
public class NosqlProperties extends Properties {


    //使用volatile关键字保其可见性
    private static volatile NosqlProperties nosqlProperties = null;

    //properties 数据封装到hashMap
    public static Map<String, String> propertiesMap = new HashMap<String, String>();


    static Logger logger = LoggerFactory.getLogger(NosqlProperties.class);

    /**
     * apm-web.properties 路径
     */
    private static final String path = "/properties/nosql-web.properties";


    private NosqlProperties() {


    }

    public static NosqlProperties getNosqlProperties() {

        if (nosqlProperties == null) {

            synchronized (ApmProperties.class) {
                if (nosqlProperties == null) {
                    nosqlProperties = new NosqlProperties();

                    InputStream in = null;
                    try {

                        in = ApmProperties.class.getResourceAsStream(path);

                        nosqlProperties.load(in);


                         /*
                         Properties 继承HashTable,将properties 数据保存到hashMap.
                          */
                        Set<Object> propertyKeySet = nosqlProperties.keySet();
                        for (Object objKey : propertyKeySet) {
                            String key = (String) objKey;
                            String value = (String) nosqlProperties.get(key);
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
        return nosqlProperties;
    }

    public static void main(String[] args) {
        getNosqlProperties();
    }
}
