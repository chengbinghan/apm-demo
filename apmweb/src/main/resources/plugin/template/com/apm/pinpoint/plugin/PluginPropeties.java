package com.apm.pinpoint.plugin;



import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author ChengBing Han
 * @date 15:36  2018/3/9
 * @description
 * apm-web.properties对应的单例对象
 */
public class PluginPropeties extends Properties {


    //使用volatile关键字保其可见性
    private static volatile PluginPropeties apmProperties = null;

    //properties 数据封装到hashMap
    public static Map<String, String> propertiesMap = new HashMap<String, String>();



    /**
     * plugin.properties 路径
     */
    private static final String path = "/plugin.properties";

    private PluginPropeties() {

    }

    private PluginPropeties(String path){

        PluginPropeties properties = new PluginPropeties();

        InputStream in = null;
        try {

            in = PluginPropeties.class.getResourceAsStream(path);
            properties.load(in);

            /*
            Properties 继承HashTable,将properties 数据保存到hashMap.
             */
            Set<Object> propertyKeySet = properties.keySet();
            for (Object objKey : propertyKeySet) {
                String key = (String) objKey;
                String value = (String) properties.get(key);
                propertiesMap.put(key, value);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    public static PluginPropeties getPluginProperties() {

        if (apmProperties == null) {

            synchronized (PluginPropeties.class) {
                if (apmProperties == null) {
                    apmProperties = new PluginPropeties(path);
                }
            }
        }
        return apmProperties;
    }

}
